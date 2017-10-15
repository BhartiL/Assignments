/**
 * Problem1: You will be writing code in support of a Dessert Shoppe which sells
 * candy by pound, cookies by the dozen, ice cream, and sundaes (ice cream with
 * a topping). Your software will be used for the checkout system. To do this,
 * you will implement an inheritance hierarchy of classes derived from a
 * DessertItem abstract superclass. The Candy, Cookie, and IceCream classes will
 * be derived from the DessertItem class. The Sundae class will be derived from
 * the IceCream class. You will also write a Checkout class which maintains a
 * list (Vector) of DessertItem's. A simple testdriver, TestCheckout.java along
 * with its expected output, are provided for you to test your class
 * implementations.
 */
public class TestCheckout {

	public static void main(String[] args) {
		Checkout checkout = new Checkout();

		checkout.enterItem(new Candy("Peanut Butter Fudge", 2.25, 399));
		checkout.enterItem(new IceCream("Vanilla Ice Cream", 105));
		checkout.enterItem(new Sundae("Choc. Chip Ice Cream", 145, "Hot Fudge", 50));
		checkout.enterItem(new Cookie("Oatmeal Raisin Cookies", 4, 399));

		System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
		System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
		System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
		System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
		System.out.println(checkout);

		checkout.clear();

		checkout.enterItem(new IceCream("Strawberry Ice Cream", 145));
		checkout.enterItem(new Sundae("Vanilla Ice Cream", 105, "Caramel", 50));
		checkout.enterItem(new Candy("Gummy Worms", 1.33, 89));
		checkout.enterItem(new Cookie("Chocolate Chip Cookies", 4, 399));
		checkout.enterItem(new Candy("Salt Water Taffy", 1.5, 209));
		checkout.enterItem(new Candy("Candy Corn", 3.0, 109));

		System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
		System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
		System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
		System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
		System.out.println(checkout);
	}
}
