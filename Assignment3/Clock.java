/**
 * 
 *Problem-2
 * Total Error-1
 * Error in line no-12
 */
public class Clock {

	String time;

	void getTime() { // Wrong return type of getTime method, void return type implies that no return value is expected
	return time;} /* Here getTime method has "void" return type and this method is trying to
	 				 return "time" variable which is of "String" Type */

	void setTime(String t) {
		time = t;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
