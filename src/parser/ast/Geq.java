package parser.ast;

import visitor.Visitor;

public class Geq extends BinaryOp {
    public Geq(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitGeq(left,right);
    }
}
