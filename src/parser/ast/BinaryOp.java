package parser.ast;

import static java.util.Objects.requireNonNull;

public abstract class BinaryOp implements Exp {
    Exp left;
    Exp right;
    public BinaryOp(Exp left, Exp right){
        this.left=requireNonNull(left);
        this.right=requireNonNull(right);
    }

    @Override
    public String toString() {
        return AST.toString(this, left, right);
    }
}
