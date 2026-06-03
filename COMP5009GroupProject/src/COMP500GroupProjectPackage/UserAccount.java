package COMP500GroupProjectPackage;

/**
 * Represents an account that has made the bookings.
 * @author ra711
 * @version 2026-05-11
 */
public class UserAccount {

	private int userID;  //All fields being used for user accounts

	private String username;

	private String email;

	private String password;

	private String accountType;


	public UserAccount(

			String username,
			String email,
			String password,  //Ensures these are the only data being inputted
			String accountType

	) {

		this.username = username;
                                     //Calling for the database
		this.email = email;

		this.password = password;

		this.accountType = accountType;
	}


	public int getUserID() {

		return userID;
	}


	public void setUserID(int userID) {

		this.userID = userID;
	}


	public String getUsername() {

		return username;
	}


	public String getEmail() {

		return email;
	}


	public String getPassword() {

		return password;
	}
                                    //Getters and setters for those to be stored and called when logging in

	public String getAccountType() {

		return accountType;
	}
}
