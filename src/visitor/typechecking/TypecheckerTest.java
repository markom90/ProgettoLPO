package visitor.typechecking;

import parser.Parser;
import parser.ParserClass;
import parser.ReaderTokenizer;
import parser.ast.AST;

import java.io.InputStreamReader;

public class TypecheckerTest {
	public static void main(String[] args) {
		Parser parser = new ParserClass(new ReaderTokenizer(new InputStreamReader(System.in)));
		try {
			AST ast = parser.parseMain();
			System.out.println(ast);
			System.out.println(ast.accept(new TypecheckVisitor()));
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
