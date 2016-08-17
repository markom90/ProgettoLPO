package parser;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static parser.TokenType.*;

public class ReaderTokenizer implements StreamTokenizer {
                                                 //Ident        |     IntLit    |           StringLit              |    skip   |                            Symbols
    private static final String regEx = "([a-zA-Z][a-zA-Z0-9_]*)|(0|[1-9][0-9]*)|(\"(?:[^\"\\\\]|\\\\[\"\\\\n])*\")|(\\s+|//.*)|->|\\+|-|\\*|/=|/|<=|:>|<:|<|>=|>|==|=|\\^|\\(|\\)|;|:|,|\\[|\\]|%|&|\\||@|~|#";
	private static final Map<String, TokenType> keywords = new HashMap<>();
	// bool,else,false,fun,if,in,int,let,list,string,then,true
	static {
		keywords.put("bool", BOOL);
		keywords.put("else", ELSE);
        keywords.put("false", FALSE);
        keywords.put("fun", FUN);
        keywords.put("if", IF);
        keywords.put("in", IN);
        keywords.put("int", INT);
        keywords.put("let", LET);
        keywords.put("list", LIST);
        keywords.put("string", STRING);
        keywords.put("then", THEN);
        keywords.put("true", TRUE);
    }

	private static final Map<String, TokenType> symbols = new HashMap<>();

	static {
		symbols.put("+", PLUS);
		symbols.put("-", MINUS);
		symbols.put("*", TIMES);
		symbols.put("/", DIV);
		symbols.put("<=", LEQ);
		symbols.put("<", LTH);
		symbols.put(">=", GEQ);
		symbols.put(">", GTH);
		symbols.put("==", EQ);
		symbols.put(":", COLON);
		symbols.put("=", ASSIGN);
		symbols.put("(", OPENPAR);
		symbols.put(")", CLOSEDPAR);
		symbols.put(";", SEMICOLON);
        symbols.put("->", ARROW);
        symbols.put(",", COMMA);
        symbols.put("[", OPENSQUAREPAR);
        symbols.put("]", CLOSEDSQUAREPAR);
        symbols.put("%", PERCENT);
        symbols.put("/=", NEQ);
        symbols.put("&", AMPERSAND);
        symbols.put("|", PIPE);
        symbols.put("@", AT);
        symbols.put("^", CONCAT);
        symbols.put("~", TILDE);
        symbols.put("#", HASH);
        symbols.put("<:", EXTRACTHEAD);
        symbols.put(":>", EXTRACTTAIL);
	}

	private TokenType tokenType;
	private String tokenString;
	private int intValue;
	private String stringValue;

	private final StreamMatcher sm;

	public ReaderTokenizer(Reader reader) {
		sm = new ReaderMatcher(regEx, reader);
	}

	private void getType() {
		tokenString = sm.group();
		if (sm.group(IDENT.ordinal()) != null) { // IDENT or a keyword
			tokenType = keywords.get(tokenString);
			if (tokenType == null)
				tokenType = IDENT;
			return;
		}
		if (sm.group(INTLIT.ordinal()) != null) { // INT
			tokenType = INTLIT;
			intValue = Integer.parseInt(tokenString);
			return;
		}
		if (sm.group(STRINGLIT.ordinal()) != null) { // STRING
			tokenType = STRINGLIT;
			stringValue = tokenString.substring(1, tokenString.length() - 1).replace("\\\"", "\"").replace("\\\\",
					"\\");
			return;
		}
		if (sm.group(SKIP.ordinal()) != null) { // SKIP
			tokenType = SKIP;
			return;
		}
		tokenType = symbols.get(tokenString); // a symbol
		if (tokenType == null)
			throw new AssertionError();
	}

	@Override
	public TokenType next() throws IOException, TokenizerException {
		do {
			tokenType = null;
			tokenString = "";
			if (sm.nextMatches())
				getType();
			else if (!sm.hasNext())
				return tokenType = EOF;
			else
				throw new TokenizerException("Unknown token " + sm.getSkipped());
		} while (tokenType == SKIP);
		return tokenType;
	}

	private void checkValidToken() {
		if (tokenType == null)
			throw new IllegalStateException();
	}

	private void checkValidToken(TokenType ttype) {
		if (tokenType != ttype)
			throw new IllegalStateException();
	}

	@Override
	public String tokenString() {
		checkValidToken();
		return tokenString;
	}

	@Override
	public int intValue() {
		checkValidToken(INTLIT);
		return intValue;
	}

	@Override
	public String stringValue() {
		checkValidToken(STRINGLIT);
		return stringValue;
	}

	@Override
	public TokenType tokenType() {
		checkValidToken();
		return tokenType;
	}

	@Override
	public boolean hasNext() {
		return sm.hasNext();
	}
}
