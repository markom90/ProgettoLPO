package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 10/03/16.
 */
public class Lth extends BinaryOp {
    public Lth(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitLth(left,right);
    }
}
