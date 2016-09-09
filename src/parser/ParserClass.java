package parser;

import parser.ast.*;

import java.io.IOException;

import static parser.TokenType.*;

/**
 * Parser per il linguaggio del progetto
 */
public class ParserClass implements Parser {
    /**
     * Tokenizzatore utilizzato per analizzare l'input
     */
	private final StreamTokenizer tok;

	public ParserClass(StreamTokenizer tokenizer) {
		tok = tokenizer;
	}


    @Override
    public AST parseMain() throws IOException, ParserException {
        AST result = parseProg();
        match(EOF);
        return result;
    }

    /**
     * Effettua il parsing di un non terminale <code>Prog</code>, che rappresenta un programma
     * @return un programma
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
	private Prog parseProg() throws IOException, ParserException {
		tok.next();
        Prog result;
        if (tok.tokenType()==FUN){
            tok.next();
            FunDecs fundecs = parseFunDecs();
            consume(IN);
            result = new ProgImpl(fundecs,parseExp());
        }
        else result = new ProgImpl(parseExp());
        return result;
    }

    /**
     * Effettua il parsing di un non terminale <code>FunDecs</code>,che rappresenta una sequenza di dichiarazioni di funzione
     * @return lista di dichiarazioni di funzione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
	private FunDecs parseFunDecs() throws ParserException, IOException {
        FunDecs funDecs = new FunDecList();
        funDecs.add(parseFunDec());
        while (isTypeDec(tok.tokenType()))
            funDecs.add(parseFunDec());
        return funDecs;
    }

    /**
     * Effettua il parsing di un non terminale <code>FunDec</code>,che rappresenta una dichiarazione di funzione
     * @return dichiarazione di funzione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private FunDec parseFunDec() throws ParserException, IOException {
        Type type = parseType();
        Ident ident = parseIdent();
        consume(OPENPAR);
        Params params = parseParams();
        consume(CLOSEDPAR);
        consume(ARROW);
        Exp exp = parseExp();
        return new SimpleFunDec(type,ident,params,exp);
    }

    /**
     * Effettua il parsing di un non terminale <code>Params</code>,che rappresenta una sequenza parametri di funzione
     * @return lista di parametri di funzione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Params parseParams() throws ParserException, IOException {
        ParamsList params = new ParamsList();
        if(isTypeDec(tok.tokenType())) {
            Type type = parseType();
            Ident ident = parseIdent();
            params.add(new ParamImpl(type,ident));
            while (tok.tokenType()==COMMA){
                tok.next();
                type = parseType();
                ident = parseIdent();
                params.add(new ParamImpl(type,ident));
            }
        }
        return params;
    }
    /**
     * Effettua il parsing di un non terminale <code>Type</code>,che rappresenta i tipi di dato primitivo del linguaggio
     * @return il tipo di dato del token
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Type parseType() throws IOException, ParserException {
        Type result = null;
        switch (tok.tokenType()){
            case INT: result = PrimType.INT;
                break;
            case BOOL: result = PrimType.BOOL;
                break;
            case STRING: result = PrimType.STRING;
                break;
            default: unexpectedTokenError(tok.tokenString());
        }
        tok.next();
        while (tok.tokenType() == LIST){
            result = new ListType(result);
            tok.next();
        }
        return result;
    }
    /**
     * Effettua il parsing di un non terminale <code>Exp</code>,che rappresenta l'operazione logica <tt>OR</tt>
     * @return un'espressione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Exp parseExp() throws ParserException, IOException {
		Exp result = parseAnd();
        while (tok.tokenType()==PIPE){
            tok.next();
            result = new Or(result,parseAnd());
        }
        return result;
	}
    /**
     * Effettua il parsing di un non terminale <code>And</code>,che rappresenta l'operazione logica <tt>AND</tt>
     * @return un'espressione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Exp parseAnd() throws IOException, ParserException {
        Exp result = parseEq();
        while (tok.tokenType()==AMPERSAND){
            tok.next();
            result = new And(result,parseEq());
        }
        return result;

    }
    /**
     * Effettua il parsing di un non terminale <code>Eq</code>,che rappresenta l'operazione di uguaglianza
     * @return un'espressione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Exp parseEq() throws IOException, ParserException {
        Exp result = parseRel();
        TokenType opType;
        while (isEqOp(opType = tok.tokenType())){
            tok.next();
            result = buildEqOp(opType,result,parseRel());
        }
        return result;
    }
    /**
     * Effettua il parsing di un non terminale <code>Args</code>,che rappresenta una sequenza di argomenti di funzione
     * @return una lista di argomenti di funzione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Args parseArgs() throws IOException, ParserException {
        Args args = new ArgsList();
        if(tok.tokenType()==CLOSEDPAR){
            tok.next();
            return args;
        }
        args.add(parseExp());
        while(tok.tokenType()==COMMA) {
            tok.next();
            args.add(parseExp());
        }
        consume(CLOSEDPAR);
        return args;
    }
    /**
     * Effettua il parsing di un elemento lessicale <code>BoolLit</code>,che rappresenta un literal booleano
     * @return un literal booleano
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Exp parseBoolLit() throws IOException, TokenizerException {
        Exp result = new BoolLit(tok.tokenType() == TRUE);
        tok.next();
        return result;
    }
    /**
     * Effettua il parsing di un non terminale <code>Args</code>,che rappresenta una sequenza di argomenti di funzione
     * @return una lista di argomenti di funzione
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se trova un token inaspettato
     *
     */
    private Exp parseRel() throws ParserException, IOException {
		Exp result = parseListConcat();
		TokenType opType;
		while (isRelOp(opType = tok.tokenType())) {
			tok.next();
			result = buildRelOp(opType, result, parseListConcat());
		}
		return result;
	}

