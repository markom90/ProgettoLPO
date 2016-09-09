package parser.ast;

import visitor.Visitor;

public class Neq extends BinaryOp {
    public Neq(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitNeq(left,right);
    }
}
