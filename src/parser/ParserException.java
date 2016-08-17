package parser;

public class ParserException extends Exception {

	private static final long serialVersionUID = 4866896086728171330L;

	public ParserException() {
	}

	public ParserException(String detailMessage) {
		super(detailMessage);
	}

	public ParserException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public ParserException(Throwable throwable) {
		super(throwable);
	}
}
