package parser.ast;

import visitor.Visitor;

public class IfExp implements Exp {
    private Exp ifExp,thenExp,elseExp;
    public IfExp(Exp ifExp,Exp thenExp, Exp elseExp){
        this.ifExp=ifExp;
        this.thenExp=thenExp;
        this.elseExp=elseExp;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitIfThenElse(ifExp,thenExp,elseExp);
    }
}
