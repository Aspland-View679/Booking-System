package COMP500GroupProjectPackage;

public class PayBooking {
	
	private int cardNumber;
	private String cardName;
	private String bank;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard"};
	
	
	/**
	 * checkCardType - ensures that a valid card is entered into the field
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
}
