package parser;

public class TokenizerException extends ParserException {

	private static final long serialVersionUID = -5461137298216810702L;

	public TokenizerException() {
	}

	public TokenizerException(String message) {
		super(message);
	}

	public TokenizerException(Throwable cause) {
		super(cause);
	}

	public TokenizerException(String message, Throwable cause) {
		super(message, cause);
	}

}
