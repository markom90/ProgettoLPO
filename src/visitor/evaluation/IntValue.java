package visitor.evaluation;

public class IntValue extends PrimValue<Integer> {

	public IntValue(Integer value) {
		super(value);
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof IntValue))
			return false;
		return value.equals(((IntValue) obj).value);
	}

	@Override
	public final int compareTo(Value o) {
		if (!(o instanceof IntValue))
			throw new ClassCastException(errMsg);
		return value.compareTo(((IntValue) o).value);
	}

	@Override
	public int asInt() {
		return value;
	}

}
