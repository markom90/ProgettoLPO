package parser.ast;

import visitor.Visitor;

public enum PrimType implements Type {
    INT,BOOL,STRING;

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitType(this);
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
