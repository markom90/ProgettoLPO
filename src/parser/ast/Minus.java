package parser.ast;

import visitor.Visitor;

public class Minus extends UnaryOp {
    public Minus(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitSign(exp);
    }
}
