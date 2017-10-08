package Assignment4;

/**
 * Problem-6-Extra-Credit
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. (Score 2)
 * 
 *
 */
public class ExtraCreditProblem {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int sum = nums1.length + nums2.length; //adding up two input arrays length
		if (sum % 2 == 0) {
			return (findKth((sum / 2) + 1, nums1, nums2, 0, 0) + findKth(sum / 2, nums1, nums2, 0, 0)) / 2.0;
		} else {
			return findKth((sum / 2) + 1, nums1, nums2, 0, 0);
		}
	}

	public int findKth(int k, int[] nums1, int[] nums2, int s1, int s2) {
		if (s1 >= nums1.length)
			return nums2[s2 + k - 1];

		if (s2 >= nums2.length)
			return nums1[s1 + k - 1];

		if (k == 1)
			return Math.min(nums1[s1], nums2[s2]);

		int m1 = s1 + k / 2 - 1;
		int m2 = s2 + k / 2 - 1;

		int mid1 = m1 < nums1.length ? nums1[m1] : Integer.MAX_VALUE;
		int mid2 = m2 < nums2.length ? nums2[m2] : Integer.MAX_VALUE;

		if (mid1 < mid2) {
			return findKth(k - k / 2, nums1, nums2, m1 + 1, s2);
		} else {
			return findKth(k - k / 2, nums1, nums2, s1, m2 + 1);
		}
	}

	public static void main(String[] args) {
		ExtraCreditProblem Prob = new ExtraCreditProblem();
		int ar1[] = { 1, 3 }; // array 1
		int ar2[] = { 2, 7 }; //array 2

		System.out.println("The median of two sorted arrays is: " + Prob.findMedianSortedArrays(ar1, ar2));
	}

}
