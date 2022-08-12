package org.treats;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SweetTreatsShop {
    static Courier bobby = new Courier("Bobby", "5", 1.75, "09:00", "13:00", true);
    static Courier martin = new Courier("Martin", "3", 1.50, "09:00", "17:00", false);
    static Courier geoff = new Courier("Geoff", "5", 2.00, "10:00", "16:00", true);

    // Couriers List
    static List<Courier> couriers = new ArrayList<>(
            Arrays.asList(bobby, martin, geoff));


    // Courier selector for Best courier of the hour
    public static Courier getBestSuitableCourier(Order order) throws Exception {

        if (order.getOrderTime() == null || order.getDistance() == 0) {
            throw new Exception("Order time, Refrigerated box requirement and Distance are required");
        }
        if (order.getOrderTime().isBefore(LocalTime.parse("09:00")) || order.getOrderTime().isAfter(LocalTime.parse("17:00"))) {
            throw new Exception("Order time is outside of working hours");
        }
        return couriers.stream()
                .filter(courier -> courier.isAvailable(order))
                .findFirst()
                .orElseThrow(() -> new Exception("No suitable courier found"));
    }


    // Get courier details
    public static String getCourierDetails(Order order) throws Exception {
        Courier bestCourier = getBestSuitableCourier(order);
        return "Best Courier Found: " + "\n" + "Name: " + bestCourier.getName() + "\n" + "Start Time: " + bestCourier.getStartTime() + "\n" + "End Time: " + bestCourier.getEndTime() + "\n" + "Charge Per Mile: £" + bestCourier.getPricePerMile() + "\n" + "Can Deliver up to: " + bestCourier.getMaxDistance() + " miles" + "\n" + "Has Refrigerated Box? " + bestCourier.isHasRefrigeratedBox();
    }

    // Get delivery price
    public static String getDeliveryPrice(Order order) throws Exception {
        Courier bestCourier = getBestSuitableCourier(order);
        return "Total Delivery Price For This Order is: £" + bestCourier.getPricePerMile() * order.getDistance();
    }


}

