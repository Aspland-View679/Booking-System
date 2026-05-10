package COMP500GroupProjectPackage;
import java.time.*;

public class PayBooking {
	
	private Long cardNumber;
	private String cardName;
	private String cardType;
	private String bank;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard"};
	private int expiryYear;
	private Integer expiryMonth; 
	private MakeBooking bookingPaymentIsBeingMadeFor;
	private String billingAddress;	
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
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * setCardNumber - assigns card number
	 * @param cardNumber
	 */

	public void setCardNumber(int cardNumber) {
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
	 * getBank - returns name of card's bank
	 * @return the bank the card belongs to
	 */

	public String getBank() {
		return bank;
	}

	/**
	 * setBank - assigns bank name
	 * @param the bank name
	 */
	
	public void setBank(String bank) {
		this.bank = bank;
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
	 * getBookingPaymentIsBeingMadeFor - returns the booking payment is linked to
	 * @return the booking linked to the payment
	 */

	public MakeBooking getBookingPaymentIsBeingMadeFor() {
		return bookingPaymentIsBeingMadeFor;
	}
	
	/**
	 * setBookingPaymentIsBeingMadeFor - links payment to the booking
	 * @param bookingPaymentIsBeingMadeFor
	 */

	public void setBookingPaymentIsBeingMadeFor(MakeBooking bookingPaymentIsBeingMadeFor) {
		this.bookingPaymentIsBeingMadeFor = bookingPaymentIsBeingMadeFor;
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
	
}
