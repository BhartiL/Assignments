/**
 * The Sundae class should be derived from the IceCream class. The cost of a
 * Sundae is the cost of the IceCream plus the cost of the topping
 */
public class Sundae extends IceCream {
	private String nameTopping;
	private int costTopping;

	Sundae(String nameIceCream, int costIceCream, String nameTopping, int costTopping) {
		super(nameIceCream, costIceCream);
		this.nameTopping = nameTopping;
		this.costTopping = costTopping;

	}

	public int getcost() {
		int cost = super.getCost() + costTopping;
		return cost;
	}

	public String getNameTopping() {
		return nameTopping;
	}

}