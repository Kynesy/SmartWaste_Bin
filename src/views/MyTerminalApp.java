package views;

import services.DataStorage;
import services.IDataStorage;
import views.menu.BinManagementMenu;
import views.menu.ThrowTrashMenu;

import java.util.Scanner;

public class MyTerminalApp {
    private Scanner input;
    private final IDataStorage dataStorage;
    public MyTerminalApp() {
        input = new Scanner(System.in);
        dataStorage = new DataStorage();
        dataStorage.loadData();
    }

    public void start(){
        boolean exit = false;
        while(!exit){
            int choice = showMainMenu();
            switch (choice){
                case 0:{
                    exit = true;
                    dataStorage.saveData();
                    break;
                }
                case 1:{
                    BinManagementMenu binManagementMenu = new BinManagementMenu();
                    binManagementMenu.start();
                    break;
                }
                case 2:{
                    ThrowTrashMenu throwTrashMenu = new ThrowTrashMenu();
                    throwTrashMenu.start();
                    break;
                }
                default:{
                    System.out.println("Invalid choice.");
                    break;
                }
            }
        }
    }

    private int showMainMenu(){
        boolean done = false;
        int selection = 0;

        do{
            System.out.println("------------------ Main Menu -----------------");
            System.out.println("Use numbers to select.");
            System.out.println("1. Bin Management Simulation");
            System.out.println("2. Throw some trash");
            System.out.println("0. Exit");
            System.out.print("Waiting for selection... ");

            if(input.hasNextInt()){
                selection = input.nextInt();
                done = true;
            }else{
                System.out.println("Invalid input: Please enter a valid integer.");
                input.nextLine();
            }
        }while(!done);

        return selection;
    }

}
