package parser.ast;

import visitor.Visitor;

public class And extends BinaryOp {
    public And(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitAnd(left,right);
    }
}
