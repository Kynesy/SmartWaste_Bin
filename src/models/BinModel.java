package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BinModel {
    @JsonProperty("id")
    private String id;
    @JsonProperty("latitude")
    private float latitude;
    @JsonProperty("longitude")
    private float longitude;
    @JsonProperty("capacity")
    private int capacity;
    @JsonProperty("sortedWaste")
    private int sortedWaste;
    @JsonProperty("unsortedWaste")
    private int unsortedWaste;
    @JsonProperty("alertLevel")
    private int alertLevel;

    public String getId() {
        return id;
    }

    public BinModel() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public int getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(int alertLevel) {
        this.alertLevel = alertLevel;
    }


    @Override
    public String toString() {
        return "Bin: {" +
                "id='" + id + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", capacity=" + capacity +
                ", sortedWaste=" + sortedWaste +
                ", unsortedWaste=" + unsortedWaste +
                ", alertLevel=" + alertLevel +
                '}';
    }
}
