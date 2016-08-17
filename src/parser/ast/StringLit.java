package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 08/03/16.
 */
public class StringLit extends Literal<String> {
    public StringLit(String value){
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitStringLiteral(value);
    }
}
