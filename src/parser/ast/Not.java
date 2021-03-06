package parser.ast;

import visitor.Visitor;

public class Not extends UnaryOp {
    public Not(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitNot(exp);
    }
}
