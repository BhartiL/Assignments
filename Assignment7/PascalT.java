/**
 * Problem-4 Pascal’s triangle is a triangular array of the binomial
 * coefficients. Write a function that takes an integer value n as input and
 * prints first n lines of the Pascal’s triangle. Following are the first 6 rows
 * of Pascal’s Triangle.(Score 2)
 *
 */
public class PascalT {

	public void printPascalTriangle(int n) {
		int i, count, j;
		for (i = 0; i < n; i++) {
			count = 1; //setting count 1
			for (j = 0; j <= i; j++) {
				System.out.print(count + " "); //printing count value which makes pascal triangle
				count = count * (i - j) / (j + 1); 
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		PascalT t = new PascalT();
		t.printPascalTriangle(6);
	}

}
