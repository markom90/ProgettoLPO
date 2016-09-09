package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ReaderMatcher implements StreamMatcher {
	private static final MatchResult noresult = Pattern.compile("").matcher("").toMatchResult();
	private final Matcher matcher;
	private final BufferedReader buffReader;
	private MatchResult result;
	private boolean hasNext = true;
	private String line;
	private String skipped = "";

	private void skip() {
		if (matcher.find()) { // still valid tokens in the region
			skipped = line.substring(matcher.regionStart(), matcher.start());
			matcher.region(matcher.start(), matcher.regionEnd());
		} else { // no valid tokens in the region
			skipped = line.substring(matcher.regionStart(), matcher.regionEnd());
			matcher.region(matcher.regionEnd(), matcher.regionEnd());
		}
	}

	ReaderMatcher(String regex, Reader reader) {
		matcher = Pattern.compile(regex).matcher("");
		buffReader = new BufferedReader(reader);
	}

	@Override
	public boolean nextMatches() throws IOException {
		skipped = "";
		result = noresult;
		if (!hasNext())
			throw new NoSuchElementException();
		while (matcher.regionStart() == matcher.regionEnd()) {
			line = buffReader.readLine();
			if (line == null) {
				matcher.reset("");
				return hasNext = false;
			}
			matcher.reset(line);
		}
		boolean matched = matcher.lookingAt();
		result = matcher.toMatchResult();
		if (!matched)
			skip();
		else
			matcher.region(matcher.end(), matcher.regionEnd());
		return matched;
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public String getSkipped() {
		return skipped;
	}

	@Override
	public String group() {
		return result.group();
	}

	@Override
	public String group(int group) {
		return result.group(group);
	}

}
