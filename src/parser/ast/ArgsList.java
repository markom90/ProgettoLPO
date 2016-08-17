package parser.ast;

import com.sun.org.apache.xpath.internal.Arg;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 06/08/16.
 */
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
