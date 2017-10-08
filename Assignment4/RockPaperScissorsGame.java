package Assignment4;

/**
 * Problem-2
 * Implement a class called Tool. It should have an int field called strength
 * and a char field called type. You may make them either private or protected.
 * The Tool class should also contain the function void setStrength(int), which
 * sets the strength for the Tool. Create 3 more classes called Rock, Paper, and
 * Scissors, which inherit from Tool. Each of these classes will need a
 * constructor which will take in an int that is used to initialize the strength
 * field. The constructor should also initialize the type field using ‘r’ for
 * Rock, ‘p’ for Paper, and ’s' for Scissors. These classes will also need a
 * public function bool fight(Tool) that compares their strengths in the
 * following way: Rock’s strength is doubled (temporarily) when fighting
 * scissors, but halved (temporarily) when fighting paper. In the same way,
 * paper has the advantage against rock, and scissors against paper. The
 * strength field shouldn’t change in the function, which returns true if the
 * original class wins in strength and false otherwise. You may also include any
 * extra auxiliary functions and/or fields in any of these classes. Run the
 * program without changing the main function, and verify that the results are
 * correct. (Score
 * 
 * 
 *
 */
public class RockPaperScissorsGame {

	public static void main(String[] args) {

		Scissors s = new Scissors(5);
		Paper p = new Paper(7);
		Rock r = new Rock(15);
		System.out.println(s.fight(p) + " , " + p.fight(s));
		System.out.println(p.fight(r) + " , " + r.fight(p));
		System.out.println(r.fight(s) + " , " + s.fight(r));

	}
}

class Tool {
	protected int strength;
	protected char type;

	public void setStrength(int strength) { 
		this.strength = strength;
	}
}

class Rock extends Tool {

	public Rock(int strength) { //Rock class constructor
		this.strength = strength;
		this.type = 'r';
	}

	public boolean fight(Tool t) {
		if (t.type == 'p') {
			if ((0.5 * strength) > t.strength)//Comparing strength of original class with strength of fighting class
				return true;
			else
				return false;

		}
		if (t.type == 's') {
			if (2 * strength > t.strength) //Comparing strength of original class with strength of fighting class
				return true;
			else
				return false;
		}
		return false;
	}
}

class Paper extends Tool {
	int tempstrength;

	public Paper(int strength) { //Paper class constructor
		this.strength = strength;
		this.type = 'p';
	}

	public boolean fight(Tool t) {

		if (t.type == 'r') {
			if (2 * strength > t.strength) //Comparing strength of original class with strength of fighting class
				return true;
			else
				return false;
		}
		if (t.type == 's') {
			if ((0.5 * strength) > t.strength)//Comparing strength of original class with strength of fighting class
				return true;
			else
				return false;

		}

		return false;

	}

}

class Scissors extends Tool {
	int tempstrength;

	public Scissors(int strength) { //Scissors class constructor
		this.strength = strength;
		this.type = 's';
	}

	public boolean fight(Tool t) {
		if (t instanceof Paper) {
		if (2 * strength > t.strength) //Comparing strength of original class with strength of fighting class
				return true;
			else
				return false;
		}
		if (t instanceof Rock) {
			if ((0.5 * strength) > t.strength) //Comparing strength of original class with strength of fighting class
				return true;
			else
				return false;
		}
		return false;

	}
}
