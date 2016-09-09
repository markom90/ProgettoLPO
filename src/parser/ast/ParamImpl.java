package parser.ast;

import visitor.Visitor;

public class ParamImpl implements Param {
    private Type type;
    private Ident ident;
    public ParamImpl(Type type, Ident ident){
        this.type = type;
        this.ident=ident;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Ident getId() {
        return ident;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitParam(type,ident);
    }
}
