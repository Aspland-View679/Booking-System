package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;

import org.junit.jupiter.api.Test;

class PayNewBookingTest {

	PayNewBooking myPayBooking = new PayNewBooking(null);
	
	
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
		assertEquals(false, result);
	}
	
	@Test
	void acceptMonthFutureExpiryDate() {
		boolean result2 = myPayBooking.isExpiryDateValid(month+1, year);
		assertEquals(true, result2);
	}

	@Test
	void acceptYearFutureExpiryDate() {
		boolean result3 = myPayBooking.isExpiryDateValid(month, year+1);
		assertEquals(true, result3);
	}
	
	@Test
	void rejectMonthPastExpiryDate() {
		boolean result4 = myPayBooking.isExpiryDateValid(month-1, year);
		assertEquals(false, result4);
	}
	
	@Test
	void rejectYearPastExpiryDate() {
		boolean result5 = myPayBooking.isExpiryDateValid(month, year-1);
		assertEquals(false, result5);
	
	}
	
	@Test
	void acceptCardNumber16Digits() {
		boolean result = myPayBooking.isCardNumberValid(1234567891011121L);
		assertEquals(true, result);
	}
	
	@Test
	void acceptCardNumber19Digits() {
		myPayBooking.setCardNumber("0123456789010123456");
		boolean result = myPayBooking.isCardNumberValid(myPayBooking.getCardNumber());
		assertEquals(true, result);
	}
	
	//Longs cannot be more than 19 digits so we cannot test a card number longer than 19 digits
	
	@Test
	void rejectCardNumber15Digits() {
		boolean result = myPayBooking.isCardNumberValid(123456789101112L);
		assertEquals(false, result);
	}
	
	@Test
	void bookingFeeTestUnder100kmBusy() {
		myPayBooking.setBookingFee(14, 99.4, 2);
		boolean result1 = (myPayBooking.getBookingFee() == 16);
		assertEquals(true, result1);
	}
	
	@Test
	void bookingFeeTestUnder100kmQuiet() {
		myPayBooking.setBookingFee(18, 99.4, 2);
		boolean result = (myPayBooking.getBookingFee() == 11);
		assertEquals(true, result);
	}

	@Test
	void bookingFeeTestOver100kmBusy() {
		myPayBooking.setBookingFee(14, 310, 2);
		assertEquals(26, myPayBooking.getBookingFee());
	}
	
	@Test
	void encryptCardNumber() {
		myPayBooking.setCardNumber("0123456789101234");
		myPayBooking.setEncryptedCardNumber();
		assertEquals("*********1234", myPayBooking.getEncryptedCardNumber());
	}
	
	@Test
	void encryptCardNumber2() {
		myPayBooking.setCardNumber("01234567891012345");
		myPayBooking.setEncryptedCardNumber();
		assertEquals("*********2345", myPayBooking.getEncryptedCardNumber());
	}
	
	@Test
	void checkMonthFormatVal7() {
		assertEquals("7", myPayBooking.formatMonth("07"));
	}
	
	@Test
	void checkMonthFormatVal10() {
		assertEquals("10", myPayBooking.formatMonth("10"));
	}
	
	@Test
	void checkMonthFormatSingle() {
		assertEquals("8", myPayBooking.formatMonth("8"));
	}
	
}
