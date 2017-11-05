
public class Game {

	/**
	 * number of players in game
	 */
	public final int PLAYERS;
	private Deck deck = new Deck();
	private Hand[] players;
	private Trick[] tricks;
	private int numberOfTricks;
	private boolean hearts = false;;
	private boolean queenOfSpades = false;

	// Game constructor
	public Game(int numberOfPlayers) {
		this.PLAYERS = numberOfPlayers;
		this.players = new Hand[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			this.players[i] = new Hand(i, 52 / numberOfPlayers);

		}
		// this.numberOfTricks = 52/numberOfPlayers;
		this.tricks = new Trick[52 / numberOfPlayers];

	}

	/**
	 * get number of tricks played so far 
	 * @return number of tricks
	 */
	public int getNumberOfTricks() {
		return this.numberOfTricks;
	}

	/**
	 * Has a heart been played in trick
	 * @return true if heart has been played in trick, false otherwise
	 */
	public boolean getHearts() {
		return this.hearts;
	}

	/**
	 * Has a queen of spades been planed in trick
	 * @return true if queen of spades has been played in trick, false otherwise
	 */
	public boolean getQueenOfSpades() {
		return this.queenOfSpades;
	}

	public void playAGame() {
		// shuffle
		deck.shuffle();

		// deal cards
		for (int i = 0; i < 52 / PLAYERS; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				Card dealtCard = deck.dealCard();
				players[j].addCard(dealtCard);
			}
		}

		// verify remaining cards
		int totalDealtCards = 0;
		for (int i = 0; i < PLAYERS; i++) {
			totalDealtCards = totalDealtCards + players[i].getCurrentSize();
		}
		int remainingCards = 52-totalDealtCards;
		if (remainingCards != 52 % PLAYERS)
			System.out.println(String.format("Error in remaining cards count. Expected %s remaining cards, found %s.",
					String.valueOf(52 % PLAYERS), String.valueOf(deck.getCurrentSize())));
		System.out.println("Output - first part:\n");

		int lowestClubPlayer = -1;
		int lowestClubNum = 15;

		for (int i = 0; i < PLAYERS; i++) {
			// sort for each player and set shortest
			players[i].sort();
			players[i].setShortest();
			System.out.println(String.format("\t\tPlayer %d shortest=%s\t\t", i, players[i].getShortest()));
			players[i].display();
			// find lowest club player
			int playersLowestClub = players[i].findLowest(0) > -1
					? players[i].getCard(players[i].findLowest(0)).getNum() : 15;
			if (lowestClubNum > playersLowestClub) {
				lowestClubNum = playersLowestClub;
				lowestClubPlayer = i;
			}
		}
		int index = -1;
		System.out.println("\nOutput - second part:");
		for (int trickNum = 0; trickNum < 52 / PLAYERS; trickNum++) {
			System.out.print("\n");
			Trick currentTrick = new Trick(PLAYERS);
			tricks[trickNum] = currentTrick;
			numberOfTricks++;
			
			Card card;
			if (trickNum == 0) {
				index = lowestClubPlayer;
				// setting card reference to lowest club card
				card = players[lowestClubPlayer].removeCard(players[lowestClubPlayer].findLowest(0));
				currentTrick.addCard(card);
				currentTrick.update(index, card);
				
			} else {
				
				//if it's not the first trick, play a card and update hearts and queens
				card = players[index].playACard(this, currentTrick);
				this.updateHeartsAndQueen(card);
			}
			// display player and card for first play of trick
			System.out.print(String.format("player %s\t\t", index));
			card.display();

			// loop through remaining players
			for (int currentPlayer = (index + 1) % PLAYERS; currentPlayer % PLAYERS != index; currentPlayer++) {
				if (currentPlayer >= PLAYERS)
					currentPlayer = currentPlayer % PLAYERS;

				card = players[currentPlayer].playACard(this, currentTrick);
				// currentTrick.addCard(card);
				// currentTrick.update(currentPlayer, card);
				this.updateHeartsAndQueen(card);

				System.out.print(String.format("player %s\t\t", currentPlayer));
				card.display();
			}

			// deal undelt cards in first trick
			if (trickNum == 0) {
				for (int i = 0; i < deck.getCurrentSize(); i++) {
					Card undeltCard = deck.removeCard(i);
					currentTrick.addCard(undeltCard);
					this.updateHeartsAndQueen(undeltCard);
					System.out.print("undelt card\t\t");
					undeltCard.display();
				}
			}
			//update the index of winner
			index = currentTrick.getWinner();

		}
		System.out.println("\n");
		for (int i = 0; i < PLAYERS; i++) {
			System.out.println(String.format("Player %d score=%d", i, this.computePoints(i)));
		}
		System.out.println("_____________________________________");
	}

	/**
	 * update if the current card is breaking hearts or if it's queen of spades
	 * @param card current card
	 */
	public void updateHeartsAndQueen(Card card) {
		// if current card is breaking hearts, set heart flag to true
		if (card.getSuit() == 2 && !hearts) {
			hearts = true;
			System.out.println("Hearts is now broken");
		}

		// if current card is queen of spades, set queen of spades flag to true
		if (card.getSuit() == 3 && card.getNum() == 12)
			queenOfSpades = true;

	}

	/**
	 * compute points for a player
	 * @param playerNum index of player to compute points for
	 * @return points on the player
	 */
	private int computePoints(int playerNum) {
		int points = 0;
		// loop through number of tricks in games
		for (int i = 0; i < numberOfTricks; i++) {
			// if parameter player is winner, add points
			if (tricks[i].getWinner() == playerNum) {
				for (int j = 0; j < tricks[i].getCurrentSize(); j++) {
					// add one point for hearts
					if (tricks[i].getCard(j).getSuit() == 2)
						points++;
					
					// add 13 points for queen of spades
					if (tricks[i].getCard(j).getSuit() == 3 && tricks[i].getCard(j).getNum() == 12)
						points = points + 13;

				}
			}
		}
		return points;
	}
}
