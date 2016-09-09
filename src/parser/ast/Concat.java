package parser.ast;

import visitor.Visitor;

public class Concat extends BinaryOp {
    public Concat(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitConcat(left,right);
    }
}
