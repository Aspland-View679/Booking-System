package COMP500GroupProjectPackage;

import javax.swing.*;

public class NewBooking extends JFrame {

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

        JTextField luggageField = new JTextField();
        luggageField.setBounds(150, 100, 250, 30);

        JLabel pickupLabel = new JLabel("Pickup Location:");
        pickupLabel.setBounds(50, 150, 120, 30);

        JTextField pickupField = new JTextField();
        pickupField.setBounds(170, 150, 230, 30);
        
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 200, 100, 30);
        
        JTextField dateField = new JTextField();
        dateField.setBounds(150, 200, 250, 30);
        
        add(dateLabel);
        add(dateField);
        
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
        
        JTextField timeField = new JTextField();
        timeField.setBounds(150, 250, 250, 30);
        
        add(timeLabel);
        add(timeField);

        JButton bookButton = new JButton("Book Your Flight");
        bookButton.setBounds(150, 320, 180, 40);

        add(luggageLabel);
        add(luggageField);

        add(pickupLabel);
        add(pickupField);
        
        JLabel pickupHint = new JLabel(
        		"Example: Ashford, Canterbury, London, Manchester"
        );
        
        pickupHint.setBounds(170, 175, 400, 30);
        
        add(pickupHint);

        add(bookButton);
        
        
        bookButton.addActionListener(e -> {
        	
        	int estimationOfDuration = 30;
        	int timeOfDay = Integer.parseInt(timeField.getText());
        	
        	if (timeOfDay >= 7 && timeOfDay <= 9) {
        		estimationOfDuration += 20;
        	}
        	else if (timeOfDay >= 16 && timeOfDay <=18) {
        		estimationOfDuration += 20;
        	}
        	else {
        		estimationOfDuration += 10;
        	}
        	
        	int luggageAmount = Integer.parseInt(luggageField.getText());
        	
        	estimationOfDuration += luggageAmount * 5;
        	
        	String pickup = pickupField.getText().toLowerCase();
        	
        	if (pickup.contains("ashford")) {
        		estimationOfDuration += 10;
        	}
        	else if (pickup.contains("canterbury")) {
        		estimationOfDuration += 20;
        	}
        	else {
        		estimationOfDuration += 30;
        	}
        	
        	String selectedAirport = airportBox.getSelectedItem().toString().toLowerCase();
        	
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
        
        // PaymentPage payment = new payNewPage();
    });
        
    
    setVisible(true);
    
    }
    
 
}

