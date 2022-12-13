package main.os;

public class FilePathHandler {

	public static String POSITION_JSON_PATH = FilePathHandler.GetProjectPath();
	
	// Run this method after creating a temporary file with 'new File(".")'.
	public static String GetProjectPath() {
		return System.getProperty("user.dir");
	}
	
}
