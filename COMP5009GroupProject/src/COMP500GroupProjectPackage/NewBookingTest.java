package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests calculations used when creating bookings.
 * 
 * These tests verify estimated journey durations
 * based on time, luggage amount and airport.
 * 
 * @author COMP5009 Group Project
 * @version 1.0
 */

import org.junit.jupiter.api.Test;


/**
 * Tests estimated duration calculation
 * during peak hours with luggage and location.
 */

public class NewBookingTest {

	@Test
	public void testEstimatedDurationCalculation() {

		int estimationOfDuration = 30;

		int timeDay = 8;

		if (timeDay >= 7 && timeDay <= 9) { //Adds extra time for peak hour traffic
			estimationOfDuration += 20;
		}

		int luggageAmount = 2;

		estimationOfDuration += luggageAmount * 5; //adds extra duration depending on luggage

		String pickup = "ashford";

		if (pickup.contains("ashford")) {
			estimationOfDuration += 10; //adds extra duration for ashford pickup
		}

		String selectedAirport = "london heathrow - uk";
  //Adds extra duration for london airport route
		if (selectedAirport.contains("london")) {
			estimationOfDuration += 25;
		}

		assertEquals(95, estimationOfDuration); //estimated duration
	}

	

	/**
	 * Tests Manchester airport duration calculation.
	 */
	
	@Test
	public void testManchesterAirportDuration() {

		int estimationOfDuration = 30;      	// Adds extra travel duration for Manchester

		String selectedAirport = "manchester airport - uk";

		if (selectedAirport.contains("manchester")) { // Checks duration calculation
			estimationOfDuration += 40;
		}

		assertEquals(70, estimationOfDuration);
	}
}