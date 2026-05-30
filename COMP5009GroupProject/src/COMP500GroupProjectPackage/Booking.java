package COMP500GroupProjectPackage;


/**
 * Stores all data of the booking 
 */

public class Booking {
    
    
	private String airport;
	private String pickupLocation;
	private String bookingDate;
	private String bookingTime;
	
	private int luggageAmount;
	private int estimatedDuration;
	private int bookingID;
	
	private double totalPrice;
	
	private boolean confirmed;
	private boolean cancelled;

	
	public Booking(
			String airport,
			String pickupLocation,
			String bookingDate,    // Saves the data you inputed on new Booking
			String bookingTime,
			int luggageAmount,
			int estimatedDuration,
			double totalPrice
	) {
		this.airport = airport;
		this.pickupLocation = pickupLocation;  // this section assign a function to a method or object to pass through
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		
		this.luggageAmount = luggageAmount;
		this.estimatedDuration = estimatedDuration;
		
		this.totalPrice = totalPrice;
		
		 confirmed = false; // Set to false as it isnt confirmed or payed
		 cancelled = false;
	}
	
    public String getAirport() {
        return airport;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public int getLuggageAmount() {
        return luggageAmount;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public boolean isCancelled() {
        return cancelled;
    }
    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setLuggageAmount(int luggageAmount) {
        this.luggageAmount = luggageAmount;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
	public int getBookingID() {
	    return bookingID;
	}

	public void setBookingID(int bookingID) {
	    this.bookingID = bookingID;
	}
} //This section is getters and set to ensure all the fields are filled
