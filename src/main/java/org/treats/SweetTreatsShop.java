package org.treats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SweetTreatsShop {
    static Courier bobby = new Courier("Bobby", "5", 1.75, "09:00", "13:00", true);
    static Courier martin = new Courier("Martin", "3", 1.50, "09:00", "17:00", false);
    static Courier geoff = new Courier("Geoff", "5", 2.00, "09:00", "13:00", true);

    // Couriers List
    static List<Courier> couriers = new ArrayList<>(
            Arrays.asList(bobby, martin, geoff));


    // Courier selector for Best courier of the hour
    public static Courier getBestSuitableCourier(Order order) throws Exception {
        Courier bestCourier = null;
        for (Courier courier : couriers) {
            if (courier.getStartTime().isBefore(order.getOrderTime()) && courier.getEndTime().isAfter(order.getOrderTime())) {
                if ((order.isRefrigeratedBoxRequired() && courier.isHasRefrigeratedBox()) && (courier.getMaxDistance()) >= (order.getDistance())) {
                    if (bestCourier == null) {
                        bestCourier = courier;
                    } else if (courier.getPricePerMile() < bestCourier.getPricePerMile()) {
                        bestCourier = courier;
                    }

                } else if ((!order.isRefrigeratedBoxRequired() && !courier.isHasRefrigeratedBox()) && ((courier.getMaxDistance()) >= (order.getDistance()))) {
                    if (bestCourier == null) {
                        bestCourier = courier;
                    } else if (courier.getPricePerMile() < bestCourier.getPricePerMile()) {
                        bestCourier = courier;
                    }
                }
//                else {
//                    throw new Exception("No suitable courier found for this order distance: " + order.getDistance() + " and refrigerated box requirement: " + order.isRefrigeratedBoxRequired());
//                }

            } else {
                throw new Exception("Sorry, no courier available at this order time : " + order.getOrderTime());
            }
        }
        return bestCourier;
    }


    // Get courier details
    public static String getCourierDetails(Order order) throws Exception {
        Courier bestCourier = getBestSuitableCourier(order);
        return "Best Courier Found: " + "\n" + "Name: " + bestCourier.getName() + "\n" + "Start Time: " + bestCourier.getStartTime() + "\n" + "End Time: " + bestCourier.getEndTime() + "\n" + "Charge Per Mile: " + bestCourier.getPricePerMile() + "\n" + "Can Deliver up to: " + bestCourier.getMaxDistance() + "miles" + "\n" + "Has Refrigerated Box? " + bestCourier.isHasRefrigeratedBox();
    }

    // Get delivery price
    public static String getDeliveryPrice(Order order) throws Exception {
        Courier bestCourier = getBestSuitableCourier(order);
        return "Total Delivery Price For This Order is: £" + bestCourier.getPricePerMile() * order.getDistance();
    }


}

