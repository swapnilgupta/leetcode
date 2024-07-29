package hashmap;

import java.util.Arrays;

public class IsIsomorphic {

	public boolean isIsomorphic(String s, String t) {
		int n = s.length();
		int[] mappingSToT = new int[256];
		Arrays.fill(mappingSToT, -1);
		int[] mappingTToS = new int[256];
		Arrays.fill(mappingTToS, -1);

		for (int i = 0; i < n; ++i) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);

			// Case 1 where no mapping is available for c1 and c2
			if (mappingSToT[c1] == -1 && mappingTToS[c2] == -1) {
				mappingSToT[c1] = c2;
				mappingTToS[c2] = c1;
			} else if (!(mappingSToT[c1] == c2 && mappingTToS[c2] == c1)) {
				return false;
			}
		}
		return true;
	}

	// driver code for the above function
	public static void main(String[] args) {
		IsIsomorphic ii = new IsIsomorphic();
		String s = "egg";
		String t = "add";
		System.out.println(ii.isIsomorphic(s, t));
	}

}
