package parser;

import java.io.IOException;

public interface StreamMatcher {
	boolean nextMatches() throws IOException;

	boolean hasNext();

	String getSkipped();

	String group();

	String group(int group);
}