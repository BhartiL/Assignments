import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Problem-3 Design an Automated Teller Machine (ATM). Write the test cases to
 * each function of the machine in AtmTest
 */
public class ATM { // ATM class
	double availableAmountInMachine;
	double transactionFee;
	long currentUserBankAccountNo = -1;
	// map for bank account number - User
	HashMap<Long, User> bankAccountUserMap = new HashMap<>();
	// Map for User - Password
	HashMap<User, Integer> userPasswordMap = new HashMap<>();
	Random rand = new Random();

	public ATM(double availableAmountInMachine, double transactionFee) { // ATM constructor
		this.availableAmountInMachine = availableAmountInMachine;
		this.transactionFee = transactionFee;
	}

	/*
	 * createNewUser method is creating new user using given details This returns
	 * object of created user
	 */
	public User createNewUser(String name, String address, int age, long phoneNo) {
		// Create a random 9 digit bank account number
		long bankAccountNumber = rand.nextInt(900000000) + 100000000;
		// Create a random 4 digit password
		int password = rand.nextInt(8000) + 1000;
		User user = new User(name, age, address, phoneNo, bankAccountNumber);
		bankAccountUserMap.put(bankAccountNumber, user);
		userPasswordMap.put(user, password);
		System.out.println("New User Created!! Details : Name-> " + name + " : Address-> " + address + " : Age->" + age
				+ " : Phoneno-> " + phoneNo + " : BankAccount No-> " + bankAccountNumber + " : and password-> "
				+ password + "\n");
		return user;
	}

	/*
	 * loginUser method lets current user to login
	 */
	public boolean loginUser(long bankaccount, int password) {
		User user = bankAccountUserMap.get(bankaccount);
		int pin = userPasswordMap.get(user);
		if (password == pin) {
			currentUserBankAccountNo = bankaccount;
			System.out.println("You are successfully logged in!!");
			return true;
		} else {
			return false;
		}
	}

	/*
	 * forgotPassword method lets current user get new password
	 */
	public int forgotPassword(String name, int age, long phoneno, long bankaccount) {
		User user = bankAccountUserMap.get(bankaccount);
		if (user.name.equals(name) && user.age == age && user.phoneNumber == phoneno) {
			int password = rand.nextInt(9000) + 1000;
			userPasswordMap.put(user, password);
			System.out.println("Your new password is : " + password + "\n");
			return password;
		} else {
			System.out.println("Authetication Failed!! \n");
			return 0;
		}
	}

	/*
	 * transactDeposit method lets current user to deposit amount in his/her bank
	 * account This adds the amount deposited to available balance and also
	 * increases ATM's dispensing capacity accordingly.
	 */
	public void transactDeposit(double amount) {
		User user = bankAccountUserMap.get(currentUserBankAccountNo);
		user.availableBalance += amount;
		availableAmountInMachine += amount;
		// Add amount+transaction fee to user's transaction history
		user.transaction.add(amount);
		System.out.println("The amount has been successfully added !!");
		System.out.println("The available amount balance is :" + user.availableBalance + "\n");
	}

	/*
	 * transactWithdrawl method lets current user to withdraw amount
	 */
	public void transactWithdrawl(double amount) {
		User user = bankAccountUserMap.get(currentUserBankAccountNo);
		double amountToDeduct = (amount + transactionFee);
		// if amount+transaction fee is less than user's balance, let him withdraw
		if (amountToDeduct <= user.availableBalance) {
			// Check if ATM has cash to dispense
			if (amountToDeduct <= availableAmountInMachine) {
				user.availableBalance -= amountToDeduct;
				availableAmountInMachine -= (amount);
				// Add amount+transaction fee to user's transaction history
				user.transaction.add(-1 * (amountToDeduct));
				System.out.println("The amount has been successfully withdrawn!! ");
				System.out.println("The available amount balance is :" + user.availableBalance);
				System.out.println("The amount with transaction fee is which got deducted from your account is : "
						+ amountToDeduct + "\n");
			} else {
				System.out.println("There is insufficient balance in ATM.");
				System.out.println("Please enter amount lower than " + availableAmountInMachine + "\n");
			}
		} else {
			System.out.println("There is not sufficient balance in your account to complete withdrawl.\n ");
		}
	}

	/*
	 * availableBalance method lets user to check available amount balance in
	 * his/her bank account
	 */
	public double availableBalance() {
		User user = bankAccountUserMap.get(currentUserBankAccountNo);
		System.out.println("The available balance in your account is : " + user.availableBalance + "\n");
		return user.availableBalance;
	}

	/*
	 * recentTransactions methods displays latest/last 10 transactions
	 */
	public ArrayList<Double> recentTransactions() {
		ArrayList<Double> transactions = new ArrayList<>();
		int count = 0;
		User user = bankAccountUserMap.get(currentUserBankAccountNo);
		if (user.transaction.size() == 0) {
			System.out.println("There are no transactions to show!!");
			return transactions;
		}
		for (int i = user.transaction.size(); i > 0; i--) {
			count++;

			if (count <= 10) {
				if (user.transaction.get(i - 1) < 0) {
					System.out.println("Withdrawal-> " + Math.abs(user.transaction.get(i - 1)));
				} else {
					System.out.println("Deposit-> " + user.transaction.get(i - 1));
				}
				transactions.add(user.transaction.get(i - 1));
			}
		}
		return transactions;
	}

	/*
	 * changePassword method lets current user to change password
	 */
	public void changePassword(int pin) {
		User user = bankAccountUserMap.get(currentUserBankAccountNo);
		userPasswordMap.put(user, pin);
		System.out.println("Your Password has been changed!! \n");
	}

	/*
	 * Exit current logged in user
	 */
	public void exit() {
		System.out.println("Exiting!!");
		currentUserBankAccountNo = -1;
	}

	public static void main(String[] args) {

	}
}

// User class
class User {
	String name;
	String address;
	long phoneNumber;
	int age;
	long bankAccountNumber;
	double availableBalance;
	ArrayList<Double> transaction = new ArrayList<Double>();

	User(String name, int age, String address, long phoneno, long bankaccount) { // User Constructor
		this.address = address;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneno;
		this.bankAccountNumber = bankaccount;
	}
}
