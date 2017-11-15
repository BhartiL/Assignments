package Assignment4;

public class Problem1 {
	/**
	 * Problem-1
	 * Now you are given a string S, which represents a software license key which
	 * we would like to format. The string is composed of alphanumerical characters
	 * and dashes. The dashes split the alphanumerical characters within the string
	 * into groups.(i.e If they are M dashes, the string is split into M+1 groups).
	 * The dashes in the given string are possibly misplaced. We want each group of
	 * characters to be of length K (except for possibly the first group, which
	 * could be shorter, but still must contain at least one character). To satisfy
	 * this requirement, we will reinsert dashes. Additionally, all the lower case
	 * letters in the string must be converted to upper case. So, you are given a
	 * non-empty string S, representing a license key to format, and an integer K.
	 * And you need to return the license key formatted according to the description
	 * above. (Score 2)
	 *
	 * 
	 */
	public void softwareFormatKey(String input, int k) {

		String tempString = "";
		String output = "";
		String output1 = "";
		String output2 = "";
		String pattern1 = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9-]+)$";

		int inputStringLength = input.length();
		if (input != "" && input.length() < 12000) { 
			if (k > 0) {
				if (input.matches(pattern1) && input.contains("-")) { //checking string is alphanumeric with '-'
					for (int i = inputStringLength - 1; i >= 0; i--) {
						if (input.charAt(i) != '-') { //getting reverse input string without '-'
							tempString = String.valueOf(input.charAt(i)).toUpperCase();
							output = output + tempString;
						}
					}
					int resultedOutputStringlength = output.length();
					int count = 0;
					for (int j = 0; j < resultedOutputStringlength; j++) {
						output1 = output1 + String.valueOf(output.charAt(j));
						count++;
						if (count == k) { //checking count is equal to K or not
							output1 = output1 + "-"; //appending '-' in resulted output string
							count = 0;
						}
					}
					int resultedOutput1StringLength = output1.length();
					for (int m = resultedOutput1StringLength - 1; m >= 0; m--) {
						if(m==resultedOutput1StringLength - 1 &&output1.charAt(m)=='-')
							continue;
						output2 = output2 + String.valueOf(output1.charAt(m)); // Final output2 string
					}
					System.out.println(output2);

				} else {
					System.out.println("Please enter alphanumeric input string which also has dash in it");
				}
			} else {
				System.out.println("Please enter positive integer value of k ");
			}
		} else {
			System.out.println("Please enter non empty String,also String length Should be less than 12000");
		}
	}

	public static void main(String[] args) {
		Problem1 A = new Problem1();
		A.softwareFormatKey("2-4A0r7-4k",3);
		A.softwareFormatKey("24A0-R74K", 4);
	}

} 
