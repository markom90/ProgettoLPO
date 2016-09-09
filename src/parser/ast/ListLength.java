package parser.ast;

import visitor.Visitor;

public class ListLength extends UnaryOp {
    public ListLength(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitListLength(exp);
    }
}
