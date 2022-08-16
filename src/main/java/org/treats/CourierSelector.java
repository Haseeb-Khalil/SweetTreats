package org.treats;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CourierSelector {

    private static final Logger LOGGER = Logger.getLogger(CourierSelector.class.getName());

    static {
//        ConsoleHandler consoleHandler = new ConsoleHandler();
//        LOGGER.addHandler(consoleHandler);
//        consoleHandler.setFormatter(new SimpleFormatter());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(CourierSelector.class.getSimpleName() + ".log");
            fileHandler.setFormatter(new SimpleFormatter());

//            Filter filterAll = s -> false;
//            fileHandler.setFilter(filterAll);

        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.addHandler(fileHandler);
    }

    static Courier bobby = new Courier("Bobby", 5.0, 1.75, "09:00", "13:00", true);
    static Courier martin = new Courier("Martin", 3.0, 1.50, "09:00", "17:00", false);
    static Courier geoff = new Courier("Geoff", 4.0, 2.00, "10:00", "16:00", true);

    // Couriers List
    static List<Courier> couriers = new ArrayList<>(
            Arrays.asList(bobby, martin, geoff));


    // Courier selector for Best courier of the hour
    public static Courier getBestSuitableCourier(Order order) throws Exception {


        LOGGER.log(Level.INFO, "Getting best suitable courier for order: " + "\n" + "Order Time: " + order.getOrderTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" + "Distance Between Shop and Customer: " + order.getDistance() + "miles" + "\n" + "Refrigeration Requirement: " + order.isRefrigeratedBoxRequired());


        if (order.getDistance() <= 0) {
            LOGGER.log(Level.WARNING, "Distance cannot be less than or equal to 0");
            throw new Exception("Correct Distance is required");
        }
        if (order.getOrderTime().isBefore(LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm"))) || order.getOrderTime().isAfter(LocalTime.parse("17:00", DateTimeFormatter.ofPattern("HH:mm")))) {
            LOGGER.log(Level.WARNING, "Order time is not between business hours: 09:00 - 17:00");
            throw new Exception("Order time is outside of working hours");
        }
//        return couriers.stream()
//                .filter(courier -> {
//                    courier.isAvailable(order);
//                    LOGGER.log(Level.INFO, "Best suitable courier for this order is: " + courier.getName() +
//                            "\n" + "Working Hours: " + courier.getStartTime() + " - " + courier.getEndTime() +
//                            "\n" + "Has Refrigerated Box: " + courier.isHasRefrigeratedBox() +
//                            "\n" + "Price: £" + courier.getPricePerMile() + " per mile");
//
//                    return false;
//                })
//                .findFirst()
//                .orElseThrow(() -> new Exception("No suitable courier found for this order: " + "\n" + "Order Time: " + order.getOrderTime() + "\n" + "Refrigerated Box Required: " + order.isRefrigeratedBoxRequired() + "\n" + "Distance Between Customer And Restaurant: " + order.getDistance() + " miles."));

        for (Courier courier : couriers) {
            if (courier.isAvailable(order)) {
                LOGGER.log(Level.INFO, "Best suitable courier for this order is: " + courier.getName() +
                        "\n" + "Working Hours: " + courier.getStartTime() + " - " + courier.getEndTime() +
                        "\n" + "Has Refrigerated Box: " + courier.isHasRefrigeratedBox() +
                        "\n" + "Price: £" + courier.getPricePerMile() + " per mile" +
                        "\n" + "Total Delivery Price For This Order is: £" + courier.getPricePerMile() * order.getDistance());
                return courier;
            }
        }
        LOGGER.log(Level.WARNING, "No suitable courier found for this order: " + "\n" + "Order Time: " + order.getOrderTime() + "\n" + "Refrigerated Box Required: " + order.isRefrigeratedBoxRequired() + "\n" + "Distance Between Customer And Restaurant: " + order.getDistance() + " miles.");
        throw new Exception("No suitable courier found for this order: " + "\n" + "Order Time: " + order.getOrderTime() + "\n" + "Refrigerated Box Required: " + order.isRefrigeratedBoxRequired() + "\n" + "Distance Between Customer And Restaurant: " + order.getDistance() + " miles.");


    }

    // Get courier details
//    public static String getCourierDetails(Order order) throws Exception {
//        Courier bestCourier = getBestSuitableCourier(order);
//        return "Best Courier Found: " + "\n" + "Name: " + bestCourier.getName() + "\n" + "Start Time: " + bestCourier.getStartTime() + "\n" + "End Time: " + bestCourier.getEndTime() + "\n" + "Charge Per Mile: £" + bestCourier.getPricePerMile() + "\n" + "Can Deliver up to: " + bestCourier.getMaxDistance() + " miles" + "\n" + "Has Refrigerated Box? " + bestCourier.isHasRefrigeratedBox();
//    }
//
//    // Get delivery price
//    public static String getDeliveryPrice(Order order) throws Exception {
//        Courier bestCourier = getBestSuitableCourier(order);
//        return "Total Delivery Price For This Order is: £" + bestCourier.getPricePerMile() * order.getDistance();
//    }


}

