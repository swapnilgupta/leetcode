package arrays;

public class JumpGame {

	public boolean canJump1(int[] nums) {
		int reachable = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > reachable) {
				return false;
			}
			reachable = Math.max(reachable, i + nums[i]);

		}
		return true;
	}

	public static boolean canJump(int[] nums) {
		int n = nums.length;
		int lastGoodIndex = n - 1;
		for (int i = n - 1; i >= 0; --i) {
			if (i + nums[i] >= lastGoodIndex) {
				lastGoodIndex = i;
			}
		}
		return lastGoodIndex == 0;
	}

	// driver code for above
	public static void main(String[] args) {

		// Call the method and print the result
		int[] nums = {2, 3, 1, 1, 4};
		System.out.println("Can Jump: " + canJump(nums));

		int[] nums2 = {3, 2, 1, 0, 4};
		System.out.println("Can Jump: " + canJump(nums2));
	}

}
