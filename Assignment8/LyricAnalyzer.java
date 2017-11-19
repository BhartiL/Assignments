/**
 * Problem-2
 * Create a java class LyricAnalyzer with the following methods.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class LyricAnalyzer {
	
	private HashMap<String, ArrayList<Integer>> map = new HashMap<>();
	private int wordPositionCount = 0;

	public void read(File file) { // This method will read the lyrics from file and adds to the map
		try {
			FileReader reader = new FileReader(file);
			@SuppressWarnings("resource")
			Scanner fileIn = new Scanner(reader);
			while (fileIn.hasNextLine()) {
				String currentLine = fileIn.nextLine(); // Reading next line into currentLine
				String[] words = currentLine.split(" ");
				for (int i = 0; i < words.length; i++) {
					String word = words[i];
					wordPositionCount++;
					if (i == words.length - 1)
						add(word, -1 * wordPositionCount); // calling add method by passing word and wordposition
					else
						add(word, wordPositionCount);

				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	/**
	 * add method will determine if the given lyric word is in the map. If the word
	 * is not in the map, then a mapping is added that maps that word to a list
	 * containing the position of the word in the lyric. If the word is already in
	 * the map, then its word position is added to the list of word positions for
	 * this word.
	 * 
	 */
	private void add(String lyricWord, int wordPosition){
		lyricWord = lyricWord.toUpperCase();
		if (!map.containsKey(lyricWord)) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(wordPosition);
			map.put(lyricWord, list); // adding lyric word and list of word positions into map
		} else {
			ArrayList<Integer> list = map.get(lyricWord);
			list.add(wordPosition);
			map.put(lyricWord, list);// adding lyric word and list of word positions into map
		}

	}

	public void displayWords() {//This method displays words with the word positions of the word, one word per line, in alphabetical order
		System.out.println("========================");
		Object[] sortedWords = map.keySet().toArray();
		Arrays.sort(sortedWords);
		for (Object word : sortedWords) {
			ArrayList<Integer> occurenceList = map.get(word);
			System.out.print(word + ": ");
			for (int i = 0; i < occurenceList.size(); i++) {
				int occ = occurenceList.get(i);
				System.out.print(occ);
				if (i != occurenceList.size() - 1)
					System.out.print(",");
			}
			System.out.println("");
		}
	}

	public void writeLyrics(File file) throws IOException {//This method will write the lyrics for the song (in uppercase) stored in the map to the file.
		String[] arr = new String[wordPositionCount + 1];
		for (int i = 1; i < wordPositionCount + 1; i++) {
			arr[i] = "";
		}
		Set<String> Words = map.keySet();
		for (String word : Words) {
			ArrayList<Integer> occurenceList = map.get(word);
			for (int j = 0; j < occurenceList.size(); j++) {
				int k = occurenceList.get(j);
				if (k > 0)
					arr[k] = word;
				else
					arr[Math.abs(k)] = word + "\n";
			}
		}

		FileWriter outputWriter = new FileWriter(file);
		for (int m = 1; m < arr.length; m++) {
			try {
				if ((m == arr.length - 1) && (arr[m].contains("\n"))) {
					arr[m] = arr[m].replace("\n", "");
				}
				outputWriter.write(arr[m]);
				if (!(arr[m].contains("\n") || (m == arr.length - 1)))
					outputWriter.write(" ");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		outputWriter.close();
	}

	public int count() { //This method will return the total number of unique words in the lyric by analyzing the map.
		return map.keySet().size();

	}

	public String mostFrequentWord() { //This method will return the word that occurs most frequently in the lyric
		int temp = 0;
		int freqcount = 0;
		String freqword = "";
		Set<String> Words = map.keySet();
		for (String word : Words) {
			ArrayList<Integer> occurenceList = map.get(word);
			for (int j = 0; j < occurenceList.size(); j++) {
				freqcount++;
				if (temp < freqcount) {
					temp = freqcount; //storing freqcount value in temp counter
					freqword = word;
				}
			}
			freqcount = 0;
		}
		return freqword;

	}

	public static void main(String[] args) throws IOException {
		LyricAnalyzer ls = new LyricAnalyzer();
		String name = "C:\\Users\\student\\ApplicationProgramming\\Assignment8\\src\\Question2_test1";
		File file = new File(name);
		ls.read(file);
		File outputFile = new File("C:\\Users\\student\\ApplicationProgramming\\Assignment8\\src\\outputFile");
		ls.writeLyrics(outputFile);
		System.out.println("Total number of unique words : " + ls.count());
		System.out.println("The word that occurs most frequently is: " + ls.mostFrequentWord());
		ls.displayWords();
	}

}