package parser.ast;

import visitor.Visitor;

public class IntLiteral extends Literal<Integer>{
    public IntLiteral(int value) {
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitIntLiteral(value);
    }
}
