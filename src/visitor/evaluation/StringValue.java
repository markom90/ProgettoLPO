package visitor.evaluation;

public class StringValue extends PrimValue<String> {

	public StringValue(String value) {
		super(value);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof StringValue && value.equals(((StringValue) obj).value);
	}

	@Override
	public int compareTo(Value o) {
		if (!(o instanceof StringValue))
			throw new ClassCastException(errMsg);
		return value.compareTo(((StringValue) o).value);
	}

	@Override
	public String asString() {
		return value;
	}
}