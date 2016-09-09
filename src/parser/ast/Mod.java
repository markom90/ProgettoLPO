package parser.ast;

import visitor.Visitor;

public class Mod extends BinaryOp {
    public Mod(Exp left, Exp right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitMod(left,right);
    }
}
