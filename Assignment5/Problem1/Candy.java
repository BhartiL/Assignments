/**
 * The Candy class should be derived from the DessertItem class. A Candy item
 * has a weight and a price per pound which are used to determine its cost. For
 * example, 2.30 lbs.of fudge @ .89 /lb. = 205 cents. The cost should be rounded
 * to the nearest cent
 */
public class Candy extends DessertItem {
	private double weight;
	private int pricePerLb;

	public Candy(String name, double weight, int pricePerLb) {
		super(name);
		this.weight = weight;
		this.pricePerLb = pricePerLb;
	}

	public int getCost() {
		int cost = (int) Math.round(pricePerLb * weight);
		return cost;
	}

	public double getWeight() {
		return weight;
	}

	public int getPricePerLb() {
		return pricePerLb;
	}
}
