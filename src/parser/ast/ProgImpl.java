package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

public class ProgImpl implements Prog {
    private FunDecs fundecs;
    private Exp exp;

    public ProgImpl(FunDecs fundecs, Exp exp){
        this.fundecs=requireNonNull(fundecs);
        this.exp=requireNonNull(exp);
    }

    public ProgImpl(Exp exp) {
        this.exp = requireNonNull(exp);
        fundecs = new FunDecList();
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitProg(fundecs,exp);
    }

    @Override
    public String toString() {
        return AST.toString(this, fundecs, exp);
    }
}
