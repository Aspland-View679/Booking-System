package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AmendBookingTest {

	@Test
	public void testAmendBookingUpdatesAirport() {

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
				booking.getAirport()
				);
	}

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
				booking.getLuggageAmount()
				);
	}
}