package services;

import models.BinModel;

import java.util.ArrayList;

public interface IDataStorage {
    void saveData();

    void loadData();

    ArrayList<BinModel> getLocalBins();

    int installBin(BinModel bin);

    int removeBin(String binID);

    int unloadBin(String binID);

    int uploadBin(String binID, int sortedWaste, int unsortedWaste);

    boolean binExist(String binID);
}
