
/**
 * The Cookie class should be derived from the DessertItem class. A Cookie item
 * has a number and a price per dozen which are used to determine its cost. For
 * example, 4 cookies @ 399 cents /dz. = 133 cents. The cost should be rounded
 * to the nearest cent.
 */
public class Cookie extends DessertItem {
	private int numCookie;
	private int pricePerDozen;

	public Cookie(String name, int pricePerDozen, int numCookie) {
		super(name);
		this.numCookie = numCookie;
		this.pricePerDozen = pricePerDozen;
	}

	public int getCost() {
		int cost = (int) Math.round(numCookie * pricePerDozen / 12);
		return cost;
	}

	public int getNumCookie() {
		return numCookie;
	}

	public int getPricePerDozen() {
		return pricePerDozen;
	}

}
