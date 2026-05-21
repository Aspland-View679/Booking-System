package COMP500GroupProjectPackage;

import javax.swing.*;

public class NewBooking extends JFrame {
	
	private int timeDay;
	private int luggageAmount;
	private int estimationOfDuration;
	private String pickup;
	private String selectedAirport;
	private String bookingDate;
	
	public int getTimeDay() {
		return timeDay;
	}
	
	public int getluggageAmount() {
		return luggageAmount;
	}
	
	public int getestimationOfDuration() {
		return estimationOfDuration;
	}
	
	public String getpickup() {
		return pickup;
	}
	
	public String getselectedAirport() {
		return selectedAirport;
	}
	
	public String getbookingDate() {
		return bookingDate;
	}

	
    public NewBooking() {

        setTitle("Booking Page");
        setSize(650, 500);
        setLayout(null);

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

        JComboBox<String> airportBox = new JComboBox<>(airports);
        airportBox.setBounds(150, 50, 350, 30);

        add(airportLabel);
        add(airportBox);

        JLabel luggageLabel = new JLabel("Luggage:");
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
                        "dd/MM/yyyy"
                );

        dateSpinner.setEditor(dateEditor);

        dateSpinner.setBounds(150, 200, 250, 30);
        
        add(dateLabel);
        add(dateSpinner);
        
        JLabel timeHint = new JLabel("Use 24 hour format");
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
        
        pickupHint.setBounds(170, 175, 400, 30);
        
        add(pickupHint);

        add(bookButton);
        
        
        bookButton.addActionListener(e -> {
        	if (pickupField.getText().trim().isEmpty()) {

        	    JOptionPane.showMessageDialog(
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
        	
        	 selectedAirport = airportBox.getSelectedItem().toString().toLowerCase();
        	
        	if (selectedAirport.contains("london")) {
        		estimationOfDuration += 25;
        	}
        	else if (selectedAirport.contains("manchester")) {
        		estimationOfDuration += 40;
        	}
        	else {
        		estimationOfDuration += 60;
        	}
        	
        	JOptionPane.showMessageDialog(
        			null,
        			"Estimated Duration Of Your Trip: "
        			+ estimationOfDuration + " minutes"
        	);
        	
      

        setVisible(false);
        
        PayNewBooking payment = new PayNewBooking(this, null, null);
        });
        
    
    setVisible(true);
    
    }
    
 
}
    


