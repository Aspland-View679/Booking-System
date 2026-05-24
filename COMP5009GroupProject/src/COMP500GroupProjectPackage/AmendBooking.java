package COMP500GroupProjectPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

public class AmendBooking extends JFrame {

	JComboBox<String> airportBox;
	JSpinner luggageSpinner;
	JTextField pickupField;
	JTextField dateField;
	JTextField timeField;

	JButton updateButton;
	JButton cancelButton;

	public AmendBooking() {
		Booking booking = Booking.currentBooking;

		if (booking == null) {
			JOptionPane.showMessageDialog(
					null,
					"Please create a booking first."
					);

			new Home();
			return;
		}

		try {

			LocalDate bookingDate =
					LocalDate.parse(
							booking.getBookingDate()
							);

			long daysUntilBooking =
					ChronoUnit.DAYS.between(
							LocalDate.now(),
							bookingDate
							);

			if (daysUntilBooking < 7) {

				JOptionPane.showMessageDialog(
						null,
						"Bookings cannot be amended within 7 days of travel."
						);

				new BookingSummary(booking);

				return;
			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(
					null,
					"Date format issue detected."
					);
		}

		setTitle("Amend Booking");

		setSize(520, 500);

		setLayout(null);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JLabel airportLabel = new JLabel("Airport:");
		airportLabel.setBounds(50, 50, 100, 30);

		String[] airports = {
				"London Heathrow - UK",
				"London Gatwick - UK",
				"Manchester - UK",
				"Stansted - UK",
				"London Luton - UK",
				"London City Airport - UK",
				"Birmingham Airport - UK",
				"Bristol Airport - UK",
				"Liverpool John Lennon Airport - UK",
				"Leeds Bradford Airport - UK",
				"Newcastle Airport - UK",
				"East Midlands Airport - UK",
				"Southampton Airport - UK",
				"Bournemouth Airport - UK",
				"Exeter Airport - UK",
				"Cardiff Airport - UK",
				"Edinburgh Airport - UK",
				"Glasgow Airport - UK",
				"Aberdeen Airport - UK",
				"Inverness Airport - UK",
				"Belfast International Airport - UK",
				"George Best Belfast City Airport - UK"
		};

		airportBox = new JComboBox<>(airports);
		airportBox.setBounds(180, 50, 220, 30);

		airportBox.setSelectedItem(
				booking.getAirport()
				);

		add(airportBox);
		JLabel luggageLabel =
				new JLabel("Luggage:");

		luggageLabel.setBounds(50, 100, 100, 30);

		add(luggageLabel);

		luggageSpinner =
				new JSpinner(
						new SpinnerNumberModel(
								booking.getLuggageAmount(),
								0,
								10,
								1
								)
						);
		luggageSpinner.setBounds(180, 100, 80, 30);

		add(luggageSpinner);

		JLabel pickupLabel =
				new JLabel("Pickup Location:");

		pickupLabel.setBounds(50, 150, 120, 30);

		add(pickupLabel);

		pickupField =
				new JTextField(
						booking.getPickupLocation()
						);

		pickupField.setBounds(180, 150, 220, 30);

		add(pickupField);

		JLabel dateLabel =
				new JLabel("Date:");

		dateLabel.setBounds(50, 200, 100, 30);

		add(dateLabel);

		dateField =
				new JTextField(
						booking.getBookingDate()
						);

		dateField.setBounds(180, 200, 220, 30);

		add(dateField);

		JLabel timeLabel =
				new JLabel("Time:");

		timeLabel.setBounds(50, 250, 100, 30);

		add(timeLabel);

		timeField =
				new JTextField(
						booking.getBookingTime()
						);

		timeField.setBounds(180, 250, 220, 30);

		add(timeField);
		updateButton =
				new JButton("Update Booking");

		updateButton.setBounds(70, 330, 150, 40);

		add(updateButton);

		cancelButton =
				new JButton("Cancel");

		cancelButton.setBounds(260, 330, 150, 40);

		add(cancelButton);

		JButton homeButton =
				new JButton("Return Home");

		homeButton.setBounds(160, 380, 180, 40);

		add(homeButton);


		updateButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(
							ActionEvent e
							) {

						booking.setAirport(
								airportBox.getSelectedItem().toString()
								);

						booking.setLuggageAmount(
								(int) luggageSpinner.getValue()
								);

						booking.setPickupLocation(
								pickupField.getText()
								);

						booking.setBookingDate(
								dateField.getText()
								);

						booking.setBookingTime(
								timeField.getText()
								);

						JOptionPane.showMessageDialog(
								null,
								"Booking amended successfully."
								);

						setVisible(false);

						new BookingSummary(booking);
					}
				});



		cancelButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(
							ActionEvent e
							) {

						homeButton.addActionListener(
								new ActionListener() {

									@Override
									public void actionPerformed(
											ActionEvent e
											) {

										setVisible(false);

										new Home();
									}
								});
						setVisible(false);

						new Home();
					}
				});

		setVisible(true);
	}
}