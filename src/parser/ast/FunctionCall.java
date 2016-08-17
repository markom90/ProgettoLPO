package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * Created by marco on 06/08/16.
 */
public class FunctionCall implements Exp {
    SimpleIdent name;
    Args args;

    public FunctionCall(SimpleIdent name, Args args) {
        this.name = requireNonNull(name);
        this.args = requireNonNull(args);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitFunctionCall(name,args.getArgs());
    }
}
