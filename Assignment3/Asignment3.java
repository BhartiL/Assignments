import java.util.Arrays;

public class Asignment3 {

	public static void main(String[] args) {
		Asignment3 as = new Asignment3();
		System.out.println(as.removeVowelsFromString("Deepak Patel"));// Execution of removeVowelsFromString method
		// Execution of checkIfTwoStringsAreAnagram method//	
		System.out.println("The Anagram result of two input Strings is:" + as.checkIfTwoStringsAreAnagram("Keep", "Peek"));
		Calculator cal = as.new Calculator();
		cal.basicFunctions(6, 3, '+'); // Execution of basicFunctions method that calculates add,sub,product
										// and division of number
		cal.calculatorAdvancedFunction(8);// Execution of calculatorAdvancedFunction that calculates square,cube and
											// squareRoot of number
		System.out.println("Fahrenheit to Celsius Conversion is: " + cal.fahrenheitToCelsius(100));
		System.out.println("Celsius to Fahrenheit Conversion is: " + cal.celsiusToFahrenheit(37.77777777777778));
		System.out.println("Feet to Inches Conversion is: " + cal.feetToInchesConversion(1));
		System.out.println("Inches to Feet Conversion is: " + cal.inchesToFeetConversion(12));
		cal.quadraticCalculator(2, 3, 1);// Execution of quadraticCalculator method that solves quadratic equation

	}

	/**
	 * Problem-3 Write a Java function to remove vowels in a string. (Score 2) 
	 * i.The function should take a string as input.
	 * ii. Should return the input string after omitting the vowels.
	 */
	public String removeVowelsFromString(String input) {
		System.out.print("Removing Vowels from The String [" + input + "]\n");
		String strNew = input.replaceAll("[aeiouAEIOU]", "");

		System.out.print("All Vowels Removed Successfully..!! Now the String is : ");

		return strNew;
	}

	/**
	 * Problem-4 Write a java function to check if two strings are Anagrams or not.(Score 2)
	 * i.The function should take two input strings. 
	 * ii. Should return a boolean 'true' if the inputs are Anagrams else return 'false'.
	 */
	public boolean checkIfTwoStringsAreAnagram(String S1, String S2) {
		boolean flag = true;

		if (S1.length() != S2.length()) {
			flag = false;

		} else {
			char[] S1Array = S1.toLowerCase().toCharArray();
			char[] S2Array = S2.toLowerCase().toCharArray();
			Arrays.sort(S1Array);
			Arrays.sort(S2Array);
			flag = Arrays.equals(S1Array, S2Array);
		}
		return flag;
	}

	/**
	 * Problem-5 Create a calculator that can perform the following features. (TotalScore 4) 
	 * i.The calculator should be able to perform Addition, subtraction, multiplication, division.(Score 2)
	 * ii. Should be able to perform squareRoot,square, cube. (Score 1)
	 * iii. Should be able to convert 'Fahrenheit-Celsius' ,Feet-Inches'. (Score 1)
	 * Extra Credit(Score 2) 
	 * iv.The calculator should be able to solve a quadratic equation and return the solution as array.
	 */
	public class Calculator {

		/**
		 * basicFunctions method is able to perform Addition,subtraction,multiplication
		 * and division of two numbers
		 */
		public void basicFunctions(int a, int b, char Ch) {
			double result;
			char Choice;
			Choice = Ch;

			switch (Choice) {
			case '+':
				result = a + b;
				System.out.print("The Sum of two numbers is = " + result);
				break;
			case '-':
				result = a - b;
				System.out.print("The Subraction of two numbers is = " + result);
				break;
			case '*':
				result = a * b;
				System.out.print("The Product of two number is = " + result);
				break;
			case '/':
				result = a / b;
				System.out.print("The Division of two number is = " + result);
				break;
			case ' ':
				System.exit(0);
				break;
			default:
				System.out.print("Wrong Choice!!!");
				break;
			}
			System.out.println("");
		}

		/**
		 * CalculatorAdvancedFunction1 is able to perform squareRoot, square, cube of number.
		 * 
		 */

		public void calculatorAdvancedFunction(int num) {
			// Square,cube and squareRoot of number can be calculated by below Math.pow functions
			/* System.out.println("Square of " + num + " is: " + Math.pow(num, 2)); */
			/* System.out.println("Cube of " + num + " is: " + Math.pow(num, 3)); */
			/* System.out.println("Square Root of " + num + " is: " + Math.sqrt(num)); */
			double square = num * num;
			System.out.println("Square of " + num + " is: " + square);
			double cube = num * num * num;
			System.out.println("Cube of " + num + " is: " + cube);
			double temp;
			double squareroot = num / 2;
			do {
				temp = squareroot;
				squareroot = (temp + (num / temp)) / 2;
			} while ((temp - squareroot) != 0);

			System.out.println("Square Root of " + num + " is: " + squareroot);
		}

		/**
		 * fahrenheitToCelsius method is able to convert Fahrenheit to Celsius.
		 * 
		 * @param Fahrenheit
		 * @return
		 */
		public double fahrenheitToCelsius(double Fahrenheit) {

			double celsius = ((Fahrenheit - 32) * 5) / 9;
			return celsius;
		}

		/**
		 * celsiusToFahrenheit method is able to convert Celsius to Fahrenheit
		 * 
		 */

		public double celsiusToFahrenheit(double celsius) {

			double Fahrenheit = ((9 * celsius) / 5 + 32);
			return Fahrenheit;
		}

		/**
		 * feetToInches method is able to convert 'Feet-Inches'
		 * 
		 */
		public double feetToInchesConversion(double feet) {
			double inches = feet * 12;
			return inches;
		}

		/**
		 * 
		 * inchesToFeetConversion method is able to convert Inches to Feet
		 */
		public double inchesToFeetConversion(double inches) {
			double feet = inches / 12;
			return feet;
		}

		/**
		 * quadraticCalculator is able to solve a quadratic equation and return the
		 * solution as array.
		 * 
		 */

		public void quadraticCalculator(double a, double b, double c) {
			double[] roots = new double[4];
			double[] variables = new double[3];
			variables[0] = a;
			variables[1] = b;
			variables[2] = c;
			double d = variables[1] * variables[1] - (4 * variables[0] * variables[2]);
			System.out.println("Given quadratic equation is: " + a + "x^2 + " + b + "x + " + c);
			if (d > 0) {
				System.out.println("Roots are real and unequal");
				roots[0] = (-variables[1] + Math.sqrt(d)) / (2 * variables[0]);
				roots[1] = (-variables[1] - Math.sqrt(d)) / (2 * variables[0]);
				System.out.println("First root is:" + roots[0]);
				System.out.println("Second root is:" + roots[1]);
				} else if (d == 0) {
				System.out.println("Roots are real and equal");
				roots[0] = (-variables[1] + Math.sqrt(d)) / (2 * variables[0]);

				System.out.println("Root:" + roots[0]);

				} else {
				System.out.println("Roots are imaginary " + roots[0]);
			}
		}
	}
}
