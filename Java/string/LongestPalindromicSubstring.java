package string;

public class LongestPalindromicSubstring {

	private int expandAroundCenter(char[] s, int start, int end) {
		int L = start, R = end;
		// Expand around the center
		// if out of bounds or not a palindrome
		if (L < 0 || R >= s.length || s[L] != s[R]) {
			return 0;
		}
		while (L >= 0 && R < s.length && s[L] == s[R]) {
			L--;
			R++;
		}
		return R - L - 1;
	}

	public String longestPalindrome(String sx) {
		char[] s = sx.toCharArray();
		int start = 0, end = 0;
		for (int i = 0; i < s.length; i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return sx.substring(start, end + 1);
	}

	// driver code
	public static void main(String[] args) {
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
		String s = "babad";
		System.out.println(lps.longestPalindrome(s)); // prints "bab"
	}

}
