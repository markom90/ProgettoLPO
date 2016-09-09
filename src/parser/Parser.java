package parser;

import parser.ast.AST;

import java.io.IOException;

/**
 * Interfaccia che definisce un analizzatore sintattico
 *
 */
public interface Parser {

    /**
     * Costruisce l' Abstract Syntax Tree a partire da un programma
     * @return <code>AST</code>
     * @throws IOException
     *         se si verifica un errore di I/O
     * @throws ParserException
     *         se si verifica un errore di parsing
     */
	AST parseMain() throws IOException, ParserException;

}