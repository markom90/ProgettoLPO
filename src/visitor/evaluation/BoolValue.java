package visitor.evaluation;

public class BoolValue extends PrimValue<Boolean> {

	public BoolValue(Boolean value) {
		super(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BoolValue))
			return false;
		return value.equals(((BoolValue) obj).value);
	}

	@Override
	public int compareTo(Value o) {
		if (!(o instanceof BoolValue))
			throw new ClassCastException(errMsg);
		return value.compareTo(((BoolValue) o).value);
	}

	@Override
	public boolean asBool() {
		return value;
	}
}
