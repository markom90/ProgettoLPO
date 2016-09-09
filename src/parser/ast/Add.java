package parser.ast;

import visitor.Visitor;

public class Add extends BinaryOp {

    public Add(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitAdd(left,right);
    }
}
