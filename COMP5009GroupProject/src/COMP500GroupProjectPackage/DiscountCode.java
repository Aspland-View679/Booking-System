package COMP500GroupProjectPackage;

/**
 * Unified type for representing a single Discount Code and its percentage.
 * @author <a href="mailto:njw38@kent.ac.uk">Alexis Williams</a>
 * @version 2026-05-11 
 */
public record DiscountCode(
	String code,
	float discountPercentage
	// We may need checks for a discount code? 
	// Predicate<SomeSortOfStateType> checkIsValid
) {

}
