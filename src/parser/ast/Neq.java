package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 05/08/16.
 */
public class Neq extends BinaryOp {
    public Neq(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitNeq(left,right);
    }
}
