package string;

public class CountSubStringsWithKFrequencyCharacters {
	public int numberOfSubstrings(String s, int k) {
		int len = s.length();
		int res = 0;
		int left = 0;
		int[] f = new int[26];


		for (int right = 0; right < len; right++) {

			char rightChar = s.charAt(right);
			f[rightChar - 'a']++;


			while (left <= right && f[rightChar - 'a'] >= k ) {
				res += (len - right);
				char leftChar = s.charAt(left);
				f[leftChar - 'a']--;
				left++;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		CountSubStringsWithKFrequencyCharacters obj = new CountSubStringsWithKFrequencyCharacters();
		System.out.println(obj.numberOfSubstrings("abcabc", 2));
		System.out.println(obj.numberOfSubstrings("abacab", 2));
		System.out.println(obj.numberOfSubstrings("abcde", 1));
		System.out.println(obj.numberOfSubstrings("abacb", 2));
	}

}
