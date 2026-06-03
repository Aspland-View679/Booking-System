package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
	
	private Booking myBookingToCancelCanDo =  new Booking("London", "Canterbury", "11/07/2028", "12:30", 2, 3, 45.45);
	private Booking myBookingToCancelCannotDo =  new Booking("London", "Canterbury", "11/07/2006", "12:30", 2, 3, 45.45);

	Booking goodBooking;
	Booking badBooking;

	@BeforeEach
	public void insert() {

		GuestAccount guest =
				new GuestAccount();

		guest.setUserID(1);

		DataManager.setCurrentUser(guest);

		DataManager.insertBooking(
				myBookingToCancelCanDo
		);

		goodBooking =
				myBookingToCancelCanDo;

		DataManager.insertBooking(
				myBookingToCancelCannotDo
		);

		badBooking =
				myBookingToCancelCannotDo;
	}
	
	
	
	private CancelBooking cancelledBooking =
			new CancelBooking();


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

	s
	@Test
	public void isNotCancellablePastDate() {
		assertEquals(false, cancelledBooking.isCancellable("11/02/2003"));
	}
	

	@Test 
	public void cancelAndRemoveBooking() {
		assertEquals(true, cancelledBooking.mainLogic(goodBooking.getBookingID()));
	}

	@Test
	public void donTCancelOrRemoveBooking() {		
		assertEquals(false, cancelledBooking.mainLogic(badBooking.getBookingID()));
	
}

}