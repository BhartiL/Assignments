import java.util.Random;

/**
 * 
 * The Deck class’s constructor is already provided in the text. To shuffle the
 * deck, use a for loop that starts with unshuffled = getCurrentSize and steps
 * down to one. In each iteration, use Math.random to pick an index in the
 * unshuffled range, remove the card at that index, and then add it to the high
 * end of the array. To deal a card, just remove the card at index = 0.
 *
 */
public class Deck extends GroupOfCards {
	/**
	 * total number of cards in group
	 */
	public final int TOTAL_CARDS = 52;

	public Deck() {
		super(52);
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				this.addCard(new Card(j, i));
			}
		}
	}

	/**
	 * shuffle deck
	 */
	public void shuffle() {
		int unshuffled = this.getCurrentSize();
		Random random = new Random();

		while (unshuffled > 1) {
			int randomIndex = random.nextInt(unshuffled - 1);
			Card selectedCard = this.removeCard(randomIndex);
			this.addCard(selectedCard);
			unshuffled--;
		}
	}

	/**
	 * deal a card
	 * 
	 * @return card at 0th index in deck
	 */
	public Card dealCard() {
		return this.removeCard(0);
	}
}
