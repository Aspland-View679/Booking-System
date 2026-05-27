package COMP500GroupProjectPackage;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class CancelBookingTest {


	Booking myBookingToCancel;
	CancelBooking cancelledBooking;
	
	
	LocalDate today = LocalDate.now();
	
	Integer dayToday = today.getDayOfMonth(); 
    Integer month = today.getMonthValue();
	Integer year = today.getYear();
	
	String todayFormattedWith0 = ("O" + String.valueOf(dayToday) + "/0" + String.valueOf(month) + "/" + String.valueOf(year));
	String todayFormattedWithout0 = (String.valueOf(dayToday) + "/" + String.valueOf(month) + "/" + String.valueOf(year));	
	
	@Test
	public void isNotCancellableTodayWith0(){
		assertEquals(false, cancelledBooking.isCancellable(todayFormattedWith0));
		
	}
	
	@Test
	public void isNotCancellableTodayWithout0() {
		assertEquals(false, cancelledBooking.isCancellable(todayFormattedWithout0));

	}
	
}