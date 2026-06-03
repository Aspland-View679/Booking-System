package COMP500GroupProjectPackage;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;

public class CancelBooking extends JFrame {

	private Booking bookingToCancel;

	/**
	 * getBookingToCancel
	 * @returns the booking needing to be cancelled
	 */

	public Booking getBookingToCancel() {

		return bookingToCancel;
	}


	public static void main(String[] args) {

		new CancelBooking();
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

		try {

			FlatLightLaf.setup();

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		setTitle("Cancel Booking");

		setSize(900, 650);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());


		//Main background panel

		JPanel backgroundPanel =
				new JPanel(new BorderLayout());

		backgroundPanel.setBackground(
				new Color(245, 245, 245)
		);


		//Heading section

		JPanel headingPanel =
				new JPanel();

		headingPanel.setBackground(
				new Color(245, 245, 245)
		);

		headingPanel.setBorder(
				new EmptyBorder(30, 40, 10, 40)
		);

		headingPanel.setLayout(
				new BoxLayout(
						headingPanel,
						BoxLayout.Y_AXIS
				)
		);


		JLabel titleLabel =
				new JLabel("Cancel Booking");


		ImageIcon cancelIcon =
				new ImageIcon(
						"src/icons/CancelBooking.png"
				);

		Image cancelImage =
				cancelIcon.getImage().getScaledInstance(
						55,
						55,
						Image.SCALE_SMOOTH
				);

		titleLabel.setIcon(
				new ImageIcon(cancelImage)
		);

		titleLabel.setIconTextGap(15);


		titleLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						34
				)
		);

		titleLabel.setForeground(
				new Color(30, 30, 30)
		);


		JLabel subtitle =
				new JLabel(
						"Search and cancel customer bookings"
				);

		subtitle.setFont(
				new Font(
						"SansSerif",
						Font.PLAIN,
						16
				)
		);

		subtitle.setForeground(
				new Color(90, 90, 90)
		);


		headingPanel.add(titleLabel);

		headingPanel.add(
				Box.createRigidArea(
						new Dimension(0, 8)
				)
		);

		headingPanel.add(subtitle);


		//Main cancellation card

		JPanel cancelCard =
				new JPanel();

		cancelCard.setLayout(
				new GridBagLayout()
		);

		cancelCard.setBackground(Color.WHITE);

		cancelCard.setBorder(
				BorderFactory.createCompoundBorder(

						BorderFactory.createLineBorder(
								new Color(220, 220, 220),
								1,
								true
						),

						new EmptyBorder(35, 35, 35, 35)
				)
		);


		JPanel cardWrapper =
				new JPanel(new BorderLayout());

		cardWrapper.setBackground(
				new Color(245, 245, 245)
		);

		cardWrapper.setBorder(
				new EmptyBorder(10, 40, 40, 40)
		);

		cardWrapper.add(cancelCard);


		GridBagConstraints gbc =
				new GridBagConstraints();

		gbc.insets =
				new Insets(15, 15, 15, 15);

		gbc.fill =
				GridBagConstraints.HORIZONTAL;


		//Search booking section

		JLabel searchLabel =
				new JLabel("Search Booking");

		searchLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JTextField searchField =
				new JTextField();

		searchField.setPreferredSize(
				new Dimension(350, 40)
		);


		JButton searchButton =
				new JButton("Search");

		searchButton.setFocusPainted(false);

		searchButton.setBorderPainted(false);

		searchButton.setBackground(
				new Color(155, 109, 255)
		);

		searchButton.setForeground(Color.WHITE);

		searchButton.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						14
				)
		);


		//Booking dropdown section

		JLabel bookingLabel =
				new JLabel("Select Booking");

		bookingLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JComboBox<String> bookingBox =
				new JComboBox<>();

		//This is going to be populated with all of booking ids

		bookingBox.setPreferredSize(
				new Dimension(350, 40)
		);


		JLabel warn =
				new JLabel();

		warn.setText(
				"You cannot cancel 24hrs before travel"
		);

		warn.setForeground(
				new Color(200, 70, 70)
		);

		warn.setFont(
				new Font(
						"SansSerif",
						Font.PLAIN,
						14
				)
		);


		JButton cancelBtn =
				new JButton("Cancel Booking");

		cancelBtn.setPreferredSize(
				new Dimension(220, 50)
		);

		cancelBtn.setFocusPainted(false);

		cancelBtn.setBorderPainted(false);

		cancelBtn.setBackground(
				new Color(220, 70, 70)
		);

		cancelBtn.setForeground(Color.WHITE);

		cancelBtn.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JButton homeButton =
				new JButton("Return Home");

		homeButton.setPreferredSize(
				new Dimension(180, 50)
		);

		homeButton.setFocusPainted(false);

		homeButton.setBorderPainted(false);

		homeButton.setBackground(
				new Color(230, 230, 230)
		);

		homeButton.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		//Adds components to card

		gbc.gridx = 0;
		gbc.gridy = 0;

		cancelCard.add(searchLabel, gbc);


		gbc.gridx = 1;

		cancelCard.add(searchField, gbc);


		gbc.gridx = 2;

		cancelCard.add(searchButton, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		cancelCard.add(bookingLabel, gbc);


		gbc.gridx = 1;
		gbc.gridwidth = 2;

		cancelCard.add(bookingBox, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		gbc.gridwidth = 3;

		cancelCard.add(warn, gbc);


		JPanel buttonPanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.CENTER,
								20,
								10
						)
				);

		buttonPanel.setBackground(Color.WHITE);

		buttonPanel.add(cancelBtn);

		buttonPanel.add(homeButton);


		gbc.gridx = 0;
		gbc.gridy++;

		cancelCard.add(buttonPanel, gbc);


		//Adds everything to frame

		backgroundPanel.add(
				headingPanel,
				BorderLayout.NORTH
		);

		backgroundPanel.add(
				cardWrapper,
				BorderLayout.CENTER
		);

		add(backgroundPanel);


		//Search logic

		searchButton.addActionListener(e -> {

			try {

				bookingBox.removeAllItems();

				String search =
						searchField.getText().trim();

				String sql =

						"SELECT * FROM bookings "

						+ "WHERE "

						+ "pickupLocation LIKE ? "

						+ "OR bookingID LIKE ?";


				Connection conn =
						DataManager.connect();

				PreparedStatement pstmt =
						conn.prepareStatement(sql);

				pstmt.setString(
						1,
						"%" + search + "%"
				);
				
				search =
				        search.replace(
				                "ABS-",
				                ""
				        );

				pstmt.setString(
						2,
						"%" + search + "%"
				);


				ResultSet rs =
						pstmt.executeQuery();


				while (rs.next()) {

					String bookingDisplay =

							"ABS-"
							+ rs.getInt("bookingID")
							+ " | "
							+ rs.getString("pickupLocation")
							+ " -> "
							+ rs.getString("airport");


					bookingBox.addItem(
							bookingDisplay
					);
				}

			}

			catch (SQLException ex) {

				ex.printStackTrace();
			}
		});


		cancelBtn.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e
			) {

				// TODO Auto-generated method stub

				try {

					String selected =
							(String) bookingBox.getSelectedItem();

					if (selected == null) {

						JOptionPane.showMessageDialog(
								null,
								"No booking selected"
						);

						return;
					}


					String bookingIDText =

							selected.substring(
									4,
									selected.indexOf("|")
							).trim();


					int bookingID =
							Integer.parseInt(bookingIDText);


					if (mainLogic(bookingID)) {

					    JOptionPane.showMessageDialog(
					            null,
					            "Booking cancelled successfully!"
					    );

					    bookingBox.removeItem(selected);

					    searchField.setText("");

					    if (bookingBox.getItemCount() == 0) {

					        searchButton.doClick();
					    }
					

						setVisible(false);

						new Home();
					}

					else {

						JOptionPane.showMessageDialog(
								null,
								"Booking could not be cancelled."
						);
					}

				}

				catch (Exception ex) {

					ex.printStackTrace();
				}
			}
		});


		homeButton.addActionListener(e -> {

			setVisible(false);

			new Home();

		});


		setVisible(true);
	}


	public boolean mainLogic(
			int bookingID
	) {

		try {

			Connection conn =
			        DataManager.connect();

			PreparedStatement pstmt =
			        conn.prepareStatement(
			                "SELECT * FROM bookings WHERE bookingID = ?"
			        );

			pstmt.setInt(1, bookingID);

			ResultSet bookingFound =
			        pstmt.executeQuery();


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
			
			bookingFound.close();

			pstmt.close();

			conn.close();


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


        if ((bookingYear >= year) && (bookingMonth >= month)) {
            return true;
        } else {
            if ((bookingYear == year) && (bookingMonth == month) && (bookingDate-dateOfMonth)>=1) {
                return true;
            }
            return false;
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