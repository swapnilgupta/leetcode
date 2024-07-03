// MergeTwoSortedArray in O(1) space.
public class MergeTwoSortedArray {

	public void merge(int[] A1, int[] A2, int n, int m) {
		int i = 0, j, gap;
		gap = (int) Math.ceil((float) (n + m) / 2.0);
		while (gap > 0) {
			j = gap;
			if (j < n && A1[i] > A2[j]) {

			}
		}
	}

	public static void main(String[] args) {

	}

}
