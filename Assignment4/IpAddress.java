package Assignment4;

public class IpAddress {
	/**
	 * Problem-3
	 * Implement an IpAddress class that stores an IP address as a dotted-decimal
	 * string and as four octet ints. You must implement all of the following:
	 * Instance variables: dottedDecimal – a dotted-decimal string. Example value:
	 * “216.27.6.136” firstOctet, secondOctet, thirdOctet, fourthOctet – four int
	 * variables that store the octets for an IP address Constructor: This
	 * constructor receives one parameter, a dotted-decimal string. You may assume
	 * that the parameter’s value is valid (i.e., no error checking required). The
	 * constructor initializes the instance variables with appropriate values. There
	 * are many ways to solve the problem of extracting octets from the given
	 * dotted-decimal string. We recommend that you use String methods to extract
	 * the individual octets as strings, and then use parseInt method calls to
	 * convert the octet strings to ints. getDottedDecimal method: This is a
	 * standard accessor method that simply returns the dottedDecimal instance
	 * variable’s value. getOctet method: This method receives the position of one
	 * of the octets (1, 2, 3, or 4) and returns the octet that’s at that position.
	 * Provide a driver class that tests your IpAddress class. Your driver class
	 * should contain this main method: (Score 2)
	 */
	
	String getDottedDecimalString;
	String tempStr = ""; // declaring temporary string
	int count = 0;
	int firstOctet;
	int secondOctet;
	int thirdOctet;
	int fourthOctet;

	public IpAddress(String dottedDecimalString) {
		this.getDottedDecimalString = dottedDecimalString;
		int length = dottedDecimalString.length();
		for (int i = 0; i < length; i++) {
			if (dottedDecimalString.charAt(i) != '.') {
				tempStr = tempStr + String.valueOf(dottedDecimalString.charAt(i));
				fourthOctet = Integer.parseInt(tempStr); //getting fourthOctet
			} else if (dottedDecimalString.charAt(i) == '.' && count == 0) {
				firstOctet = Integer.parseInt(tempStr); //getting firstOctet
				tempStr = "";
				count = count + 1; 
			} else if (dottedDecimalString.charAt(i) == '.' && count == 1) {
				secondOctet = Integer.parseInt(tempStr); //getting second octet
				tempStr = "";
				count = count + 1;
			} else if (dottedDecimalString.charAt(i) == '.' && count == 2) {
				thirdOctet = Integer.parseInt(tempStr); //getting third octet
				tempStr = "";
				count = count + 1;
			}
		}
	}

	public String getGetDottedDecimalString() {
		return getDottedDecimalString; //returning DottedDecimalString
	}

	int getOctet(int j) {
		int k = 0;
		if (j == 1) {
			k = firstOctet;
		}
		if (j == 2) {
			k = secondOctet;
		}
		if (j == 3) {
			k = thirdOctet;
		}
		if (j == 4) {
			k = fourthOctet;
		}
		return k;
	}

	public static void main(String[] args) {
		IpAddress ip = new IpAddress("216.27.6.136");
		System.out.println(ip.getDottedDecimalString);
		System.out.println(ip.getOctet(4));
		System.out.println(ip.getOctet(1));
		System.out.println(ip.getOctet(3));
		System.out.println(ip.getOctet(2));

	}
}
