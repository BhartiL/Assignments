/**
 * 
 * The Trick class is the next easiest one to write. The constructor’s parameter
 * is the number of players, and the constructor calls the superclass’s
 * constructor with one less than twice this number, to allow room in the first
 * trick for undelt cards. The four get- methods are trivial. The isWinner
 * method should return true unless the previous winning card is not null and
 * the current card is not in the suit being played or its number is less than
 * the winning card’s number. In the update method, if the current card is the
 * winner, set winner equal to current player’s number and set the winning card
 * equal to the current card. If the current card is a heart, set hearts to
 * true. If the current card is the queen of spades, set queen to true
 *
 */
public class Trick extends GroupOfCards {

	private int winner;
	private Card winningCard;
	private boolean hearts = false;
	private boolean queen = false;

	public Trick(int players) {
		super(2 * players - 1);
	}

	/**
	 * Returns winner player number
	 * @return
	 */
	public int getWinner() {
		return this.winner;
	}

	/**
	 * returns winning card
	 * @return
	 */
	public Card getWinningCard() {
		return this.winningCard;
	}

	/**
	 * returns true if heart has been played in trick
	 * @return
	 */
	public boolean getHearts() {
		return this.hearts;
	}

	/**
	 * return true if queen has been played in trick
	 * @return
	 */
	public boolean getQueen() {
		return this.queen;
	}

	/**
	 * Update winner, hearts and queen for the trick
	 * @param playerNum current player number
	 * @param card card played
	 */
	public void update(int playerNum, Card card) {
		if (isWinner(card)) {
			this.winner = playerNum;
			this.winningCard = card;
		}

		if (card.getSuit() == 2)
			this.hearts = true;

		if (card.getSuit() == 3 && card.getNum() == 12)
			this.queen = true;
	}

	/**
	 * returns true if current card is new winner
	 * @param card
	 * @return
	 */
	private boolean isWinner(Card card) {
		if (winningCard == null)
			return true;
		else if (winningCard.getSuit() != card.getSuit())
			return false;
		else if (card.getNum() < winningCard.getNum())
			return false;
		return true;
	}
}
