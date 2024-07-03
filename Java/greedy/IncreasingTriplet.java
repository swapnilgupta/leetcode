package greedy;

public class IncreasingTriplet {

	public boolean increasingTriplet(int[] nums) {
		int firstMin = Integer.MAX_VALUE;
		int secondMin = Integer.MAX_VALUE;

		for (int num : nums) {
			if (num <= firstMin) {
				firstMin = num;
			} else if (num <= secondMin) {
				secondMin = num;
			} else {
				return true;
			}
		}
		return false;
	}

	// Add a driver code for the above function
	public static void main(String[] args) {
		IncreasingTriplet it = new IncreasingTriplet();
		int[] nums = {1, 2, 3, 4, 5};
		System.out.println(it.increasingTriplet(nums));
	}
}