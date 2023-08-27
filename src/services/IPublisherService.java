package services;

import models.AlertNotification;
import models.TrashNotification;

public interface IPublisherService {
    void sendTrash(TrashNotification trashNotification);
    void sendAlert(AlertNotification alertNotification);
}
