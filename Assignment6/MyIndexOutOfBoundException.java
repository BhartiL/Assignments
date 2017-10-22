/**
 * Problem-1
 * Create your own MyIndexOutOfBoundException Class. 
 * It should contain these parameters
 * lowerBound - the lowest legal index value. 
 * upperBound - the highest legal
 * index value. index - the current index value.
 * Test your code in main method,by creating an indexOutOfBoundException. 
 * Output error message should be like this:
 * “Error Message: Index: 10, but Lower bound: 0, Upper bound: 9”
 *
 */
public class MyIndexOutOfBoundException extends Throwable {

	private static final long serialVersionUID = 1L;
	static int lowerBound = 1;
	static int upperBound = 12;
	static int index;

	public String toString() {
		return "Error Message: Index: " + index + ", but Lower bound: 0, Upper bound: " + upperBound;
	}

	public static void main(String args[]) throws MyIndexOutOfBoundException {
		index = 20;
		if (index > upperBound)
			throw new MyIndexOutOfBoundException();
	}
}
