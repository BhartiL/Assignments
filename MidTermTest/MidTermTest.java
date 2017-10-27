import java.util.Arrays;

public class MidTermTest {
	/**
	 * Problem 1 Write a method named reverseEvenIndices that takes an integer array
	 * as input and outputs an array such that all the values with odd indices
	 * remain in the same position. However, elements with even indices should be
	 * output in reverse order. That is, the first element with even index should be
	 * swapped with the last element with even index, the second even­indexed
	 * element with the second­to­last even­indexed element, and so on. Note that
	 * zero is an even index. Example inputs and outputs are as follows: Input: {9,
	 * 4, 8, 7, 5, 1, 3} Output: {3, 4, 5, 7, 8, 1, 9} Input: {6, 4, 1, 0, 3, 2}
	 * Output: {3, 4, 1, 0, 6, 2} Input: {1, 2, 3} Output: {3, 2, 1} public
	 * int[]reverseEvenIndices(int[] nums){ // your code }
	 */
	public int[] reverseEvenIndices(int[] nums) {
		int len = nums.length; // length of array
		int temp; // temporary variable
		int i = 0, j = 0;
		// if last index is odd, put pointer at previous to last
		if (len % 2 == 0)
			j = len - 2;
		else
			j = len - 1; // if last index is even, put the pointer there
		// keep swapping until first pointer stays before second one
		while (i < j) {
			// Do the swapping magic
			temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
			// move to next set of even indices
			i = i + 2;
			j = j - 2;
		}
		return nums;
	}

	/**
	 * Problem 2 You have a total of n coins that you want to form in a staircase
	 * shape, where every k­th row must have exactly k coins. Given n, find the
	 * total number of full staircase rows that can be formed. n is a non­negative
	 * integer and fits within the range of a 32­bit signed integer. Example 1: n =
	 * 5 The coins can form the following rows: ¤ ¤ ¤ ¤ ¤ Because the 3rd row is
	 * incomplete, we return 2.
	 * 
	 */

	public int arrangeCoins(int n) {
		int begin = 0;
		int endVal = n;
		int midVal = 0;
		while (begin <= endVal) {
			midVal = (begin + endVal) >>> 1; // calculating mid value of start and end values
			if ((0.5 * midVal * midVal + 0.5 * midVal) <= n) {
				begin = midVal + 1;
			} else {
				endVal = midVal - 1;
			}
		}
		return begin - 1; // returning total number of full staircase rows that can be formed
	}

	/**
	 * Problem 3 Given a non­empty integer array of size n, find the minimum number
	 * of moves required to make all array elements equal, where a move is
	 * incrementing n ­-1 elements by 1. Example: Input: [1,2,3] Output: 3
	 * Explanation: Only three moves are needed (remember each move increments two
	 * elements): [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
	 * 
	 */
	public int minMoves(int[] nums) {

		if (nums.length == 0) // array length
			return 0; // returning 0 when array is empty
		int minVal = nums[0];
		for (int n : nums)
			minVal = Math.min(minVal, n); // calculating minimum element value of array nums[]
		int res = 0;
		for (int n : nums)
			res += n - minVal; // adding counts in result as total number of minimum moves.
		return res;
	}

	/**
	 * Problem-4 Given n dice each with m faces, numbered from 1 to m, find the
	 * number of ways to get sum X. X is the summation of values on each face when
	 * all the dice are thrown. Your function should take, number of faces, number
	 * of dice and required sum as input and return a number of possible ways.
	 * 
	 */

	public int countNumberOfPossibleWays(int m, int n, int x) {
		int[][] possibleWays = new int[n + 1][x + 1];
		int min = Math.min(x, m);
		for (int i = 1; i <= min; i++) {
			possibleWays[1][i] = 1;
		}
		// find number of possible ways using dynamic programming
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= x; j++)
				for (int k = 1; k <= m && k < j; k++) {
					possibleWays[i][j] += possibleWays[i - 1][j - k];
				}

		}

		return possibleWays[n][x];
	}

	public static void main(String[] args) {
		MidTermTest m = new MidTermTest();
		int[] nums = { 9, 4, 8, 7, 5, 1, 3 };
		int[] nums1 = { 1, 2, 3 };

		System.out.println(Arrays.toString(m.reverseEvenIndices(nums))); // Execution of reverseEvenIndices method
		System.out.println(m.arrangeCoins(8)); // Execution of arrangeCoins method
		System.out.println(m.minMoves(nums1)); // Execution of minMoves method
		System.out.println(m.countNumberOfPossibleWays(2, 2, 3));// Execution of countNumberOfPossibleWays

	}

}
