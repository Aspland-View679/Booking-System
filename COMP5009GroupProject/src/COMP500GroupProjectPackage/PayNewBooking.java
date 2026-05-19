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

public class PayNewBooking extends JFrame implements ActionListener {
	
	private Long cardNumber;
	private String cardName;
	private String cardType;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard", "Visa Debit", "Apple card"};
	private int expiryYear;
	private int expiryMonth; 
	private NewBooking booking;
	private String billingAddress;	
	private double bookingFee;
	private String encryptedCardNumber;
	

	public static void main(String[] args) {
		PayNewBooking pay = new PayNewBooking(null);
	}
	
	public PayNewBooking(NewBooking bookingToSet) {
		
		this.booking = bookingToSet;
		
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
		labelForCardNumber.setBounds(90, 150, 160, 30);
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
				
				if (mainLogic(mainPayFrame, cardTypeInput, cardNumberInput, payersName, expiryMonth, expiryYear, cvv, bookingToSet)) {
					mainPayFrame.setVisible(false);
		//	ConfirmationMessage confirm = new ConfirmationMessage(this);		//Here the confirmation GUI should be created for the next step in the confirmation constructor
				} else {
					System.out.println("ERROR");
			}
			}
			
		});		
		
		}
	
	/**
	 * mainLogic - does all assignments and checks
	 * @param cardType
	 * @param cardNumber
	 * @param cardName
	 * @param expiryMonthInput
	 * @param expiryYearInput
	 * @param cvv
	 * @param order
	 * @return whether checks passed
	 */
	
	public boolean mainLogic(JFrame mainPayFrame, JComboBox<String> cardType, JTextField cardNumber, JTextField cardName, JTextField expiryMonthInput, JTextField expiryYearInput, JTextField cvv, NewBooking booking) {
		String cardTypeToSet = cardType.getSelectedItem().toString();
		setCardType(cardTypeToSet);
		setCardName(cardName.getText());
		try {
			System.out.println(expiryMonthInput.getText());
			String formattedMonth = formatMonth(expiryMonthInput.getText());
			setExpiryMonthAndYear(Integer.parseInt(formattedMonth), Integer.parseInt(expiryYearInput.getText()));
			setCVV(Integer.parseInt(cvv.getText()));
			setCardNumber(cardNumber.getText());
			setEncryptedCardNumber();
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
		setBookingFee(1, 1.00, 1);
		
		if (isCVVAcceptable(getCVV()) && isExpiryDateValid(getExpiryMonth(), getExpiryYear()) && isCardNumberValid(getCardNumber())) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public String[] getAcceptedCardTypes() {
		return acceptedCardTypes;
		}
	
	/**
	 * getBookingFee - returns address relating to car
	 * @return bookingFee
	 */
	
	public double getBookingFee() {
		return bookingFee;
	}
	
	
	/**
	 * getBillingAddress - returns address relating to car
	 * @return billingAddress
	 */
	
	public String getBillingAddress() {
		return billingAddress;
	}
	
	/**
	 * setBillingAdress - assigns billing address
	 * @param billingAddressToSet
	 */
	
	public void setBillingAddress(String billingAddressToSet) {
		billingAddress = billingAddressToSet;
	}
	
	
	/**
	 * getCardType - returns card type
	 * @return cardType
	 */
	
	public String getCardType() {
		return cardType;
	}
	
	/**
	 * setCardType - checks cardType is acceptable, and if so sets it
	 * @param cardType
	 */

	public void setCardType(String cardTypeInput) {
			cardType = cardTypeInput;
	}

	/**
	 * getCardNumber - returns card number
	 * @return card number
	 */
	
	public long getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * setCardNumber - assigns card number
	 * @param cardNumber
	 */

	public void setCardNumber(String cardNumber) {
		this.cardNumber = Long.parseLong(cardNumber);
	}
	
	/**
	 * getCardName - returns name of the holder of the card
	 * @return cardName
	 */

	public String getCardName() {
		return cardName;
	}
	
	/**
	 * setCardName - sets name of card owner
	 * @param cardName
	 */

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	/**
	 * getCVV - returns card CVV
	 * @return the card CVV
	 */
	
	public int getCVV() {
		return CVV;
	}
	
	/**
	 * setCVV - assigns CVV if valid
	 * @param cVV
	 */

	public void setCVV(int cVV) {
		
		if (isCVVAcceptable(cVV)) {
			CVV = cVV;
		}
	}

	
	/**
	 * returns card expiry year
	 * @return the expiry year of the card
	 */
	
	public int getExpiryYear() {
		return expiryYear;
	}


	/**
	 * returns card expiry month
	 * @return the expiry month of the card
	 */
	
	public Integer getExpiryMonth() {
		return expiryMonth;
	}
	
	/**
	 * setExpiryMonthAndYear - checks if the expiry date is valid and if so assigns it
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
	 * getBooking - returns the booking payment is linked to
	 * @return the booking linked to the payment
	 */

	public NewBooking getBooking() {
		return booking;
	}
	
	/**
	 * setNewBookingPaymentIsBeingMadeFor - links payment to the booking
	 * @param booking
	 */

	public void setNewBookingPaymentIsBeingMadeFor(NewBooking booking) {
		this.booking = booking;
	}

	
	/**
	 * isCVVAcceptable - ensures the CVV is the right format
	 * @param inputCVV
	 * @return a true/false value if CVV is right format (standard is 3-4 digits)
	 */
	
	public Boolean isCVVAcceptable(int inputCVV) {
		
		if ((inputCVV >= 100) && (inputCVV <= 9999)) {			//This is not checking the actual value, but a checker for the length of CVV 
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * formatMonth - gets rid of 0 if month style is 01,02...09
	 * @param inputMonth
	 * @return the correct implemented month (without 0)
	 */
	
	public String formatMonth(String inputMonth) {
		   
	  
	    if (inputMonth.startsWith("0")) {
	    	inputMonth = inputMonth.replaceFirst("0", "");
	    }
	    return inputMonth;
	    
	}
	
	/**
	 * isExpiryDateValid - ensures date is correct
	 * @param inputMonth
	 * @param inputYear
	 * @return a boolean stating if the date is valid or not
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
	 * isCardNumberValid - ensures that the card number is the right length
	 * @param cardNumber 
	 * @return a boolean if cardNumber is within reasonable length
	 */
	
	public Boolean isCardNumberValid(Long cardNum) {
		
		if ((cardNum.toString().length() >= 16) && (cardNum.toString().length() <= 19)) {		//16-19 digits is a standard length
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * setBookingFee - generates the additional booking fee for the order
	 * @param timeDay - the time of day booking was made
	 * @param distance - travelling distance
	 * @param numPeople - number of people the booking is made for
	 */
	
	public void setBookingFee(int timeDay, double distance, int numPeople) {
		
		double bookingFeeToAssign = 0;
		double feePerPerson = 0.5;
		bookingFeeToAssign = bookingFeeToAssign + (feePerPerson * numPeople);
		if (timeDay >= 9 && timeDay <= 17) {		//Peak-time being normal 9-5 shift
			bookingFeeToAssign+= 10;
		} else {									//Off-peak times
			bookingFeeToAssign += 5;
		}
		if (distance <= 100) {
			bookingFeeToAssign += 5;
		} else {
			bookingFeeToAssign += (Math.round(distance)/100) * 5; 
		}
		
		this.bookingFee = bookingFeeToAssign;
		
	}
	
	/**
	 * getEncryptedCardNumber - returns ** followed by last 4 card digits
	 * @return encryptedCardNumber
	 */
	public String getEncryptedCardNumber() {
		return encryptedCardNumber;
	}
	
	public void setEncryptedCardNumber() {
		
		String getCardNum = Long.toString(getCardNumber());		
		String last4digits = getCardNum.substring(getCardNum.length() - 4, getCardNum.length());	//This gets the last 4 digits
		String stars = "*********";
		String encCardNumber = stars + last4digits;
		this.encryptedCardNumber = encCardNumber;
	}


	
	
}
