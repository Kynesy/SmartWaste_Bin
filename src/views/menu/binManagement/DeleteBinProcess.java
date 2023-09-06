package views.menu.binManagement;

import services.DataStorage;
import services.IDataStorage;

import java.util.Scanner;

/**
 * Questa classe gestisce il processo di rimozione di un bidone simulato.
 * Gli utenti possono inserire l'ID di un bidone e avviare il processo di rimozione.
 */
public class DeleteBinProcess {
    private final Scanner scanner;
    private final IDataStorage dataStorage;

    /**
     * Costruttore della classe DeleteBinProcess.
     * Inizializza l'input da console (scanner) e il servizio di gestione dei dati dei bidoni (dataStorage).
     */
    public DeleteBinProcess(){
        scanner = new Scanner(System.in);
        dataStorage = new DataStorage();
    }

    /**
     * Avvia il processo di rimozione del bidone simulato.
     * Gli utenti possono inserire l'ID del bidone da rimuovere.
     * Dopo la rimozione, viene visualizzato un messaggio di conferma o di errore.
     */
    public void start() {
        System.out.println("-------- Fake Bin Removing Process -------");

        System.out.println("Inserisci l'ID del bidone (stringa)... ");
        if(dataStorage.removeBin(scanner.next()) == 0){
            System.out.println("Bidone rimosso con successo.");
        }else{
            System.out.println("Errore durante la rimozione del bidone");
        }
    }
}
