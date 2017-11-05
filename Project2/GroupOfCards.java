import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Write the GroupOfCards next. The constructor’s parameter should establish the
 * size of the card array. The getCurrentSize and getCard methods are trivial.
 * The addCard method should increment currentSize after adding the input card
 * to the end of the currently filled part of the cards array. The removeCard
 * method should retrieve a reference to the card at index in the cards array,
 * decrement the currentSize of the cards array, shift all array elements above
 * index down by one place, and return the reference to the card originally at
 * index. Use the display method to test this class.
 *
 */
public class GroupOfCards {

	private List<Card> cards;
	private int currentSize;

	// contructor for group of cards
	public GroupOfCards(int number) {
		this.cards = new ArrayList<>(number);
	}

	/**
	 * return current size of the group
	 * @return current size
	 */
	public int getCurrentSize() {
		return this.currentSize;
	}

	/**
	 * get card at an index
	 * @param i index of card to check
	 * @return reference to card object
	 */
	public Card getCard(int i) {
		return cards.get(i);
	}

	/**
	 * add a card to group
	 * @param card reference to card object
	 */
	public void addCard(Card card) {
		this.cards.add(card);
		currentSize++;
	}

	/**
	 * Remove card at an index
	 * @param index index of card to remove
	 * @return reference to removed card's object
	 */
	public Card removeCard(int index) {
		Card cardToRemove = cards.get(index);
		for (int i = index; i < currentSize - 1; i++) {
			cards.set(i, cards.get(i + 1));
		}
		cards.remove(currentSize - 1);
		currentSize--;
		return cardToRemove;
	}

	/**
	 * Display cards in the deck
	 */
	public void display() {
		for (int i = 0; i < currentSize; i++)
			this.cards.get(i).display();
	}

}
