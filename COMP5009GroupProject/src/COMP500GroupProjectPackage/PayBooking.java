package COMP500GroupProjectPackage;
import java.time.*;

public class PayBooking {
	
	private int cardNumber;
	private String cardName;
	private String bank;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard"};
	private int ExpiryYear;
	private Integer ExpiryMonth; 
	
	
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
	
}
