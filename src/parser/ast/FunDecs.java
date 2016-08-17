package parser.ast;

/**
 * Created by marco on 08/03/16.
 */
public interface FunDecs extends AST {
    @Override
    String toString();
    void add(FunDec funDec);
}
