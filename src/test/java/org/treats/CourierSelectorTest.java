package org.treats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierSelectorTest {

    private CourierSelector selectCheapestCourier;

    @BeforeEach
    void setUp() {
        selectCheapestCourier = new CourierSelector();

    }

    @DisplayName("Given a list of couriers with  time, distance and refrigeration requirement of an order")
    @Nested
    class GivenAListOfCouriersAndATimeDistanceAndRefrigerationRequirementOfAnOrderShouldReturnTheCheapestCourier {
        @DisplayName("When the time is between 09:00 abd 13:00, the distance is between 0 and 5 and the refrigeration requirement is true")
        @Nested
        class WhenTheTimeIsBetween0900And1300AndTheDistanceIsBetween0And5AndTheRefrigerationRequirementIsTrueShouldReturnBobby {
            @DisplayName("Then the cheapest courier should be Bobby")
            @Test
            void testTheCheapestCourierShouldBeMartin() throws Exception {
                Order order = new Order("09:30", true, 4.0);
                assertEquals("Bobby", selectCheapestCourier.getBestSuitableCourier(order).getName());
            }
        }

        @DisplayName("When the time is between 09:00 abd 17:00, the distance is between 0 and 3 and the refrigeration requirement is false")
        @Nested
        class WhenTheTimeIsBetween0900And1700AndTheDistanceIsBetween0And3AndTheRefrigerationRequirementIsFalseShouldReturnMartin {
            @DisplayName("Then the cheapest courier should be Martin")
            @Test
            void testTheCheapestCourierShouldBeMartin() throws Exception {
                Order order = new Order("11:00", false, 3.0);
                assertEquals("Martin", selectCheapestCourier.getBestSuitableCourier(order).getName());
            }
        }

        @DisplayName("When the time is between 10:00 abd 16:00, the distance is between 0 and 3 and the refrigeration requirement is true")
        @Nested
        class WhenTheTimeIsBetween1000And1600AndTheDistanceIsBetween0And3AndTheRefrigerationRequirementIsTrueShouldReturnJohn {
            @DisplayName("Then the cheapest courier should be John")
            @Test
            void testTheCheapestCourierShouldBeJohn() throws Exception {
                Order order = new Order("11:00", true, 3.0);
                assertEquals("John", selectCheapestCourier.getBestSuitableCourier(order).getName());
            }
        }

        @DisplayName("When the time is between 10:00 abd 16:00, the distance is between 3 and 4 and the refrigeration requirement is true")
        @Nested
        class WhenTheTimeIsBetween1000And1600AndTheDistanceIsBetween3And4AndTheRefrigerationRequirementIsTrueShouldReturnGeoff {
            @DisplayName("Then the cheapest courier should be Geoff")
            @Test
            void testTheCheapestCourierShouldBeGeoff() throws Exception {
                Order order = new Order("13:00", true, 4.0);
                assertEquals("Geoff", selectCheapestCourier.getBestSuitableCourier(order).getName());
            }
        }
    }

    @DisplayName("Given a list of couriers with incorrect time or distance requirement of an order")
    @Nested
    class GivenAListOfCouriersAndAnIncorrectTimeOrDistanceRequirementOfAnOrderShouldThrowException {
        @DisplayName("When the order time is between 09:00 abd 17:00, the distance is 0 or less")
        @Nested
        class WhenTheTimeIsBetween0900And1700AndTheDistanceIsZeroOrLessShouldThrowException {
            @DisplayName("Then the program should be throw exception")
            @Test
            void testTheCheapestCourierShouldThrowException() {
                Order order = new Order("11:00", true, 0);
                Order order1 = new Order("11:00", false, -1);
                assertAll("Verify conditions for distance",
                        () -> assertThrows(Exception.class, () -> selectCheapestCourier.getBestSuitableCourier(order)),
                        () -> assertThrows(Exception.class, () -> selectCheapestCourier.getBestSuitableCourier(order1)));
            }
        }

        @DisplayName("When the order time is NOT between 09:00 abd 17:00, the distance is not greater than courier's max distance")
        @Nested
        class WhenTheTimeIsNotBetween0900And1700AndTheDistanceIsNotGreaterThanCourierMaxDistanceShouldThrowException {
            @DisplayName("Then the program should be throw exception")
            @Test
            void testTheCheapestCourierShouldThrowException() {
                Order order = new Order("19:00", true, 5.0);
                Order order1 = new Order("08:59", false, 3.0);
                assertAll("Verify conditions for time of order",
                        () -> assertThrows(Exception.class, () -> selectCheapestCourier.getBestSuitableCourier(order)),
                        () -> assertThrows(Exception.class, () -> selectCheapestCourier.getBestSuitableCourier(order1)));
            }
        }

        @DisplayName("When an order's time, refrigeration or distance requirements does not match with any courier")
        @Nested
        class WhenAnOrderTimeOrRefrigerationOrDistanceRequirementsDoesNotMatchWithAnyCourierShouldThrowException {
            @DisplayName("Then the program should be throw exception")
            @Test
            void testTheCheapestCourierShouldThrowException() {
                Order order = new Order("13:00", true, 5.0);
                Order order1 = new Order("09:30", false, 4.0);
                Order order2 = new Order("15:00", true, 4.5);
                assertAll("Verify conditions for time of order",
                        () -> assertThrows(Exception.class, () -> selectCheapestCourier.getBestSuitableCourier(order)),
                        () -> assertThrows(Exception.class, () -> selectCheapestCourier.getBestSuitableCourier(order1)),
                        () -> assertThrows(Exception.class, () -> selectCheapestCourier.getBestSuitableCourier(order2)));
            }
        }

    }

}