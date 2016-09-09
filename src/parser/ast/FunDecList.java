package parser.ast;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FunDecList implements FunDecs {

    private List<FunDec> funDecs;

    public FunDecList(){funDecs = new ArrayList<>();}

    public void add(FunDec funDec) {
        funDecs.add(funDec);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        Iterator<FunDec> it = funDecs.iterator();
        if (it.hasNext())
            sb.append(it.next());
        for (; it.hasNext();)
            sb.append(",").append(it.next());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitFunDecList(funDecs);
    }
}
