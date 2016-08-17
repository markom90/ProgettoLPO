package visitor.evaluation;

import static java.util.Objects.requireNonNull;

public abstract class PrimValue<T> implements Value {
	protected static final String errMsg = "Uncomparable values";
	protected T value;

	protected PrimValue(T value) {
		this.value = requireNonNull(value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
