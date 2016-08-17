package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 10/03/16.
 */
public class Div extends BinaryOp {
    public Div(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitDiv(left,right);
    }
}
