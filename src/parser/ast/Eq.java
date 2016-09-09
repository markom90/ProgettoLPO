package parser.ast;

import visitor.Visitor;

public class Eq extends BinaryOp {
    public Eq(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitEq(left,right);
    }
}
