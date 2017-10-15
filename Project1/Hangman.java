package Assignment4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Design a Hangman Game that is played by a user 
 * Hangman(ArrayList< String >words) - Initializes the words list.
 * chooseWord()- Randomly chooses a word from the list.
 * handleGuess()- handle the guess and add the letter to correctList or WrongList. 
 * gameWon()- return true if user wins the game.
 * gameOver()- exit the program after the game is over. 
 * printHangman()- print hangman after every guess. 
 * playGame()- Starts the game. 
 * displayWord()-display the correctly guessed letters and hide the remaining with dashes. 
 * NOTE:
 * Intial step is to welcome the user for game. 
 * Display the secret word with dashes. 
 * Ask the user to guess a letter.
 * Update the word, display previously guessed letters,remainingguesses and hangman. 
 * Exit the console after finishing the game.
 *
 */
public class Hangman {
	ArrayList<String> words;
	static ArrayList<Character> correctLetters;
	static ArrayList<Character> wordToGuess;
	private ArrayList<Character> previouslyGuessedLetters; // the list of characters previously guessed
	private ArrayList<Character> incorrectLetters; // incorrect guessed letters list
	static String secretword = "";
	private char[] word;
	private int totalChances = 8; // total number of guess chances
	private int totalGuessChances; // current totalGuessChances
	static int count = 0;
	private Random randomNo = new Random();

	public Hangman(ArrayList<String> wordsList) {
		this.words = new ArrayList<>();
		this.words = wordsList;
	}

	void chooseWord() {
		wordToGuess = new ArrayList<Character>();
		int randIndex = randomNo.nextInt(words.size());
		secretword = words.get(randIndex);// Randomly chooses a word from list
		String str = words.get(randIndex);
		for (char c : str.toCharArray()) {
			wordToGuess.add(c);
		}
		word = words.get(randIndex).toCharArray();
		correctLetters = new ArrayList<Character>();
		for (int i = 0; i < secretword.length(); i++) { // Adding dashes equal to characters in secret word
			correctLetters.add('_');
		}
		incorrectLetters = new ArrayList<Character>();
		previouslyGuessedLetters = new ArrayList<Character>();
		totalGuessChances = totalChances;

	}

	boolean handleGuess(char letter) {
		boolean foundOne = false; // did at least one letter found?
		for (int i = 0; i < wordToGuess.size(); i++) {
			if (wordToGuess.get(i).equals(letter)) {
				foundOne = true;
				correctLetters.set(i, letter); // set found letters on place of dashes in correctLetters list
			}
		}
		if (foundOne == false)
			incorrectLetters.add(letter);// adding non matched letters to incorrect letters list
		return foundOne;
	}

	public String displayWord() { // This method returns String of the correctly guessed letters
									// and hide the remaining with dashes
		String toPrint = "";
		for (int i = 0; i < correctLetters.size(); i++) {
			toPrint += correctLetters.get(i) + "";
		}
		return toPrint;
	}

	char getGuess() { // This method is getting guess character from player
		System.out.println("");
		System.out.print("Enter a lower-case letter as your guess: ");
		Scanner sc = new Scanner(System.in);
		String guess = sc.nextLine();
		System.out.println("");
		boolean valid = false;
		char c = ' '; //
		while (valid == false) {
			c = guess.toLowerCase().charAt(0); // make lowercase and get the first character
			if (Character.isLetter(c)) // check if is a letter
			{
				valid = true;
			} else {
				System.out.print(
						"Ahh.. Seems you did a typo!! Please enter a lowercase english alphabet as your guess: ");
				sc = new Scanner(System.in);
				guess = sc.nextLine();
			}
		}
		return c;// returns the letter
	}

