
/**
 * Problem3
 * Write a java class MyJson in which you will have the following methods
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyJson {
	/**
	 * readAndGetVehicles method read from the file, create vehicle objects, add
	 * them to an arrayList
	 * 
	 * @return arrayList
	 * @throws FileNotFoundException
	 */
	private static ArrayList<Vehicle> readAndGetVehicles(File file) throws FileNotFoundException {
		ArrayList<Vehicle> vehicle = new ArrayList<>();
		FileReader reader = new FileReader(file);
		@SuppressWarnings("resource")
		Scanner fileIn = new Scanner(reader);
		fileIn.nextLine();
		while (fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			String[] words = line.split("~"); // spliting line from ~ sign
			vehicle.add(new Vehicle(words));
		}
		return vehicle;
	}

	/**
	 * getJsonString method takes the vehicle objects of the particular dealer
	 * (stated as webId in the input file) and convert its content to a json string
	 * @return json String
	 */
	public static String getJsonString(ArrayList<Vehicle> vehicles) {
		String webId;
		if (vehicles == null || vehicles.isEmpty()) // check vehicle list is not empty
			return "";

		webId = vehicles.get(0).webId;
		StringBuilder json = new StringBuilder();
		json.append("{\n" + "\"" + webId + "\"" + " : [\n");
		for (Vehicle vehicle : vehicles) { // making Json file string
			json.append("{\n");
			json.append("\"id\" : \"" + vehicle.id + "\"" + ",\n");
			json.append("\"category\" : \"" + vehicle.category + "\"" + ",\n");
			json.append("\"year\" : \"" + vehicle.year + "\"" + ",\n");
			json.append("\"make\" : \"" + vehicle.make + "\"" + ",\n");
			json.append("\"model\" : \"" + vehicle.model + "\"" + ",\n");
			json.append("\"trim\" : \"" + vehicle.trim + "\"" + ",\n");
			json.append("\"type\" : \"" + vehicle.type + "\"" + ",\n");
			json.append("\"price\" : \"" + vehicle.price + "\"" + ",\n");
			json.append("\"photo\" : \"" + vehicle.photo + "\"");
			json.append("\n},\n");
		}
		json.replace(json.length() - 2, json.length() - 1, "");
		json.append("]\n" + "}");
		return json.toString();
	}

	public static void writeToFile(String inputToWrite, String filePath) throws IOException {
		String filename = "/jsonFile";
		String fileCreated = filePath + filename; // creating jsonFile
		FileWriter writer = new FileWriter(fileCreated); // writing json string to jsonFile
		writer.write(inputToWrite);
		writer.close();
	}

	public static void main(String[] args) throws IOException {

		File file = new File(System.getProperty("user.dir") + "/src/camino");
		ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
		String s = getJsonString(vehicles);
		writeToFile(s, file.getParent());

	}
}