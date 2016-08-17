package parser;



import parser.ast.AST;

import java.io.InputStreamReader;

public class ParserTest {
	public static void main(String[] args) {
		Parser parser = new ParserClass(new ReaderTokenizer(new InputStreamReader(System.in)));
		try {
			AST ast = parser.parseMain();
			System.out.println(ast);
		} catch (Exception e) {
			System.err.println("Error: " + e.toString());
		}
	}
}
