package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CancelBookingTest {


	private Booking myBookingToCancel =  new Booking("London", "Canterbury", "11/07/2006", "12:30", 2, 3, 45.45);
	private CancelBooking cancelledBooking = new CancelBooking(myBookingToCancel);
	
	
	LocalDate today = LocalDate.now();
	
	Integer dayToday = today.getDayOfMonth(); 
    Integer month = today.getMonthValue();
	Integer year = today.getYear();
	
	String todayFormattedWith0 = ("O" + dayToday.toString() + "/0" + month.toString() + "/" + year.toString());
	String todayFormattedWithout0 = (dayToday.toString() + "/" + month.toString() + "/" + year.toString());	
	
	@Test
	public void isNotCancellableTodayWith0(){
		assertEquals(false, cancelledBooking.isCancellable(todayFormattedWith0));
		
	}
	
	@Test
	public void isNotCancellableTodayWithout0() {
		assertEquals(false, cancelledBooking.isCancellable(todayFormattedWithout0));

	}
	
}