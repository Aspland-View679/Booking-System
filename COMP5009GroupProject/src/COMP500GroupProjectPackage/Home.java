package COMP500GroupProjectPackage;

import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame {

    private JButton bookingButton;
    private JButton amendButton;
    private JButton cancelButton;
    private JButton payNewButton;
    private JButton confirmationButton;  //This is for the creation of buttons
    private JButton discountButton;
    private JButton bookingSummaryButton;

    public Home() {
        setTitle("Airport Booking System"); //sets the window application window size, title and layout
        setSize(450, 420);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when you close the application

        bookingButton = new JButton("Make New Booking");
        bookingButton.setBounds(100, 50, 200, 40);  //sets up the booking button
        
        amendButton = new JButton("Amend Booking"); //sets up the amend button
        amendButton.setBounds(100, 100, 200, 40);
        
        cancelButton = new JButton("Cancel Booking"); //sets up the cancel button
        cancelButton.setBounds(100, 150, 200, 40);
        
        payNewButton = new JButton("Pay for Booking"); //sets up the payment button
        payNewButton.setBounds(100, 200, 200, 40);
        
        confirmationButton = new JButton("Confirmation Message"); //sets up the confirmation button
        confirmationButton.setBounds(100, 250, 200, 40);
        
        discountButton = new JButton("Offer Discount"); //sets up the discount button
        discountButton.setBounds(100, 300, 200, 40);
        
        bookingSummaryButton = new JButton("Booking Summary"); //sets up the booking summary button
        bookingSummaryButton.setBounds(100, 350, 200, 40);
        
        
        add(bookingButton);
        add(amendButton);
        add(cancelButton);                //creates the buttons to the application
        add(payNewButton);
        add(confirmationButton);
        add(discountButton); 
        add(bookingSummaryButton);

        bookingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	setVisible(false); // does not call the JPanel
            	                               //allows user to click and go onto making a booking
            	new NewBooking();
            }
        });
            
        bookingSummaryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(  //creates a warning if you try and look at a booking but no bookings have been created
                        null,
                        "Please create a booking first"
                	    );

            }
        });
        
        setVisible(true); //Calls the JPanel
    }

    public static void main(String[] args) {
        new Home(); //Home page
    }
}