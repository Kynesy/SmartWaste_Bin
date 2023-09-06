package views.menu.binManagement;

import models.BinModel;
import services.DataStorage;
import services.IDataStorage;

import java.util.Scanner;

/**
 * Questa classe gestisce il processo di installazione di un bidone fittizio.
 * Gli utenti possono inserire i dettagli del bidone, come ID, latitudine, longitudine
 * e capacità, per simulare l'installazione di un bidone nel sistema.
 */
public class InstallBinProcess {
    private final Scanner scanner;
    private final BinModel bin;
    private final IDataStorage dataStorage;

    /**
     * Costruttore della classe InstallBinProcess.
     * Inizializza l'input da console (scanner), il modello del bidone (bin)
     * e il servizio di gestione dei dati dei bidoni (dataStorage).
     * Il bidone viene inizializzato con livello di allarme, rifiuti ordinati e rifiuti non ordinati pari a 0.
     */
    public InstallBinProcess(){
        scanner = new Scanner(System.in);
        bin = new BinModel();
        bin.setAlertLevel(0);
        bin.setSortedWaste(0);
        bin.setUnsortedWaste(0);
        dataStorage = new DataStorage();
    }

    /**
     * Avvia il processo di installazione del bidone fittizio.
     * Gli utenti possono inserire i dettagli del bidone, inclusi ID, latitudine, longitudine e capacità.
     * Dopo l'inserimento dei dettagli, viene visualizzato il riepilogo del bidone
     * e gli utenti possono confermare o annullare l'installazione del bidone.
     */
    public void start() {
        System.out.println("-------- Fake Bin Installation Menu -------");

        System.out.println("Inserisci l'ID del bidone (stringa)... ");
        bin.setId(scanner.next());

        System.out.println("Inserisci la latitudine del bidone (float)... ");
        bin.setLatitude(scanner.nextFloat());

        System.out.println("Inserisci la longitudine del bidone (float)... ");
        bin.setLongitude(scanner.nextFloat());

        System.out.println("Inserisci la capacità del bidone (int)...");
        bin.setCapacity(scanner.nextInt());

        System.out.println(bin.toString());
        System.out.println("Installare il bidone? [s/n]    ");
        if("s".equals(scanner.next())){
            if (dataStorage.installBin(bin) == 0){
                System.out.println("Bidone installato con successo");
            }else{
                System.out.println("Errore durante l'installazione del bidone");
            }
        }else{
            System.out.println("Installazione del bidone annullata");
        }
    }
}
