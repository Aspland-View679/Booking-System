package COMP500GroupProjectPackage;

public class PayBooking {
	
	private int cardNumber;
	private String cardName;
	private String bank;
	private int CVV;
	private String[] acceptedCardTypes = {"Visa", "Mastercard"};
	
	
	public Boolean checkCardType(String inputCard) {
		
		for (int i = 0; i <= acceptedCardTypes.length; i++) {
			if (acceptedCardTypes[i] == inputCard) {
				return true;
			}
		}
		
		return false;
		
	}
}
