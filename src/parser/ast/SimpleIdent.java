package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

public class SimpleIdent implements Ident {
    private final String name;

    public SimpleIdent(String name) {
        requireNonNull(name);
        if (name.length() == 0)
            throw new IllegalArgumentException();
        this.name = name;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitSimpleIdent(this);
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return AST.toString(this, name);
    }
    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        return obj instanceof SimpleIdent && name.equals(((SimpleIdent) obj).getName());
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

