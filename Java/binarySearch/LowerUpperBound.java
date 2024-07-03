package binarySearch;

public class LowerUpperBound {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 5, 5, 5, 5, 7, 7};
		int key = 5;

		int lb = lower(arr, key);
		int ub = upper(arr, key);

		System.out.println("Lower bound for key " + key + " is at index " + lb);
		System.out.println("Upper bound for key " + key + " is at index " + ub);
	}

	public static int lower(int arr[], int key) {
		int low = 0;
		int high = arr.length;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] >= key) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public static int upper(int arr[], int key) {
		int low = 0;
		int high = arr.length;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] > key) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}


}
