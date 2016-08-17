package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 08/03/16.
 */
public class BoolLit extends Literal<Boolean> {
    public BoolLit(boolean value){
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitBoolLit(value);
    }
}
