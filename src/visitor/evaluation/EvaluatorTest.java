package visitor.evaluation;


import parser.Parser;
import parser.ParserClass;
import parser.ParserException;
import parser.ReaderTokenizer;
import parser.ast.AST;
import visitor.typechecking.TypecheckVisitor;
import visitor.typechecking.TypecheckerException;

import java.io.InputStreamReader;

public class EvaluatorTest {
	public static void main(String[] args) {
		Parser parser = new ParserClass(new ReaderTokenizer(new InputStreamReader(System.in)));
		try {
			AST exp = parser.parseMain();
			System.out.println(exp);
			System.out.println(exp.accept(new TypecheckVisitor()));
			System.out.println(exp.accept(new EvalVisitor()));
		} catch (ParserException e) {
			System.err.println("Syntax error: " + e.getMessage());
		} catch (TypecheckerException e) {
			System.err.println("Type error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
