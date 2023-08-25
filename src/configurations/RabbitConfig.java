package configurations;

public class RabbitConfig {
    private static final String EXCHANGE_NAME = "wasteDisposalAgencyTopic";
    private static final String TRASH_ROUTING_KEY = "trashNotificationRK";
    private static final String TRASH_QUEUE = "trashNotificationTopic";
    private static final String ALERT_ROUTING_KEY = "capacityAlertRK";
    private static final String ALERT_QUEUE = "capacityAlertTopic";

    private static final String HOST = "localhost"; // Replace with your desired routing key
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static String getAlertRoutingKey() {
        return ALERT_ROUTING_KEY;
    }

    public static String getExchangeName() {
        return EXCHANGE_NAME;
    }

    public static String getTrashRoutingKey() {
        return TRASH_ROUTING_KEY;
    }

    public static String getHOST() {
        return HOST;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getAlertQueue() {
        return ALERT_QUEUE;
    }

    public static String getTrashQueue() {
        return TRASH_QUEUE;
    }
}
