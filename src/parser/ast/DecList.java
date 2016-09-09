package parser.ast;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DecList implements Decs {

    private List<Dec> decs;

    public DecList(){this.decs= new ArrayList<>();}

    @Override
    public void add(Dec dec) {
        decs.add(dec);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(");
        Iterator<Dec> it = decs.iterator();
        if (it.hasNext())
            sb.append(it.next());
        for (; it.hasNext();)
            sb.append(",").append(it.next());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitDecList(decs);
    }
}
