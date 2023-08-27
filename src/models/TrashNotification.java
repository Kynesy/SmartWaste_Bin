package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrashNotification {
    @JsonProperty("id")
    private String id;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("binId")
    private String binId;
    @JsonProperty("sortedWaste")
    private int sortedWaste;
    @JsonProperty("unsortedWaste")
    private int unsortedWaste;

    public TrashNotification() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public int getSortedWaste() {
        return sortedWaste;
    }

    public void setSortedWaste(int sortedWaste) {
        this.sortedWaste = sortedWaste;
    }

    public int getUnsortedWaste() {
        return unsortedWaste;
    }

    public void setUnsortedWaste(int unsortedWaste) {
        this.unsortedWaste = unsortedWaste;
    }

    @Override
    public String toString() {
        return "TrashNotification{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", userId='" + userId + '\'' +
                ", binId='" + binId + '\'' +
                ", sortedWaste=" + sortedWaste +
                ", unsortedWaste=" + unsortedWaste +
                '}';
    }
}
