package visitor.typechecking;

import parser.ast.PrimType;
import parser.ast.Type;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;


public class FunTypeImpl implements FunType {
    protected final List<Type> paramsType;
    protected final Type returnType;

    public FunTypeImpl(Type returnType) {
        this.returnType = returnType;
        paramsType = new ArrayList<>();
    }

    @Override
    public List<Type> getArgsType() {
        return paramsType;
    }

    @Override
    public Type getRetType() {
        return returnType;
    }

    @Override
    public void addType(Type argType) {
        paramsType.add(argType);

    }
}
