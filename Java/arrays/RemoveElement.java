package arrays;

public class RemoveElement {

	public int removeElement(int[] nums, int val) {
		int n = nums.length, j = n - 1, i = 0;
		while (i <= j) {
			while (j >= 0 && nums[j] == val) {
				j--;
			}
			if (j < 0 || i > j) {
				break;
			}
			if (nums[i] == val) {
				int temp = nums[j];
				nums[j] = nums[i];
				nums[i] = temp;
				j--;
			}
			i++;
		}
		return i;
	}

	// driver code for removeElement method
	public static void main(String[] args) {
		RemoveElement re = new RemoveElement();
		int[] nums = {2, 2, 2, 2, 2};
		int val = 2;
		System.out.println("Ans: " + re.removeElement(nums, val));
		System.out.println("-----");
		int[] nums1 = {0, 1, 2, 2, 3, 0, 4, 2};
		val = 1;
		System.out.println("Ans: " + re.removeElement(nums1, val));
		System.out.println("-----");
		int[] nums2 = {2, 2, 3};
		val = 2;
		System.out.println("Ans: " + re.removeElement(nums2, val));
	}

}
