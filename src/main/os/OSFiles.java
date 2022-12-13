package main.os;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OSFiles {

	public static boolean doesFileExist( String filePath ) {
		try {
			return !(new File(filePath).exists());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	public static File getFile( String filePath, Boolean createIfMissing ) {
		// Check if the file already exists
		Boolean fileExists = doesFileExist(filePath);
		
		// IF the file does not exist
		if (!fileExists) {
			
			// If createFile is false, return.
			if (!createIfMissing) {
				return null;
			}
			
			// Create the file
			try {
				File myObj = new File(filePath);
				myObj.createNewFile();
				return myObj;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static FileReader getFileReader( String filePath, Boolean createIfMissing ) {
		File file = getFile(filePath, createIfMissing);
		if (file == null) {
			return null;
		}
		
		// Load the file into a FileReader
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		
		// Return the FileReader (or null)
		return fileReader;
	}
	
	public static String readFileData(FileReader reader) {
		StringBuilder data = new StringBuilder();
		while (true) {
			int charCode;
			try {
				charCode = reader.read();
			} catch (IOException e) {
				return null;
			}
			if (charCode == -1) {
				break;
			}
			data.append(charCode);
		}
		return data.toString();
	}
	
}
