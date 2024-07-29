package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

	public static void backtrack(String digits, Map<Character, String> map, List<String> res,
		StringBuilder sb, int sz) {
		if (sb.length() == digits.length()) {
			res.add(sb.toString());
			return;
		}
		for (int i = sz; i < digits.length(); ++i) {
			String s = map.get(digits.charAt(i));
			for (char c : s.toCharArray()) {
				sb.append(c);
				backtrack(digits, map, res, sb, i + 1);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		// create the map for the digits
		Map<Character, String> map = new HashMap<>();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");

		backtrack(digits, map, res, new StringBuilder(), 0);

		return res;
	}

}
