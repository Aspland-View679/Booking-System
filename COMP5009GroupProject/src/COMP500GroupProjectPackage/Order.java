package COMP500GroupProjectPackage;

import java.util.ArrayList;

/**
 * Represents the collation of all items in the order and calculates different
 * parameters such as the subtotal and total after tax for formatting into an 
 * email or message on the screen.
 * @author <a href="mailto:njw38@kent.ac.uk">Alexis Williams</a>
 * @version 2026-05-11
 */
public class Order {
	
	// --- Constructor args ---
	
	
	
	/**
	 * This is the arraylist of all of the items in this particular order.
	 */
	private ArrayList<OrderItem> orderItems;
	
	/**
	 * If an order has not just a per-item discount but a discount code, we
	 * also need to know that.
	 */
	private DiscountCode overallDiscountPercentage;
	
	/**
	 * The account that made the order.
	 */
	private UserAccount account;
	
	
	
	// --- Internals ---
	
	
	
	/**
	 * The total of all of the individual prices of each <code>OrderItem</code>. 
	 */
	private float subtotal;
	
	/**
	 * This is the total of all of the added tax from the taxable items. 
	 * As much as we are combining the tax on the <i>End user's side</i>,
	 * we should make sure that they are seperate on the back-end for purposes
	 * of declaring tax (hypothetically).
	 */
	private float taxTotal;
	
	/**
	 * Total before any sort of discount has been applied. Worth showing to the user
	 * in the receipt so that they know exactly the financial calcuations that have
	 * been made.
	 */
	private float grossTotal;
	
	/**
	 * Total of all of the money to be subtracted from the gross total due to 
	 * discounts.
	 */
	private float discountTotal;
	
	/**
	 * The final total that the user will actually have to/has paid, with all tax and 
	 * discount deductions made. 
	 */
	private float netTotal;
}
