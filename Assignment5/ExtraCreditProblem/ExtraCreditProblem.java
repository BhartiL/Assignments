import java.util.ArrayList;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order. (Score 2) For example, Given the following
 * matrix: {{1,2,3}, {4,5,6}, {7,8,9}} You should return {1,2,3,6,9,8,7,4,5}.
 * public List<Integer> spiralOrder(int[][] matrix) { //write your code here }
 *
 */
public class ExtraCreditProblem {

	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0)
			return result;
		int m = matrix.length;
		int n = matrix[0].length;
		int i; // iterator
		int x = 0; // starting row index
		int y = 0; // starting column index
		while (x < m && y < n) {
			// Print the first row from the remaining rows
			for (i = y; i < n; i++) {
				result.add(matrix[x][i]);
			}
			x++;

			// Print the last column from the remaining columns
			for (i = x; i < m; i++) {
				result.add(matrix[i][n - 1]);
			}
			n--;

			// Print the last row from the remaining rows
			if (x < m) {
				for (i = n - 1; i >= y; i--) {
					result.add(matrix[m - 1][i]);
				}
				m--;
			}

			// Print the first column from the remaining columns
			if (y < n) {
				for (i = m - 1; i >= x; i--) {
					result.add(matrix[i][y]);
				}
				y++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ExtraCreditProblem ep = new ExtraCreditProblem();
		int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(ep.spiralOrder(matrix));
	}
}
