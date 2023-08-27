package views.menu;

import views.menu.binManagement.DeleteBinProcess;
import views.menu.binManagement.InstallBinProcess;
import views.menu.binManagement.UnloadBinProcess;
import views.menu.throwTrash.ThrowProcess;

import java.util.Scanner;

public class ThrowTrashMenu {
    private final Scanner scanner;

    public ThrowTrashMenu(){
        scanner = new Scanner(System.in);
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
                    ThrowProcess throwProcess = new ThrowProcess();
                    throwProcess.startThrow();
                }
                case 2 -> {
                    ThrowProcess throwProcess = new ThrowProcess();
                    throwProcess.startRandomThrow();
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
            System.out.println("-------------- Throw trash menu -------------");
            System.out.println("Use numbers to select.");
            System.out.println("1. Manual trash throw (Rabbit)");
            System.out.println("2. Random trash throw [It needs only UserID] (Rabbit)");
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
}
