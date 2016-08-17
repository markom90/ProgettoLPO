package parser;

import java.io.IOException;

public interface StreamTokenizer {

	TokenType next() throws IOException, TokenizerException;

	String tokenString();

	int intValue();

	String stringValue();

	TokenType tokenType();

	boolean hasNext();

}