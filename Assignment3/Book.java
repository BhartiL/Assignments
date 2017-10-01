/**
 * Problem-1
 * Total Errors-3
 * Errors in line numbers-13, 25, 31
 */

public class Book {
	
		int size;
		int price;
		String name;
	
		public Book(int size){		//Error->Duplicate Method Book(int) is defined within the class
			this.size = size;	//Two same (int) type Book constructor is defined within the class
		}				/*Two methods with same name and [arg]types i.e.'int' here in this case,
						   can not be defined within the same class */
	
		public Book(int size, int price, String name){
			super(); 
			this.size = size;
			this.price = price;
			this.name = name;
		}
	
		public Book(int price){       //Error->Duplicate Method Book(int) is defined within the class
			this.price = price;    //Two same Book(int) type method is defined within the class
						/*Two methods with same name and [arg]types i.e.'int' here in this case,
						 can not be defined within the same class */	
		}
	
		public setName(String name){ //Error->return type of setName method is missing
			return name;	} //Every method should have return type e.g. String, int ,void etc..
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
