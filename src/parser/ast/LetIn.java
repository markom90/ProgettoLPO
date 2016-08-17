package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * Created by marco on 10/03/16.
 */
public class LetIn implements Exp {
    private Decs decs;
    private Exp exp;
    public LetIn(Decs decs, Exp exp){
        this.decs=requireNonNull(decs);
        this.exp=requireNonNull(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitLetIn(decs,exp);
    }
}
