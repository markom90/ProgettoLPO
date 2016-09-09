package parser.ast;

import java.util.List;

public interface Params extends AST {
    void add(Param param);

    List<Param> getParams();

    @Override
    String toString();
}
