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
            
        amendButton.addActionListener(new ActionListener() {

           
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (DataManager.getCurrentBooking() == null) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Please create a booking first"
                        );

                    } else {

                        setVisible(false);

                        new AmendBooking();
                    }
                }
            });
        bookingSummaryButton.addActionListener(e -> {

            if (DataManager.getCurrentBooking() == null) {

                JOptionPane.showMessageDialog(
                        null, 
                        "Please create a booking first"              //This two sections of code stops the user going further if they haven't made a booking to review or amend
                );

            } else {

                setVisible(false);

                new BookingSummary(DataManager.getCurrentBooking()); //this if you made a booking calls it back
            }
        });
        
        setVisible(true); //Calls the JPanel
    }

    public static void main(String[] args) {

        DataManager.createTable(); //connects to sqlite to check if the booking table exists and creates if there isn't a booking table

        Booking latestBooking =
                DataManager.getLatestBooking();  //searchs the database to find the most recent booking and make it a booking object

        DataManager.setCurrentBooking(
                latestBooking  //this stores booking into ram so it can be called back even when restarting the application
        );

        new Home(); //relaunches the homepage once the backend is complete.
    }
}