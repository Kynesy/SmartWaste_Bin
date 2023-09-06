package views.menu;

import services.DataStorage;
import services.IDataStorage;
import views.menu.throwTrash.ThrowProcess;

import java.util.Scanner;

/**
 * Questa classe gestisce il menu per la simulazione del lancio dei rifiuti.
 * L'utente può scegliere tra il lancio manuale dei rifiuti o il lancio casuale
 * (richiede solo l'ID dell'utente). Inoltre, l'utente può tornare indietro al menu precedente.
 */
public class ThrowTrashMenu {
    private final Scanner scanner;
    private final IDataStorage dataStorage;

    /**
     * Costruttore della classe ThrowTrashMenu.
     * Inizializza l'input da console e crea un'istanza di DataStorage per la gestione dei dati dei bidoni.
     */
    public ThrowTrashMenu() {
        scanner = new Scanner(System.in);
        dataStorage = new DataStorage();
    }

    /**
     * Avvia il menu per la simulazione del lancio dei rifiuti.
     * L'utente può scegliere tra le varie opzioni e interagire con la simulazione.
     */
    public void start() {
        boolean back = false;
        while (!back) {
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
                default -> System.out.println("Scelta non valida.");
            }

            dataStorage.saveData();
        }
    }

    /**
     * Visualizza il menu della simulazione del lancio dei rifiuti
     * e permette all'utente di effettuare una selezione.
     *
     * @return La scelta effettuata dall'utente.
     */
    private int showMenu() {
        boolean done = false;
        int selection = 0;

        do {
            System.out.println("-------------- Menu Lancio Rifiuti -------------");
            System.out.println("Utilizzare i numeri per selezionare un'opzione.");
            System.out.println("1. Lancio manuale dei rifiuti (Rabbit)");
            System.out.println("2. Lancio casuale dei rifiuti [Richiede solo l'ID dell'utente] (Rabbit)");
            System.out.println("0. Torna indietro");
            System.out.print("In attesa di selezione... ");

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
                done = true;
            } else {
                System.out.println("Input non valido: Inserire un intero valido.");
                scanner.nextLine();
            }
        } while (!done);

        return selection;
    }
}
