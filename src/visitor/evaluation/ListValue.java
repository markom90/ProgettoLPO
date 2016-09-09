package visitor.evaluation;

import java.util.LinkedList;
import java.util.List;

public class ListValue implements Value {
    private List<Value> values;

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
        return this == obj || obj instanceof ListValue && values.equals(((ListValue) obj).values);
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
