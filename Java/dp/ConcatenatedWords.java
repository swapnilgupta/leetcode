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
	 words =
	 ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
	 3      4.        10.       3.       10.             14         3.      12

	 catsdogcats -> cats dog cat
	 dogcatsdog -> dog cat dog
	 ratcatdogcat -> rat cat dog cat


	 */
		private final Map<String, Boolean> isOk = new HashMap<>();
		private boolean isConcatenatedWord(String w, Set<String> ws) {
			if(isOk.containsKey(w)) {
				return isOk.get(w);
			}
			for(int i = 1; i < w.length(); ++i) {
				String prefix = w.substring(0, i);
				String suffix = w.substring(i); // automatically takes the rest of the string
				if((ws.contains(prefix) && ws.contains(suffix)) ||
					( ws.contains(prefix) && isConcatenatedWord(suffix, ws) ) ) {
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

			for(String word : words) {
				if(isConcatenatedWord(word, ws)) {
					res.add(word);
				}
			}
			return res;
		}

}
