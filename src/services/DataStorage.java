package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.BinModel;
import java.io.*;
import java.util.ArrayList;


/**
 * Questa classe gestisce l'archiviazione e il recupero dei dati dei bidoni di raccolta.
 * I dati vengono salvati su un file di testo e caricati da tale file quando richiesto.
 */
public class DataStorage implements IDataStorage{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final ArrayList<BinModel> localBins = new ArrayList<>();
    private static final String DATABASE_FILE_PATH = "src/localDB/database.txt";

    public DataStorage(){
    }

    /**
     * Il metodo salva i dati dei bidoni sul file del database locale.
     */
    @Override
    public void saveData() {
        System.out.println("StorageService | Saving data...");
        try(FileWriter fileWriter = new FileWriter(DATABASE_FILE_PATH)) {
            for(BinModel bin : localBins){
                String json = objectMapper.writeValueAsString(bin);
                fileWriter.write(json + "\n");
            }

        }catch(IOException e) {
            System.out.println("StorageService | Saving data error");
        }
    }

    /**
     * Il metodo carica i dati dei bidoni dal file del database locale.
     */
    @Override
    public void loadData() {
        System.out.println("StorageService | Loading data...");

        File databaseFile = new File(DATABASE_FILE_PATH);
        if (!databaseFile.isFile()) {
            System.out.println("StorageService | No previous Bins");
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATABASE_FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                BinModel bin = objectMapper.readValue(line, BinModel.class);
                localBins.add(bin);
            }

        } catch (IOException e) {
            System.out.println("StorageService | Loading data error");
        }
    }

    /**
     * Il metodo viene usato per ottenere la lista di tutti i bidoni del DB.
     *
     * @return Un oggetto ArrayList contentente i bidoni
     */
    @Override
    public ArrayList<BinModel> getLocalBins() {
        return localBins;
    }

    /**
     * Il metodo viene usato per simulare l'installazione di un bidone.
     * In pratica lo aggiunge al db locale.
     *
     * @param bin Oggetto bin (con ID, latitudine, longitudine e capacità istanziate secondo
     *            le info fornite dal FrontEnd
     * @return 0 in caso di successo
     */
    @Override
    public int installBin(BinModel bin) {
        localBins.add(bin);
        return 0;
    }

    /**
     * Il metodo simula la rimozione di un cestino.
     * In pratica lo elimina dal db locale.
     *
     * @param binID ID del cestino da rimuovere
     * @return 0 se è stato rimosso, 1 altrimenti
     */
    @Override
    public int removeBin(String binID) {
        boolean removed = localBins.removeIf(bin -> bin.getId().equals(binID));

        if(removed){
            return 0;
        }

        return 1;
    }

    /**
     * Il metodo simula lo svuotamento del cestino.
     * In pratica azzera il livello di spazzatura ed il livello di alert
     *
     * @param binID ID del cestino da svuotare
     * @return 0 se è stato svuotato, 1 altrimenti
     */
    @Override
    public int unloadBin(String binID) {
        boolean find = false;

        for(BinModel bin: localBins){
            if(bin.getId().equals(binID)){
                bin.setUnsortedWaste(0);
                bin.setSortedWaste(0);
                bin.setAlertLevel(0);
                find = true;
            }
        }

        if(find){
            return 0;
        }

        return 1;
    }

    /**
     * Il metodo controlla se è possibile ancora buttare della spazzatura nel cestino,
     * e se possibile, aggiunge della spazzatura al cestino indicato.
     *
     * @param binID ID del cestino da aggiornare
     * @param sortedWaste KG di spazzatura differenziata da aggiungere
     * @param unsortedWaste KG di spazzatura indifferenziata da aggiungere
     *
     * @return 0 se i dati sono stati aggiornati, 1 altrimenti
     */
    @Override
    public int uploadBin(String binID, int sortedWaste, int unsortedWaste) {
        for(BinModel bin: localBins){
            if(bin.getId().equals(binID)){
                if(canThrow(bin, sortedWaste, unsortedWaste)){
                    updateBinValue(bin, sortedWaste, unsortedWaste);

                    return updateAlert(bin);

                }else{
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * Il metodo aggiorna il nuovo livello di spazzatura nel cestino in base ai KG da buttare.
     *
     * @param bin Oggetto del cestino da aggiornare
     * @param sortedWaste KG di spazzatura differenziata da aggiungere
     * @param unsortedWaste KG di spazzatura indifferenziata da aggiungere
     */
    private void updateBinValue(BinModel bin, int sortedWaste, int unsortedWaste) {
        bin.setUnsortedWaste(bin.getUnsortedWaste() + unsortedWaste);
        bin.setSortedWaste(bin.getSortedWaste() + sortedWaste);
    }

    /**
     * Il metodo controlla se l'ID indicato corrisponde ad un cestino esistente.
     *
     * @param binID ID del cestino
     * @return True se esiste, False altrimenti
     */
    @Override
    public boolean binExist(String binID) {
        boolean find = false;
        for (BinModel bin: localBins) {
            if (bin.getId().equals(binID)) {
                find = true;
                break;
            }
        }
        return find;
    }

    /**
     * Il metodo esegue un calcolo per capire se il cestino ha abbastanza
     * spazio per gettare la spazzatura
     *
     * @param bin Oggetto del cestino da aggiornare
     * @param sorted KG di spazzatura differenziare che si intente gettare
     * @param unsorted KG di spazzatura indifferenziata che si intende gettare
     * @return True se il cestino ha abbastanza spazio, False altrimenti
     */
    private boolean canThrow(BinModel bin, int sorted, int unsorted){
        int oldTrash = bin.getSortedWaste() + bin.getUnsortedWaste();
        int newTrash = sorted + unsorted;

        return newTrash + oldTrash <= bin.getCapacity();
    }

    /**
     * Il metodo aggiorna il livello di alert sul cestino fornito in base alla quantità
     * di spazzatura contenuta.
     *
     * @param bin Oggetto cestino da controllare
     * @return Il livello di alert impostato al cestino
     */
    private int updateAlert(BinModel bin){

        float percentage = getPercentage(bin);

        int value;
        if(percentage <= 50){
            value = 0;
        } else if (percentage <= 75) {
            value = 1;
        }else {
            value = 2;
        }

        bin.setAlertLevel(value);
        return value;
    }

    /**
     * Metodo che calcola la percentuale di riempimento di un cestino.
     *
     * @param bin Oggetto cestino su cui effettuare il calcolo
     * @return Ritorna il valore della percentuale di riempimento.
     */
    private float getPercentage(BinModel bin){
        return (float) (100 * (bin.getUnsortedWaste() + bin.getSortedWaste()) / bin.getCapacity());
    }
}
