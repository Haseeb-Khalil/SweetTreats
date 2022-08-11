package org.treats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SweetTreatsShop {
    static Courier bobby = new Courier("Bobby", 5, 1.75, "09:00", "13:00", true);
    static Courier martin = new Courier("Martin", 3, 1.75, "09:00", "17:00", false);
    static Courier geoff = new Courier("Geoff", 5, 1.75, "09:00", "13:00", true);

    // Couriers List
    static List<Courier> couriers = new ArrayList<>(
            Arrays.asList(bobby, martin, geoff));

    // Courier selector for cheapest courier of the hour
    public static Courier getCheapestCourier(Order order) {
        Courier cheapestCourier = null;
        double cheapestPrice = 0;
        for (Courier courier : couriers) {
            if (courier.getStartTime().isBefore(order.getOrderTime()) && courier.getEndTime().isAfter(order.getOrderTime())) {
                if (courier.getPricePerMile() * order.getDistance() < cheapestPrice || cheapestPrice == 0) {
                    cheapestPrice = courier.getPricePerMile() * order.getDistance();
                    cheapestCourier = courier;
                }
            }
        }
        return cheapestCourier;
    }

}

