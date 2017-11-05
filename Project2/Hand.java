
public class Hand extends GroupOfCards{
	/**
	 * index of the hand/player
	 */
	public final int NUM;
	private int shortest = 0;
	
	//Hand constructor
	public Hand(int playerNum, int numberOfCards){
		super(numberOfCards);
		this.NUM = playerNum;
	}
	
	/**
	 * sort cards by selection sort strategy
	 */
	public void sort(){
		int len = this.getCurrentSize();
		 
		int unsorted, j, maxIndex;

		for(unsorted=len; unsorted>0; unsorted--)
		{
		   maxIndex=unsorted-1;
		   for(j=0; j<unsorted-1; j++)
		       if(getCardSortValue(this.getCard(j))>getCardSortValue(this.getCard(maxIndex)))
		          maxIndex=j;
		  Card maxCard = this.removeCard(maxIndex);
		  this.addCard(maxCard);
		}
		    
		
	}
	
	/**
	 * set shortest suit
	 */
	public void setShortest(){
		int shortest = 0;
		shortest = findCount(1)<=findCount(0)?1:shortest;
		if((findCount(3)<=findCount(shortest)) && find(25,3)==-1 && find(13,3)==-1 && find(12,3)==-1){
			shortest = 3;
		}
		
		this.shortest = shortest;;
	}
	
	/**
	 * get shortest suite
	 * @return int value corresponding to shortest suit
	 */
	public int getShortest(){
		return this.shortest;
	}
	
