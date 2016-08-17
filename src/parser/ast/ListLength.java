package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 11/03/16.
 */
public class ListLength extends UnaryOp {
    public ListLength(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitListLength(exp);
    }
}
