import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem1
 * Write a program that asks a user for a file name and prints the number of
 * characters, words, and lines in that file.Use the following class as your
 * main class:
 */
public class FileCounter {
	private int lineCount = 0; // line counter
	private int wordCount = 0; // word counter
	private int charCount = 0; // character counter
	
	 public FileCounter(){
	    
	   }
	public void read(Scanner in) throws IOException {
		while (in.hasNextLine()) {
			String line = in.nextLine(); // Reading next line into currentLine
			lineCount++; //counting total lines
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) != ' ' && line.charAt(i) != '\n')
					charCount++; //counting total characters
			}
			wordCount += new StringTokenizer(line, " ,").countTokens(); //counting total words 

		}

	}

	public int getWordCount() {
		return wordCount;
	}

	public int getCharacterCount() {
		return charCount;
	}

	public int getLineCount() {
		return lineCount;
	}

}