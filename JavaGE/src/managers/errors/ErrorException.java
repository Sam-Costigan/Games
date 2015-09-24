package managers.errors;

/**
 * This manages a general format for Errors which is expected, so that all Errors
 * contain the same information and can be handled in the same way.
 * 
 * @author Sam Costigan
 */
public class ErrorException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String errorText;
	
	public ErrorException(int number, String description) throws ErrorException {
		 new ErrorException(number, description, getStackTrace()[0].toString());
	}
	
	public ErrorException(int number, String description, String trace) throws ErrorException {
		errorText = number + ": " + "\n"
				+ "Description: " + description + "\n"
				+ "Trace: " + trace + "\n";
		
		throw this;
	}
	
	public String getErrorText() {
		return errorText;
	}

}
