package parser.ast;

public interface FunDecs extends AST {
    @Override
    String toString();
    void add(FunDec funDec);
}
