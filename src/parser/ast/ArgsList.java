package parser.ast;

import visitor.Visitor;
import java.util.ArrayList;
import java.util.List;


public class ArgsList implements Args {
    private List<Exp>  args;

    public ArgsList(){ args  = new ArrayList<>();}

    @Override
    public void add(Exp exp) {
        args.add(exp);
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitArgList(args);
    }

    public List<Exp> getArgs() {
        return args;
    }
}
