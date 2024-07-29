package backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

	private void backtrack(int n, int open, int close, StringBuilder curr, List<String> vp) {
		if (curr.length() == 2 * n) {
			vp.add(curr.toString());
			return;
		}
		if (open < n) {
			curr.append('(');
			backtrack(n, open + 1, close, curr, vp);
			curr.deleteCharAt(curr.length() - 1);
		}
		if (close < open) {
			curr.append(')');
			backtrack(n, open, close + 1, curr, vp);
			curr.deleteCharAt(curr.length() - 1);
		}
	}

	public List<String> generateParenthesis(int n) {
		List<String> vp = new ArrayList<>();
		backtrack(n, 0, 0, new StringBuilder(), vp);
		return vp;
	}

	// driver code
	public static void main(String[] args) {
		GenerateParentheses gp = new GenerateParentheses();
		System.out.println(gp.generateParenthesis(3));
	}
}