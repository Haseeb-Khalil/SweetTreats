package org.treats;

public class Main {
    public static void main(String[] args) throws Exception {
        // Find best courier service
        Order order = new Order("11:00", true, 5.0);  // Prints Bobby
//        Order order = new Order("09:30", false, 3.0); // Prints Martin
//        Order order = new Order("13:00", true, 4.0); // Prints Geoff

//        Order order = new Order("11:00", true, 0); // Throws Exception for distance.
//        Order order = new Order("1300", true, 4.0); // Doesn't accept this format instead uses local time of device.
        System.out.println(SweetTreatsShop.getCourierDetails(order));
        System.out.println(SweetTreatsShop.getDeliveryPrice(order));
    }

}