/**
 * The IceCream class should be derived from the DessertItem class. An IceCream
 * item simply has a cost.
 */
public class IceCream extends DessertItem {
	private int cost;

	IceCream(String name, int cost) {
		super(name);
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

}