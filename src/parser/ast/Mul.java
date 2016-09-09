package parser.ast;

import visitor.Visitor;

public class Mul extends BinaryOp {
    public Mul(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitMul(left,right);
    }
}
