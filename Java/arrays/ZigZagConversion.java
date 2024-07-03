package arrays;

public class ZigZagConversion {

	public String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		StringBuilder[] rows = new StringBuilder[numRows];
		for (int i = 0; i < numRows; ++i) {
			rows[i] = (new StringBuilder());
		}
		int row = 0;
		boolean down = true;
		int i = 0;
		while (i < s.length()) {
			if (down) {
				rows[row].append(s.charAt(i++));
				if (row == numRows - 1) {
					down = false;
					row--;
				} else {
					row++;
				}
			} else {
				rows[row].append(s.charAt(i++));
				if (row == 0) {
					down = true;
					row++;
				} else {
					row--;
				}
			}
		}

		StringBuilder ans = new StringBuilder();
		for (i = 0; i < numRows; ++i) {
			ans.append(rows[i]);
		}
		return ans.toString();
	}

	// driver code for the above function
	public static void main(String[] args) {
		ZigZagConversion zzc = new ZigZagConversion();
		String s = "PAYPALISHIRING";
		int numRows = 3;
		System.out.println(zzc.convert(s, numRows));
	}

}
