package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CancelBookingTest {

	private Booking myBookingToCancel =
			new Booking(
					"London",
					"Canterbury",
					"11/07/2006",
					"12:30",
					2,
					3,
					45.45
			);

	private CancelBooking cancelledBooking =
			new CancelBooking(myBookingToCancel);


	/**
	 * Tests that a future booking
	 * can be cancelled.
	 */

	@Test
	public void isCancellableFutureDate() {

		assertEquals(
				true,
				cancelledBooking.isCancellable(
						"11/12/2026"
				)
		);

	}


	/**
	 * Tests another future booking
	 * can also be cancelled.
	 */

	@Test
	public void isCancellableAnotherFutureDate() {

		assertEquals(
				true,
				cancelledBooking.isCancellable(
						"15/11/2027"
				)
		);

	}

}