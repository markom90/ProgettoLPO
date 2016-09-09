package parser.ast;

public interface Param extends AST {
    Type getType();
    Ident getId();
}
