
package COMP500GroupProjectPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PayNewBooking
extends JFrame
implements ActionListener {

	private Long cardNumber;
	private String cardName;
	private String cardType;
	private int CVV;

	private String[] acceptedCardTypes = {
			"Visa",
			"Mastercard",
			"Visa Debit",
			"Apple card"
	};

	private int expiryYear;
	private int expiryMonth;

	private Booking booking;

	private String billingAddress;

	private double bookingFee;

	private String starredOutCardNumber;


	public static void main(String[] args) {

		PayNewBooking pay =
				new PayNewBooking(null);
	}


	public PayNewBooking(
			Booking booking
	) {

		this.booking = booking;


		setTitle("Payment Page");

		setSize(950, 750);

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


		JLabel title =
				new JLabel("Payment Details");

		title.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						34
				)
		);

		title.setForeground(
				new Color(30, 30, 30)
		);


		JLabel subtitle =
				new JLabel(
						"Complete your booking payment securely"
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


		headingPanel.add(title);

		headingPanel.add(
				Box.createRigidArea(
						new Dimension(0, 8)
				)
		);

		headingPanel.add(subtitle);


		//Payment card

		JPanel paymentCard =
				new JPanel();

		paymentCard.setLayout(
				new GridBagLayout()
		);

		paymentCard.setBackground(Color.WHITE);

		paymentCard.setBorder(
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

		cardWrapper.add(paymentCard);


		GridBagConstraints gbc =
				new GridBagConstraints();

		gbc.insets =
				new Insets(15, 15, 15, 15);

		gbc.fill =
				GridBagConstraints.HORIZONTAL;


		//Name section

		JLabel labelForName =
				new JLabel("Card Holder Name:");

		labelForName.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JTextField payersName =
				new JTextField();

		payersName.setPreferredSize(
				new Dimension(300, 40)
		);


		//Card number section

		JLabel labelForCardNumber =
				new JLabel("Card Number:");

		labelForCardNumber.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JTextField cardNumberInput =
				new JTextField();

		cardNumberInput.setPreferredSize(
				new Dimension(300, 40)
		);


		//Card type section

		JLabel cardTypeLabel =
				new JLabel("Card Type:");

		cardTypeLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JComboBox<String> cardTypeInput =
				new JComboBox<>(acceptedCardTypes);

		cardTypeInput.setPreferredSize(
				new Dimension(300, 40)
		);


		//Expiry section

		JLabel expiryDateLabel =
				new JLabel("Expiry Date:");

		expiryDateLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JPanel expiryPanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.LEFT,
								10,
								0
						)
				);

		expiryPanel.setBackground(Color.WHITE);


		JTextField expiryMonth =
				new JTextField();

		expiryMonth.setPreferredSize(
				new Dimension(80, 40)
		);


		JTextField expiryYear =
				new JTextField();

		expiryYear.setPreferredSize(
				new Dimension(120, 40)
		);


		JLabel slash =
				new JLabel("/");

		slash.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						18
				)
		);


		expiryPanel.add(expiryMonth);

		expiryPanel.add(slash);

		expiryPanel.add(expiryYear);


		//CVV section

		JLabel cvvLabel =
				new JLabel("CVV:");

		cvvLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JTextField cvv =
				new JTextField();

		cvv.setPreferredSize(
				new Dimension(300, 40)
		);


		//Billing address section

		JLabel billAddLabel =
				new JLabel("Billing Address:");

		billAddLabel.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		JTextField billAdd =
				new JTextField();

		billAdd.setPreferredSize(
				new Dimension(300, 40)
		);


		//Pay button

		JButton payButton =
				new JButton("Complete Payment");

		payButton.setPreferredSize(
				new Dimension(240, 50)
		);

		payButton.setFocusPainted(false);

		payButton.setBorderPainted(false);

		payButton.setBackground(
				new Color(155, 109, 255)
		);

		payButton.setForeground(Color.WHITE);

		payButton.setFont(
				new Font(
						"SansSerif",
						Font.BOLD,
						15
				)
		);


		//Adds all components to payment card

		gbc.gridx = 0;
		gbc.gridy = 0;

		paymentCard.add(labelForName, gbc);

		gbc.gridx = 1;

		paymentCard.add(payersName, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		paymentCard.add(labelForCardNumber, gbc);

		gbc.gridx = 1;

		paymentCard.add(cardNumberInput, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		paymentCard.add(cardTypeLabel, gbc);

		gbc.gridx = 1;

		paymentCard.add(cardTypeInput, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		paymentCard.add(expiryDateLabel, gbc);

		gbc.gridx = 1;

		paymentCard.add(expiryPanel, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		paymentCard.add(cvvLabel, gbc);

		gbc.gridx = 1;

		paymentCard.add(cvv, gbc);


		gbc.gridx = 0;
		gbc.gridy++;

		paymentCard.add(billAddLabel, gbc);

		gbc.gridx = 1;

		paymentCard.add(billAdd, gbc);


		gbc.gridx = 1;
		gbc.gridy++;

		paymentCard.add(payButton, gbc);


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


		setVisible(true);


		payButton.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e
			) {

				if (

					mainLogic(
						PayNewBooking.this,
						cardTypeInput,
						cardNumberInput,
						payersName,
						expiryMonth,
						expiryYear,
						cvv,
						booking,
						billAdd
					)

				) {

					DataManager.updatePayment(

				            booking.getBookingID(),
				            getCardType(),
				            getEncryptedCardNumber(),
				            getBillingAddress(),
				            getBookingFee()

				    );

				    DataManager.confirmBooking(
				            booking.getBookingID()
				    );

				    setVisible(false);

				    sendConfirmation();

				}

				else {

					JOptionPane.showMessageDialog(
							null,
							"Payment validation failed."
					);
				}
			}
		});
	}


	/**
	 * Does all assignments and checks
	 * @param cardType
	 * @param cardNumber
	 * @param cardName The name on the card
	 * @param expiryMonthInput
	 * @param expiryYearInput
	 * @param cvv
	 * @param order
	 * @return whether checks passed
	 */

	public boolean mainLogic(

		JFrame mainPayFrame,
		JComboBox<String> cardType,
		JTextField cardNumber,
		JTextField cardName,
		JTextField expiryMonthInput,
		JTextField expiryYearInput,
		JTextField cvv,
		Booking booking,
		JTextField billAdd

	) {

		String cardTypeToSet =
				cardType.getSelectedItem().toString();

		setCardType(cardTypeToSet);

		setCardName(cardName.getText());

		setBillingAddress(billAdd.getText());


		try {

			String formattedMonth =
					formatMonth(
							expiryMonthInput.getText()
					);

			setExpiryMonthAndYear(

					Integer.parseInt(formattedMonth),
					Integer.parseInt(expiryYearInput.getText())

			);

			setCVV(
					Integer.parseInt(cvv.getText())
			);

			setCardNumber(
					cardNumber.getText()
			);

			setStarredOutCardNumber();

		}

		catch (Exception ex) {

			JOptionPane.showMessageDialog(
					null,
					"Please enter valid numbers for card details."
			);

			return false;
		}


		setNewBookingPaymentIsBeingMadeFor(booking);


		setBookingFee(

				Integer.parseInt(
						booking.getBookingTime().substring(0, 1)
				),

				booking.getLuggageAmount(),

				booking.getEstimatedDuration()

		);


		if (

			isCVVAcceptable(getCVV())
			&& isExpiryDateValid(
					getExpiryMonth(),
					getExpiryYear()
			)
			&& isCardNumberValid(getCardNumber())

		) {

			return true;
		}

		else {

			return false;
		}
	}


	/**
	 * Get the accepted card types for the transaction.
	 */

	public String[] getAcceptedCardTypes() {

		return acceptedCardTypes;
	}


	/**
	 * Get the fee for the booking.
	 * @return bookingFee
	 */

	public double getBookingFee() {

		return bookingFee;
	}


	/**
	 * Get address relating to car
	 * @return billingAddress
	 */

	public String getBillingAddress() {

		return billingAddress;
	}


	/**
	 * Assigns billing address
	 * @param billingAddressToSet
	 */

	public void setBillingAddress(
			String billingAddress
	) {

		this.billingAddress = billingAddress;
	}


	/**
	 * Get card type
	 * @return cardType
	 */

	public String getCardType() {

		return cardType;
	}


	/**
	 * Check cardType is acceptable, and if so set it
	 * @param cardType
	 */

	public void setCardType(String cardType) {

		this.cardType = cardType;
	}


	/**
	 * Get card number
	 * @return card number
	 */

	public long getCardNumber() {

		return cardNumber;
	}


	/**
	 * Assign card number
	 * @param cardNumber
	 */

	public void setCardNumber(
			String cardNumber
	) {

		this.cardNumber =
				Long.parseLong(cardNumber);
	}


	/**
	 * Get the name of the holder of the card
	 * @return cardName
	 */

	public String getCardName() {

		return cardName;
	}


	/**
	 * Set name of card owner
	 * @param cardName The name on the card
	 */

	public void setCardName(
			String cardName
	) {

		this.cardName = cardName;
	}


	/**
	 * Get card CVV
	 * @return the card CVV
	 */

	public int getCVV() {

		return CVV;
	}


	/**
	 * Assign CVV if valid
	 * @param cvv The CVV to check
	 */

	public void setCVV(int cvv) {

		if (isCVVAcceptable(cvv)) {

			this.CVV = cvv;
		}
	}


	/**
	 * Get the card expiry year
	 * @return the expiry year of the card
	 */

	public int getExpiryYear() {

		return expiryYear;
	}


	/**
	 * Get the card expiry month
	 * @return the expiry month of the card
	 */

	public Integer getExpiryMonth() {

		return expiryMonth;
	}


	/**
	 * Check if the expiry date is valid and if so assign it
	 * @param expiryMonth
	 * @param expiryYear
	 */

	public void setExpiryMonthAndYear(
			Integer expiryMonth,
			int expiryYear
	) {

		if (
				isExpiryDateValid(
						expiryMonth,
						expiryYear
				)
		) {

			this.expiryMonth = expiryMonth;

			this.expiryYear = expiryYear;
		}
	}


	/**
	 * getNewBooking - returns the booking payment is linked to
	 * @return the booking linked to the payment
	 */

	public Booking getNewBooking() {

		return booking;
	}


	/**
	 * setNewBookingPaymentIsBeingMadeFor - links payment to the booking
	 * @param booking
	 */

	public void setNewBookingPaymentIsBeingMadeFor(
			Booking booking
	) {

		this.booking = booking;
	}


	/**
	 * Naive check to ensure the CVV is the right format for any <i>possible</i> CVV.
	 * This does not validate if it is the correct individual CVV for a card,
	 * nor does it check if the format of CVV is valid for the specific type of card
	 * or issuer.
	 * @param cvv
	 * @return True if CVV is a valid CVV. Does not check it is correct.
	 */

	public Boolean isCVVAcceptable(
			int cvv
	) {

		if (
				(cvv >= 100)
				&& (cvv <= 9999)
		) {

			return true;
		}

		else {

			return false;
		}
	}


	/**
	 * Get rid of 0 if month style is of format 0X.
	 * @param month
	 * @return the correct implemented month (without 0)
	 */

	public static String formatMonth(
			String month
	) {

	    if (month.startsWith("0")) {

	    	month = month.replaceFirst("0", "");
	    }

	    return month;
	}


	/**
	 * Check if expiry date is valid, i.e. after today.
	 * @param inputMonth
	 * @param inputYear
	 * @return true if expiry date is after today, false otherwise.
	 */

	public Boolean isExpiryDateValid(
			int inputMonth,
			int inputYear
	) {

		LocalDate today =
				LocalDate.now();

	    Integer month =
	    		today.getMonthValue();

		int year =
				today.getYear();

		if (
				(month >= inputMonth)
				&& (year >= inputYear)
		) {

			return false;
		}

		else {

			return true;
		}
	}


	/**
	 * Naive check if the card number is between 16 and 19 digits as standard.
	 * Does not check whether the card number matches from the issuer, nor does it
	 * check that the card number format matches the specific format of the issuer.
	 * @param cardNumber
	 * @return a boolean if cardNumber is within reasonable length
	 */

	public Boolean isCardNumberValid(
			Long cardNum
	) {

		if (
				(cardNum.toString().length() >= 16)
				&& (cardNum.toString().length() <= 19)
		) {

			return true;
		}

		else {

			return false;
		}
	}


	/**
	 * Generate the additional booking fee for the order
	 * @param timeDay The time of day booking was made
	 * @param distance Travelling distance
	 * @param numPeople Number of people the booking is made for
	 */

	public void setBookingFee(
			int timeDay,
			int numLuggage,
			int estimateDuration
	) {

		double bookingFeeToAssign = 0;

		double feePerLuggage = 0.5;

		bookingFeeToAssign =
				bookingFeeToAssign
				+ (feePerLuggage * numLuggage);

		if (timeDay >= 9 && timeDay <= 17) {

			bookingFeeToAssign += 10;
		}

		else {

			bookingFeeToAssign += 5;
		}

		if (estimateDuration <= 60) {

			bookingFeeToAssign += 5;
		}

		else {

			bookingFeeToAssign +=
					(Math.round(estimateDuration) / 60) * 5;
		}

		this.bookingFee = bookingFeeToAssign;
	}


	/**
	 * getEncryptedCardNumber - returns ** followed by last 4 card digits
	 * @return encryptedCardNumber
	 */

	public String getEncryptedCardNumber() {

		return starredOutCardNumber;
	}


	/**
	 * Star out the card number so that it can be displayed securely on screen.
	 */

	public void setStarredOutCardNumber() {

		String getCardNum =
				Long.toString(getCardNumber());

		String last4digits =
				getCardNum.substring(
						getCardNum.length() - 4,
						getCardNum.length()
				);

		int starLenght =
				getCardNum.length() - 4;

		String stars = "";

		for (int i = 0; i <= starLenght; i++) {

			stars = stars + "*";
		}

		String starredOutCardNumber =
				stars + last4digits;

		this.starredOutCardNumber =
				starredOutCardNumber;
	}


	/**
	 * Fires confirmation screen
	 */

	public void sendConfirmation() {

		ConfirmationMessage confirm =
				new ConfirmationMessage(
						this,
						booking
				);
	}


	@Override
	public void actionPerformed(
			ActionEvent e
	) {

		// TODO Auto-generated method stub
	}
}

