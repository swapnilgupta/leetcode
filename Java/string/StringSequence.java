package string;
import java.util.*;

public class StringSequence {
	public List<String> stringSequence(String target) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		int sz = target.length();
		int i = 0;
		while(sz-- > 0) {
			sb.append("a");
			res.add(sb.toString());
			int diff = target.charAt(i) - 'a';
			while(diff-- > 0) {
				// increment the last character by 1
				sb.setCharAt(sb.length() - 1, (char)(sb.charAt(sb.length() - 1) + 1));
				res.add(sb.toString());
			}
			++i;
		}
		return res;
	}

	public static void main(String[] args) {
		StringSequence obj = new StringSequence();
		System.out.println(obj.stringSequence("c"));
		System.out.println(obj.stringSequence("abc"));
	}

}
