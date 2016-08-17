package parser.ast;

import visitor.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * Created by marco on 08/03/16.
 */
public class SimpleFunDec implements FunDec {
    private Type type;
    private Ident ident;
    private Params params;
    private Exp exp;

    public SimpleFunDec(Type type, Ident ident, Params params, Exp exp){
        this.type=requireNonNull(type);
        this.ident=requireNonNull(ident);
        this.params=requireNonNull(params);
        this.exp=requireNonNull(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitSimpleFunDec(type,ident,params.getParams(),exp);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getClass().getSimpleName())
                .append("(").append(type.toString())
                .append(",").append(ident.toString())
                .append(",").append(params.toString())
                .append(",").append(exp.toString())
                .append(")").toString();
    }


}
