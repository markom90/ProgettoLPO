package parser.ast;

/**
 * Created by marco on 11/03/16.
 */
public interface Param extends AST {
    Type getType();
    Ident getId();
}
