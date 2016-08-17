package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * Created by marco on 08/03/16.
 */
public class SimpleDec implements Dec {
    private Ident ident;
    private Exp exp;
    public SimpleDec(Ident ident, Exp exp){
        this.ident=requireNonNull(ident);
        this.exp=requireNonNull(exp);
    }
    @Override
    public String toString() {
        return AST.toString(this, ident, exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitSimpleDec(ident,exp);
    }
}
