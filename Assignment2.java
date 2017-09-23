import java.util.HashMap;
import java.util.Set;

public class Assignment2 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Assignment2 A=new Assignment2();
		System.out.println("The Total Salary of Employee is : "+A.employeeSalary(45)); /*Execution of employeeSalary function, Problem-1*/
		System.out.println("The Sum of Digits is: "+A.addDigits(573));	/*Execution of addDigits Function,Problem-2*/
		A.printPerfectNumbers(500);		/*Execution of printPerfectNumbers function,Problem-3*/
		System.out.println(" ");
		A.printIsoscelesTriangle(6);		/*Execution of printIsocelesTriangle function,Problem-6*/
	
		Pizza pizza=A.new Pizza();					/*Execution of Default Pizza Constructor-Problem-4 */
		Pizza Pizza1=A.new Pizza("ChickenPizza",5.5,20);		/*Execution of Parameterized Pizza Constructor-Problem-4 */
		Pizza1.Display();						/*Execution of Display method to show output of parameterized pizza Constructor-Problem-4 */
		System.out.println("This is Copied Constructor:");		/*Execution of copy Pizza Constructor-Problem-4 */
		Pizza Pizza2=A.new Pizza("VegPizza",10);			/*Execution of copy Pizza Constructor-Problem-4 */
		Pizza Pizza3=A.new Pizza(Pizza2);				/*Execution of copy Pizza Constructor-Problem-4 */
		Pizza2.show();							/*Execution of Show method to show output of copied Pizza Constructor-Problem-4 */
		Pizza3.show();							/*Execution of Show method to show output of copied Pizza Constructor-Problem-4 */
		
		System.out.println(" ");
		
		Customer C=A.new Customer("David",5.90);	 /*Creating Customer class object and Passing Customer Name and Price of Pizza values in Customer constructor-Problem-5 */
		C.addPizzaToOrder("ChickenPizza",2);		/*Execution of addPizzaToOrder function by Passing Pizza Type and Quantity of Pizza*/
		System.out.println("Money Spent on Pizza by "+C.customerName+" : "+C.calculateAmount());/* Execution of calculateAmount function which calculates Money/Amount the 															customer spent and Printing the output -Problem-5*/

		Customer C1=A.new Customer("Nancy",9.00);   /*Creating Customer class object and Passing Customer Name and Price of Pizza values in Customer constructor -Problem-5 */
		C1.addPizzaToOrder("VegPizza", 3);	      /*Execution of addPizzaToOrder function by Passing Pizza Type and Quantity of Pizza*/
		System.out.println("Money Spent on Pizza by "+C1.customerName+" : "+C1.calculateAmount());/* Execution of calculateAmount function which calculates Money/Amount the 															customer spent and Printing the output -Problem-5*/
		
	}
	/**
	 * 1. Write a java function to calculate the salary of an employee based on the following rules.
 			i. function takes input of number of hours an employee worked and returns the salary.
 			ii. The first 36 hours worked are paid at a rate of 15.0, then the next 5 hours are paid at a rate of 15 * 1.5. Hours after that up to a max of 48 are paid at a rate of 15 * 2.
	 */
	
	public double employeeSalary(double hours) {
		double employeeSalary=0.0;
		int  fixedSalaryRate=15;
		double firstIncrementRate=1.5;
		int secondIncrementRate=2;
		int firstWorkingHours=36;
		int midWorkingHours=41;
		int maxWorkingHours= 48;
		if((hours<0)&&(hours>maxWorkingHours)) {
			employeeSalary=Integer.MIN_VALUE;
		}
		if(hours>0 && hours<=firstWorkingHours) {
			employeeSalary=hours*fixedSalaryRate;
		}
		else if(hours>firstWorkingHours && hours<=midWorkingHours) {
			double remainingHours=hours-firstWorkingHours;
			employeeSalary=firstWorkingHours*fixedSalaryRate+remainingHours*fixedSalaryRate*firstIncrementRate;
		}
		else if(hours>midWorkingHours && hours<=maxWorkingHours) {
			double firstRemaininghours=midWorkingHours-firstWorkingHours;
			double secondRemaininghours=hours-midWorkingHours;
			employeeSalary=firstWorkingHours*fixedSalaryRate+firstRemaininghours*fixedSalaryRate*firstIncrementRate+secondRemaininghours*fixedSalaryRate*secondIncrementRate;
		}
		return employeeSalary;
	}
	
	/**
	 * 2. Write a java function that adds all the digits of an integer until it is single digit.
			i. function takes an integer as input and returns its sum of digits.
			ii. for example input = 37, sum = 3+7 = 10, sum = 1+0 = 1. result = 1.
	 */
	 public int addDigits(int input) {
		 int sum=0;
		 while(input>0) {
			 sum=sum+(input%10);
			 input/=10;
			 
		 }
		 return sum;
	 }
	 
	 /**
	  * 3. Write a java function to print all perfect number between 1 and n.
			i. Perfect number is a positive integer which is equal to the sum of its proper positive divisors.
			ii. For example: 6 is the first perfect number, Proper divisors of 6 are 1, 2, 3. Sum of its proper divisors = 1 + 2 + 3 = 6.		
	  */
	 public void printPerfectNumbers(int n) {
		 System.out.println("The perfect numbers between 1 to " + n +" : ");
		 int i; int j; int sum=0;
		 for (i=1;i<=n;i++){
			 for(j=1;j<i;j++) {
				 if(i%j==0) {
					 sum= sum+j;
				 }
			 }
			 if(sum==i) {
				 System.out.print(+sum+" "); 
			 }
			 sum=0;
		 }
		    
	 }
	 
	 /**
	  * 4. Write a java class called pizza with (Add detail as needed) : 
	     i. Create atleast 3 attributes :pizza type , unit price and loyalty points. More attributes are welcome to have. 
	     ii. Create constructors . Extra-credit of 0.5 point if you write more than 1 right constructor to this class		
	  */
	 public class Pizza{
		 String pizzaType;
		 double unitPrice;
		 int loyaltyPoints;
		 /*
		  * Default Constructor
		  */
		  Pizza() {
			 System.out.println("This is no argument constructor");
		 }
		  /*
		   * Parameterized Constructor
		   */
		  Pizza(String PizzaType,double unitPrice,int loyalityPoints){
			  this.pizzaType=PizzaType;
			  this.unitPrice=unitPrice;
			  this.loyaltyPoints=loyalityPoints;
		  }
		  void Display() {
			  System.out.println("This is Parameterized constructor:");
			  System.out.println("PizzaType is "+pizzaType+", UnitPrice is "+unitPrice+", Loyality Points are "+loyaltyPoints);
		  }
		  /*
		   * Copy Constructor
		   */
		  Pizza(String pizzaType1,double unitPrice1){
			  this.pizzaType=pizzaType1;
			  this.unitPrice=unitPrice1;
		  }
		  Pizza(Pizza Object){
			  this.pizzaType=Object.pizzaType;
			  this.unitPrice=Object.unitPrice;
		  }
		  void show() {
			 
			  System.out.println("PizzaType:"+pizzaType);
			  System.out.println("UnitPrice:"+unitPrice);
		  }
	 }
	 
	 /**
	  * 5. Write a java class called customer (Add detail as needed) : 
	       i. Create Attributes: customer name and which pizzas customer has ordered. 
	       ii. Think about what kind of data structure can be used to record the pizza name and number of each kind of pizza.( Give me your thought, Extra credit of 1 point)
	       iii. In main method , sum up how much money the customer spent.
	  */
	 
	 public class Customer {
		 private String customerName;
		 double amount=0.0;
		 double pizzaPrice;		/* pizzaPrice for Pizza */
		 HashMap<String, Integer> pizzas = new HashMap<String, Integer>(); /*HashMap DataStructure can be used*/
		 Customer(String CustomerName,double pizzaPrice){
			 this.customerName=CustomerName;
			 this.pizzaPrice=pizzaPrice;
		 }
		 /*
		  * Put Pizza And Pizza Quantity in HashMap
		  */
		 public void addPizzaToOrder(String pizzaName, int pizzaQuantity) {
			 pizzas.put(pizzaName, pizzaQuantity);
		 }
		 /*
		  * Calculate total order amount/Money the customer spent
		  * @return total order amount/Money
		  */
		 public double calculateAmount() {
			 Set<String> pizzaNames = pizzas.keySet();
			 for(String pizza : pizzaNames) {
				 amount = amount + pizzas.get(pizza)*pizzaPrice;
			 }
			 return amount;
		 }
	 }
	 /**
	  * // EXTRA CREDIT
	  * 6. Write a Java program that generates an isosceles right angled triangle made of asterisks. 
		  i. function should take input of one equal side as integer. Other than the edges the inner part of the triangle should be empty.
		  ii. For example input is 6. the function should print—
	  */
	 public void printIsoscelesTriangle( int n) {
		 for(int i=0;i<=n-1;i++) {
			 for(int j=0;j<=i;j++) {
				 if(i==n-1 || j==0 ||i==j) {
					 System.out.print("*");
				 }
				 else
					 System.out.print(" ");
			 }
			 System.out.println("");
		 
		 }
		
	 }
	
}
