import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * Write the test cases to each function of the machine in AtmTest for ATM.java
 * 
 */
public class ATMTest {

	public static double transactionFee = 2.0;
	public static double availableAmountInMachine = 100;
	public static ATM ATM = new ATM(availableAmountInMachine, transactionFee);
	public static User testUser;
	public static int password;
	public static String name = "Paul";
	public static String address = "SLU, Seattle";
	public static int age = 50;
	public static long phoneNo = 425123567l;
	// create test user before launching tests
	static {
		testUser = ATM.createNewUser(name, address, age, phoneNo);
		password = ATM.userPasswordMap.get(testUser);
	}

	@Rule
	public TestName x = new TestName();

	@Before
	public void xyz() {
		System.out.println("------" + x.getMethodName() + "------");
	}

	/*
	 * Test that created user's details match
	 */
	@Test
	public void createUserTest() {
		Assert.assertTrue("Name is not correct " + testUser.name, testUser.name.equals(name));
		Assert.assertTrue("Address is not correct " + testUser.address, testUser.address.equals(address));
		Assert.assertTrue("Age is not correct " + testUser.age, testUser.age == age);
		Assert.assertTrue("PhoneNo is not correct!", testUser.phoneNumber == phoneNo);
		Assert.assertTrue("Bank account number is not as expected " + testUser.bankAccountNumber,
				(testUser.bankAccountNumber > 100000000 && testUser.bankAccountNumber < 999999999));
		Assert.assertTrue("Password is not a 4-digit number: " + password, password > 1000 && password < 9999);
	}

	/*
	 * Test that login was successful
	 */
	@Test
	public void loginUserTest() {
		Assert.assertTrue("Login wasn't successful", ATM.loginUser(testUser.bankAccountNumber, password));
		Assert.assertTrue("Wrong user was logged in", ATM.currentUserBankAccountNo == testUser.bankAccountNumber);
	}

	/*
	 * Test that user's password is reset using forgot password functionality
	 */
	@Test
	public void forgotPasswordTest() {
		int pin = ATM.forgotPassword(name, age, phoneNo, testUser.bankAccountNumber);
		password = pin;
		Assert.assertTrue("Password is not a 4-digit number: " + pin, pin > 1000 && pin < 9999);

	}

	/*
	 * Test for transactDeposit method, checking currentUser is able to deposit
	 * successfully
	 */
	@Test
	public void depositTest() {
		double previousBal = testUser.availableBalance;
		double amountToDeposit = 10;
		ATM.transactDeposit(amountToDeposit);
		double currentBal = testUser.availableBalance;
		Assert.assertTrue("Amount deposit was unsuccessful", (currentBal - previousBal) == amountToDeposit);
	}

	/*
	 * Test for transactWithdrawl method with sufficient available balance
	 */
	@Test
	public void withdrawlTestWithSufficientBal() {
		testUser.availableBalance = 12;
		double previousBal = testUser.availableBalance;
		double amount = 1;
		// double transactionFee=1;
		ATM.transactWithdrawl(amount);
		double currentBal = testUser.availableBalance;
		Assert.assertTrue("Amount withdrawl was unsuccessful", (previousBal - currentBal) == (amount + transactionFee));
	}

	/*
	 * Test for transactWithdrawl method with insufficient available balance
	 */
	@Test
	public void withdrawlTestWithInSufficientBal() {
		testUser.availableBalance = 2;
		double previousBal = testUser.availableBalance;
		double amount = 5;
		ATM.transactWithdrawl(amount);
		double currentBal = testUser.availableBalance;
		Assert.assertTrue("Amount withdrawl failed for insufficient balance case", previousBal == currentBal);
	}

	/*
	 * Test for availableBalance in current user's bank account
	 */
	@Test
	public void availableBalanceTest() {
		Assert.assertTrue("Available balance is incorrect!!", testUser.availableBalance == ATM.availableBalance());

	}

	/*
	 * Test for recent transactions done by current user.
	 */
	@Test
	public void recentTransactionTest() {
		if (testUser.transaction.size() <= 10) {
			Assert.assertTrue("Recent transaction length is not correct",
					testUser.transaction.size() == ATM.recentTransactions().size());
		} else {
			Assert.assertTrue("Recent transaction length is not 10", ATM.recentTransactions().size() == 10);
		}
	}

	/*
	 * Test for changePassword method
	 */
	@Test
	public void changePasswordTest() {
		int pin = 9807;
		ATM.changePassword(pin);
		Assert.assertTrue("Password was not changed as expected!!", pin == ATM.userPasswordMap.get(testUser));
	}

	/*
	 * Test for exit current user
	 */
	@Test
	public void exitTest() {
		ATM.exit();
		Assert.assertTrue("Exit unsuccessful!!", ATM.currentUserBankAccountNo == -1);
	}
} 
