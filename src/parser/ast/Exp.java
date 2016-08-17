package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 07/03/16.
 */
public interface Exp extends AST {
    @Override
    <T> T accept(Visitor<T> v);
    @Override
    String toString();
}
