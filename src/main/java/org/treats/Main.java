package org.treats;

public class Main {
    public static void main(String[] args) {
        // Find cheapest courier service
        Order order = new Order("12:30", true, 1.5);

        System.out.println(SweetTreatsShop.getCheapestCourier(order));


    }
}