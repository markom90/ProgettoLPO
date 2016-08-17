package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * Created by marco on 06/08/16.
 */
public class EmptyList implements Exp {
    private final Type type;

    public EmptyList(Type type) {
        this.type = requireNonNull(type);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitEmptyList(type);
    }
}
