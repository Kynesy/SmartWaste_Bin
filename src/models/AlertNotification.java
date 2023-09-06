package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Questa classe rappresenta un oggetto di notifica di allarme.
 * Contiene informazioni relative all'ID, al timestamp, all'ID del bidone e al livello di allarme.
 */
public class AlertNotification {
    // Proprietà dell'oggetto di notifica di allarme
    @JsonProperty("id")
    private String id;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("binId")
    private String binId;

    @JsonProperty("alertLevel")
    private int alertLevel;

    // Costruttore vuoto
    public AlertNotification() {
    }

    // Metodi di accesso per le proprietà dell'oggetto
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public int getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(int alertLevel) {
        this.alertLevel = alertLevel;
    }

    // Metodo di override per rappresentazione testuale
    @Override
    public String toString() {
        return "AlertNotification{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", binId='" + binId + '\'' +
                ", alertLevel=" + alertLevel +
                '}';
    }
}
