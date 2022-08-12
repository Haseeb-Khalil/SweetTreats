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


    public Courier(String name, String maxDistance, double pricePerMile, String startTime, String endTime, boolean hasRefrigeratedBox) {
        this.name = name;
        this.maxDistance = Double.toString(Double.parseDouble(maxDistance));
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
        return Double.parseDouble(maxDistance);
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = maxDistance;
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

    //    @Override
    public boolean isAvailable(Order order) {
        return order.getOrderTime().isAfter(startTime) && order.getOrderTime().isBefore(endTime) && order.getDistance() <= getMaxDistance() && order.isRefrigeratedBoxRequired() == hasRefrigeratedBox;
    }
}
