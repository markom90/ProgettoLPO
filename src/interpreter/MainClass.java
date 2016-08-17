package interpreter;



import parser.Parser;
import parser.ParserClass;
import parser.ParserException;
import parser.ReaderTokenizer;
import parser.ast.AST;
import visitor.evaluation.EvalVisitor;
import visitor.typechecking.TypecheckVisitor;

import java.io.*;

public class MainClass {

	public static void main(String[] args) throws ParserException, IOException {
		Reader reader = null;
		FileWriter fw = null;
		try {
			switch (args.length) {
				case 0:
					reader= new InputStreamReader(System.in);
					break;
				case 1:
					reader = new FileReader(new File(args[0]));
					break;
				case 2:
					if (!args[0].equals("-o"))
						System.err.println("INPUT ERROR");
					reader= new InputStreamReader(System.in);
					fw=new FileWriter(args[1]);
					break;
				case 3:
					reader = new FileReader(new File(args[2]));
					if (!args[0].equals("-o"))
						System.err.println("INPUT ERROR");
					fw = new FileWriter(args[1]);
					break;
			}
		}
		catch (FileNotFoundException e) {
					System.err.println("File "+ args[0] + " does not exists");
					return;
				}
		
		Parser parser = new ParserClass(new ReaderTokenizer((reader)));
		try {
			AST ast = parser.parseMain();
			ast.accept(new TypecheckVisitor());
			if(fw==null)
				System.out.println(ast.accept(new EvalVisitor()));
			else {
				fw.write(ast.accept(new EvalVisitor()).toString());
				fw.flush();
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getSimpleName() + ": " +  e.getMessage());
		}
	}
}
