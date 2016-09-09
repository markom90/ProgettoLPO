package parser.ast;

import visitor.Visitor;

public class Or extends BinaryOp {
    public Or(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitOr(left,right);
    }
}
