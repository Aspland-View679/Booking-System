package COMP500GroupProjectPackage;

import javax.swing.*;
import java.awt.event.*;

public class BookingSummary extends JFrame {
	
	JButton payButton;
	
	public BookingSummary(Booking booking) {
		setTitle("Booking Summary");
        setSize(500, 400);    // This is to create a summary ticket before the user continues
        setLayout(null);
        
        JLabel titleLabel = new JLabel("Booking Summary");
        titleLabel.setBounds(170, 20, 200, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(titleLabel);
        JLabel summaryLabel = new JLabel(
        	    "<html>"
        	    + "Airport: " + booking.getAirport()
        	    + "<br><br>Pickup Location: " + booking.getPickupLocation()         // Displays all the information from the booking to display in the booking summary
        	    + "<br><br>Date: " + booking.getBookingDate()
        	    + "<br><br>Time: " + booking.getBookingTime()
        	    + "<br><br>Luggage Amount: " + booking.getLuggageAmount()
        	    + "<br><br>Estimated Duration: "
        	    + booking.getEstimatedDuration() + " minutes"
        	    + "<br><br>Total Price: £"
        	    + booking.getTotalPrice()
        	    + "</html>"
        );

        //Shows reciept of booking
        
        summaryLabel.setBounds(50, 60, 400, 180);
        
        add(summaryLabel);
        
        payButton = new JButton("Proceed To Payment");

        payButton.setBounds(100, 280, 200, 40);

        add(payButton);
        
        JButton cancelButton =   // This if clicked, hides this summary and deletes the current booking
        		new JButton("Cancel Booking");
        
        cancelButton.setBounds(260, 280, 170, 40);
        
        add(cancelButton);
        
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

                PayNewBooking payment = new PayNewBooking(
            		   booking
            	);
            } 
            //On being clicked, hides the JFrame and opens payment frame
        });
 
        cancelButton.addActionListener(e -> {

            setVisible(false);

            new NewBooking();

        });

        setVisible(true);
    }
}
