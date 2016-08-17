package parser.ast;

/**
 * Created by marco on 10/03/16.
 */
public interface Decs extends AST{
    void add(Dec parseDec);
    @Override
    String toString();
}
