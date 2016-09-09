package visitor.evaluation;

class BoolValue extends PrimValue<Boolean> {

	BoolValue(Boolean value) {
		super(value);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof BoolValue && value.equals(((BoolValue) obj).value);
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
