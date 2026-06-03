package COMP500GroupProjectPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class NewBooking extends JFrame {

	private int timeDay;
	private int luggageAmount;
	private int estimationOfDuration;  //For Pay New booking to call this class
	private String pickup;
	private String selectedAirport;
	private String bookingDate;


	/**
	 * getTimeDay
	 * @return timeDay
	 */

	public int getTimeDay() {
		return timeDay;
	}


	/**
	 * getluggageAmount
	 * @return luggageAmount
	 */

	public int getluggageAmount() {          //All getters for the Pay New Booking Class
		return luggageAmount;
	}


	/**
	 * getestimationOfDuration
	 * @return estimationOfDuration
	 */

	public int getestimationOfDuration() {
		return estimationOfDuration;
	}


	/**
	 * getpickup
	 * @return pickup
	 */

	public String getpickup() {
		return pickup;
	}


	/**
	 * getselectedAirport
	 * @return selectedAirport
	 */

	public String getselectedAirport() {
		return selectedAirport;
	}


	/**
	 * getbookingDate
	 * @return bookingDate
	 */

	public String getbookingDate() {
		return bookingDate;
	}


	public NewBooking() {

		setTitle("Booking Page");

		setSize(950, 700);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());


		//Main background panel

		JPanel backgroundPanel =
				new JPanel(new BorderLayout());             //Creates the new booking JPanel, sets the window size with the title

		backgroundPanel.setBackground(
				new Color(245, 245, 245)
		);


		//Top heading section

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


		JLabel title =
				new JLabel("Create New Booking");

		title.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						34
				)
		);

		title.setForeground(
				new Color(30, 30, 30)
		);


		JLabel subtitle =
				new JLabel(
						"Enter customer journey details below"
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


		headingPanel.add(title);

		headingPanel.add(
				Box.createRigidArea(
						new Dimension(0, 8)
				)
		);

		headingPanel.add(subtitle);


		//Main booking card

		JPanel bookingCard =
				new JPanel();

		bookingCard.setLayout(
				new GridBagLayout()
		);

		bookingCard.setBackground(Color.WHITE);

		bookingCard.setBorder(
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

		cardWrapper.add(bookingCard);


		GridBagConstraints gbc =
				new GridBagConstraints();

		gbc.insets =
				new Insets(15, 15, 15, 15);

		gbc.fill =
				GridBagConstraints.HORIZONTAL;


		//Airport label

		JLabel airportLabel =
				new JLabel("Airport:");

		airportLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


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
				"Newcastle Airport - UK",          //This is the list for the drop down menu
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


		JComboBox<String> airportBox =
				new JComboBox<>(airports);

		airportBox.setPreferredSize(
				new Dimension(300, 40)
		);


		//Luggage section

		JLabel luggageLabel =
				new JLabel("Luggage:");

		luggageLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		SpinnerNumberModel luggageModel =
				new SpinnerNumberModel(0, 0, 10, 1);

		JSpinner luggageSpinner =
				new JSpinner(luggageModel);

		luggageSpinner.setPreferredSize(
				new Dimension(300, 40)
		);


		//Pickup section

		JLabel pickupLabel =
				new JLabel("Pickup Location:");

		pickupLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JTextField pickupField =
				new JTextField();

		pickupField.setPreferredSize(
				new Dimension(300, 40)
		);


		JLabel pickupHint =
				new JLabel(
						"Example: Ashford, Canterbury, London, Manchester"
				);

		pickupHint.setForeground(
				new Color(100, 100, 100)
		);


		//Date section

		JLabel dateLabel =
				new JLabel("Date:");

		dateLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		SpinnerDateModel dateModel =
				new SpinnerDateModel();

		JSpinner dateSpinner =
				new JSpinner(dateModel);

		JSpinner.DateEditor dateEditor =
				new JSpinner.DateEditor(
						dateSpinner,
						"dd/MM/yyyy"
				);

		dateSpinner.setEditor(dateEditor);

		dateSpinner.setPreferredSize(
				new Dimension(300, 40)
		);


		JLabel dateHint =
				new JLabel(
						"Format: DD/MM/YYYY"  //Sets the format to a calendar format
		                );


		dateHint.setForeground(
				new Color(100, 100, 100)
		);


		//Time section

		JLabel timeLabel =
				new JLabel("Time:");

		timeLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		SpinnerDateModel timeModel =
				new SpinnerDateModel();

		JSpinner timeSpinner =
				new JSpinner(timeModel);


		JSpinner.DateEditor timeEditor =
				new JSpinner.DateEditor(
						timeSpinner,
						"HH:mm"         //this is to create the 24 hour time
				);

		timeSpinner.setEditor(timeEditor);

		timeSpinner.setPreferredSize(
				new Dimension(300, 40)
		);


		JLabel timeHint =
				new JLabel(
						"Use 24 hour format"
				);

		timeHint.setForeground(
				new Color(100, 100, 100)
		);


		//Book button

		JButton bookButton =
				new JButton("Book Your Ride");

		bookButton.setPreferredSize(
				new Dimension(220, 50)
		);

		bookButton.setFocusPainted(false);

		bookButton.setBorderPainted(false);

		bookButton.setBackground(
				new Color(155, 109, 255)
		);

		bookButton.setForeground(Color.WHITE);

		bookButton.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		//Adds all components to the booking card

		gbc.gridx = 0;
		gbc.gridy = 0;

		bookingCard.add(airportLabel, gbc);

		gbc.gridx = 1;

		bookingCard.add(airportBox, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		bookingCard.add(luggageLabel, gbc);

		gbc.gridx = 1;

		bookingCard.add(luggageSpinner, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		bookingCard.add(pickupLabel, gbc);

		gbc.gridx = 1;

		bookingCard.add(pickupField, gbc);


		gbc.gridx = 1;
		gbc.gridy++;

		bookingCard.add(pickupHint, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		bookingCard.add(dateLabel, gbc);

		gbc.gridx = 1;

		bookingCard.add(dateSpinner, gbc);


		gbc.gridx = 1;
		gbc.gridy++;

		bookingCard.add(dateHint, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		bookingCard.add(timeLabel, gbc);

		gbc.gridx = 1;

		bookingCard.add(timeSpinner, gbc);


		gbc.gridx = 1;
		gbc.gridy++;

		bookingCard.add(timeHint, gbc);


		gbc.gridx = 1;
		gbc.gridy++;

		bookingCard.add(bookButton, gbc);


		//Adds everything to the frame

		backgroundPanel.add(
				headingPanel,
				BorderLayout.NORTH
		);

		backgroundPanel.add(
				cardWrapper,
				BorderLayout.CENTER
		);

		add(backgroundPanel);


		bookButton.addActionListener(e -> {

			try {

				if (pickupField.getText().trim().isEmpty()) {

					JOptionPane.showMessageDialog(     //If you leave a field empty this catches it and tells the user to fill out the boxes
	        	            null,
							"Please enter a pickup location"
					);

					return;
				}


				LocalDate selectedDate =
						((Date) dateSpinner.getValue())
						.toInstant()
						.atZone(ZoneId.systemDefault())
						.toLocalDate();


				if (selectedDate.isBefore(LocalDate.now())) {

					JOptionPane.showMessageDialog(
							null,
							"Booking date cannot be in the past."
					);

					return;
				}

				
				//Estimates time based on how busy time of day it is


				estimationOfDuration = 30;


				String timeText =
						timeEditor.getFormat()
						.format(timeSpinner.getValue());


				timeDay = Integer.parseInt(
						timeText.substring(0, 2)
				);


				if (timeDay >= 7 && timeDay <= 9) {

					estimationOfDuration += 20;
				}

				else if (timeDay >= 16 && timeDay <= 18) {

					estimationOfDuration += 20;
				}

				else {

					estimationOfDuration += 10;
				}


				bookingDate = dateEditor.getFormat()
						.format(dateSpinner.getValue());


				luggageAmount =
						(Integer) luggageSpinner.getValue();


				estimationOfDuration += luggageAmount * 5;


				pickup =
						pickupField.getText().trim().toLowerCase();


				if (pickup.contains("ashford")) {

					estimationOfDuration += 10;
				}

				else if (pickup.contains("canterbury")) {

					estimationOfDuration += 20;
				}

				else {

					estimationOfDuration += 30;
				}


				selectedAirport =
						airportBox.getSelectedItem().toString();


				if (selectedAirport.toLowerCase().contains("london")) {

					estimationOfDuration += 25;
				}

				else if (selectedAirport.toLowerCase().contains("manchester")) {

					estimationOfDuration += 40;
				}

				else {

					estimationOfDuration += 60;
				}
				
                //Generates duration based on airport chosen


				double totalPrice =
						estimationOfDuration * 1.5;


				double discountPercentage =
						DataManager.getGlobalDiscountPercentage();


				if (discountPercentage > 0) {

					totalPrice =
							totalPrice
							- (totalPrice * (discountPercentage / 100));
				}


				Booking booking = new Booking(
						selectedAirport,
						pickup,
						bookingDate,
						timeText,
						luggageAmount,
						estimationOfDuration,
						totalPrice
				);


				DataManager.setCurrentBooking(booking);

				DataManager.insertBooking(booking);


				//Creates a booking object
				JOptionPane.showMessageDialog(
						null,
						"Estimated Duration Of Your Trip: "
						+ estimationOfDuration
						+ " minutes"
						+ "\nTotal Price: £"
						+ String.format("%.2f", totalPrice)
				);


				setVisible(false);  //doesnt show panel

				new BookingSummary(booking);

			}

			catch (Exception ex) {

				JOptionPane.showMessageDialog(
						null,   //catches if any invalid inputs happened
						"An error occurred while creating the booking."
				);

				ex.printStackTrace();
			}

		});


		setVisible(true);
	}
}