package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AmendBookingTest {
	
	/**
	 * Tests the amend booking functionality.
	 * 
	 * These tests check that booking details
	 * can successfully be updated using setters.
	 * 
	 * @author COMP5009 Group Project
	 * @version 1.0
	 */

	@Test
	public void testAmendBookingUpdatesAirport() {
		
		/**
		 * Tests that the airport can be updated correctly.
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

		booking.setAirport("Manchester Airport - UK");

		assertEquals(
				"Manchester Airport - UK",
				booking.getAirport()   //updates the airport and checks it
				);
	}

	/**
	 * Tests that luggage amount updates correctly.
	 */

	
	@Test
	public void testAmendBookingUpdatesLuggage() {
		

		Booking booking = new Booking(
				"London Heathrow - UK",
				"Ashford",
				"20/05/2026",
				"14:00",
				2,
				90,
				120.0
				);

		booking.setLuggageAmount(5);

		assertEquals(
				5,
				booking.getLuggageAmount() //Updates the luggage amount and see if it updates it properly
				);
	}
}