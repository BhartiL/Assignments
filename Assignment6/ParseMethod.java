import java.io.RandomAccessFile;
import java.io.File;
import java.io.IOException;
/**
 * Problem-2
 * Modify the following parse() method so that it will compile: (Score 1) 
 * public static void parse(File file) 
 * { RandomAccessFile input = null; String line =null;
 * try { input = new RandomAccessFile(file, "r"); 
 * while ((line =input.readLine()) != null) 
 * { System.out.println(line); } 
 * return; } 
 * finally {
 * if (input != null) { input.close(); } } }
 * 
 */
public class ParseMethod {
	public static void parse(File file) {
		StringBuffer sb = new StringBuffer();
		RandomAccessFile input = null;
		String line = null;
		try {
			input = new RandomAccessFile(file, "r");
			while ((line = input.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ParseMethod.parse(new File("/Users/stilok2/summary.txt"));
	}
}
