/**
 * Questa classe rappresenta un'applicazione a riga di comando per la gestione dei bidoni
 * di raccolta e la simulazione del lancio dei rifiuti. L'applicazione offre un menu principale
 * con diverse opzioni, tra cui la gestione dei bidoni e il lancio dei rifiuti.
 */
package views;

import services.DataStorage;
import services.IDataStorage;
import views.menu.BinManagementMenu;
import views.menu.ThrowTrashMenu;

import java.util.Scanner;

public class MyTerminalApp {
    private final Scanner input;
    private final IDataStorage dataStorage;

    /**
     * Costruttore della classe MyTerminalApp.
     * Inizializza l'input da console, crea un'istanza di DataStorage per la gestione dei dati dei bidoni,
     * e carica i dati esistenti se presenti.
     */
    public MyTerminalApp() {
        input = new Scanner(System.in);
        dataStorage = new DataStorage();
        dataStorage.loadData();
    }

    /**
     * Avvia l'applicazione, visualizzando il menu principale e gestendo le scelte dell'utente.
     */
    public void start() {
        boolean exit = false;
        while (!exit) {
            int choice = showMainMenu();

            switch (choice) {
                case 0 -> {
                    exit = true;
                    dataStorage.saveData();
                }
                case 1 -> {
                    BinManagementMenu binManagementMenu = new BinManagementMenu();
                    binManagementMenu.start();
                }
                case 2 -> {
                    ThrowTrashMenu throwTrashMenu = new ThrowTrashMenu();
                    throwTrashMenu.start();
                }
                default -> {
                    System.out.println("Scelta non valida.");
                }
            }
        }
    }

    /**
     * Visualizza il menu principale e permette all'utente di effettuare una selezione.
     *
     * @return La scelta effettuata dall'utente.
     */
    private int showMainMenu() {
        boolean done = false;
        int selection = 0;

        do {
            System.out.println("------------------ Menu Principale -----------------");
            System.out.println("Utilizzare i numeri per selezionare un'opzione.");
            System.out.println("1. Simulazione di Gestione dei Bidoni");
            System.out.println("2. Lancio di Rifiuti");
            System.out.println("0. Uscita");
            System.out.print("In attesa di selezione... ");

            if (input.hasNextInt()) {
                selection = input.nextInt();
                done = true;
            } else {
                System.out.println("Input non valido: Inserire un intero valido.");
                input.nextLine();
            }
        } while (!done);

        return selection;
    }
}
