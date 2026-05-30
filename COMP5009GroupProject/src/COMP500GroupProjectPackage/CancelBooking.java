package COMP500GroupProjectPackage;

import java.time.LocalDate;
import java.util.Date;

public class CancelBooking {
	
	Booking bookingToCancel;
	
	public CancelBooking(Booking bookingtocancel) {
		this.bookingToCancel = bookingtocancel;
	}
	

	/**
	 * isCancellable - returns whether this booking can be cancelled at all
	 * @param dateOfBooking - the date of booking to check against of type dd/mm/yyyy
	 * @return true if can be cancelled or false if not cancellable
	 */
	
	public boolean isCancellable(String dateOfBooking) {

		LocalDate today = LocalDate.now();

		Integer dateOfMonth = today.getDayOfMonth();
		Integer month = today.getMonthValue();
		Integer year = today.getYear();

		String[] splitDate = dateOfBooking.split("/");

		Integer bookingDate =
				Integer.parseInt(splitDate[0]);

		Integer bookingMonth =
				Integer.parseInt(splitDate[1]);

		Integer bookingYear =
				Integer.parseInt(splitDate[2]);

		if (((dateOfMonth - bookingDate) <= 1)
				&& (bookingMonth == month)
				&& (bookingYear == year)) {

			return false;

		} else {

			return true;
		}
	}
	
	/**
	* changeDuration - reduces duration for other user bookings due to this cancellation
	* @param otherUsersBookings - a list of bookings for us to change duration as they are sharing the car
	* @param cancelledBooking - the booking we are cancelling
	* @return the amended other booking with changed durations
	*/

	public Booking[] changeDuration(
	        Booking[] otherUsersBookings,
	        Booking cancelledBooking
	) {

	    Booking[] amendedBookings =
	            otherUsersBookings;

	    for (int i = 0;
	         i < amendedBookings.length;
	         i++) {

	        amendedBookings[i].setEstimatedDuration(
	                amendedBookings[i].getEstimatedDuration() - 10
	        );
	    }

	    return amendedBookings;
	
	}
}
	