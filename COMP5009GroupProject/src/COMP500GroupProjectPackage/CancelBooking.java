package COMP500GroupProjectPackage;

import java.sql.Date;
import java.time.*;

public class CancelBooking {
	
	Booking bookingToCancel;
	

	/**
	 * isCancellable - returns whether this booking can be cancelled at all
	 * @param dateOfBooking - the date of booking to check against of type dd/mm/yyyy
	 * @return true if can be cancelled or false if not cancellable
	 */
	
	public boolean isCancellable(String dateOfBooking) {
		
		Date today = new Date();
		Integer dateOfMonth = today.getDate();
		Integer month = today.getMonth(); //0 to 11 with 0 = jan
		Integer year = today.getYear();
		Integer inputDate, inputMonth, inputYear;
		
		
		if (dateOfBooking.startsWith("0") {
			dateOfBooking.replaceFirst("0", "");		
			if (dateOfBooking.charAt(2) == "0") {
				dateOfBooking.substring(2, 2).replace("0", "");
				inputMonth = Integer.parseInt(dateOfBooking.substring(2,2));
				inputYear = Integer.parseInt(dateOfBooking.substring(4, 7));
			}
			inputDate = Integer.parseInt(dateOfBooking.substring(0, 0));
			inputMonth = Integer.parseInt(dateOfBooking.substring(2,3));
			inputYear = Integer.parseInt(dateOfBooking.substring(5, 8));
		}  else {
			if (dateOfBooking.charAt(3) == "0") {
				dateOfBooking.substring(3, 3).replace("0", "");
				inputMonth = Integer.parseInt(dateOfBooking.substring(3,3));
				inputYear = Integer.parseInt(dateOfBooking.substring(5, 8));
			}
			inputDate = Integer.parseInt(dateOfBooking.substring(0, 1));
			inputMonth = Integer.parseInt(dateOfBooking.substring(3,4));
			inputYear = Integer.parseInt(dateOfBooking.substring(6, 9));
		}
		
		
		if ((inputDate < date) && (inputMonth < month) && (year < inputYear)) {
			return true;
		} else {
			return false;
		}
		
		
	}
}
