package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

public class ListType implements Type {
    private Type type;

    public ListType(Type type) {
        this.type = requireNonNull(type);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitListType(type);
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString() + " list";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        return obj instanceof ListType && type.equals(((ListType) obj).getType());
    }
}
