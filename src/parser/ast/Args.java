package parser.ast;

import java.util.List;


public interface Args extends AST{
    void add(Exp exp);

    List<Exp> getArgs();
}
