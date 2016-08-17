package environments;

public class EnvironmentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 121754068242606305L;

	public EnvironmentException() {
	}

	public EnvironmentException(String message) {
		super(message);
	}

	public EnvironmentException(Throwable cause) {
		super(cause);
	}

	public EnvironmentException(String message, Throwable cause) {
		super(message, cause);
	}

}
