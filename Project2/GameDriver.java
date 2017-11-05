import java.util.Scanner;

public class GameDriver {

	public static void main(String args[]) {

		while (true) {
			Game game = new Game(4);
			game.playAGame();
			System.out.println("Play another game (y/n)?");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			if (scan.nextLine().equalsIgnoreCase("N")) {
				scan.close();
				break;
			}
		}

	}
}
