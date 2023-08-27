package views.menu.binManagement;

import models.AlertNotification;
import services.DataStorage;
import services.IDataStorage;
import services.IPublisherService;
import services.PublisherService;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UnloadBinProcess {
    private final Scanner scanner;
    private final IDataStorage dataStorage;
    private final IPublisherService publisherService;
    public UnloadBinProcess(){
        scanner = new Scanner(System.in);
        dataStorage = new DataStorage();
        publisherService = new PublisherService();
    }

    public void start() {
        System.out.println("-------- Bin Unloading Process -------");

        System.out.println("Insert Bin ID path (use q to exit)... ");
        while(scanner.hasNext()){
            String binID = scanner.next();
            if(binID.equals("q")){
                break;
            }
            if(dataStorage.unloadBin(binID) == 0){
                System.out.println("Bin ("+binID+") unloaded successfully");
                AlertNotification alertNotification = new AlertNotification();

                alertNotification.setBinId(binID);
                alertNotification.setAlertLevel(0);
                alertNotification.setTimestamp(String.valueOf(LocalDateTime.now()));

                publisherService.sendAlert(alertNotification);
            }else{
                System.out.println("Error: Bin not found.");
            }
        }
    }
}