    private Exp parseListConcat() throws IOException, ParserException {
        Exp result = parseListConstructor();
        while (tok.tokenType() == AT) {
            tok.next();
            result = new ListConcat(result, parseListConcat());
        }
        return result;
    }

    private Exp parseListConstructor() throws IOException, ParserException {
        Exp result = parseAdd();
        if (tok.tokenType() == COLON) {
            tok.next();
            result = new ListConstructor(result, parseListConstructor());
        }
        return result;
    }



	private Exp parseAdd() throws ParserException, IOException {
		Exp result = parseMul();
		TokenType opType;
		while (isAddOp(opType = tok.tokenType())) {
			tok.next();
			result = buildAddOp(opType, result, parseMul());
		}
		return result;
	}



	private Exp parseMul() throws ParserException, IOException {
		Exp result = parseAtom();
		TokenType opType;
		while (isMulOp(opType = tok.tokenType())) {
			tok.next();
			result = buildMulOp(opType, result, parseAtom());
		}
		return result;
	}

	private Exp parseAtom() throws ParserException, IOException {
		final Exp result;
		switch (tok.tokenType()) {
		default:
			unexpectedTokenError(tok.tokenString());
            case TILDE:
                tok.next();
                return new Not(parseAtom());
            case HASH:
                tok.next();
                return new ListLength(parseAtom());
            case EXTRACTTAIL:
                tok.next();
                return new ExtractTail(parseAtom());
            case EXTRACTHEAD:
                tok.next();
                return new ExtractHead(parseAtom());
            case MINUS:
                tok.next();
                return new Minus(parseAtom());
            case TRUE:
            case FALSE:
                return parseBoolLit();
            case INTLIT:
                return parseNum();
            case STRINGLIT:
                return parseStringLit();
            case IDENT:
                SimpleIdent simpleIdent = parseIdent();
                if(tok.tokenType() == OPENPAR){
                    tok.next();
                    result = new FunctionCall(simpleIdent,parseArgs());
                    return result;
                }
                return simpleIdent;
            case OPENPAR:
                tok.next();
                result = parseExp();
                consume(CLOSEDPAR);
                return result;
            case LET:
                tok.next();
                Decs decs = parseDecs();
                consume(IN);
                result = new LetIn(decs, parseExp());
                consume(SEMICOLON);
                return result;
            case OPENSQUAREPAR:
                tok.next();
                result = new EmptyList(parseType());
                consume(CLOSEDSQUAREPAR);
                return result;
            case IF:
                tok.next();
                Exp ifExp = parseExp();
                consume(THEN);
                Exp thenExp = parseExp();
                consume(ELSE);
                result = new IfExp(ifExp,thenExp,parseExp());
                consume(SEMICOLON);
                return result;
        }

	}

