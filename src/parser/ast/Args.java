package parser.ast;

import java.util.List;

/**
 * Created by marco on 07/03/16.
 */
public interface Args extends AST{
    public void add(Exp exp);

    List<Exp> getArgs();
}
