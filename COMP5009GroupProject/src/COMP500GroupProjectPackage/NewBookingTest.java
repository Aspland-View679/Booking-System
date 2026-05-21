package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NewBookingTest {

	@Test
	public void testEstimatedDurationCalculation() {

		int estimationOfDuration = 30;

		int timeDay = 8;

		if (timeDay >= 7 && timeDay <= 9) {
			estimationOfDuration += 20;
		}

		int luggageAmount = 2;

		estimationOfDuration += luggageAmount * 5;

		String pickup = "ashford";

		if (pickup.contains("ashford")) {
			estimationOfDuration += 10;
		}

		String selectedAirport = "london heathrow - uk";

		if (selectedAirport.contains("london")) {
			estimationOfDuration += 25;
		}

		assertEquals(95, estimationOfDuration);
	}

	@Test
	public void testManchesterAirportDuration() {

		int estimationOfDuration = 30;

		String selectedAirport = "manchester airport - uk";

		if (selectedAirport.contains("manchester")) {
			estimationOfDuration += 40;
		}

		assertEquals(70, estimationOfDuration);
	}
}