package slidingWindow;

public class MinimumWindowSubString {

	public String minWindow(String s, String t) {
		int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
		int[] fw = new int[256];
		for (int i = 0; i < t.length(); ++i) {
			++fw[t.charAt(i)];
		}

		while (end < s.length()) {
			final char c1 = s.charAt(end);
			if (fw[c1] > 0) {
				counter--;
			}
			fw[c1]--;
			end++;
			while (counter == 0) {  // found one candidate for which has all the elements from t
				if (minLen > end - start) {
					minLen = end - start;
					minStart = start;
				}
				final char c2 = s.charAt(start);
				fw[c2]++;
				if (fw[c2] > 0) {
					counter++; // Keep ignoring non-matching elements
				}
				start++;
			}
		}

		// substring gives you substring from startIndex to endIndex - 1 in Java
		return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
	}

	// write driver code for the above function
	public static void main(String[] args) {
		MinimumWindowSubString mws = new MinimumWindowSubString();
		String s = "ADOBECODEBANC";
		String t = "ABC";
		System.out.println(mws.minWindow(s, t));
	}

}
