package parser.ast;

import visitor.Visitor;

public class Gth extends BinaryOp {
    public Gth(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitGth(left,right);
    }
}
