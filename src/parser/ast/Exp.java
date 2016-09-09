package parser.ast;

import visitor.Visitor;

public interface Exp extends AST {
    @Override
    <T> T accept(Visitor<T> v);
    @Override
    String toString();
}