	/**
	 * play a card in hand
	 * @param game reference to game object
	 * @param trick reference to current trick object
	 * @return return the card that has been played
	 */
	public Card playACard(Game game, Trick trick){
		int index = -1;
		
		// if you're first one to play
		if(trick.getCurrentSize()==0){
			// if void exists, find lowest from any suit.
			if(findCount(shortest)==0){
				if(findCount(0)!=0)
					index = findLowest(0);
				else if(findCount(1)!=0)
					index = findLowest(1);
				else if(findCount(3)!=0)
					index = findLowest(3);	
			}else{
				//if void doesn't exist follow 
				index = findHighest(shortest);
			}
		
		}
		else if(trick.getCurrentSize()==game.PLAYERS-1 && !trick.getHearts() && !trick.getQueen() && (index = findLastHigh(trick.getWinningCard().getSuit()))>=0);
	
		else if((index = findHighestBelow(trick.getWinningCard())) >= 0);
		else if((index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0);
		else if((index = find(12,3)) >= 0);
		else if((index = find(14,3)) >= 0);
		else if((index = find(13,3)) >= 0);
		else if ((index = findHighest(2)) >= 0);
		if(index==-1){
			index = findHighest();
		}
		if(index==-1)
			System.out.println("error");
		Card card = this.removeCard(index);
		trick.update(NUM, card);
		trick.addCard(card);
		game.updateHeartsAndQueen(card);
		return card;
		
	}
	
	/**
	 * find lowest card in suit
	 * @param suit int for suit
	 * @return num value for lowest card in given suit
	 */
	public int findLowest(int suit){
		int lowestNum = 15;
		int lowestIndex = -1;
		for(int i=0; i<this.getCurrentSize(); i++){
			Card currentCard = this.getCard(i);
			if(currentCard.getSuit()==suit && currentCard.getNum()<lowestNum){
				lowestNum = currentCard.getNum();
				lowestIndex = i;
			}
				
		}
		return lowestIndex;
	}
	
	/**
	 * find count of cards in a suit in hand
	 * @param suit suit integer
	 * @return number of cards in suit in hand
	 */
	public int findCount(int suit){
		int count = 0;
		for(int i=0; i<this.getCurrentSize(); i++){
			Card currentCard = this.getCard(i);
			if(currentCard.getSuit()==suit)
				count++;
		}
		return count;
	}
	
	/**
	 * find index of a particular card on hand
	 * @param num number value of card
	 * @param suit suit of card
	 * @return index of card if found, -1 otherwise
	 */
	public int find(int num, int suit){
		for(int i=0; i<this.getCurrentSize(); i++){
			Card currentCard = this.getCard(i);
			if(currentCard.getSuit()==suit && currentCard.getNum()==num)
				return i;
		}
		return -1;
	}
	
	/**
	 * find highest card in a suit on hand
	 * @param suit suit integer value
	 * @return index for highest cards in suit
	 */
	public int findHighest(int suit){
		int highestIndex = -1;
		int highestVal = -1;
		for(int i=0; i<this.getCurrentSize(); i++){
			Card currentCard = this.getCard(i);
			if(currentCard.getSuit()==suit && currentCard.getNum()>highestVal){
				highestVal = currentCard.getNum();
				highestIndex = i;
			}
		}
		return highestIndex;
	}
	
	/*
	 * find lowest card
	 */
	public int findLowest(Game game){
		int lowestIndex = -1;
		int lowestVal = 15;
		for(int i=0; i<this.getCurrentSize(); i++){
			Card currentCard = this.getCard(i);
			if(!game.getHearts() && currentCard.getSuit()==2)
				continue;
			if(currentCard.getNum()<lowestVal){
				lowestVal = currentCard.getNum();
				lowestIndex = i;
			}
				
		}
		return lowestIndex;
	}
	
	/**
	 * find lowest below the queen of spades
	 * @param suit
	 * @return
	 */
	public int findLastHigh(int suit){
		int index = findHighest(suit);
		if(index>-1 && getCard(index).getNum()==12 && getCard(index).getSuit()==3)
			index = this.findHighestBelow(new Card(12,3));
		return index;
	}
	
	/**
	 * find highest below winning card
	 * @param winningCard
	 * @return
	 */
	public int findHighestBelow(Card winningCard){
		int highestBelowNum = -1;
		int highestBelowIndex = -1;
		for(int i=0; i<this.getCurrentSize(); i++){
			Card currentCard = this.getCard(i);
			if(currentCard.getSuit()==winningCard.getSuit() && currentCard.getNum()>highestBelowNum && currentCard.getNum()<winningCard.getNum()){
				highestBelowNum = currentCard.getNum();
				highestBelowIndex = i;
			}
				
		}
		if(highestBelowIndex != -1 && (highestBelowIndex==this.getCurrentSize()-1 || this.getCard(highestBelowIndex).getSuit()!=this.getCard(highestBelowIndex+1).getSuit()))
			return -1;
		return highestBelowIndex;
	}
	
	
	/**
	 * find card to play when you're neither first hand nor last and lasthighest is -1
	 * @param game
	 * @param suit
	 * @return
	 */
	public int findMiddleHigh(Game game, int suit){
		int middleHighNum = -1;
		int middleHighIndex = -1;
		middleHighIndex = findHighest(suit);
		if(suit==3 && !game.getQueenOfSpades()){
			for(int i=0; i<this.getCurrentSize(); i++){
				Card currentCard = this.getCard(i);
				if(currentCard.getSuit()==3 && currentCard.getNum()<12 && currentCard.getNum()>middleHighNum ){
					middleHighNum = currentCard.getNum();
					middleHighIndex = i;
				}
					
			}
		}
		return middleHighIndex;
				
		
	}
	
	
	/**
	 * find index for highest card from all suits on hand
	 * @return
	 */
	public int findHighest(){
		int highestNum = -1;
		int highestIndex = -1;
		for(int i=0; i<this.getCurrentSize(); i++){
			Card currentCard = this.getCard(i);
			if(currentCard.getNum()>highestNum){
				highestNum = currentCard.getNum();
				highestIndex = i;
			}
		}
		return highestIndex;
		
	}
	
	//helper function to help in sorting cards relative to each other
	private int getCardSortValue(Card card){
		return 13*card.getSuit() + card.getNum();
	}
}
