package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

public class FunctionCall implements Exp {
    private SimpleIdent name;
    private Args args;

    public FunctionCall(SimpleIdent name, Args args) {
        this.name = requireNonNull(name);
        this.args = requireNonNull(args);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitFunctionCall(name,args.getArgs());
    }
}
