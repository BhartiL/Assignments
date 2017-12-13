/**
 * Problem: ExtraCredit
 * Create the original text file from the json text file you have created in question 3
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtraCreditProblem {
	public static void makeOriginalFile(String original, String filepath) throws IOException {
		
		String filename = filepath + "/originalFile"; //appending File name with directory path
		FileWriter writer = new FileWriter(filename);
		writer.write(original);// writing createdOrginal
		writer.close();

	}

	public static String createOriginalFileString(String filepath) throws IOException {
		StringBuilder orig = new StringBuilder();
		orig.append("id~webId~category~year~make~model~trim~type~price~photo\n");

		String text = new String(Files.readAllBytes(Paths.get(filepath + "/jsonFile")), StandardCharsets.UTF_8);
		text = text.substring(0, text.length() - 4);
		String[] array = text.split("\\[");
		String webId = array[0].substring(array[0].indexOf('"') + 1, array[0].lastIndexOf('"'));

		String[] elements = array[1].split("\\}");

		for (String jsonElement : elements) { 
			jsonElement.trim();
			jsonElement = jsonElement.replaceAll("\\{", "");
			jsonElement = jsonElement.replaceAll(" : ", "");
			jsonElement = jsonElement.replaceAll("  ", "");
			jsonElement = jsonElement.replaceAll("\\n", "");
			jsonElement = jsonElement.replaceAll(",", "");
			jsonElement = jsonElement.replaceAll("\"\"", "~");
			jsonElement = jsonElement.replaceAll("\"", "");

			jsonElement = jsonElement.replaceAll("id~", "");
			jsonElement = jsonElement.replaceAll("~category~", "~");
			jsonElement = jsonElement.replaceAll("~year~", "~");
			jsonElement = jsonElement.replaceAll("~make~", "~");
			jsonElement = jsonElement.replaceAll("~model~", "~");
			jsonElement = jsonElement.replaceAll("~trim~", "~");
			jsonElement = jsonElement.replaceAll("~type~", "~");
			jsonElement = jsonElement.replaceAll("~price~", "~");
			jsonElement = jsonElement.replaceAll("~photo", "");
			jsonElement = jsonElement.replaceAll("~NEW~", "~new~");
			jsonElement = jsonElement.replaceAll("~USED~", "~used~");
			jsonElement = jsonElement.replaceAll("~CERTIFIED~", "~certified~");
			jsonElement = jsonElement.replaceFirst("~", "~" + webId + "~");

			orig.append(jsonElement + "\n");

		}
		return orig.toString();
	}

	public static void main(String[] args) throws IOException {

		File file = new File(System.getProperty("user.dir") + "/src/camino");
		createOriginalFileString(file.getParent());
		String originalstring = createOriginalFileString(file.getParent());
		makeOriginalFile(originalstring, file.getParent());
	}
}
