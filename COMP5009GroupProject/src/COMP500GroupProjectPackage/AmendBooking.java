package COMP500GroupProjectPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

//creates the window for amend booking

public class AmendBooking extends JFrame {

	JComboBox<String> airportBox;
	JSpinner luggageSpinner;
	JTextField pickupField;
	JTextField dateField;
	JTextField timeField;  //All the fields that are being used for this

	JButton updateButton;
	JButton cancelButton;

	public AmendBooking() {

		Booking booking = DataManager.getCurrentBooking();  //Gets the current booking and return nothing if nothing is in the databse

		if (booking == null) {

			
					JOptionPane.showMessageDialog(
					        null,
					        "Booking amended successfully."  //The window that will show up after you amend your booking properly
					);

					setVisible(false);

					new BookingSummary(booking);   //formats the raw data into a structured layout

			return; //returns data to try and format it
			
		}

		try {

			DateTimeFormatter formatter =
			        DateTimeFormatter.ofPattern("dd/MM/yyyy");  //Ensures the date is in a day month year format otherwise not valid

			LocalDate bookingDate =
			        LocalDate.parse(
			                booking.getBookingDate(),  //ensures the today date is in the right format by breaking it down 
			                formatter
			        );

			long daysUntilBooking =
					ChronoUnit.DAYS.between(  //How many days is there between now until your travel
							LocalDate.now(),
							bookingDate
					);

			if (daysUntilBooking < 7) {

				JOptionPane.showMessageDialog(
						null,
						"Bookings cannot be amended within 7 days of travel." //Shows up if your trip is in 7 days or less
				);

				new Home(); //returns that frame otherwise

				return;
			}

		}

		catch (Exception e) {

			JOptionPane.showMessageDialog(
					null,
					"Date format issue detected."  //This will be the reason why you can amend your booking if it is 7 days or more
			);
		}

		setTitle("Amend Booking");  //This is the window pane for amend booking and this creates the windows and amends it

		setSize(950, 700);

		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //Closes on closing the application


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


		JLabel title =
				new JLabel("Amend Existing Booking"); //Formating a label that can be used later

		ImageIcon amendIcon = new ImageIcon(  //Icon for amend booking
				"src/icons/AmendBooking.png"
		);

		Image amendImage =
		        amendIcon.getImage().getScaledInstance(
		                40,
		                40,
		                Image.SCALE_SMOOTH
		        );

		title.setIcon(new ImageIcon(amendImage));

		title.setIconTextGap(15);


		title.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						34
				)
		);

		title.setForeground(
				new Color(30, 30, 30)  //Setting up the boarders of the panel and the size of the icon and setting the fonts
		);


		JLabel subtitle =
				new JLabel(
						"Update airport, luggage, pickup and travel details"
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


		//Main amendment card

		JPanel amendCard =
				new JPanel();

		amendCard.setLayout(
				new GridBagLayout()
		);

		amendCard.setBackground(Color.WHITE);

		amendCard.setBorder(
				BorderFactory.createCompoundBorder(  //setting the boarder

						BorderFactory.createLineBorder(
								new Color(220, 220, 220),
								1,
								true
						),

						new EmptyBorder(35, 35, 35, 35) //padding
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

		cardWrapper.add(amendCard);


		GridBagConstraints gbc =
				new GridBagConstraints();  //ensuring all the text boxes aka grids are even and in the center

		gbc.insets =
				new Insets(15, 15, 15, 15);

		gbc.fill =
				GridBagConstraints.HORIZONTAL;


		//Airport section

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
				"Liverpool John Lennon Airport - UK",  //All the airports you can chose and amend on
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

		airportBox =
				new JComboBox<>(airports); //creates the dropdown menu for you to select

		airportBox.setSelectedItem(
				booking.getAirport()
		);

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


		luggageSpinner =
				new JSpinner(
						new SpinnerNumberModel(
								booking.getLuggageAmount(),
								0,
								10,
								1
						)
				);

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


		pickupField =
				new JTextField(
						booking.getPickupLocation()
				);

		pickupField.setPreferredSize(
				new Dimension(300, 40)
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


		dateField =
				new JTextField(
						booking.getBookingDate()
				);

		dateField.setPreferredSize(
				new Dimension(300, 40)
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


		timeField =
				new JTextField(
						booking.getBookingTime()
				);

		timeField.setPreferredSize(
				new Dimension(300, 40)
		);


		//Update button

		updateButton =
				new JButton("Update Booking");

		updateButton.setPreferredSize(
				new Dimension(220, 50)
		);

		updateButton.setFocusPainted(false);

		updateButton.setBorderPainted(false);

		updateButton.setBackground(
				new Color(155, 109, 255)
		);

		updateButton.setForeground(Color.WHITE);

		updateButton.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		//Cancel button

		cancelButton =
				new JButton("Cancel");

		cancelButton.setPreferredSize(
				new Dimension(180, 50)
		);

		cancelButton.setFocusPainted(false);

		cancelButton.setBorderPainted(false);

		cancelButton.setBackground(
				new Color(230, 230, 230)
		);

		cancelButton.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		//Home button

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


		//Summary button

		JButton summaryButton =
				new JButton("View Summary");

		summaryButton.setPreferredSize(
				new Dimension(180, 50)
		);

		summaryButton.setFocusPainted(false);

		summaryButton.setBorderPainted(false);

		summaryButton.setBackground(
				new Color(230, 230, 230)
		);

		summaryButton.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		//Adds all components to the amendment card

		gbc.gridx = 0;
		gbc.gridy = 0;

		amendCard.add(airportLabel, gbc);

		gbc.gridx = 1;

		amendCard.add(airportBox, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		amendCard.add(luggageLabel, gbc);

		gbc.gridx = 1;

		amendCard.add(luggageSpinner, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		amendCard.add(pickupLabel, gbc);

		gbc.gridx = 1;

		amendCard.add(pickupField, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		amendCard.add(dateLabel, gbc);

		gbc.gridx = 1;

		amendCard.add(dateField, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		amendCard.add(timeLabel, gbc);

		gbc.gridx = 1;

		amendCard.add(timeField, gbc);


		//Button row

		JPanel buttonPanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.CENTER,
								20,
								10
						)
				);

		buttonPanel.setBackground(Color.WHITE);

		buttonPanel.add(updateButton);
                                               //Add buttons for updating and cancel and home and summary
		buttonPanel.add(cancelButton);

		buttonPanel.add(homeButton);

		buttonPanel.add(summaryButton);


		gbc.gridx = 0;
		gbc.gridy++;

		gbc.gridwidth = 2;

		amendCard.add(buttonPanel, gbc);


		//Adds everything to the frame

		backgroundPanel.add(
				headingPanel,
				BorderLayout.NORTH
		);

		backgroundPanel.add(
				cardWrapper,
				BorderLayout.CENTER  //makes sure evrything is centered
		);

		add(backgroundPanel);


		updateButton.addActionListener(  //the button to use while catching empty fields or anuthing invalid.
				new ActionListener() {

					@Override
					public void actionPerformed(
							ActionEvent e
					) {

						try {

							if (pickupField.getText().trim().isEmpty()) {

								JOptionPane.showMessageDialog(
										null,
										"Pickup location cannot be empty."
								);

								return;
							}

							DateTimeFormatter formatter =
									DateTimeFormatter.ofPattern(
											"dd/MM/yyyy"
									);

							LocalDate amendedDate =
									LocalDate.parse(
											dateField.getText(),
											formatter
									);

							if (amendedDate.isBefore(LocalDate.now())) {

								JOptionPane.showMessageDialog(
										null,
										"Date cannot be in the past."
								);

								return;
							}

							booking.setAirport(
									airportBox.getSelectedItem().toString()
							);

							booking.setLuggageAmount(
									(int) luggageSpinner.getValue()
							);

							booking.setPickupLocation(
									pickupField.getText().trim()
							);

							booking.setBookingDate(
									amendedDate.format(formatter)
							);

							booking.setBookingTime(
									timeField.getText().trim()
							);

							booking.setAmended(true);

							DataManager.updateBooking(

									booking.getBookingID(),
									booking.getAirport(),
									booking.getPickupLocation(),
									booking.getBookingDate(),
									booking.getBookingTime(),  //Updates everything in the database and gets rid of the old data
									booking.getLuggageAmount(),
									booking.getEstimatedDuration(),
									booking.getTotalPrice()

							);

							DataManager.setCurrentBooking(
									booking
							);

							JOptionPane.showMessageDialog(
									null,
									"Booking amended successfully."  //Tells the user if successful
							);

							setVisible(false);

							new Home(); //goes to a new from of this one

						}

						catch (Exception ex) {

							JOptionPane.showMessageDialog(
									null,
									"Please enter a valid date using DD/MM/YYYY."  //if invalid it will show the user why.
							);
						}
					}
				});


				summaryButton.addActionListener(e -> {

				    setVisible(false); //hdies window

				    new BookingSummary(
				            DataManager.getCurrentBooking()  //saves for a later use
				    );

				});
			


		cancelButton.addActionListener(e -> {

			setVisible(false);  //the cancel button

			new Home();

		});


		homeButton.addActionListener(e -> {

			setVisible(false);  //doesnt allow the panel to be seen

			new Home();

		});


		setVisible(true);  //allows the panel to be seen
	}
}

