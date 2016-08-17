package parser.ast;

import java.util.List;

/**
 * Created by marco on 07/03/16.
 */
public interface Params extends AST {
    void add(Param param);

    List<Param> getParams();

    @Override
    String toString();
}
