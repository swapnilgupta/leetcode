package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordBreak {

	public boolean wordBreakBFS(String s, List<String> wordDict) {
		Set<String> words = new HashSet<>(wordDict);
		Queue<Integer> queue = new LinkedList<>();
		boolean[] seen = new boolean[s.length() + 1];
		queue.add(0);
		while (!queue.isEmpty()) {
			int start = queue.poll();
			if (start == s.length()) {
				return true;
			}

			for (int end = start + 1; end <= s.length(); end++) {
				if (seen[end]) {
					continue;
				}
				if (words.contains(s.substring(start, end))) {
					queue.add(end);
					seen[end] = true;
				}
			}
		}
		return false;
	}

	/**
	 * Finding all the sentences that can be formed by breaking the input string into words present
	 * in the dictionary.
	 *
	 * @param s        Input String
	 * @param wordDict List of words
	 * @return List of sentences
	 */
	public List<String> wordBreakII(String s, List<String> wordDict) {
		Set<String> words = new HashSet<>(wordDict);
		List<String> result = new ArrayList<>();
		List<String> path = new ArrayList<>();
		boolean[] seen = new boolean[s.length() + 1];
		backtrack(s, words, result, path, seen, 0);
		return result;
	}

	private void backtrack(String s, Set<String> words, List<String> result, List<String> path,
		boolean[] seen, int start) {
		if (start == s.length()) {
			result.add(String.join(" ", path));
			return;
		}

		for (int end = start + 1; end <= s.length(); end++) {
			if (seen[end]) {
				continue;
			}
			if (words.contains(s.substring(start, end))) {
				path.add(s.substring(start, end));
				seen[end] = true;
				backtrack(s, words, result, path, seen, end);
				path.remove(path.size() - 1);
				seen[end] = false;
			}
		}
	}

	// add a driver code for the above functions
	public static void main(String[] args) {
		String s = "leetcode";
		List<String> wordDict = new ArrayList<>(Arrays.asList("leet", "code"));
		WordBreak wb = new WordBreak();
		System.out.println(wb.wordBreakBFS(s, wordDict));

		s = "catsanddog";
		wordDict = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
		System.out.println(wb.wordBreakII(s, wordDict));
	}

}
