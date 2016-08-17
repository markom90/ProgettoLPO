package visitor.typechecking;

import parser.ast.PrimType;
import parser.ast.Type;

import java.util.List;

/**
 * Created by marco on 07/08/16.
 */
public interface FunType {
    List<Type> getArgsType();
    Type getRetType();
    void addType(Type argType);
}
