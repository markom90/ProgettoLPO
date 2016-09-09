package parser.ast;

import visitor.Visitor;

public class Sub extends BinaryOp {
    public Sub(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitSub(left,right);
    }
}
