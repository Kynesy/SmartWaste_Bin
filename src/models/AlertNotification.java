package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlertNotification {
    @JsonProperty("id")
    private String id;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("binId")
    private String binId;
    @JsonProperty("alertLevel")
    private int alertLevel;

    public AlertNotification() {
    }

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
