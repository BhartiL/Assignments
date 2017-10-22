import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Extra Credit Problem Design an Automated Teller Machine (ATM) by storing the
 * above data in a file, rather than in a dataStructure. Write the test cases to
 * each function of the machine in AtmTest
 */
public class ATMExtraCredit {
	public static String filePath = "/Users/stilok2/eclipse-workspace/Assignment6/src/userdata.txt";
	long availableAmountInMachine;
	double transactionFee;
	long currentUserBankAccountNo = -1;
	Random rand = new Random();

	// ATM constructor
	public ATMExtraCredit(long availableAmountInMachine, double transactionFee) {
		this.availableAmountInMachine = availableAmountInMachine;
		this.transactionFee = transactionFee;
	}

	/*
	 * createNewUser method creates new user with given details returns map for
	 * created user object and the password
	 */
	public HashMap<User, Integer> createNewUser(String name, String address, int age, long phoneNo) {
		// create a random 9 digit bank acc number
		long bankAccountNumber = rand.nextInt(999999999) + 111111111;
		// create a random 4 digit pin
		int password = rand.nextInt(8999) + 1000;
		String fileRecord = "bankacc=" + bankAccountNumber + ";" + "pass=" + password + ";" + "name=" + name + ";"
				+ "age=" + age + ";" + "phn=" + phoneNo + ";" + "addr=" + address + ";" + "avlblbal=" + 0.0 + ";"
				+ "tranacs=";
		User user = new User(name, age, address, phoneNo, bankAccountNumber);
		ATMExtraCredit.updateUserDataFile(null, fileRecord);
		HashMap<User, Integer> uMap = new HashMap<>();
		uMap.put(user, password);
		System.out.println("New User Created!! Details : Name-> " + name + " : Address-> " + address + " : Age->" + age
				+ " : Phoneno-> " + phoneNo + " : BankAccount No-> " + bankAccountNumber + " : and password-> "
				+ password + "\n");
		return uMap;

	}

	/*
	 * loginUser method lets current user to login
	 */
	public boolean loginUser(long bankaccount, int password) {
		for (String line : ATMExtraCredit.readUserData()) {
			// if given bank acc number and pin exist in file for a user, then login
			if (line.startsWith("bankacc=" + bankaccount) && line.contains("pass=" + password)) {
				currentUserBankAccountNo = bankaccount;
				break;
			}
		}
		if (currentUserBankAccountNo > 0) {
			System.out.println("You are successfully logged in!!");
			return true;
		} else
			System.out.println("Invalid credentials!!");
		return false;
	}

	/*
	 * forgotPassword method lets current user get new password
	 */
	public int forgotPassword(String name, int age, long phoneno, long bankaccount) {
		String oldRecord = "";
		List<String> userdata = ATMExtraCredit.readUserData();

		for (int i = 0; i < userdata.size(); i++) {
			String line = userdata.get(i);
			if (line.contains("bankacc=" + bankaccount) && line.contains("age=" + age)
					&& line.contains("phn=" + phoneno) && line.contains("name=" + name)) {
				oldRecord = line;
				break;
			}
		}

		// if user's record is found in above for loop, then update it
		if (oldRecord.isEmpty()) {
			System.out.println("Authetication Failed!! \n");
			return 0;
		} else {
			int password = rand.nextInt(9999) + 1000;
			System.out.println("Your new password is : " + password + "\n");
			String oldPin = oldRecord.substring(oldRecord.indexOf("pass="), oldRecord.indexOf(";name="));
			String newRecord = oldRecord.replace(oldPin, "pass=" + password);
			updateUserDataFile(oldRecord, newRecord);
			return password;
		}
	}

	/*
	 * transactDeposit method lets current user to deposit amount in his/her bank
	 * account
	 */
	public void transactDeposit(double amount) {
		String fileRecord = "";
		for (String line : ATMExtraCredit.readUserData()) {
			if (line.startsWith("bankacc=" + currentUserBankAccountNo)) {
				fileRecord = line;
				break;
			}
		}
		Double avlblBal = Double
				.valueOf(fileRecord.substring(fileRecord.indexOf("avlblbal="), fileRecord.indexOf("tranacs="))
						.replace("avlblbal=", "").replace(";", ""));
		// add deposited amount to user's balance
		Double newAvlblBal = avlblBal + amount;

		// add deposited amount to ATM's dispensing capacity
		availableAmountInMachine += amount;

		// Update available balance for user in file
		String newRecord = fileRecord.replace("avlblbal=" + String.valueOf(avlblBal),
				"avlblbal=" + String.valueOf(newAvlblBal));
		newRecord = newRecord + "," + amount;
		updateUserDataFile(fileRecord, newRecord);

		System.out.println("The amount has been successfully added !!");

	}

