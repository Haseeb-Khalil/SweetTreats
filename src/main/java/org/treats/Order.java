package org.treats;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private LocalTime orderTime;
    private boolean isRefrigeratedBoxRequired;
    private double distance;

    public Order() {
    }

    public Order(String orderTime, boolean hasRefrigeratedBox, double distance) {
        this.orderTime = LocalTime.parse(orderTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.isRefrigeratedBoxRequired = hasRefrigeratedBox;
        this.distance = distance;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public boolean isRefrigeratedBoxRequired() {
        return isRefrigeratedBoxRequired;
    }

    public void setRefrigeratedBoxRequired(boolean refrigeratedBoxRequired) {
        isRefrigeratedBoxRequired = refrigeratedBoxRequired;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
