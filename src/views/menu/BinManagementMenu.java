package views.menu;

import models.BinModel;
import services.DataStorage;
import services.IDataStorage;
import views.menu.binManagement.DeleteBinProcess;
import views.menu.binManagement.InstallBinProcess;
import views.menu.binManagement.UnloadBinProcess;

import java.util.Scanner;

/**
 * Questa classe gestisce il menu per la simulazione della gestione dei bidoni di raccolta.
 * L'utente può scegliere tra diverse opzioni, tra cui l'installazione e la rimozione di bidoni
 * dal database locale, lo scarico dei bidoni tramite RabbitMQ e la visualizzazione di tutti i bidoni presenti
 * nel database locale.
 */
public class BinManagementMenu {
    private final Scanner scanner;
    private final IDataStorage dataStorage;

    /**
     * Costruttore della classe BinManagementMenu.
     * Inizializza l'input da console e crea un'istanza di DataStorage per la gestione dei dati dei bidoni.
     */
    public BinManagementMenu() {
        scanner = new Scanner(System.in);
        dataStorage = new DataStorage();
    }

    /**
     * Avvia il menu per la simulazione della gestione dei bidoni di raccolta.
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
                default -> System.out.println("Scelta non valida.");
            }
            dataStorage.saveData();
        }
    }

    /**
     * Visualizza il menu della simulazione della gestione dei bidoni di raccolta
     * e permette all'utente di effettuare una selezione.
     *
     * @return La scelta effettuata dall'utente.
     */
    private int showMenu() {
        boolean done = false;
        int selection = 0;

        do {
            System.out.println("-------------- Menu Simulazione Gestione Bidoni -------------");
            System.out.println("Utilizzare i numeri per selezionare un'opzione.");
            System.out.println("1. Installa un bidone (Solo per la gestione del database locale)");
            System.out.println("2. Rimuovi un bidone (Solo per la gestione del database locale)");
            System.out.println("3. Scarica i bidoni (RabbitMQ)");
            System.out.println("4. Mostra tutti i bidoni nel database locale.");

            System.out.println("0. Indietro");
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

    /**
     * Mostra tutti i bidoni presenti nel database locale.
     */
    private void showAllBins() {
        System.out.println("------- Lista Bidoni ---------- ");
        for (BinModel bin : dataStorage.getLocalBins()) {
            System.out.println(bin.toString());
        }
    }
}
