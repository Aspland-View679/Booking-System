package COMP500GroupProjectPackage;

import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame {

    JButton bookingButton;
    JButton amendButton;
    JButton cancelButton;
    JButton payNewButton;
    JButton confirmationButton;
    JButton discountButton;

    public Home() {
        setTitle("Airport Booking System");
        setSize(450, 420);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bookingButton = new JButton("Make New Booking");
        bookingButton.setBounds(100, 50, 200, 40);
        
        amendButton = new JButton("Amend Booking");
        amendButton.setBounds(100, 100, 200, 40);
        
        cancelButton = new JButton("Cancel Booking");
        cancelButton.setBounds(100, 150, 200, 40);
        
        payNewButton = new JButton("Pay for Booking");
        payNewButton.setBounds(100, 200, 200, 40);
        
        confirmationButton = new JButton("Confirmation Message");
        confirmationButton.setBounds(100, 250, 200, 40);
        
        discountButton = new JButton("Offer Discount");
        discountButton.setBounds(100, 300, 200, 40);
        
        
        add(bookingButton);
        add(amendButton);
        add(cancelButton);
        add(payNewButton);
        add(confirmationButton);
        add(discountButton);

        bookingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	setVisible(false);
            	
            	new NewBooking();
            }
        });

        // amendButton.addActionListener( e - {
        // new amendBooking();
        // });
        
     // cancelButton.addActionListener( e - {
    	// new cancelBooking();
    // });
        
     // payNewButton.addActionListener( e - {
    	// new payNewBooking();
    // });
        
     // confirmationButton.addActionListener( e - {
    	// new confirmationBooking();
    // });
        
     // discountButton.addActionListener( e - {
    	// new discountBooking();
    // });
        
        setVisible(true);
     
    }

    public static void main(String[] args) {

        new Home();
    }
}