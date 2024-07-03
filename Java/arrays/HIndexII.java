package arrays;

public class HIndexII {

	public int hIndex(int[] citations) {
		int left = 0, len = citations.length, right = len - 1, mid;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (citations[mid] == (len - mid)) {
				return citations[mid];
			} else if (citations[mid] > (len - mid)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return len - (right + 1);
	}

	// driver code for the above function
	public static void main(String[] args) {
		HIndexII h = new HIndexII();
		int[] citations = {0, 1, 3, 5, 6};
		System.out.println(h.hIndex(citations));
	}

}
