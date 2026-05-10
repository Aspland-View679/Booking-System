package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

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
	
	@Test
	void testCVV() {
		
		
		boolean result = myPayBooking.isCVVAcceptable(0);
		boolean result2 = myPayBooking.isCVVAcceptable(100);
		boolean result3 = myPayBooking.isCVVAcceptable(9999);
		boolean result4 = myPayBooking.isCVVAcceptable(1654);
		boolean result5 = myPayBooking.isCVVAcceptable(99);
		boolean result6 = myPayBooking.isCVVAcceptable(10000);
		
		assertEquals(false, result);
		assertEquals(true, result2);
		assertEquals(true, result3);
		assertEquals(true, result4);
		assertEquals(false, result5);
		assertEquals(false, result6);
		

	}
	
	@Test
	void testExpiryDate() {
		
		LocalDate today = LocalDate.now();
		Integer month = today.getMonthValue();
		Integer year = today.getYear();
		
		boolean result = myPayBooking.isExpiryDateValid(month,year);
		boolean result2 = myPayBooking.isExpiryDateValid(month+1, year);
		boolean result3 = myPayBooking.isExpiryDateValid(month, year+1);
		boolean result4 = myPayBooking.isExpiryDateValid(month-1, year);
		boolean result5 = myPayBooking.isExpiryDateValid(month, year-1);
	
		assertEquals(true, result);
		assertEquals(false, result2);
		assertEquals(false, result3);
		assertEquals(true, result4);
		assertEquals(true, result5);
	
	}
	
}
