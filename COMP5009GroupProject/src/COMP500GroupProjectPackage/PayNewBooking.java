package COMP500GroupProjectPackage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import javax.swing.*;

public class PayNewBooking 
extends JFrame 
implements ActionListener {
	private Long cardNumber;
	private String cardName;
	private String cardType;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard", "Visa Debit", "Apple card"};
	private int expiryYear;
	private int expiryMonth; 
	private Booking booking;
	private String billingAddress;	
	private double bookingFee;
	private String starredOutCardNumber;
	
	public static void main(String[] args) {
		PayNewBooking pay = new PayNewBooking(null);
	}
	
	public PayNewBooking(
		Booking booking
	) {
		
		this.booking = booking;
		
		JFrame mainPayFrame = new JFrame();
		
		mainPayFrame.setSize(1000, 1000);
				
		JTextField payersName = new JTextField();
		
		payersName.setBounds(240, 100, 130, 30);
		mainPayFrame.add(payersName);
		
		JLabel labelForName = new JLabel();
		labelForName.setBounds(90, 100, 150, 30);
		labelForName.setText("Enter your name here: ");
		mainPayFrame.add(labelForName);
		
		JTextField cardNumberInput = new JTextField();
		cardNumberInput.setBounds(240, 150, 130, 30);
		mainPayFrame.add(cardNumberInput);
		
		JLabel labelForCardNumber = new JLabel();
		labelForCardNumber.setBounds(90, 150, 200, 30);
		labelForCardNumber.setText("Enter your card number: ");
		mainPayFrame.add(labelForCardNumber);
		
		JComboBox<String> cardTypeInput = new JComboBox(acceptedCardTypes);	//This automatically populates the picklist from the field acceptedCardTypes
		cardTypeInput.setBounds(240, 190, 120, 30);
		mainPayFrame.add(cardTypeInput);
		
		JLabel cardTypeLabel = new JLabel();
		cardTypeLabel.setBounds(90, 190, 130, 30);
		cardTypeLabel.setText("Choose card type: ");
		mainPayFrame.add(cardTypeLabel);
		
		JTextField expiryMonth = new JTextField();
		expiryMonth.setBounds(240, 230, 50, 30);
		mainPayFrame.add(expiryMonth);

		JTextField expiryYear = new JTextField();
		expiryYear.setBounds(290, 230, 60, 30);
		
		mainPayFrame.add(expiryYear);
		
		JLabel expiryDateLabel = new JLabel();
		expiryDateLabel.setBounds(50, 230, 230, 30);
		expiryDateLabel.setText("Enter expiry month, then date: ");
		mainPayFrame.add(expiryDateLabel);

		JTextField cvv = new JTextField();
		cvv.setBounds(240, 270, 100, 30);
		mainPayFrame.add(cvv);
		
		JLabel cvvLabel = new JLabel();
		cvvLabel.setBounds(140, 255, 100, 60);
		cvvLabel.setText("Enter CVV here: ");
		mainPayFrame.add(cvvLabel);
		
		JButton payButton = new JButton();
		payButton.setBounds(250, 300, 100, 50);
		payButton.setText("Click to pay");
		payButton.setBackground(Color.pink);
		mainPayFrame.add(payButton);
		
		JTextField test = new JTextField();
		mainPayFrame.add(test);
		
		mainPayFrame.setVisible(true);

		payButton.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (
					mainLogic(
						mainPayFrame, 
						cardTypeInput, 
						cardNumberInput, 
						payersName, 
						expiryMonth, 
						expiryYear, 
						cvv, 
						booking
					)
				) {
					mainPayFrame.setVisible(false);
					sendConfirmation();
				} else {
					System.out.println("ERROR");
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
		Booking booking
	) {
		String cardTypeToSet = cardType.getSelectedItem().toString();
		setCardType(cardTypeToSet);
		setCardName(cardName.getText());
		
			
		try {
			System.out.println(expiryMonthInput.getText());
			String formattedMonth = formatMonth(expiryMonthInput.getText());
			
			setExpiryMonthAndYear(
					Integer.parseInt(formattedMonth), 
					Integer.parseInt(expiryYearInput.getText())
			);
			
			setCVV(Integer.parseInt(cvv.getText()));
			setCardNumber(cardNumber.getText());
			setStarredOutCardNumber();
			
		} catch (Exception ex) {
			System.out.println("Error");
			JTextField showError = new JTextField();
			showError.setBounds(250, 350, 300, 30);
			showError.setText("error");
			mainPayFrame.add(showError);
			JTextField showError2 = new JTextField();
			showError2.setBounds(250, 360, 300, 30);
			showError2.setText("Please check you've enter numbers for card number, cvv and expiry dates");
			mainPayFrame.add(showError);
		
		}
		
		setNewBookingPaymentIsBeingMadeFor(booking);
		
		setBookingFee(
				Integer.parseInt(booking.getBookingTime()), 
				booking.getLuggageAmount(), 
				booking.getEstimatedDuration()
		);
		
		if (
			isCVVAcceptable(getCVV()) 
			&& isExpiryDateValid(getExpiryMonth(), getExpiryYear()) 
			&& isCardNumberValid(getCardNumber())) 
		{
			return true;
		} else {
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
	
	public void setBillingAddress(String billingAddress) {
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
	public void setCardNumber(String cardNumber) {
		this.cardNumber = Long.parseLong(cardNumber);
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
	public void setCardName(String cardName) {
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
	public void setExpiryMonthAndYear(Integer expiryMonth, int expiryYear) {
		if (isExpiryDateValid(expiryMonth, expiryYear)) {
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
	public void setNewBookingPaymentIsBeingMadeFor(Booking booking) {
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
	public Boolean isCVVAcceptable(int cvv) {
		if ((cvv >= 100) && (cvv <= 9999)) {			//This is not checking the actual value, but a checker for the length of CVV 
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get rid of 0 if month style is of format 0X.
	 * @param month
	 * @return the correct implemented month (without 0)
	 */
	
	public static String formatMonth(String month) {
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
	public Boolean isExpiryDateValid(int inputMonth, int inputYear) {
		
		LocalDate today = LocalDate.now();
	    Integer month = today.getMonthValue();	 
		
		int year = today.getYear();
		
		if ((month >= inputMonth) && (year >= inputYear)) {
			return false;
		} else {
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
	public Boolean isCardNumberValid(Long cardNum) {
		if ((cardNum.toString().length() >= 16) && (cardNum.toString().length() <= 19)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Generate the additional booking fee for the order
	 * @param timeDay The time of day booking was made
	 * @param distance Travelling distance
	 * @param numPeople Number of people the booking is made for
	 */
	public void setBookingFee(int timeDay, int numLuggage, int estimateDuration) {
		
		double bookingFeeToAssign = 0;
		double feePerLuggage = 0.5;
		bookingFeeToAssign = bookingFeeToAssign + (feePerLuggage * numLuggage);
		if (timeDay >= 9 && timeDay <= 17) {		// Peak-time being normal 9-5 shift
			bookingFeeToAssign+= 10;
		} else {									// Off-peak times
			bookingFeeToAssign += 5;
		}
		if (estimateDuration <= 60) {
			bookingFeeToAssign += 5;
		} else {
			bookingFeeToAssign += (Math.round(estimateDuration)/60) * 5;  // Checks against 60mins
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
		String getCardNum = Long.toString(getCardNumber());		
		String last4digits = getCardNum.substring(getCardNum.length() - 4, getCardNum.length());
		int starLenght = getCardNum.length() - 4;
		String stars = "";
		for (int i = 0; i <= starLenght; i++) {
			stars = stars + "*";
			}
		String starredOutCardNumber = stars + last4digits;
		this.starredOutCardNumber = starredOutCardNumber;
	}

	/**
	 * Fires confirmation screen
	 */
	public void sendConfirmation() {
		ConfirmationMessage confirm = new ConfirmationMessage(this, booking);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


