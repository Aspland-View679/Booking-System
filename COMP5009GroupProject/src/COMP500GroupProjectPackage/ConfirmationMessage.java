package COMP500GroupProjectPackage;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Represents an order and its confirmation status.
 * @author <a href="mailto:njw38@kent.ac.uk">Alexis Williams</a>
 * @version 2026-05-11
 */
public class ConfirmationMessage extends JFrame {
	private Order order;
	private boolean confirmed;

	
	public ConfirmationMessage(PayNewBooking payment, NewBooking booking, AmendBooking amend, CancelBooking cancel) {
		
		JFrame confirmationMessage = new JFrame();
		confirmationMessage.setSize(500, 500);
		
		JLabel showDetails = new JLabel();
		showDetails.setBounds(250, 250, 30, 100);
		JLabel showPaymentDetails = new JLabel();
		showPaymentDetails.setBounds(250, 290, 20, 100);

		if (booking != null) {
		showDetails.setText("Payment successful for your new booking from to " + booking.getselectedAirport() + " from " + booking.getpickup());
		showPaymentDetails.setText("Payment made from card number " + payment.getEncryptedCardNumber() + " with booking fee of " + payment.getBookingFee());
		}
		else if (amend != null) {
			showDetails.setText("Amendment successful");
			showPaymentDetails.setText("Payment made from card number " + payment.getEncryptedCardNumber() + " with booking fee of " + payment.getBookingFee());
		}
		
		confirmationMessage.add(showPaymentDetails);
		confirmationMessage.add(showDetails);
		
		confirmationMessage.setVisible(true);
	}
	
	
}