	private Decs parseDecs() throws ParserException, IOException {
		Decs decs = new DecList();
		decs.add(parseDec());
		while (tok.tokenType() == IDENT)
			decs.add(parseDec());
		return decs;
	}

	private Dec parseDec() throws ParserException, IOException {
		SimpleIdent id = parseIdent();
		consume(ASSIGN);
        return new SimpleDec(id, parseExp());
	}

	private IntLiteral parseNum() throws TokenizerException, IOException {
        IntLiteral result = new IntLiteral(tok.intValue());
        tok.next();
        return result;
	}

	private StringLit parseStringLit() throws TokenizerException, IOException {
		final StringLit result = new StringLit(tok.stringValue());
		tok.next();
		return result;
	}

	private SimpleIdent parseIdent() throws TokenizerException, IOException {
		final SimpleIdent result = new SimpleIdent(tok.tokenString());
		tok.next();
		return result;
	}

    private boolean isTypeDec(TokenType tokenType) {
        return tokenType == INT || tok.tokenType() == BOOL || tok.tokenType() == STRING;
    }


    private static boolean isAddOp(TokenType opType) {
        return opType == PLUS || opType == MINUS || opType == CONCAT;
    }

    private boolean isEqOp(TokenType tokenType) {
        return tokenType == EQ || tokenType == NEQ;
    }

    private static boolean isMulOp(TokenType opType) {
        return opType == TIMES || opType == DIV || opType == PERCENT;
    }


    private static boolean isRelOp(TokenType opType) {
        return opType == LTH || opType == LEQ || opType == GTH || opType == GEQ;
    }

    private static Exp buildRelOp(TokenType opType, Exp left, Exp right) {
        switch (opType) {
            default:
                throw new AssertionError();
            case LTH:
                return new Lth(left, right);
            case LEQ:
                return new Leq(left, right);
            case GTH:
                return new Gth(left, right);
            case GEQ:
                return new Geq(left, right);
        }

    }

    private static Exp buildAddOp(TokenType opType, Exp left, Exp right) {
        switch (opType) {
            default:
                throw new AssertionError();
            case PLUS:
                return new Add(left, right);
            case MINUS:
                return new Sub(left, right);
            case CONCAT:
                return new Concat(left, right);
        }

    }

    private Exp buildEqOp(TokenType tokenType, Exp left, Exp right) {
        switch (tokenType){
            default:
                throw new AssertionError();
            case EQ:
                return new Eq(left,right);
            case NEQ:
                return new Neq(left,right);
        }
    }

    private static Exp buildMulOp(TokenType opType, Exp left, Exp right) {
        switch (opType) {
            default:
                throw new AssertionError();
            case TIMES:
                return new Mul(left, right);
            case DIV:
                return new Div(left, right);
            case PERCENT:
                return new Mod(left, right);
        }

    }

	private void unexpectedTokenError(String token) throws ParserException {
		throw new ParserException("Unexpected token \'" + token + '\'');
	}

	private void match(TokenType expected) throws ParserException {
		final TokenType found = tok.tokenType();
		if (found != expected)
			throw new ParserException("Expecting \'" + expected + "\', found \'" + found + '\'');
	}

	private void consume(TokenType expected) throws ParserException, IOException {
		match(expected);
		tok.next();
	}
}
