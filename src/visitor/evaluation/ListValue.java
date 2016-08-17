package visitor.evaluation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by marco on 16/08/16.
 */
public class ListValue implements Value {
    List<Value> values;

    public ListValue(List<Value> values) {
        this.values = values;
    }

    public ListValue() {
        values = new LinkedList<>();
    }

    @Override
    public int compareTo(Value o) {
        return 0;
    }

    @Override
    public List<Value> asList() {
        return values;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ListValue))
            return false;
        return values.equals(((ListValue)obj).values);
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }
    @Override
    public String toString() {
        return values.toString();
    }


}
