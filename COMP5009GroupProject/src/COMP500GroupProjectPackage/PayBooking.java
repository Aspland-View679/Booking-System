package COMP500GroupProjectPackage;

public class PayBooking {
	
	private int cardNumber;
	private String cardName;
	private String bank;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard"};
	
	
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
	
}
