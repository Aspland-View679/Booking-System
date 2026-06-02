
package COMP500GroupProjectPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Represents an order and its confirmation status.
 * @author <a href="mailto:njw38@kent.ac.uk">Alexis Williams</a>
 * @version 2026-05-11
 */

public class ConfirmationMessage extends JFrame
implements ActionListener {

	private Order order;

	private boolean confirmed;


	public ConfirmationMessage(
			PayNewBooking payment,
			Booking booking
	) {


		setTitle("Booking Confirmation");

		setSize(900, 650);

		setLocationRelativeTo(null);

		setLayout(new BorderLayout());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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


		JLabel titleLabel =
				new JLabel("Booking Confirmed");

		titleLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						34
				)
		);

		titleLabel.setForeground(
				new Color(30, 30, 30)
		);


		JLabel subtitle =
				new JLabel(
						"Your airport booking has been successfully processed"
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


		headingPanel.add(titleLabel);

		headingPanel.add(
				Box.createRigidArea(
						new Dimension(0, 8)
				)
		);

		headingPanel.add(subtitle);


		//Confirmation card

		JPanel confirmationCard =
				new JPanel();

		confirmationCard.setLayout(
				new BoxLayout(
						confirmationCard,
						BoxLayout.Y_AXIS
				)
		);

		confirmationCard.setBackground(Color.WHITE);

		confirmationCard.setBorder(
				BorderFactory.createCompoundBorder(

						BorderFactory.createLineBorder(
								new Color(220, 220, 220),
								1,
								true
						),

						new EmptyBorder(35, 35, 35, 35)
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

		cardWrapper.add(confirmationCard);


		//Success icon

		JLabel successIcon =
				new JLabel("✓");

		successIcon.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						60
				)
		);

		successIcon.setForeground(
				new Color(76, 175, 80)
		);

		successIcon.setAlignmentX(Component.CENTER_ALIGNMENT);


		//Confirmation message

		JLabel successMessage =
				new JLabel(
						"Payment Successful"
				);

		successMessage.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						28
				)
		);

		successMessage.setForeground(
				new Color(30, 30, 30)
		);

		successMessage.setAlignmentX(Component.CENTER_ALIGNMENT);


		//Booking details

		JLabel bookingDetails =
				new JLabel(

						"<html><center>"
						+ "Your booking to "
						+ booking.getAirport()
						+ "<br><br>"
						+ "from "
						+ booking.getPickupLocation()
						+ " has been successfully confirmed."
						+ "</center></html>"

				);

		bookingDetails.setFont(
				new Font(
						"SansSerif",
						Font.PLAIN,
						18
				)
		);

		bookingDetails.setForeground(
				new Color(90, 90, 90)
		);

		bookingDetails.setAlignmentX(Component.CENTER_ALIGNMENT);


		//Payment details

		JLabel paymentDetails =
				new JLabel(

						"<html><center>"
						+ "Payment made using card number "
						+ payment.getEncryptedCardNumber()
						+ "<br><br>"
						+ "Booking fee paid: £"
						+ payment.getBookingFee()
						+ "</center></html>"

				);

		paymentDetails.setFont(
				new Font(
						"SansSerif",
						Font.PLAIN,
						17
				)
		);

		paymentDetails.setForeground(
				new Color(90, 90, 90)
		);

		paymentDetails.setAlignmentX(Component.CENTER_ALIGNMENT);


		//End of flow message

		JLabel endOfFlow =
				new JLabel(
						"Please return home to leave this booking flow"
				);

		endOfFlow.setFont(
				new Font(
						"SansSerif",
						Font.PLAIN,
						15
				)
		);

		endOfFlow.setForeground(
				new Color(120, 120, 120)
		);

		endOfFlow.setAlignmentX(Component.CENTER_ALIGNMENT);


		//Home button

		JButton home =
				new JButton("Return Home");

		home.setPreferredSize(
				new Dimension(220, 50)
		);

		home.setMaximumSize(
				new Dimension(220, 50)
		);

		home.setFocusPainted(false);

		home.setBorderPainted(false);

		home.setBackground(
				new Color(155, 109, 255)
		);

		home.setForeground(Color.WHITE);

		home.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);

		home.setAlignmentX(Component.CENTER_ALIGNMENT);


		//Adds everything to the confirmation card

		confirmationCard.add(successIcon);

		confirmationCard.add(
				Box.createRigidArea(
						new Dimension(0, 15)
				)
		);

		confirmationCard.add(successMessage);

		confirmationCard.add(
				Box.createRigidArea(
						new Dimension(0, 30)
				)
		);

		confirmationCard.add(bookingDetails);

		confirmationCard.add(
				Box.createRigidArea(
						new Dimension(0, 30)
				)
		);

		confirmationCard.add(paymentDetails);

		confirmationCard.add(
				Box.createRigidArea(
						new Dimension(0, 40)
				)
		);

		confirmationCard.add(home);

		confirmationCard.add(
				Box.createRigidArea(
						new Dimension(0, 20)
				)
		);

		confirmationCard.add(endOfFlow);


		//Adds everything to frame

		backgroundPanel.add(
				headingPanel,
				BorderLayout.NORTH
		);

		backgroundPanel.add(
				cardWrapper,
				BorderLayout.CENTER
		);

		add(backgroundPanel);


		home.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e
			) {

				setVisible(false);

				new Home();
			}
		});


		setVisible(true);
	}


	@Override
	public void actionPerformed(
			ActionEvent e
	) {

		// TODO Auto-generated method stub

	}
}