	/*
	 * transactWithdrawl method lets current user to withdraw amount
	 */
	public void transactWithdrawl(double amount) {

		double amountToDeduct = (amount + transactionFee);

		String fileRecord = "";
		for (String line : ATMExtraCredit.readUserData()) {
			if (line.startsWith("bankacc=" + currentUserBankAccountNo)) {
				fileRecord = line;
				break;
			}
		}

		Double avlblBal = Double
				.valueOf(fileRecord.substring(fileRecord.indexOf("avlblbal="), fileRecord.indexOf("tranacs="))
						.replace("avlblbal=", "").replace(";", ""));
		// if user's available balance is greater than withdrawal amount + transaction
		// fee, let him do the transaction
		if (amountToDeduct <= avlblBal) {
			// Checks ATM's cash dispensing capacity
			if (amountToDeduct <= availableAmountInMachine) {
				Double newAvlblBal = avlblBal - amountToDeduct;
				availableAmountInMachine -= (amount + transactionFee);

				String newRecord = fileRecord.replace("avlblbal=" + String.valueOf(avlblBal),
						"avlblbal=" + String.valueOf(newAvlblBal));
				newRecord = newRecord + ",-" + amount;
				updateUserDataFile(fileRecord, newRecord);

				System.out.println("The amount has been successfully withdrawn!! ");
				System.out.println("The avalible amount balance is :" + newAvlblBal);
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

		String fileRecord = "";
		for (String line : ATMExtraCredit.readUserData()) {
			if (line.startsWith("bankacc=" + currentUserBankAccountNo)) {
				fileRecord = line;
				break;
			}
		}
		String avlblBal = fileRecord.substring(fileRecord.indexOf("avlblbal="), fileRecord.indexOf("tranacs="))
				.replace(";", "");
		System.out.println(
				"The available balance in your account is : " + avlblBal.replace("avlblbal=", "").replace(";", ""));
		return Double.valueOf(avlblBal.replace("avlblbal=", "").replace(";", ""));

	}

	/*
	 * recentTransactions methods displays latest/last 10 transactions
	 * 
	 */
	public ArrayList<Double> recentTransactions() {
		int count = 0;
		ArrayList<Double> recentTransactions = new ArrayList<>();
		String fileRecord = "";
		for (String line : ATMExtraCredit.readUserData()) {
			if (line.startsWith("bankacc=" + currentUserBankAccountNo)) {
				fileRecord = line;
				break;
			}
		}

		String transactionsStr = fileRecord.substring(fileRecord.lastIndexOf('='), fileRecord.length());
		String[] transactions = transactionsStr.split(",");
		if (transactions.length == 1) {
			System.out.println("There are no transactions to show!!");
			return recentTransactions;
		}
		for (int i = transactions.length - 1; i > 0; i--) {
			count++;
			if (count <= 10) {
				String tx = transactions[i];
				if (tx.isEmpty())
					continue;
				Double amount = Double.valueOf(tx);
				recentTransactions.add(amount);
				if (amount < 0) {
					System.out.println("Withdrawal ->" + Math.abs(amount));
				} else {
					System.out.println("Deposit ->" + Math.abs(amount));
				}
			}
		}
		return recentTransactions;
	}

	/*
	 * changePassword method lets current user to change password
	 * 
	 */
	public void changePassword(int pin) {

		String oldRecord = "";
		for (String line : ATMExtraCredit.readUserData()) {
			if (line.startsWith("bankacc=" + currentUserBankAccountNo)) {
				oldRecord = line;
				break;
			}
		}
		String oldPin = oldRecord.substring(oldRecord.indexOf("pass="), oldRecord.indexOf(";name="));
		String newRec = oldRecord.replace(oldPin, "pass=" + pin);
		updateUserDataFile(oldRecord, newRec);
		System.out.println("Your Password has been changed!! \n");

	}

	public void exit() {
		System.out.println("Exiting!!");
		currentUserBankAccountNo = -1;
	}

	public static void main(String[] args) {
	
	}

	/*
	 * Read data in file
	 */
	public static List<String> readUserData() {
		List<String> fileContent;
		try {
			fileContent = new ArrayList<>(
					Files.readAllLines(Paths.get(ATMExtraCredit.filePath), StandardCharsets.UTF_8));
			return fileContent;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Update userdata.txt file. For writing a new line, pass null for oldRecord
	 * 
	 */
	public static void updateUserDataFile(String oldRecord, String newRecord) {
		try {
			List<String> fileContent = new ArrayList<>(
					Files.readAllLines(Paths.get(ATMExtraCredit.filePath), StandardCharsets.UTF_8));
			// Check if new record needs to be created, or should update existing record
			if (oldRecord == null) {
				Files.write(Paths.get(filePath), newRecord.getBytes(), StandardOpenOption.APPEND);
			} else {
				for (int i = 0; i < fileContent.size(); i++) {
					if (fileContent.get(i).equals(oldRecord)) {
						fileContent.set(i, newRecord);
						Files.write(Paths.get(ATMExtraCredit.filePath), fileContent, StandardCharsets.UTF_8);
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class User { // User class
	String name;
	String address;
	long phoneNumber;
	int age;
	long bankAccountNumber;
	double availableBalance;
	ArrayList<Double> transaction = new ArrayList<Double>();

	User(String name, int age, String address, long phoneno, long bankaccount) {
		this.address = address;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneno;
		this.bankAccountNumber = bankaccount;
	}
}
