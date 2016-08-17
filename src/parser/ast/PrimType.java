package parser.ast;

import visitor.Visitor;

/**
 * Created by marco on 08/03/16.
 */
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
