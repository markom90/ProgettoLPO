package parser.ast;

import visitor.Visitor;

public class Leq extends BinaryOp {
    public Leq(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitLeq(left,right);
    }
}
