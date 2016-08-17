package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 10/03/16.
 */
public class Sub extends BinaryOp {
    public Sub(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitSub(left,right);
    }
}
