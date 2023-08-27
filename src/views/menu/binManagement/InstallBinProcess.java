package views.menu.binManagement;

import models.BinModel;
import services.DataStorage;
import services.IDataStorage;

import java.util.Scanner;

public class InstallBinProcess {
    private final Scanner scanner;
    private final BinModel bin;
    private final IDataStorage dataStorage;

    public InstallBinProcess(){
        scanner = new Scanner(System.in);
        bin = new BinModel();
        bin.setAlertLevel(0);
        bin.setSortedWaste(0);
        bin.setUnsortedWaste(0);
        dataStorage = new DataStorage();
    }

    public void start() {
        System.out.println("-------- Fake Bin Installation Menu -------");

        System.out.println("Insert Bin ID (string)... ");
        bin.setId(scanner.next());

        System.out.println("Insert Bin Latitude (float)... ");
        bin.setLatitude(scanner.nextFloat());

        System.out.println("Insert Bin Longitude (float)... ");
        bin.setLongitude(scanner.nextFloat());

        System.out.println("Insert Bin Capacity (int)...");
        bin.setCapacity(scanner.nextInt());

        System.out.println(bin.toString());
        System.out.println("Install the bin? [y/n]    ");
        if("y".equals(scanner.next())){
            if (dataStorage.installBin(bin) == 0){
                System.out.println("Bin installed successfully");
            }else{
                System.out.println("Error installing bin");
            }
        }else{
            System.out.println("Bin installation aborted");
        }
    }
}
