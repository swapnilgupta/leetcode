package arrays;

public class ReverseWords {

	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();

		StringBuilder ans = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; --i) {
			char c = s.charAt(i);
			if (i == 0) {
				ans.append(sb.reverse());
			} else if (c == ' ') {
				ans.append(sb.reverse());
				sb = new StringBuilder();
			} else {
				sb.append(c);
			}
		}
		return ans.toString();
	}

}
