package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 11/03/16.
 */
public class ExtractHead extends UnaryOp{
    public ExtractHead(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitExtractHead(exp);
    }
}
