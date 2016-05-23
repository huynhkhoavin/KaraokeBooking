package com.example.khoavin.karaokebooking.KaraokeObject;

/**
 * Created by OatOal on 5/23/2016.
 */
public class BranchLocation {
    private int id;
    private String branchName;
    private String description;
    private double latitude;
    private double longitude;
    private String distance;
    private String time;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {return latitude;}

    public void setLatitude(double latitude) {this.latitude = latitude;}

    public BranchLocation(int h_id, String h_branchName, String h_description, String h_address, double h_latitude, double h_longitude){
        this.id = h_id;
        this.branchName = h_branchName;
        this.description = h_description;
        this.latitude = h_latitude;
        this.longitude = h_longitude;
        this.address = h_address;
    }
}
