package string;

import java.util.Arrays;

public class MakePalindromesInWords {

	public static void main(String[] args) {
		MakePalindromesInWords mp = new MakePalindromesInWords();
		String[] words = {"abbb", "ba", "aa"};
		System.out.println(mp.maxPalindromesAfterOperations(words));
	}

	public int maxPalindromesAfterOperations(String[] words) {
		int n = words.length;
		int res = 0, pairs = 0;
		int[] count = new int[26];
		int[] sizes = new int[n];
		for (int i = 0; i < n; i++) {
			String word = words[i];
			for (char c : word.toCharArray()) {
				pairs += ((++count[c - 'a'] % 2 == 0) ? 1 : 0);
			}
			sizes[i] = word.length();
		}

		Arrays.sort(sizes);
		for (int sz : sizes) {
			pairs -= sz / 2;
			res += pairs >= 0 ? 1 : 0;
		}

		return res;
	}
}
