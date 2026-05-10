package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PayBookingTest {

	PayBooking myPayBooking = new PayBooking();
	
	/*
	 * tests card type function
	 */
	
	@Test
	void testCardType() {
		
		boolean result1 = myPayBooking.isCardAcceptable("Mastercard");
		boolean result2 = myPayBooking.isCardAcceptable("Visa");
		boolean result3 = myPayBooking.isCardAcceptable("HSBC");
		
		assertEquals(true, result1);
		assertEquals(true, result2);
		assertEquals(false, result3);
		
	}

}
