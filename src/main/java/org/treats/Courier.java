package org.treats;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Courier {
    private String name;
    private double maxDistance;
    private double pricePerMile;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean hasRefrigeratedBox;


    // Constructor for Courier class.
    public Courier(String name, double maxDistance, double pricePerMile, String startTime, String endTime, boolean hasRefrigeratedBox) {
        this.name = name;
        this.maxDistance = maxDistance;
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

    public double getMaxDistance() {
        return maxDistance;
    }

//    public void setMaxDistance(String maxDistance) {
//        this.maxDistance = maxDistance;
//    }

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


    // Check if courier is available for the order
    public boolean isAvailable(Order order) {
        return order.getOrderTime().isAfter(startTime) && order.getOrderTime().isBefore(endTime) && order.getDistance() <= getMaxDistance() && order.isRefrigeratedBoxRequired() == hasRefrigeratedBox;
    }

    public LocalTime getOrderTime(Order order) {
        return order.getOrderTime();
    }
}
