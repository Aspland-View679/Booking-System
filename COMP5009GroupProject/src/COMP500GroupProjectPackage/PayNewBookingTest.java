package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

public class PayNewBookingTest {
	private PayNewBooking myPayBooking = new PayNewBooking(null);
	private LocalDate today = LocalDate.now();
	private Integer month = today.getMonthValue();
	private Integer year = today.getYear();
	JFrame payFrame = new JFrame();
	String[] types = {"Visa", "Mastercard"};
	JComboBox<String> cardType = new JComboBox(types);
	JTextField cardNumber = new JTextField();
	JTextField billAdd = new JTextField();
	JTextField expiryMonthInput = new JTextField();
	JTextField expiryYearInput = new JTextField();
	JTextField cvv = new JTextField();
	Booking booking = new Booking("London", "Canterbury", "11/07/2006", "20:30", 5, 120, 5.55);
	JTextField name = new JTextField();
	
	@Test
	void rejectMainLogic() {
		cardNumber.setText("01234567899012748373273737373");
		cvv.setText("27272");
		expiryMonthInput.setText("01");
		expiryYearInput.setText("2024");
		name.setText("Oliver");
		billAdd.setText("UOK");
		assertEquals(false, myPayBooking.mainLogic(
									   payFrame,
									   cardType,
									   cardNumber,
									   name,
									   expiryMonthInput,
									   expiryYearInput,
									   cvv,
									   booking,
									   billAdd
											));
		
	}
	
	
	@Test
	void acceptMainLogic() {
		cardNumber.setText("01234567899012748");
		cvv.setText("272");
		expiryMonthInput.setText("01");
		billAdd.setText("UOK");
		expiryYearInput.setText("2028");
		name.setText("Oliver");
		assertEquals(true, myPayBooking.mainLogic(
									   payFrame,
									   cardType,
									   cardNumber,
									   name,
									   expiryMonthInput,
									   expiryYearInput,
									   cvv,
									   booking,
									   billAdd
											));
		
	}
	
	@Test 
	void addPayment() throws SQLException {
		DataManager.connect();
		Booking booking = new Booking("London", "UoK", "2027-04-03", "19:03", 3, 4, 4.32);
		cardNumber.setText("01234567899012748");
		cvv.setText("272");
		expiryMonthInput.setText("01");
		billAdd.setText("UOK");
		expiryYearInput.setText("2028");
		name.setText("Oliver");
		DataManager.insertBooking(booking);
		DataManager.updatePayment(booking.getBookingID(), "Visa", 
				cardNumber.getText(), 
				billAdd.getText(), 0);
		ResultSet rs =
				DataManager.getBooking(
						booking.getBookingID()
				);

		assertEquals(
				"Visa",
				rs.getString("paymentType")
		);
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
	void bookingFeeTestUnder60minsBusy() {
		myPayBooking.setBookingFee(14, 2, 45);
		boolean result1 = (myPayBooking.getBookingFee() == 16);
		assertEquals(true, result1);
	}
	
	@Test
	void bookingFeeTestUnder60minsQuiet() {
		myPayBooking.setBookingFee(18, 3, 45);
		boolean result = (myPayBooking.getBookingFee() == 11.5);
		assertEquals(true, result);
	}

	@Test
	void bookingFeeTestOver60minsBusy() {
		myPayBooking.setBookingFee(14, 4, 70);
		assertEquals(17.0, myPayBooking.getBookingFee());
	}
	
	@Test
	void encryptCardNumber() {
		myPayBooking.setCardNumber("0123456789101234");
		myPayBooking.setStarredOutCardNumber();
		assertEquals("************1234", myPayBooking.getEncryptedCardNumber());
	}
	
	@Test
	void encryptCardNumber2() {
		myPayBooking.setCardNumber("01234567891012345");
		myPayBooking.setStarredOutCardNumber();
		assertEquals("*************2345", myPayBooking.getEncryptedCardNumber());
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

