package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 11/03/16.
 */
public class ExtractTail extends UnaryOp {
    public ExtractTail(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitExtractTail(exp);
    }
}
