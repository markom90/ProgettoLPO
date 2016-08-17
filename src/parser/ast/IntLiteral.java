package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 06/08/16.
 */
public class IntLiteral extends Literal<Integer>{
    public IntLiteral(int value) {
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitIntLiteral(value);
    }
}
