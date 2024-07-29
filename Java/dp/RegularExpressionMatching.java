package dp;

// Define an enum for these types of scenarios otherwise we need to fill
// the whole array with 3rd value as we need 3 values here for the memo
// now here TRUE FALSE OR null (default) value
enum Result {
	TRUE,
	FALSE
}

public class RegularExpressionMatching {

	Result[][] memo;

	public boolean isMatch(String text, String pattern) {
		int m = text.length(), n = pattern.length();
		// Using memoization - Top-down Approach
		Result[][] memo = new Result[m + 1][n + 1];
		return helper(0, 0, text, pattern, memo);
	}


	boolean helper(int i, int j, String text, String pattern, Result[][] memo) {
		// If we already calculated the results then return from the table
		// where data is already saved
		if (memo[i][j] != null) {
			return memo[i][j] == Result.TRUE;
		}
		boolean ans;
		// Base Case: If both pattern and text pointers i.e. i & j both reached to end
		// means pattern can generate text successfully -
		if (j == pattern.length()) {
			ans = i == text.length();
		} else {
			// Either pattern is at '.' or both text[i] == pattern[j]
			boolean firstMatch = (i < text.length() &&
				(text.charAt(i) == pattern.charAt(j) ||
					pattern.charAt(j) == '.'));

			// If Kleen's '*' is the 2nd character - pattern
			// Case 1 - [char]* - is NULL
			// Case 2 - [char]* - any occurrence of [char]
			if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
				ans = helper(i, j + 2, text, pattern, memo) || // NULL - Case - 1
					firstMatch && helper(i + 1, j, text, pattern, memo); // 1 - Match case
			} else {
				// If 2nd Char is NOT Kleen's * then only case is to match the first char
				// Advances both i and j as it's case of match
				ans = firstMatch && helper(i + 1, j + 1, text, pattern, memo);
			}
		}

		memo[i][j] = ans ? Result.TRUE : Result.FALSE;
		return ans;
	}
}