package org.treats;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Courier {
    private String name;
    private String maxDistance;
    private double pricePerMile;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean hasRefrigeratedBox;

    public Courier() {
    }


    public Courier(String name, double maxDistance, double pricePerMile, String startTime, String endTime, boolean hasRefrigeratedBox) {
        this.name = name;
        this.maxDistance = maxDistance + " miles";
        this.pricePerMile = pricePerMile;
        this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.hasRefrigeratedBox = hasRefrigeratedBox;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = String.valueOf(maxDistance);
    }

    public double getPricePerMile() {
        return pricePerMile;
    }

    public void setPricePerMile(double pricePerMile) {
        this.pricePerMile = pricePerMile;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isHasRefrigeratedBox() {
        return hasRefrigeratedBox;
    }

    public void setHasRefrigeratedBox(boolean hasRefrigeratedBox) {
        this.hasRefrigeratedBox = hasRefrigeratedBox;
    }

    public String toString() {
        return "Name: " + this.name + "\n" + "Miles: " + this.maxDistance + "\n" + "Charge per Mile: " + this.pricePerMile + "\n" + "Start Time: " + this.startTime + "\n" + "End Time: " + this.endTime + "\n" + "Refrigerated Box: " + this.hasRefrigeratedBox;
    }



}
