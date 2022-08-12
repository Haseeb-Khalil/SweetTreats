package org.treats;

public class Main {
    public static void main(String[] args) throws Exception {
        // Find best courier service
        Order order = new Order("09:30", true, "5.00");  // Prints Bobby
//        Order order = new Order("09:30", false, "3.00"); // Prints Martin
//        Order order = new Order("13:00", true, "4.00"); // Prints Geoff

//        Order order = new Order("10:30", true, "0"); // Throws Exception
        System.out.println(SweetTreatsShop.getCourierDetails(order));
        System.out.println(SweetTreatsShop.getDeliveryPrice(order));
    }

}