package parser.ast;

import visitor.Visitor;

public class StringLit extends Literal<String> {
    public StringLit(String value){
        super(value);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitStringLiteral(value);
    }
}
