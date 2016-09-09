package parser.ast;

import visitor.Visitor;

public class Lth extends BinaryOp {
    public Lth(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitLth(left,right);
    }
}
