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

public class ThrowProcess {
    private final Scanner scanner;
    private final IPublisherService publisherService;
    private final IDataStorage dataStorage;


    public ThrowProcess() {
        scanner = new Scanner(System.in);
        publisherService = new PublisherService();
        dataStorage = new DataStorage();
    }

    public void startThrow(){
        System.out.println("------- Throw Trash Menu -------");

        TrashNotification trashNotification = new TrashNotification();

        String userID = identifyUser();
        if(userID==null){
            System.out.println("This UserID is not one provided by the Smart Management System.");
            return;
        }
        trashNotification.setUserId(userID);

        System.out.println("Insert BinID...");
        String binID = scanner.next();
        if(dataStorage.binExist(binID)){
            trashNotification.setBinId(binID);
        }else{
            System.out.println("This BinID does not exist.");
            return;
        }

        System.out.println("Insert amount of sorted waste (int)...");
        trashNotification.setSortedWaste(scanner.nextInt());

        System.out.println("Insert amount of unsorted waste (int)...");
        trashNotification.setUnsortedWaste(scanner.nextInt());

        trashNotification.setTimestamp(String.valueOf(LocalDateTime.now()));

        int result = dataStorage.uploadBin(trashNotification.getBinId(), trashNotification.getSortedWaste(), trashNotification.getUnsortedWaste());

        if(verifyAlert(result, binID) == 0){
            publisherService.sendTrash(trashNotification);
        }
    }

    public void startRandomThrow(){
        System.out.println("------- Random Throw Trash Menu -------");

        TrashNotification trashNotification = new TrashNotification();

        String userID = identifyUser();
        if(userID == null){
            System.out.println("This UserID is not one provided by the Smart Management System.");
            return;
        }else{
            System.out.println("The provided UserID is valid.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(dataStorage.getLocalBins().size());
        String binID = dataStorage.getLocalBins().get(randomIndex).getId();
        int sortedTrash = random.nextInt(10) + 1;
        int unsortedTrash = random.nextInt(10) + 1;

        trashNotification.setBinId(binID);
        trashNotification.setUserId(userID);
        trashNotification.setSortedWaste(sortedTrash);
        trashNotification.setUnsortedWaste(unsortedTrash);
        trashNotification.setTimestamp(String.valueOf(LocalDateTime.now()));

        int result = dataStorage.uploadBin(trashNotification.getBinId(), trashNotification.getSortedWaste(), trashNotification.getUnsortedWaste());

        if(verifyAlert(result, binID) == 0){
            publisherService.sendTrash(trashNotification);
        }

        //System.out.println(trashNotification);
    }

    private String identifyUser(){
        System.out.println("Insert UserID (string)...");
        String encodedID = scanner.next();

        UserVerifier userVerifier = new UserVerifier(encodedID);

        if(userVerifier.isEncodedIdValid()){
            return userVerifier.getRealID();
        }else{
            return null;
        }
    }

    private int verifyAlert(int value, String binID){
        if(value == -1){
            System.out.println("Process aborted. Bin is full");
            return 1;
        }else if(value != 0){
            AlertNotification alertNotification = new AlertNotification();
            alertNotification.setBinId(binID);
            alertNotification.setTimestamp(String.valueOf(LocalDateTime.now()));
            alertNotification.setAlertLevel(value);
            publisherService.sendAlert(alertNotification);
        }
        return 0;
    }
}
