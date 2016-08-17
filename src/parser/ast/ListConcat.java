package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 08/08/16.
 */
public class ListConcat extends BinaryOp {
    public ListConcat(Exp left, Exp right) {
        super(left, right);

    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitListConcat(left,right);
    }
}
