package arrays;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

	public List<String> fullJustify(String[] words, int maxWidth) {
		int n = words.length;
		List<String> ans = new ArrayList<>();
		for (int i = 0, w; i < n; i = w) {
			// Length of the text with 1 space between words
			int len = -1; // a last word in a line doesn't need space at the end
			// check how much can be accommodated in a line
			for (w = i; w < n && len + words[w].length() + 1 <= maxWidth; w++) {
				len += words[w].length() + 1;
			}
			StringBuilder line = new StringBuilder(words[i]);

			// Initially, we have only one space and zero extra spaces
			int space = 1, extra = 0;
			// if there is more than one word in a line, and it is not the last line
			if (w != i + 1 && w != n) {
				// spaces remaining at end -> maxWidth - len
				// slots -> w - i - 1
				space = (maxWidth - len) / (w - i - 1) + 1;
				extra = (maxWidth - len) % (w - i - 1);
			}

			// Adjust spaces in remaining slots
			for (int j = i + 1; j < w; ++j) {
				line.append(" ".repeat(Math.max(0, space)));
				// Put extra spaces in a left aligned manner as we are going from left to right
				if (extra-- > 0) {
					line.append(" ");
				}
				line.append(words[j]);
			}

			// Putting extra spaces at the end of the line
			int remaining = maxWidth - line.length();
			if (remaining > 0) {
				line.append(" ".repeat(remaining));
			}
			// Finally, add the line to the answer
			ans.add(line.toString());
		}
		return ans;
	}

	// driver code for text justification
	public static void main(String[] args) {
		TextJustification tj = new TextJustification();
		String[] words = {"Science", "is", "what", "we", "understand", "well", "enough", "to",
			"explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
		int maxWidth = 20;
		List<String> ans = tj.fullJustify(words, maxWidth);
		for (String line : ans) {
			System.out.println("\"" + line + "\"");
		}
	}


}
