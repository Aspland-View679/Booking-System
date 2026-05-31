package COMP500GroupProjectPackage;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CancelBooking {
	
	private Booking bookingToCancel;
	
	/**
	 * getBookingToCancel
	 * @returns the booking needing to be cancelled
	 */
	
	public Booking getBookingToCancel() {
		return bookingToCancel;
	}

	/**
	 * setBookingToCancel - sets the booking which needs to be cancelled
	 * @param - bookingToCancel - the booking needing to be cancelled
	 */

	
	public void setBookingToCancel(Booking bookingToCancel) {
		this.bookingToCancel = bookingToCancel;
	}
	public CancelBooking() {
		
		JFrame mainCancelFrame = new JFrame();
		mainCancelFrame.setSize(500, 500);
		
		Connection myConn = DataManager.connect();
		
		String sql = "SELECT bookingID FROM bookings";
		
		PreparedStatement prep = myConn.prepareStatement(sql);
		
		ResultSet values = prep.executeQuery();
		
		Integer[] ids = {};
		Integer i = 0;
		while (values.hasNext()) {
			ids[i] = (values.getInt("bookingID"));
			i++;
		}
		
		
		JComboBox<Integer> bookingID = new JComboBox(ids); //This is going to be populated with all of booking ids
		bookingID.setBounds(250,250,100,30);
		mainCancelFrame.add(bookingID);
	
		
		mainCancelFrame.setVisible(true);
		
		JButton cancelBtn = new JButton();
		cancelBtn.setBounds(250,290,100,30);
		cancelBtn.setText("Cancel");
		mainCancelFrame.add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
			if (mainLogic((int) bookingID.getSelectedItem())) {
				mainCancelFrame.setVisible(false);
				JOptionPane.showMessageDialog(null, "Cancelled!");
			} else {
				mainCancelFrame.setVisible(false);
			}
			
			}		
		});
	}
	
	public boolean mainLogic(int bookingID) {
		
		ResultSet bookingFound = DataManager.getBooking(bookingID);
		setBookingToCancel(new Booking(bookingFound.getString("airport"), 
										bookingFound.getString("pickupLocation", 
										bookingFound.getString("bookingDate"), 
									    bookingFound.getString("bookingTime"),
									    bookingFound.getString("luggageAmount"), 
									    bookingFound.getInt("estimatedDuration"), 
									    bookingFound.getDouble("totalPrice"))));
		String date = bookingFound.getString("bookingDate");
		String airport = bookingFound.getString("airport");
	
	
		if (isCancellable(date)) {
				DataManager.cancelBooking(bookingID);
				changeDuration(date, airport);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Not cancellable!");
				return false;
			}
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

		//The below 2 if statements make the month and year x instead of 0x in line with month and year not starting with 0
		
		if (bookingDate.toString().startsWith("0")) {
			bookingDate = Integer.parseInt(bookingDate.toString().replaceFirst("0", ""));
		}

		
		if (bookingMonth.toString().startsWith("0")) {
			bookingMonth = Integer.parseInt(bookingMonth.toString().replaceFirst("0", ""));
		}


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
	* @param date - the date of the booking
	* @param airport - the destination airport 
	*/
	
	public void changeDuration(String date, String airport) {
		Connection con = DataManager.connect();
		String sql = "SELECT * FROM bookings WHERE bookingDate = ? && airport = ?";
		PreparedStatement pstms = con.prepareStatement(sql);
		pstms.setString(1, date);
		pstms.setString(2, airport);
		ResultSet results = pstms.executeQuery(sql);
		Booking[] bookings;
		int i = 0;
		while (results.hasNext()) {
			Booking bAdd = new Booking(rs.getString("airport"), 
										rs.getString("pickupLocation", 
										rs.getString("bookingDate"), 
									    rs.getString("bookingTime"),
									    rs.getString("luggageAmount"), 
									    rs.getInt("estimatedDuration"), 
									    rs.getDouble("totalPrice"));
			bAdd,setBookingID(rs.getString("bookingID"));
			bookings[i] = bAdd;
			i++;
			
		}
		for (int ind = 0; ind <= bookings.length; ind++) {
			bookings[ind].setEstimatedDuration(bookings[ind].getEstimatedDuration() - 10); //reduces time by 10mins
			DataManager.updateBooking(bookings[ind].getBookingID(), 
									  bookings[ind].getAirport(), 
									  bookings[ind].getPickupLocation(), 
									  bookings[ind].getBookingDate(), 
									  bookings[ind].getBookingTime(), 
									  bookings[ind].getLuggageAmount(), 
									  bookings[ind].getEstimatedDuration(), 
									  bookings[ind].getTotalPrice());
		}
	}
	
	
}
	