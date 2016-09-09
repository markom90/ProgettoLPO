package visitor.evaluation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Value extends Comparable<Value> {
	/* inherited from Object, must be overridden in implementing classes */
	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();

	@Override
	String toString();

	/* inherited from Comparable, must be overridden in implementing classes */
	@Override
	int compareTo(Value o);

	/* default conversion methods */
	default int asInt() {
		throw new ClassCastException("Expecting an integer value");
	}

	default boolean asBool() {
		throw new ClassCastException("Expecting a boolean value");
	}

	default String asString() {
		throw new ClassCastException("Expecting a string value");
	}

	default List<Value> asList(){throw new ClassCastException("Expecting a list");}

}
