package namedAlgorithm;

// https://www.youtube.com/watch?v=BXCEFAzhxGY&ab_channel=BackToBackSWE
// https://www.youtube.com/watch?v=V5-7GzOfADQ&ab_channel=AbdulBari
// https://leetcode.com/problems/number-of-subarrays-that-match-a-pattern-ii/
public class KMP {

	private String text;
	private String pattern;
	private int[] lps;

	private void createLPSTable(String pattern) {
		int m = pattern.length();
		lps = new int[m];
		if (m < 2) {
			return;
		}
		int i = 0, j = 1;
		System.out.print("LPS: ");
		while (j < m) {
			if (pattern.charAt(i) == pattern.charAt(j)) {
				lps[j] = i + 1;
				i++;
				j++;
			} else {
				if (i != 0) {
					i = lps[i - 1];
				} else {
					lps[j] = i;
					j++;
				}
			}
		}
		for (int p : lps) {
			System.out.print(p + " , ");
		}
		System.out.println();
	}

	public int findStr(String text, String pattern) {
		int M = pattern.length();
		int N = text.length();
		int index = -1;
		// Pre-processing step
		// The longest prefix which is also the suffix
		createLPSTable(pattern);

		// Applying the namedAlgorithm.KMP algorithm
		int i = 0; // index for txt[]
		int j = 0;
		while (i < N) {
			if (pattern.charAt(j) == text.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				System.out.println("Found pattern " + "at index " + (i - j));
				index = i - j;
				j = lps[j - 1]; // look for the next match
			}

			// mismatch after j matches then lps[j - 1] knows where to start now
			else if (i < N && pattern.charAt(j) != text.charAt(i)) {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i = i + 1; // pattern cannot match at i, advance i -> i + 1
				}
			}
		}
		return index;
	}

	public static void main(String[] args) {
		KMP kmp = new KMP();
		String text = "adsgwadsxdsgwadsgz";
		String pattern = "cacacaabc";
		int index = kmp.findStr(text, pattern);
		System.out.println("Last occurrence found at: " + index);
	}
}
