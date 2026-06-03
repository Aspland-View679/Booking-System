
package COMP500GroupProjectPackage;

/**
 * Represents a guest account.
 * @author ra711
 * @version 2026-05-11
 */
public class GuestAccount extends UserAccount {

	public GuestAccount() {

		super(

				"Guest",
				"guest@guest.com",
				"",
				"Guest"
		);
	}
}
