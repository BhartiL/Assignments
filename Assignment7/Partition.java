/**
 * Extra CreditProblem.
 * Determine whether a given array can be partitioned into two subsets such 
 * that the sum of elements in both subsets is same.
 */
public class Partition {

	public boolean findPartition(int arr[]) {
		// check edge case
		if (arr == null || arr.length == 0) {
			return true;
		}
		// preprocess
		int countVal = 0;
		for (int num : arr) {
			countVal += num; // taking sum of array elements of arr array in countVal variable
		}
		countVal = Math.abs(countVal); // calculating absolute value of countVal variable
		if (countVal % 2 != 0) { // Checking remainder
			return false;
		}
		countVal /= 2;

		boolean[] tempArray = new boolean[countVal + 1]; // define boolean Temporary Array

		tempArray[0] = true; // setting first element of tempArray to true
		// tempArray transition
		for (int i = 1; i <= arr.length; i++) {
			for (int j = countVal; j >= Math.abs(arr[i - 1]); j--) {
				tempArray[j] = tempArray[j] || tempArray[j - Math.abs(arr[i - 1])];
			}
		}
		return tempArray[countVal];
	}

	public static void main(String[] args) {
		// testing with various array values
		int[] arr1 = { 1, 50, 51, 4, 4 };
		int[] arr2 = { 1, 5, 5, 15, 1, 1 };
		int[] arr3 = { 0, 5, 5, 10, 1, 1 };
		int[] arr4 = { -1, 5, -11, 5 };
		int[] arr5 = { 51, 98, 99, 82, 132 };
		int[] arr6 = { -8, -2, 7, 4, -6, 1 };
		int[] arr7 = { -1, 5, 11, 5 };
		int[] arr8= {1, 5, 11, 5};
		int[] arr9= {1, 5, 3};

		Partition p = new Partition();
		System.out.println(p.findPartition(arr8));
	}

}
