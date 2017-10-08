package Assignment4;

import java.util.Map;
import java.util.TreeMap;

public class Problem5 {
	/**
	 * Problem-5
	 * Given an integer, convert it to a Roman numeral. Input is guaranteed to be
	 * within the range from 1 to 3999. (Score 2)
	 */
	private static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
	static {
		map.put(1000, "M"); // adding Roman numeral values for integers into tree map
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");
	}

	public static String inToRoman(int num) {
		String Error = "Please enter the number within the range from 1 to 3999";
		if (num > 0 && num <= 3999) { 
			int l = map.floorKey(num);
			if (num == l) {
				return map.get(num);
			}
			return map.get(l) + inToRoman(num - l);
		}
		return Error;
	}

	public static void main(String[] args) {
		Problem5 prob = new Problem5();
		System.out.println("The result of conversion of an integer to roman numeral is: " + prob.inToRoman(2000));

	}

}
