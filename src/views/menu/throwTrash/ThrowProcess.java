package views.menu.throwTrash;

import models.AlertNotification;
import models.TrashNotification;
import security.UserVerifier;
import services.DataStorage;
import services.IDataStorage;
import services.IPublisherService;
import services.PublisherService;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Questa classe gestisce il processo di simulazione del lancio dei rifiuti.
 * Gli utenti possono effettuare un lancio manuale dei rifiuti o un lancio casuale.
 * Inoltre, viene effettuata la verifica dell'utente, la gestione del rifiuto e l'invio di notifiche
 * in caso di allarme di capacità superata.
 */
public class ThrowProcess {
    private final Scanner scanner;
    private final IPublisherService publisherService;
    private final IDataStorage dataStorage;

    /**
     * Costruttore della classe ThrowProcess.
     * Inizializza l'input da console, il servizio di pubblicazione (publisherService)
     * e l'istanza di DataStorage per la gestione dei dati dei bidoni.
     */
    public ThrowProcess() {
        scanner = new Scanner(System.in);
        publisherService = new PublisherService();
        dataStorage = new DataStorage();
    }

    /**
     * Avvia il processo di lancio manuale dei rifiuti.
     * Gli utenti possono inserire informazioni sul rifiuto come BinID, UserID, quantità di rifiuti
     * e orario di lancio. Inoltre, viene effettuata una verifica dell'utente e la gestione delle notifiche.
     */
    public void startThrow() {
        System.out.println("------- Throw Trash Menu -------");

        TrashNotification trashNotification = new TrashNotification();

        String userID = identifyUser();
        if (userID == null) {
            System.out.println("Questo UserID non è fornito dal sistema di gestione intelligente.");
            return;
        }
        trashNotification.setUserId(userID);

        System.out.println("Inserisci il BinID...");
        String binID = scanner.next();
        if (dataStorage.binExist(binID)) {
            trashNotification.setBinId(binID);
        } else {
            System.out.println("Questo BinID non esiste.");
            return;
        }

        System.out.println("Inserisci la quantità di rifiuti separati (int)...");
        trashNotification.setSortedWaste(scanner.nextInt());

        System.out.println("Inserisci la quantità di rifiuti non separati (int)...");
        trashNotification.setUnsortedWaste(scanner.nextInt());

        trashNotification.setTimestamp(String.valueOf(LocalDateTime.now()));

        int result = dataStorage.uploadBin(trashNotification.getBinId(), trashNotification.getSortedWaste(), trashNotification.getUnsortedWaste());

        if (verifyAlert(result, binID) == 0) {
            publisherService.sendTrash(trashNotification);
        }
    }

    /**
     * Avvia il processo di lancio casuale dei rifiuti.
     * Viene generato casualmente un bin da cui verranno lanciati i rifiuti,
     * insieme a un UserID valido e quantità di rifiuti casuali. Vengono effettuate verifiche
     * sull'utente e sulle notifiche.
     */
    public void startRandomThrow(){
        System.out.println("------- Random Throw Trash Menu -------");

        TrashNotification trashNotification = new TrashNotification();

        String userID = identifyUser();
        if (userID == null) {
            System.out.println("Questo UserID non è fornito dal sistema di gestione intelligente.");
            return;
        } else {
            System.out.println("Il UserID fornito è valido.");
        }

        System.out.println("Quante volte vuoi buttare la spazzatura?");
        int n = scanner.nextInt();


        Random random = new Random();
        for(int i=0; i<n; i++){
            int randomIndex = random.nextInt(dataStorage.getLocalBins().size());
            String binID = dataStorage.getLocalBins().get(randomIndex).getId();
            int sortedTrash = random.nextInt(20) + 1;
            int unsortedTrash = random.nextInt(20) + 1;

            trashNotification.setBinId(binID);
            trashNotification.setUserId(userID);
            trashNotification.setSortedWaste(sortedTrash);
            trashNotification.setUnsortedWaste(unsortedTrash);
            trashNotification.setTimestamp(String.valueOf(LocalDateTime.now()));

            int result = dataStorage.uploadBin(trashNotification.getBinId(), trashNotification.getSortedWaste(), trashNotification.getUnsortedWaste());

            if (verifyAlert(result, binID) == 0) {
                publisherService.sendTrash(trashNotification);
            }
        }
    }

    /**
     * Effettua la verifica dell'utente in base all'ID fornito.
     * Se l'ID è valido, restituisce l'ID reale dell'utente; altrimenti, restituisce null.
     *
     * @return L'ID reale dell'utente o null se l'ID non è valido.
     */
    private String identifyUser() {
        System.out.println("Inserisci l'UserID (stringa)...");
        String encodedID = scanner.next();

        UserVerifier userVerifier = new UserVerifier(encodedID);

        if (userVerifier.isEncodedIdValid()) {
            return userVerifier.getRealID();
        } else {
            return null;
        }
    }

    /**
     * Verifica se è necessario inviare una notifica di allarme in base al risultato dell'upload dei rifiuti.
     * Se il valore è -1, il processo è annullato poiché il bidone è pieno. Se il valore non è 0,
     * viene creata e inviata una notifica di allarme.
     *
     * @param value Il valore restituito dal processo di upload dei rifiuti.
     * @param binID L'ID del bidone associato all'upload.
     * @return 0 se il processo è completato con successo, altrimenti 1.
     */
    private int verifyAlert(int value, String binID) {
        if (value == -1) {
            System.out.println("Processo annullato. Il bidone è pieno.");
            return 1;
        } else if (value != 0) {
            AlertNotification alertNotification = new AlertNotification();
            alertNotification.setBinId(binID);
            alertNotification.setTimestamp(String.valueOf(LocalDateTime.now()));
            alertNotification.setAlertLevel(value);
            publisherService.sendAlert(alertNotification);
        }
        return 0;
    }
}
