
/**
 * The DessertShoppe class contains constants such as the tax rate as well the
 * name of the store, the maximum size of an item name and the width used to
 * display the costs of the items on the receipt. Your code should use these
 * constants wherever necessary! The DessertShoppe class also contains the
 * cents2dollarsAndCentsmethod which takes an integer number of cents and
 * returns it as a String formatted in dollars and cents. For example, 105 cents
 * would be returned as "1.05".
 */
public class DessertShoppe {
	public final static double TAX_RATE = 11.1;
	public final static String STORE_NAME = "M-M Dessert Shoppe";
	public final static int MAX_ITEM_NAME_SIZE = 25;
	public final static int COST_WIDTH = 6;

	public static String cents2dollarsAndCents(int cents) {
		String str = "";

		if (cents < 0) {
			str += "-";
			cents *= -1;
		}

		int dollars = cents / 100;
		cents = cents % 100;

		if (dollars > 0) {
			str += dollars;
		}

		str += ".";

		if (cents < 10) {
			str += "0";
		}

		str += cents;
		return str;
	}

}
