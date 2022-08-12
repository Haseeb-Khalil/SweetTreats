package org.treats;

public class Main {
    public static void main(String[] args) throws Exception {
        // Find best courier service
//        Order order = new Order("09:30", true, 5.00);
        Order order = new Order("14:00", false, 3.00);
//        Order order = new Order("09:30", false, 3.00);
//        Order order = new Order("10:30", true, 4.00);
        System.out.println(SweetTreatsShop.getCourierDetails(order));
        System.out.println(SweetTreatsShop.getDeliveryPrice(order));
    }

}