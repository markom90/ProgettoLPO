package parser.ast;

import static java.util.Objects.requireNonNull;

public abstract class Literal<T> implements Exp {

    protected final T value;

    protected Literal(T value) {
        this.value = requireNonNull(value);
    }
    @Override
    public String toString() {
        return AST.toString(this, value);
    }
    public T getValue(){return value;}
}



