package views.menu;

import models.BinModel;
import services.DataStorage;
import services.IDataStorage;
import views.menu.binManagement.DeleteBinProcess;
import views.menu.binManagement.InstallBinProcess;
import views.menu.binManagement.UnloadBinProcess;

import java.util.Scanner;

public class BinManagementMenu {
    private final Scanner scanner;
    private final IDataStorage dataStorage;
    public BinManagementMenu(){
        scanner = new Scanner(System.in);
        dataStorage = new DataStorage();
    }

    public void start() {
        boolean back = false;
        while(!back){
            int choice = showMenu();
            switch (choice) {
                case 0 -> {
                    back = true;
                }
                case 1 -> {
                    InstallBinProcess installBinProcess = new InstallBinProcess();
                    installBinProcess.start();
                }
                case 2 -> {
                    DeleteBinProcess deleteBinProcess = new DeleteBinProcess();
                    deleteBinProcess.start();
                }
                case 3 -> {
                    UnloadBinProcess unloadBinProcess = new UnloadBinProcess();
                    unloadBinProcess.start();
                }
                case 4 -> {
                    showAllBins();
                }
                default -> {
                    System.out.println("Invalid choice.");
                }
            }
        }
    }

    private int showMenu() {
        boolean done = false;
        int selection = 0;

        do{
            System.out.println("-------------- Bin Management Simulation Menu -------------");
            System.out.println("Use numbers to select.");
            System.out.println("1. Install a Bin (Only for managing local DB)");
            System.out.println("2. Remove a Bin (Only for managing local DB)");
            System.out.println("3. Unload a Bin (RabbitMQ)");
            System.out.println("4. Show all bins in local DB.");

            System.out.println("0. Back");
            System.out.print("Waiting for selection... ");

            if(scanner.hasNextInt()){
                selection = scanner.nextInt();
                done = true;
            }else{
                System.out.println("Invalid input: Please enter a valid integer.");
                scanner.nextLine();
            }
        }while(!done);

        return selection;
    }

    private void showAllBins(){
        System.out.println("------- Bin List ---------- ");
        for(BinModel bin: dataStorage.getLocalBins()){
            System.out.println(bin.toString());
        }
    }

}
