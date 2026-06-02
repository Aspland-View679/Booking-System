
package COMP500GroupProjectPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	public static void main(String[] args) {
		CancelBooking c = new CancelBooking();
	}
	
	/**
	 * setBookingToCancel - sets the booking which needs to be cancelled
	 * @param - bookingToCancel - the booking needing to be cancelled
	 */

	public void setBookingToCancel(
			Booking bookingToCancel
	) {

		this.bookingToCancel = bookingToCancel;
	}

	public CancelBooking() {

		JFrame mainCancelFrame =
				new JFrame();

		mainCancelFrame.setSize(500, 500);

		mainCancelFrame.setLayout(null);

		try {

			String sql =
					"SELECT bookingID FROM bookings";

			Connection myConn =
					DataManager.connect();

			PreparedStatement pst =
					myConn.prepareStatement(sql);

			ResultSet values =
					pst.executeQuery();


			ArrayList<Integer> ids =
					new ArrayList<>();


			while (values.next()) {

				ids.add(
						values.getInt("bookingID")
				);
			}


			Integer[] bookingIds =
					ids.toArray(new Integer[0]);


			JComboBox<Integer> bookingID =
					new JComboBox<>(bookingIds);

			//This is going to be populated with all of booking ids

			bookingID.setBounds(
					180,
					180,
					150,
					30
			);

			mainCancelFrame.add(bookingID);


			JLabel label =
					new JLabel();

			label.setBounds(
					180,
					150,
					150,
					30
			);

			label.setText("Cancel Booking ID");

			mainCancelFrame.add(label);


			JButton cancelBtn =
					new JButton();

			cancelBtn.setBounds(
					180,
					240,
					150,
					40
			);

			cancelBtn.setText("Cancel Booking");

			mainCancelFrame.add(cancelBtn);


			JLabel warn =
					new JLabel();

			warn.setBounds(
					110,
					300,
					300,
					30
			);

			warn.setText(
					"You cannot cancel 24hrs before travel"
			);

			mainCancelFrame.add(warn);


			cancelBtn.addActionListener(
					new ActionListener() {

				@Override
				public void actionPerformed(
						ActionEvent e
				) {

					// TODO Auto-generated method stub

					if (
							mainLogic(
									(int) bookingID.getSelectedItem()
							)
					) {

						mainCancelFrame.setVisible(false);

						JOptionPane.showMessageDialog(
								null,
								"Cancelled!"
						);
					}

					else {

						JOptionPane.showMessageDialog(
								null,
								"Booking could not be cancelled."
						);
					}
				}
			});


			mainCancelFrame.setVisible(true);

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public boolean mainLogic(
			int bookingID
	) {

		try {

			ResultSet bookingFound =
					DataManager.getBooking(bookingID);


			if (!bookingFound.next()) {

				return false;
			}


			Booking booking =
	                 new Booking(

	                         bookingFound.getString("airport"),
	                         bookingFound.getString("pickupLocation"),
	                         bookingFound.getString("bookingDate"),
	                         bookingFound.getString("bookingTime"),
	                         bookingFound.getInt("luggageAmount"),
	                         bookingFound.getInt("estimatedDuration"),
	                         bookingFound.getDouble("totalPrice")
	                 );


			setBookingToCancel(booking);


			String date =
					booking.getBookingDate();

			String airport =
					booking.getAirport();


			if (isCancellable(date)) {

				DataManager.cancelBooking(bookingID);

				changeDuration(date, airport);

				return true;
			}

			else {

				JOptionPane.showMessageDialog(
						null,
						"Not cancellable!"
				);

				return false;
			}
		}

		catch (SQLException e) {

			e.printStackTrace();

			return false;
		}
	}


	/**
	 * isCancellable - returns whether this booking can be cancelled at all
	 * @param dateOfBooking - the date of booking to check against of type dd/mm/yyyy
	 * @return true if can be cancelled or false if not cancellable
	 */

	public boolean isCancellable(
			String dateOfBooking
	) {

		LocalDate today =
				LocalDate.now();

		Integer dateOfMonth =
				today.getDayOfMonth();

		Integer month =
				today.getMonthValue();

		Integer year =
				today.getYear();

		String[] splitDate =
				dateOfBooking.split("/");

		Integer bookingDate =
				Integer.parseInt(splitDate[0]);


		Integer bookingMonth =
				Integer.parseInt(splitDate[1]);


		//The below 2 if statements make the month and year x instead of 0x in line with month and year not starting with 0

		if (
				bookingDate.toString().startsWith("0")
		) {

			bookingDate =
					Integer.parseInt(
							bookingDate.toString()
							.replaceFirst("0", "")
					);
		}


		if (
				bookingMonth.toString().startsWith("0")
		) {

			bookingMonth =
					Integer.parseInt(
							bookingMonth.toString()
							.replaceFirst("0", "")
					);
		}


		Integer bookingYear =
				Integer.parseInt(splitDate[2]);


		if (
				((bookingDate - dateOfMonth) <= 1)
				&& (bookingMonth == month)
				&& (bookingYear == year)
		) {

			return false;
		}

		else {

			return true;
		}
	}


	/**
	* changeDuration - reduces duration for other user bookings due to this cancellation
	* @param date - the date of the booking
	* @param airport - the destination airport
	*/

	public void changeDuration(
			String date,
			String airport
	) {

		try {

			Connection con =
					DataManager.connect();

			String sql =
					"SELECT * FROM bookings WHERE bookingDate = ? AND airport = ?";

			PreparedStatement pstms =
					con.prepareStatement(sql);

			pstms.setString(1, date);

			pstms.setString(2, airport);


			ResultSet results =
					pstms.executeQuery();


			ArrayList<Booking> bookings =
					new ArrayList<>();


			while (results.next()) {

				Booking bAdd =
						new Booking(

								results.getString("airport"),
								results.getString("pickupLocation"),
								results.getString("bookingDate"),
								results.getString("bookingTime"),
								results.getInt("luggageAmount"),
								results.getInt("estimatedDuration"),
								results.getDouble("totalPrice")
						);


				bAdd.setBookingID(
						Integer.parseInt(
								results.getString("bookingID")
						)
				);

				bookings.add(bAdd);
			}


			for (Booking booking : bookings) {

				booking.setEstimatedDuration(
						booking.getEstimatedDuration() - 10
				);

				//reduces time by 10mins

				DataManager.updateBooking(

							  booking.getBookingID(),
							  booking.getAirport(),
							  booking.getPickupLocation(),
							  booking.getBookingDate(),
							  booking.getBookingTime(),
							  booking.getLuggageAmount(),
							  booking.getEstimatedDuration(),
							  booking.getTotalPrice()
				);
			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}
}

