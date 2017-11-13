/**
 * Problem-2 Write a program called ReverseHello.java that creates a thread
 * (let's call it Thread 1). Thread 1 creates another thread (Thread 2); Thread
 * 2 creates Thread 3; and so on, up to Thread 50. Each thread should print
 * Hello from Thread num!
 */

public class ReverseHello extends Thread {
	int count;

	public ReverseHello(int count) { // ReverseHello constructor
		this.count = count;
	}

	public void run() {
		if (count < 50) {
			createThread(count + 1);//creating a new child thread with count + 1.
		}
		System.out.println("Hello from Thread num! " + count); //printing greetings/message with its count number.
	}

	public void createThread(int counter) {
		ReverseHello t = new ReverseHello(counter);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) {
		ReverseHello t = new ReverseHello(1);// Creating first thread with count 1
		t.start();
	}
}
