
package COMP500GroupProjectPackage;

import javax.swing.*;


public class NewBooking extends JFrame {
	
	private int timeDay;
	private int luggageAmount;
	private int estimationOfDuration;
	private String pickup;               //For Pay New booking to call this class
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

	public int getluggageAmount() {
		return luggageAmount;
	}
	                                     //All getters for the Pay New Booking Class
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
        setSize(650, 500);               //Creates the new booking JPanel, sets the window size with the title
        setLayout(null);

        JLabel airportLabel = new JLabel("Airport:");
        airportLabel.setBounds(50, 50, 100, 30);    //Creates a textbox next to the dropdown menu

        String[] airports = {
            "London Heathrow - UK",
            "London Gatwick - UK",
            "Manchester - UK",
            "Stansted - UK",
            "London Luton - UK",
            "London City Airport - UK",
            "Birmingham Airport - UK",
            "Bristol Airport - UK",
            "Liverpool John Lennon Airport - UK",             //This is the list for the drop down menu
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

        JComboBox<String> airportBox = new JComboBox<>(airports);
        airportBox.setBounds(150, 50, 350, 30);

        add(airportLabel);       //This is the drop down menu
        add(airportBox);

        JLabel luggageLabel = new JLabel("Luggage:"); //All text boxes for Luggage, Pickup, Date and time fields
        luggageLabel.setBounds(50, 100, 100, 30);

        SpinnerNumberModel luggageModel =
                new SpinnerNumberModel(0, 0, 10, 1);

        JSpinner luggageSpinner =
                new JSpinner(luggageModel);

        luggageSpinner.setBounds(150, 100, 250, 30);

        JLabel pickupLabel = new JLabel("Pickup Location:");
        pickupLabel.setBounds(50, 150, 120, 30);

        JTextField pickupField = new JTextField();
        pickupField.setBounds(170, 150, 230, 30);
        
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 200, 100, 30);
        
        SpinnerDateModel dateModel =
                new SpinnerDateModel();

        JSpinner dateSpinner =
                new JSpinner(dateModel);

        JSpinner.DateEditor dateEditor =
                new JSpinner.DateEditor(
                        dateSpinner,
                        "dd/MM/yyyy"     //Sets the format to a calendar format
                );

        dateSpinner.setEditor(dateEditor);

        dateSpinner.setBounds(150, 200, 250, 30);
        
        add(dateLabel);
        add(dateSpinner);
        
        JLabel timeHint = new JLabel("Use 24 hour format");   //it tells the user what format is accepted - 00:00
        timeHint.setBounds(410, 250, 150, 30);
        
        add(timeHint);
        
        JLabel dateHint = new JLabel(
        		"Format: DD/MM/YYYY"
        );
        
        dateHint.setBounds(410, 200, 150, 30);
        
        add(dateHint);
        		
        
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(50, 250, 100, 30);
        
        SpinnerDateModel timeModel =
                new SpinnerDateModel();

        JSpinner timeSpinner =
                new JSpinner(timeModel);
                                         //this is to create the 00:00
        JSpinner.DateEditor timeEditor =
                new JSpinner.DateEditor(
                        timeSpinner,
                        "HH:mm"
                );

        timeSpinner.setEditor(timeEditor);

        timeSpinner.setBounds(150, 250, 250, 30);
        
        add(timeLabel);
        add(timeSpinner);

        JButton bookButton = new JButton("Book Your Ride");
        bookButton.setBounds(150, 320, 180, 40);

        add(luggageLabel);
        add(luggageSpinner);

        add(pickupLabel);
        add(pickupField);
        
        JLabel pickupHint = new JLabel(
        		"Example: Ashford, Canterbury, London, Manchester"
        );
        
        pickupHint.setBounds(170, 175, 400, 30);       //Creates buttons
        
        add(pickupHint);

        add(bookButton);
        
        
        bookButton.addActionListener(e -> {
        	if (pickupField.getText().trim().isEmpty()) {

        	    JOptionPane.showMessageDialog(     //If you leave a field empty this catches it and tells the user to fill out the boxes
        	            null,
        	            "Please enter a pickup location"
        	    );

        	    return;
        	}
        	
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
        	else if (timeDay >= 16 && timeDay <=18) {
        		estimationOfDuration += 20;
        	}
        	else {
        		estimationOfDuration += 10;
        	}

                //Estimates time based on how busy time of day it is
        	
        	bookingDate = dateEditor.getFormat()
        	        .format(dateSpinner.getValue());
        	
        	luggageAmount = (Integer) luggageSpinner.getValue();
        	
        	estimationOfDuration += luggageAmount * 5;
        	
        	pickup = pickupField.getText().toLowerCase();
        	
        	if (pickup.contains("ashford")) {
        		estimationOfDuration += 10;
        	}
        	else if (pickup.contains("canterbury")) {
        		estimationOfDuration += 20;
        	}
        	else {
        		estimationOfDuration += 30;
        	}
        	
        	 selectedAirport = airportBox.getSelectedItem().toString();
        	
        	if (selectedAirport.contains("london")) {
        		estimationOfDuration += 25;
        	}
        	else if (selectedAirport.contains("manchester")) {
        		estimationOfDuration += 40;
        	}
        	else {
        		estimationOfDuration += 60;
        	}

                //Generates duration based on airport chosen

        	
        	double totalPrice = estimationOfDuration * 1.5;
        	
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
        	
        	System.out.println(
        	        booking.getBookingID()
        	);
        	
               //Creates a booking object
        	
        	JOptionPane.showMessageDialog(
        			null,
        			"Estimated Duration Of Your Trip: "
        			+estimationOfDuration + "minutes"
        			+ "\nTotal Price: £" + totalPrice
        	);
        	
        	setVisible(false); // Doesn't show the JPanel
        	
        	new BookingSummary(booking);
        
        	
        });
        
    
    setVisible(true); // Shows JPanel
    
    }
    
 
}


