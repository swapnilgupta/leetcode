package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConcatenatedWords {

	/**
	 * This class is designed to find all concatenated words in a dictionary. A concatenated word is
	 * defined as a string that is comprised entirely of at least two shorter words in the
	 * dictionary.
	 * <p>
	 * Example: Given the words in the dictionary as follows: ["cat", "cats", "catsdogcats", "dog",
	 * "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"]
	 * <p>
	 * The following words are concatenated: - "catsdogcats" can be formed by concatenating "cats",
	 * "dog", and "cats" - "dogcatsdog" can be formed by concatenating "dog", "cats", and "dog" -
	 * "ratcatdogcat" can be formed by concatenating "rat", "cat", "dog", and "cat"
	 * <p>
	 * These words are identified by checking every possible prefix of a word to see if it exists in
	 * the dictionary and recursively checking the remainder of the word.
	 */
	private final Map<String, Boolean> isOk = new HashMap<>();

	private boolean isConcatenatedWord(String w, Set<String> ws) {
		if (isOk.containsKey(w)) {
			return isOk.get(w);
		}
		for (int i = 1; i < w.length(); ++i) {
			String prefix = w.substring(0, i);
			String suffix = w.substring(i); // automatically takes the rest of the string
			if ((ws.contains(prefix) && ws.contains(suffix)) ||
				(ws.contains(prefix) && isConcatenatedWord(suffix, ws))) {
				isOk.put(w, true);
				return isOk.get(w);
			}
		}
		isOk.put(w, false);
		return isOk.get(w);
	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Set<String> ws = new HashSet<>();
		Collections.addAll(ws, words);
		List<String> res = new ArrayList<>();

		for (String word : words) {
			if (isConcatenatedWord(word, ws)) {
				res.add(word);
			}
		}
		return res;
	}

}
