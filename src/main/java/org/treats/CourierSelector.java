package org.treats;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

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
    static Courier john = new Courier("John", 4.5, 1.25, "10:00", "16:00", true);

    // Couriers List
    static List<Courier> couriers = new ArrayList<>(
            Arrays.asList(bobby, martin, geoff, john));


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

        // THIS METHOD WORKS BUT IS NOT THE BEST WAY TO DO IT.//
//        for (Courier courier : couriers) {
//            if (courier.isAvailable(order)) {
//
//
//                LOGGER.log(Level.INFO, "Best suitable courier for this order is: " + courier.getName() +
//                        "\n" + "Working Hours: " + courier.getStartTime() + " - " + courier.getEndTime() +
//                        "\n" + "Has Refrigerated Box: " + courier.isHasRefrigeratedBox() +
//                        "\n" + "Price: £" + courier.getPricePerMile() + " per mile" +
//                        "\n" + "Total Delivery Price For This Order is: £" + courier.getPricePerMile() * order.getDistance());
//
//            }
//            return courier;
//
//        }
//        throw new Exception("No courier is available for this order");
//        Making a list of available couriers

        List<Courier> availableCourier = couriers.stream().filter(courier -> courier.isAvailable(order)).collect(Collectors.toList());

        if (!availableCourier.isEmpty()) {
            Comparator<Courier> comparator = Comparator.comparing(courier -> courier.getPricePerMile());
            Courier cheapestCourier = availableCourier.stream().min(comparator).get();

            LOGGER.log(Level.INFO, "Best suitable courier for this order is: " + cheapestCourier.getName() +
                    "\n" + "Working Hours: " + cheapestCourier.getStartTime() + " - " + cheapestCourier.getEndTime() +
                    "\n" + "Has Refrigerated Box: " + cheapestCourier.isHasRefrigeratedBox() +
                    "\n" + "Price: £" + cheapestCourier.getPricePerMile() + " per mile" +
                    "\n" + "Total Delivery Price For This Order is: £" + cheapestCourier.getPricePerMile() * order.getDistance());
            return cheapestCourier;

        } else {
            LOGGER.log(Level.WARNING, "No suitable courier found for this order: " + "\n" + "Order Time: " + order.getOrderTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" + "Refrigerated Box Required: " + order.isRefrigeratedBoxRequired() + "\n" + "Distance Between Customer And Restaurant: " + order.getDistance() + " miles.");
            throw new Exception("No suitable courier found for this order: " + "\n" + "Order Time: " + order.getOrderTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" + "Refrigerated Box Required: " + order.isRefrigeratedBoxRequired() + "\n" + "Distance Between Customer And Restaurant: " + order.getDistance() + " miles.");

        }

    }
}





