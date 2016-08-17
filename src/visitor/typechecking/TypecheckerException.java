package visitor.typechecking;

public class TypecheckerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6057839020485521026L;

	public TypecheckerException() {
	}

	public TypecheckerException(String message) {
		super(message);
	}

	public TypecheckerException(Throwable cause) {
		super(cause);
	}

	public TypecheckerException(String message, Throwable cause) {
		super(message, cause);
	}

}
