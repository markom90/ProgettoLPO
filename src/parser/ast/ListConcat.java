package parser.ast;

import visitor.Visitor;

public class ListConcat extends BinaryOp {
    public ListConcat(Exp left, Exp right) {
        super(left, right);

    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitListConcat(left,right);
    }
}
