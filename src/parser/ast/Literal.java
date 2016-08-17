package parser.ast;

import static java.util.Objects.requireNonNull;

/**
 * Created by marco on 10/03/16.
 */
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



