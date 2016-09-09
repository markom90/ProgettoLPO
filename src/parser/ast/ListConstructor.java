package parser.ast;

import visitor.Visitor;

public class ListConstructor extends BinaryOp {
    public ListConstructor(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitListConstructor(left,right);
    }
}
