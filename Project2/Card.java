/**
 * Project2-  Write a program that plays the game of Hearts
 * Write the Card class first. Represent numbers and face-card values by
 * integers ranging from 2 to 14, with 14 being the ace. Represent suits by
 * integers as follows: 0 = clubs; 1 = diamonds; 2 = hearts; 3 = spades. Write a
 * nice display method that uses switch statements to convert suit integers to
 * the words, “clubs,” “diamonds,” and so on, and the numbers 11…14 to the words
 * “Jack,” “Queen,” and so on. Use the display method to test this class.
 *
 */
public class Card {

	private int num;
	private int suit;

	/**
	 * Card constructor
	 * 
	 * @param num  number value of the card
	 * @param suit suit value of the card
	 */
	public Card(int num, int suit) {
		this.num = num;
		this.suit = suit;
	}

	/**
	 * get number value of the card
	 * @return number value for the card
	 */
	public int getNum() {
		return this.num;
	}

	/**
	 * get number value of suit on card
	 * @return value corresponding to suit on card
	 */
	public int getSuit() {
		return this.suit;
	}

	/**
	 * display the card information in string format
	 */
	public void display() {
		String numString = "";
		String suitString = "";
		switch (num) {
		case 11:
			numString = "Jack";
			break;
		case 12:
			numString = "Queen";
			break;
		case 13:
			numString = "King";
			break;
		case 14:
			numString = "Ace";
			break;
		default:
			numString = String.valueOf(num);

		}

		switch (suit) {
		case 0:
			suitString = "Clubs";
			break;
		case 1:
			suitString = "Diamonds";
			break;
		case 2:
			suitString = "Hearts";
			break;
		case 3:
			suitString = "Spades";
			break;
		}

		System.out.println(numString + " of " + suitString);
	}
}
