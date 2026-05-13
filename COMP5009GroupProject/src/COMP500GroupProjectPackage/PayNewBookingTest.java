package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

import org.junit.jupiter.api.Test;

class PayNewBookingTest {

	PayNewBooking myPayBooking = new PayBooking();
	
	@Test
	void acceptMastercard() {
		
		boolean result1 = myPayBooking.isCardAcceptable("Mastercard");\
		assertEquals(true, result1)
	}
	
	@Test
	void acceptVisa() {
		
		boolean result2 = myPayBooking.isCardAcceptable("Visa");
		assertEquals(true, result2);	
	}
	
	@Test
	void rejectHSBC() {
		boolean result3 = myPayBooking.isCardAcceptable("HSBC");
		assertEquals(false, result3);
	}
	
	@Test
	void rejectCVV0() {
		
		boolean result = myPayBooking.isCVVAcceptable(0);
		assertEquals(false, result);
	}
	
	@Test
	void acceptCVV100() {
		boolean result2 = myPayBooking.isCVVAcceptable(100);
		assertEquals(true, result2);
	}
	
	@Test
	void acceptCVV9999() {
		boolean result3 = myPayBooking.isCVVAcceptable(9999);
		assertEquals(true, result3);
	}
	
	@Test
	void acceptCVV1654() {
		boolean result4 = myPayBooking.isCVVAcceptable(1654);
		assertEquals(true, result4);
	}
	@Test
	void rejectCVV99() {
		boolean result5 = myPayBooking.isCVVAcceptable(99);
		assertEquals(false, result5);
	
	}
	
	@Test
	void rejectCVV10000() {
		boolean result6 = myPayBooking.isCVVAcceptable(10000);
		assertEquals(false, result6);
	}

	LocalDate today = LocalDate.now();
	Integer month = today.getMonthValue();
	Integer year = today.getYear();
	
	@Test
	void acceptCurrentDateAsExpiryDate() {
		
		
		boolean result = myPayBooking.isExpiryDateValid(month,year);
		assertEquals(true, result);
	}
	
	@Test
	void acceptMonthFutureExpiryDate() {
		boolean result2 = myPayBooking.isExpiryDateValid(month+1, year);
		assertEquals(true, result2);
	}

	@Test
	void acceptYearFutureExpiryDate() {
		boolean result3 = myPayBooking.isExpiryDateValid(month, year+1);
		assertEquals(false, result3);
	}
	
	@Test
	void rejectMonthPastExpiryDate() {
		boolean result4 = myPayBooking.isExpiryDateValid(month-1, year);
		assertEquals(true, result4);
	}
	
	@Test
	void rejectYearPastExpiryDate() {
		boolean result5 = myPayBooking.isExpiryDateValid(month, year-1);
		assertEquals(true, result5);
	
	}
	
	@Test
	void acceptCardNumber16Digits() {
		boolean result = myPayBooking.isCardNumberValid(1234567891011121);
		assertEquals(true, result);
	}
	
	@Test
	void acceptCardNumber19Digits() {
		boolean result = myPayBooking.isCardNumberValid(1234567891011121674);
		assertEquals(true, result);
	}
	
	@Test
	void rejectCardNumber20Digits() {
		boolean result = myPayBooking.isCardNumberValid(12345678910111216741);
		assertEquals(true, result);
	}
	
	@Test
	void rejectCardNumber15Digits() {
		boolean result = myPayBooking.isCardNumberValid(123456789101112);
		assertEquals(true, result);
	}
	
	@Test
	void bookingFeeTestUnder100kmBusy() {
		int result = myPayBooking.setBookingFee(14, 99.4, 2);
		assertEquals(16, result)
	}
	
	@Test
	void bookingFeeTestUnder100kmQuiet() {
		int result = myPayBooking.setBookingFee(18, 99.4, 2);
		assertEquals(11, result)
	}

	@Test
	void bookingFeeTestOver100kmBusy() {
		int result = myPayBooking.setBookingFee(14, 310, 2);
		assertEquals(26.5 result)
	}
}
