package main.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OSJSON {

	public static boolean saveJSON( String filepath, String JSONString ) {
		return saveJSON( filepath, JSONString, true);
	}
	
	public static boolean saveJSON( String filepath, String JSONString, Boolean createIfMissing ) {
		File file = OSFiles.getFile(filepath, createIfMissing);
		if (file == null) {
			return false;
		}
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(JSONString);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static JSONObject getJSON( String filepath ) {
		return getJSON( filepath, false );
	}
	
	public static JSONObject getJSON( String filepath, Boolean createIfMissing ) {
		File file = OSFiles.getFile(filepath, createIfMissing);
		if (file == null) {
			System.out.println("Could not find the file specified; " + filepath);
			return null;
		}
		
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if (fileReader == null) {
			System.out.println("Could not access the file specified; " + filepath);
			return null;
		}
		
		JSONObject jsonDATA = null;
		
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(fileReader);
			jsonDATA = (JSONObject) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Close File Reader
		try {
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonDATA;
		
	}
	
}
