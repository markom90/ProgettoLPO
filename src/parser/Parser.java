package parser;

import parser.ast.AST;

import java.io.IOException;

public interface Parser {

	AST parseMain() throws IOException, ParserException;

}