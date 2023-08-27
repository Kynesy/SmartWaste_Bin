package views.menu.binManagement;

import services.DataStorage;
import services.IDataStorage;

import java.util.Scanner;

public class DeleteBinProcess {
    private final Scanner scanner;
    private final IDataStorage dataStorage;

    public DeleteBinProcess(){
        scanner = new Scanner(System.in);
        dataStorage = new DataStorage();
    }

    public void start() {
        System.out.println("-------- Fake Bin Removing Process -------");

        System.out.println("Insert Bin ID (string)... ");
        if(dataStorage.removeBin(scanner.next()) == 0){
            System.out.println("Bin removed successfully.");
        }else{
            System.out.println("Error removing bin");
        }
    }
}
