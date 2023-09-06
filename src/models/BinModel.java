package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Questa classe rappresenta un modello per un bidone di raccolta rifiuti.
 * Contiene informazioni relative all'ID, alla posizione (latitudine e longitudine),
 * alla capacità, alla quantità di rifiuti differenziati e indifferenziati,
 * e al livello di allarme del bidone.
 */
public class BinModel {
    // Proprietà del modello del bidone
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

    // Costruttore vuoto
    public BinModel() {
    }

    // Metodi di accesso per le proprietà del modello del bidone
    public String getId() {
        return id;
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

    // Metodo di override per rappresentazione testuale
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
