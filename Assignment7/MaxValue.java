/**
 * Problem1 Write a program called MaxValue.java that finds the maximum value in
 * an array of ints using 4 threads. Your main should be similar as the one in
 * SumThread example, though you should construct your array of random numbers
 * instead of increasing numbers. You may assume in your threaded code that the
 * array has at least 4 elements.(score 2)
 */
public class MaxValue extends Thread {
	private int lo, hi;
	private int[] arr;
	private int ans = 0;

	public MaxValue(int[] arr, int lo, int hi) {
		this.lo = lo;
		this.hi = hi;
		this.arr = arr;
	}

	@Override
	public void run() {
		for (int i = lo; i < hi; i++) {
			if (arr[i] > ans) {
				ans = arr[i];
			}
		}
	}

	/**
	 * maximum element from all elements of an array
	 * @return maxValue of the array's elements
	 * @throws InterruptedException shouldn't happen
	 * 
	 */
	public static int maxValue(int[] arr) throws InterruptedException {
		int len = arr.length;
		int ans = 0;

		// Create and start 4 threads.
		MaxValue[] ts = new MaxValue[4];
		for (int i = 0; i < 4; i++) {
			ts[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
			ts[i].start();
		}

		// Wait for the threads to finish and sum their results.
		for (int i = 0; i < 4; i++) {
			ts[i].join();
			if (ans < ts[i].ans) {
				ans = ts[i].ans;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws InterruptedException {
		int[] arr2 = { 1, 4, 6, 20, 1, 3, 7, 90, 45, 27, 89, 76, 100,11,56,98,23,101,200,42};
		int[] arr1 = { 1, 2, 3, 4, 5, 6 };
		int[] arr = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}

		System.out.println("Maximum Value in Array: " + maxValue(arr2));
	}
}