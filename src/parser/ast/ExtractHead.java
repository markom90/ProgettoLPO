package parser.ast;

import visitor.Visitor;

public class ExtractHead extends UnaryOp{
    public ExtractHead(Exp exp) {
        super(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitExtractHead(exp);
    }
}
