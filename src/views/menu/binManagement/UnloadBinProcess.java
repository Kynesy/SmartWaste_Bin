package views.menu.binManagement;

import models.AlertNotification;
import services.DataStorage;
import services.IDataStorage;
import services.IPublisherService;
import services.PublisherService;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Questa classe gestisce il processo di scarico di un bidone.
 * Gli utenti possono inserire l'ID del bidone che desiderano scaricare.
 * Dopo lo scarico del bidone, viene inviata una notifica di allarme
 * con livello "0" per indicare che il bidone è vuoto.
 */
public class UnloadBinProcess {
    private final Scanner scanner;
    private final IDataStorage dataStorage;
    private final IPublisherService publisherService;

    /**
     * Costruttore della classe UnloadBinProcess.
     * Inizializza l'input da console, il servizio di gestione dei dati dei bidoni (dataStorage)
     * e il servizio di pubblicazione delle notifiche (publisherService).
     */
    public UnloadBinProcess(){
        scanner = new Scanner(System.in);
        dataStorage = new DataStorage();
        publisherService = new PublisherService();
    }

    /**
     * Avvia il processo di scarico di un bidone.
     * Gli utenti possono inserire l'ID del bidone che desiderano scaricare.
     * Dopo lo scarico del bidone, viene inviata una notifica di allarme
     * con livello "0" per indicare che il bidone è vuoto.
     */
    public void start() {
        System.out.println("-------- Bin Unloading Process -------");

        System.out.println("Inserisci l'ID del bidone (usa 'q' per uscire)... ");
        while(scanner.hasNext()){
            String binID = scanner.next();
            if(binID.equals("q")){
                break;
            }
            if(dataStorage.unloadBin(binID) == 0){
                System.out.println("Bidone ("+binID+") scaricato con successo");
                AlertNotification alertNotification = new AlertNotification();

                alertNotification.setBinId(binID);
                alertNotification.setAlertLevel(0);
                alertNotification.setTimestamp(String.valueOf(LocalDateTime.now()));

                publisherService.sendAlert(alertNotification);
            }else{
                System.out.println("Errore: Bidone non trovato.");
            }
        }
    }
}
