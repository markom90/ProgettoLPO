package visitor.typechecking;

import parser.ast.Type;

import java.util.List;


public interface FunType {
    List<Type> getArgsType();
    Type getRetType();
    void addType(Type argType);
}
