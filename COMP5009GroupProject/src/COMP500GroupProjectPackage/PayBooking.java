package COMP500GroupProjectPackage;
import java.time.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import java.swing.*;

public class PayBooking {
	
	private Long cardNumber;
	private String cardName;
	private String cardType;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard", "Visa Debit"};
	private int expiryYear;
	private Integer expiryMonth; 
	private Order orderMade;
	private String billingAddress;	
	private int bookingFee
	
	public PayBooking(Order orderToSet) {
		
		this.orderMade = orderToSet;
		
		JFrame mainPayFrame = new JFrame();
		
		mainPayFrame.setSize(500, 500);
		
		mainPayFrame.setVisible(true);
		
		JTextField payersName = new JTextField();
		
		payersName.setBounds(230, 230, 30, 30);
		mainPayFrame.add(payersName);
		
		JLabel labelForName = new JLabel();
		labelForName.setBounds(190, 230, 30, 15);
		labelForName.setText("Enter your name here: ");
		mainPayFrame.add(labelForName);
		
		JTextField cardNumberInput = new JTextField();
		cardNumberInput.setBounds(230, 240, 60, 60);
		mainPayFrame.add(cardNumberInput);
		
		JLabel labelForCardNumber = new JLabel();
		labelForCardNumber.setBounds(210, 240, 60, 15);
		labelForCardNumber.setText("Enter your card number here");
		mainPayFrame.add(labelForCardNumber);
		
		JList<> cardTypeInput = new JList(acceptedCardTypes);
		mainPayFrame.add(cardTypeInput);
		
		JTextField expiryMonth = new JTextField();
		expiryMonth.setBounds(230, 250, 10, 10);

		JTextField expiryYear = new JTextField();
		expiryYear.setBounds(245, 250, 15, 15);
		
		mainPayFrame.add(expiryYear);
		mainPayFrame.add(expiryMonth);
		
	}
	/**
	 * getBookingFee - returns address relating to car
	 * @return bookingFee
	 */
	
	public int getBookingFee() {
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

	public void setCardType(String cardType) {
		if (isCardAcceptable(cardType)) {
			this.cardType = cardType;
		}
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

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
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
	 * getOrderBeingMade - returns the order payment is linked to
	 * @return the booking linked to the payment
	 */

	public Order getOrderBeingMade() {
		return orderMade;
	}
	
	/**
	 * setOrderPaymentIsBeingMadeFor - links payment to the booking
	 * @param bookingPaymentIsBeingMadeFor
	 */

	public void setOrderPaymentIsBeingMadeFor(Order orderBeingMade) {
		this.orderMade = orderBeingMade;
	}

	/**
	 * isCardAcceptable - ensures that a valid card is entered
	 * @param inputCard
	 * @return a boolean of whether the card is accepted or rejected
	 */
	
	public Boolean isCardAcceptable (String inputCard) {
		
		for (int i = 0; i <= acceptedCardTypes.length; i++) {
			if (acceptedCardTypes[i] == inputCard) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * isCVVAcceptable - ensures the CVV is the right format
	 * @param inputCVV
	 * @return a true/false value if CVV is right format (standard is 3-4 digits)
	 */
	
	public Boolean isCVVAcceptable(int inputCVV) {
		
		if ((inputCVV >= 100) || (inputCVV <= 9999)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * isExpiryDateValid - ensures date is correct
	 * @param inputMonth
	 * @param inputYear
	 * @return a boolean stating if the date is valid or not
	 */
	
	
	public Boolean isExpiryDateValid(Integer inputMonth, int inputYear) {
		
		LocalDate today = LocalDate.now();
	    Integer month = today.getMonthValue();
		
	    String month2 = inputMonth.toString();
	    
	    
	    //month returns a value between 1 and 12, however user might input 01,02,03... so need to align format with
	     //the following code
	    
	    if (month2.startsWith("0")) {
	    	month2 = month2.replaceFirst("0", "");
	    	inputMonth = Integer.parseInt(month2);
	    }
		
		int year = today.getYear();
		
		
		if ((month > inputMonth) && (year > inputYear)) {
			return false;
		} else {
			return true;
		}

		
	}
	
	/**
	 * isCardNumberValid - ensures that the card number is the right length
	 * @param cardNumber 
	 * @return a boolean if cardNumber is within reasonable length (standard is 16-19)
	 */
	
	public Boolean isCardNumberValid(Long cardNum) {
		
		if ((cardNum.toString().length() >= 16) && (cardNum.toString().length() <= 19)) {
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
		
		int bookingFeeToAssign = 0;
		const feePerPerson = 0.5;
		bookingFeeToAssign = bookingFeeToAssign + (feePerPerson * numPeople);
		if (timeDay >= 9 && timeDay <= 17) {
			bookingFeeToAssign+= 10;
		} else {
			bookingFeeToAssign += 5;
		}
		if (distance <= 100) {
			bookingFeeToAssign += 5;
		} else {
			bookingFeeToAssign = (Math.round(distance))/100 * 5; 
		}
		
		this.bookingFee = bookingFeeToAssign;
		
	}
	
	
}
