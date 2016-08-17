package parser.ast;

/**
 * Created by marco on 08/03/16.
 */
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
