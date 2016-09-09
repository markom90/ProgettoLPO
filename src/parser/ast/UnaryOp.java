package parser.ast;

public abstract class UnaryOp implements Exp {
    Exp exp;
    UnaryOp(Exp exp){
        this.exp=exp;
    }
    @Override
    public String toString() {
        return AST.toString(this, exp);
    }
}
