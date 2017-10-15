/**
 * This class keeps track of the entered Dessert Items. It calculates the tax
 * and total, then creates a formatted String in the form of a receipt.
 */

public class Checkout {
	public static int numberOfItems = 0;
	private DessertItem[] itemArray;

	public Checkout() {
		this.itemArray = new DessertItem[1000];
	}

	public void enterItem(DessertItem newItem) {
		itemArray[numberOfItems] = newItem;
		numberOfItems++;
	}

	public void clear() {
		itemArray = new DessertItem[100];
		numberOfItems = 0;
	}

	public int numberOfItems() {
		return numberOfItems;
	}

	public int totalCost() {
		int cost = 0;
		for (int i = 0; i < numberOfItems; i++) {
			cost += itemArray[i].getCost();
		}
		return cost;
	}

	public int totalTax() {
		return (int) Math.round(totalCost() * DessertShoppe.TAX_RATE / 100);

	}

	public java.lang.String toString() {
		String s = "";
		String leftColumn = "%-" + DessertShoppe.MAX_ITEM_NAME_SIZE + "s";
		String rightColumn = "%" + DessertShoppe.COST_WIDTH + "s";
		int recieptWidth = DessertShoppe.MAX_ITEM_NAME_SIZE + DessertShoppe.COST_WIDTH;

		// Name of store underlined by row of dashes
		String storeName = DessertShoppe.STORE_NAME;
		String dashes = "";
		// create string of dashes equal to length of store name
		for (int i = 0; i < storeName.length(); i++) {
			dashes += "-";
		}
		// to center, empty strings with a set width to act as padding is added
		String padding = "%" + (recieptWidth - storeName.length()) / 2 + "s";
		s += String.format(padding + "%s\n" + padding + "%s\n\n", "", storeName, "", dashes);
		for (int i = 0; i < numberOfItems; i++) {
			String itemName = itemArray[i].getName();
			String itemCost = DessertShoppe.cents2dollarsAndCents(itemArray[i].getCost());
			if (itemArray[i] instanceof Candy) {
				double candyWeight = ((Candy) itemArray[i]).getWeight();
				String candyCost = DessertShoppe.cents2dollarsAndCents(((Candy) itemArray[i]).getPricePerLb());
				s += String.format("%,.2f lbs. @ %s /lb.\n", candyWeight, candyCost);
				// %,.2f is a double with 2 decimal places of precision
			}
			if (itemArray[i] instanceof Cookie) {
				int numCookies = ((Cookie) itemArray[i]).getNumCookie();
				String cookieCost = DessertShoppe.cents2dollarsAndCents(((Cookie) itemArray[i]).getPricePerDozen());
				s += String.format("%d @ %s /dz.\n", numCookies, cookieCost);
			}

			if (itemArray[i] instanceof Sundae) {
				String nameTopping = ((Sundae) itemArray[i]).getNameTopping();
				s += String.format("%s Sundae with\n", nameTopping);
			}
			s += String.format(leftColumn + rightColumn + "\n", itemName, itemCost);
		}
		String tax = DessertShoppe.cents2dollarsAndCents(totalTax());
		s += String.format(leftColumn + rightColumn + "\n", "Tax", tax);

		// Total
		String total = DessertShoppe.cents2dollarsAndCents(totalTax() + totalCost());
		s += String.format(leftColumn + rightColumn + "\n", "Total Cost", total);

		return s;

	}
}
