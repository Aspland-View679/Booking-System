package COMP500GroupProjectPackage;

import java.util.Date;

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
		Integer bookingDate, bookingMonth, bookingYear;
		
		
		if (dateOfBooking.startsWith("0")) {	//checking for 0x/mm/yyyy
			dateOfBooking.replaceFirst("0", ""); 		
			if (dateOfBooking.charAt(2) == '0') { //checks now for month being dd/0x/yyyy
				dateOfBooking.substring(2, 2).replace("0", "");
				bookingMonth = Integer.parseInt(dateOfBooking.substring(2,2));
				bookingYear = Integer.parseInt(dateOfBooking.substring(4, 7));
			}
			bookingDate = Integer.parseInt(dateOfBooking.substring(0, 0));
			bookingMonth = Integer.parseInt(dateOfBooking.substring(2,3));
			bookingYear = Integer.parseInt(dateOfBooking.substring(5, 8));
		   } else {
		 	if (dateOfBooking.charAt(3) == '0') {		//checking for just month being 0x
				dateOfBooking.substring(3, 3).replace("0", "");
				bookingMonth = Integer.parseInt(dateOfBooking.substring(3,3));
				bookingYear = Integer.parseInt(dateOfBooking.substring(5, 8));
			}
		 	bookingDate = Integer.parseInt(dateOfBooking.substring(0, 1));
		 	bookingMonth = Integer.parseInt(dateOfBooking.substring(3,4));
		 	bookingYear = Integer.parseInt(dateOfBooking.substring(6, 9));
		}
			
		if (((dateOfMonth - bookingDate) <= 1) && (bookingMonth == month) && (bookingYear == year)) { //can't be 24hrs (1 day) before booking
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
	
	public Booking[] changeDuration(Booking otherUsersBookings, Booking cancelledBooking) {
		Booking[] amendedBookings = otherUsersBookings; //cloning for the change
		for (int i = 0; i <= amendedBookings.length(); i++) {
			//if amendedBookings[i].id == cancalledbooking.id then
			amendedBookings[i].setEstimatedDuration(amendedBookings[i].getEstimatedDuration() - 10); //reduces time by 10mins
		}
		return amendedBookings;
	}