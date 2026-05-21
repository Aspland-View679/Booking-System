package COMP500GroupProjectPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Represents an order and its confirmation status.
 * @author <a href="mailto:njw38@kent.ac.uk">Alexis Williams</a>
 * @version 2026-05-11
 */
public class ConfirmationMessage extends JFrame implements ActionListener {
	private Order order;
	private boolean confirmed;

	
	public ConfirmationMessage(PayNewBooking payment, Booking booking) {
		
		JFrame confirmationMessage = new JFrame();
		confirmationMessage.setSize(650, 650);
		
		JLabel showDetails = new JLabel();
		showDetails.setBounds(10, 250, 600, 30);
		JLabel showPaymentDetails = new JLabel();
		showPaymentDetails.setBounds(10, 290, 600, 30);


		showDetails.setText("Payment successful for your new booking from to " + booking.getselectedAirport() + " from " + booking.getpickup());


		showPaymentDetails.setText("Payment made from card number " + payment.getEncryptedCardNumber() + " with booking fee of £" + payment.getBookingFee());
		
		confirmationMessage.add(showPaymentDetails);
		confirmationMessage.add(showDetails);
		
		JButton home = new JButton();
		home.setBounds(250,310, 50, 50);
		home.setText("Home");
		confirmationMessage.add(home);
		
		JLabel endOfFlow = new JLabel();
		endOfFlow.setBounds(250,320,50,50);
		endOfFlow.setText("Please click home to leave this booking flow");
		confirmationMessage.add(endOfFlow);
		
		home.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmationMessage.setVisible(false);
				Home goHome = new Home();
			}
		});
		
		confirmationMessage.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
