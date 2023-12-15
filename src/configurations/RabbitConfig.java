package configurations;

/**
 * Questa classe gestisce la configurazione per le connessioni RabbitMQ.
 * Contiene costanti per gli indirizzi, i nomi degli scambi e delle code,
 * nonché le credenziali di accesso al server RabbitMQ.
 */
public class RabbitConfig {
    // Nomi degli scambi e delle code
    private static final String EXCHANGE_NAME = "wasteDisposalAgencyTopic";
    private static final String TRASH_ROUTING_KEY = "trashNotificationRK";
    private static final String TRASH_QUEUE = "trashNotificationTopic";
    private static final String ALERT_ROUTING_KEY = "capacityAlertRK";
    private static final String ALERT_QUEUE = "capacityAlertTopic";

    // Indirizzo del server RabbitMQ
    private static final String HOST = "54.166.4.107";

    // Credenziali di accesso
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    // Metodi di accesso alle costanti
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
