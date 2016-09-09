package parser.ast;

public interface Decs extends AST{
    void add(Dec parseDec);
    @Override
    String toString();
}
