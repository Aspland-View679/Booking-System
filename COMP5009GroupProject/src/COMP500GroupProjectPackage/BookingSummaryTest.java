package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests booking summary data.
 * 
 * These tests verify booking information
 * is stored and returned correctly.
 * 
 * @author COMP5009 Group Project
 * @version 1.0
 */

public class BookingSummaryTest {

    @Test
    public void testBookingSummaryDisplaysCorrectPrice() {
    	
    	/**
    	 * Tests total booking price retrieval.
    	 */

        Booking booking = new Booking(
                "London Heathrow - UK",
                "Ashford",
                "20/05/2026",
                "14:00",
                2,
                90,
                120.0
        );


		// Checks booking total price
        
        assertEquals(
                120.0,
                booking.getTotalPrice()
        );
    }
}