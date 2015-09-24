package managers.errors;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import managers.EngineObject;

/**
 * Error Manager uses the singleton class to manage all errors from a single point.
 * 
 * @author Sam Costigan
 */
public class ErrorManager extends EngineObject {
	
	private static final String logFilename = "logs/errors.txt";
	private static final ErrorManager manager = new ErrorManager();
	
	private static int errorCount = 0;
	
	private PrintWriter logFile;
	private String logBuffer;
	
	protected ErrorManager() {}
	
	private void flush() {
		logFile.println(logBuffer);
		logFile.flush();
		logBuffer = "";
		errorCount++;
	}
	
	private String getTime() {
		Calendar current = Calendar.getInstance();
		
		return current.getTime().toString();
	}
	
	public static ErrorManager getErrorManager() {
		return manager;
	}
	
	public void create() {
		create(logFilename);
	}
	
	public void create(String filename) {
		try {
			logFile = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void logException(ErrorException exception) {
		logBuffer = getTime() + ": " + exception.getErrorText();
		flush();
	}
	
	public void close() {
		logFile.close();
	}

	public int getCount() {
		return errorCount;
	}
	
}
