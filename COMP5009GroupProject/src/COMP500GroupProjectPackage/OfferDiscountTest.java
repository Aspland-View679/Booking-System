package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OfferDiscountTest {

    @Test
    public void setDiscountPercentage() {

        DataManager.setGlobalDiscount(
                15,
                7
        );

        assertEquals(
                15,
                DataManager.getGlobalDiscountPercentage()
        );
    }


    @Test
    public void setDiscountDuration() {

        DataManager.setGlobalDiscount(
                25,
                14
        );

        assertEquals(
                14,
                DataManager.getGlobalDiscountDuration()
        );
    }


    @Test
    public void overwriteDiscount() {

        DataManager.setGlobalDiscount(
                10,
                5
        );

        DataManager.setGlobalDiscount(
                30,
                12
        );

        assertEquals(
                30,
                DataManager.getGlobalDiscountPercentage()
        );
    }


    @Test
    public void overwriteDuration() {

        DataManager.setGlobalDiscount(
                10,
                5
        );

        DataManager.setGlobalDiscount(
                30,
                20
        );

        assertEquals(
                20,
                DataManager.getGlobalDiscountDuration()
        );
    }


    @Test
    public void zeroDiscountCheck() {

        DataManager.setGlobalDiscount(
                0,
                10
        );

        assertEquals(
                0,
                DataManager.getGlobalDiscountPercentage()
        );
    }


    @Test
    public void negativeDurationCheck() {

        DataManager.setGlobalDiscount(
                15,
                -5
        );

        assertEquals(
                -5,
                DataManager.getGlobalDiscountDuration()
        );
    }
}