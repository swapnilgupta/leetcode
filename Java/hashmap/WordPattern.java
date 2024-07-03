package hashmap;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

	public boolean wordPattern(String pattern, String s) {

		Map<Character, String> m1 = new HashMap<>();
		Map<String, Character> m2 = new HashMap<>();
		int m = pattern.length(), n = s.length();
		int i = 0, j = 0;
		while (i < m && j < n) {
			char c1 = pattern.charAt(i);

			StringBuilder sb = new StringBuilder();
			while (j < n && s.charAt(j) != ' ') {
				sb.append(s.charAt(j++));
			}
			++j;

			String c2 = sb.toString();

			// Case 1: No mapping exists in either of the dictionaries
			if (!m1.containsKey(c1) && !m2.containsKey(c2)) {
				m1.put(c1, c2);
				m2.put(c2, c1);
			}

			// Case 2: Ether mapping doesn't exist in one of the dictionaries or Mapping exists, and
			// it doesn't match in either of the dictionaries or both
			else if (!(m1.getOrDefault(c1, "").equals(c2) && m2.getOrDefault(c2, ' ') == c1)) {
				return false;
			}
			++i;
		}

		return i >= m && j >= n;
	}

	// driver code for above
	public static void main(String[] args) {
		WordPattern obj = new WordPattern();
		String pattern = "abba";
		String s = "dog dog dog dog";
		boolean ans = obj.wordPattern(pattern, s);
		System.out.println(ans);
	}

}