	void playGame() { // starts the game
		chooseWord();
		System.out.println("Welcome to Hangman Game:");

		boolean stillPlaying = true;
		while (stillPlaying == true) {
			System.out.println("Secretword to guess: " + displayWord()); // print the secret word to guess
			printPreviouslyGuessedLetterList();
			char guessedLetter = getGuess();
			System.out.println("Output:->");
			System.out.println("Your Guessed Letter is : " + guessedLetter);
			if (!previouslyGuessedLetters.contains(guessedLetter)) // check if a letter is already guessed
			{
				previouslyGuessedLetters.add(guessedLetter); // add guess letter to previously guessed letters list!
				boolean foundLetter = handleGuess(guessedLetter);
				if (foundLetter == false) {
					totalGuessChances = totalGuessChances - 1;
					System.out.println("Wrong Guess! Sorry, the word has no '" + guessedLetter + "'. You have "
							+ totalGuessChances + " guess chances left.");
					count++;
					printHangman();
				}
			} else {
				System.out.println("You have already guessed '" + guessedLetter + "'. Pick another letter.");
			}
			if (gameWon() == true) {
				stillPlaying = false;
				System.exit(0); // Exit the console/terminate the program
			}
			if (gameOver() == false) {
				stillPlaying = false;
				System.exit(0); // Exit the console/terminate the program
			}
		}
	}

	void printPreviouslyGuessedLetterList() { // This method is printing previously guessed letters list
		String alreadyguessedletters = "";
		for (int i = 0; i < previouslyGuessedLetters.size(); i++) {
			alreadyguessedletters += (previouslyGuessedLetters.get(i) + " ");
		}
		System.out.println("The previously guessed letters are-> {" + alreadyguessedletters + "}");

	}

	boolean gameWon() { // return true if user wins the game
		boolean blanksLeft = true;

		for (int i = 0; i < correctLetters.size(); i++) {
			if (correctLetters.contains('_')) {
				blanksLeft = false;
			}
		}
		if (blanksLeft == true) // check if Secret word has any blanks
		{
			System.out.println(
					"Congratulations You won!, the word is '" + new String(word) + "'! You figured out the word. ");
		}
		return blanksLeft;
	}

	boolean gameOver() { // Exit the program after the game is over
		boolean flag = true;
		if (totalGuessChances <= 0) // check if player is out of totalGuessChances
		{
			System.out.println("No more guess chances remaining. The word was '" + new String(word) + "'.");
			flag = false;
		}
		return flag;
	}

	public static void printHangman() { // print Hangman after every wrong guess
		if (count == 1) {
			System.out.println("Try again!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |        	   ");
			System.out.println("   |         ");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |_________");
		}
		if (count == 2) {
			System.out.println("Try again!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |_________");
		}
		if (count == 3) {
			System.out.println("Try again!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |           |");
			System.out.println("   |        --- ");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |_________");
		}
		if (count == 4) {
			System.out.println("Try again!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |           |");
			System.out.println("   |        --- --- ");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |_________");
		}
		if (count == 5) {
			System.out.println("Try again!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |           |");
			System.out.println("   |        ---  --- ");
			System.out.println("   |          /  ");
			System.out.println("   |         /    ");
			System.out.println("   |_________");
		}
		if (count == 6) {
			System.out.println("Try again!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |           |");
			System.out.println("   |        ---  --- ");
			System.out.println("   |          /  \\     ");
			System.out.println("   |         /    \\ ");
			System.out.println("   |_________");
		}
		if (count == 7) {
			System.out.println("Try again!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |           |");
			System.out.println("   |        ---  --- ");
			System.out.println("   |          /  \\   ");
			System.out.println("   |         /    \\ ");
			System.out.println("   |       --       ");
			System.out.println("   |_________");
		}
		if (count == 8) {
			System.out.println("You Lost! GAME OVER!");
			System.out.println("   ____________");
			System.out.println("   |           |");
			System.out.println("   |           0");
			System.out.println("   |           |");
			System.out.println("   |        ---  --- ");
			System.out.println("   |          /  \\     ");
			System.out.println("   |         /    \\ ");
			System.out.println("   |       --      --");
			System.out.println("   |_________");
		}
	}

	public static void main(String[] args) {
		ArrayList<String> wordsList = new ArrayList<>();
		wordsList.add("apple");
		wordsList.add("mango");
		wordsList.add("pears");
		Hangman h = new Hangman(wordsList);
		h.playGame();
	}
}
