package string;

public class CountPrefixSuffixPairs {

	private boolean isPrefixAndSuffix(String st1, String st2) {
		int n = st1.length();
		int m = st2.length();
		if (n > m) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			if (st1.charAt(i) != st2.charAt(i) || st1.charAt(i) != st2.charAt(m - n + i)) {
				return false;
			}
		}
		return true;
	}

	public int countPrefixSuffixPairs(String[] words) {
		int res = 0;
		int n = words.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (isPrefixAndSuffix(words[i], words[j])) {
					res++;
				}
			}
		}
		return res;
	}
}
