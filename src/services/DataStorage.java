package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.BinModel;

import java.io.*;
import java.util.ArrayList;

public class DataStorage implements IDataStorage{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final ArrayList<BinModel> localBins = new ArrayList<>();
    private static final String DATABASE_FILE_PATH = "src/localDB/database.txt";

    public DataStorage(){
    }

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

    @Override
    public ArrayList<BinModel> getLocalBins() {
        return localBins;
    }

    @Override
    public int installBin(BinModel bin) {
        localBins.add(bin);
        return 0;
    }

    @Override
    public int removeBin(String binID) {
        boolean removed = localBins.removeIf(bin -> bin.getId().equals(binID));

        if(removed){
            return 0;
        }

        return 1;
    }

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

    @Override
    public int uploadBin(String binID, int sortedWaste, int unsortedWaste) {
        for(BinModel bin: localBins){
            if(bin.getId().equals(binID)){
                if(canThrow(bin, sortedWaste, unsortedWaste)){
                    bin.setUnsortedWaste(unsortedWaste);
                    bin.setSortedWaste(sortedWaste);

                    return updateAlert(bin);

                }else{
                    return -1;
                }
            }
        }
        return 0;
    }

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

    private boolean canThrow(BinModel bin, int sorted, int unsorted){
        int oldTrash = bin.getSortedWaste() + bin.getUnsortedWaste();
        int newTrash = sorted + unsorted;

        return newTrash + oldTrash <= bin.getCapacity();
    }

    private int updateAlert(BinModel bin){
        float percentage = (float) (100 * bin.getUnsortedWaste() + bin.getSortedWaste()) / bin.getCapacity();

        if(percentage <= 50){
            bin.setAlertLevel(0);
            return 0;
        } else if (percentage <= 75) {
            bin.setAlertLevel(1);
            return 1;
        }else {
            bin.setAlertLevel(2);
            return 2;
        }
    }
}
