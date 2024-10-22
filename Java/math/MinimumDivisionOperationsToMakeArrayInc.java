package math;

public class MinimumDivisionOperationsToMakeArrayInc {
	int sieveSize = 1000000;  // Using direct integer value
	int[] sieve = new int[sieveSize + 1];
	// Function to compute the smallest prime factor (SPF) using Sieve of Eratosthenes
	public void computeSPF(int[] sieve) {
		int n = sieve.length - 1;
		for (int i = 0; i <= n; i++) {
			sieve[i] = i;
		}

		for (int i = 2; i * i <= n; i++) {  // Fixed the condition to i * i <= n
			if (sieve[i] == i) {  // 'i' is a prime number
				for (int j = i * i; j <= n; j += i) {
					if (sieve[j] == j) {  // Only update if j hasn't been marked yet
						sieve[j] = i;
					}
				}
			}
		}
	}

	// Function to get the greatest proper divisor of x
	int greatestProperDivisor(int x) {
		if (x == 1) return 1;  // If x is 1, return 1 because 1 is not a prime number and sieve[0] = 0
		return x / sieve[x];  // Return the greatest proper divisor using SPF(smallest prime factor)
	}

	public int minOperations(int[] nums) {
		int n = nums.length;
		if (n <= 1) return 0;  // Handle an edge case for empty or single-element arrays

		int operations = 0;


		// Traverse from right to left (i.e., from second last element to first)
		// because if once decreased, we ensure that we don't have to decrease it again
		for (int i = n - 2; i >= 0; i--) {
			// If the current element is greater than or equal to the next element
			while (nums[i] > nums[i + 1]) {
				int gpd = greatestProperDivisor(nums[i]);
				if (gpd == 1) {
					return -1;  // If GPD is 1, and we can't reduce further, return -1
				}
				nums[i] = nums[i] / gpd;  // Reduce nums[i] by dividing it by its GPD
				++operations;  // Increment the number of operations

				// If nums[i] becomes 1 and is still greater than nums[i + 1], return -1
				if (nums[i] == 1 && nums[i] > nums[i + 1]) {
					return -1;
				}
			}
		}
		return operations;  // Return the total number of operations
	}

	public static void main(String[] args) {
		MinimumDivisionOperationsToMakeArrayInc obj = new MinimumDivisionOperationsToMakeArrayInc();
		obj.computeSPF(obj.sieve);
		// some examples to understand the gpd
		int[] exampleNums = new int[]{25, 26, 9};
		for(int exampleNum : exampleNums) {
			System.out.println("Greatest proper divisor of " + exampleNum + " is " + obj.greatestProperDivisor(exampleNum) + " Smallest Prime Factor: " + obj.sieve[exampleNum]);
		}
		int[] nums = {25, 7};
		System.out.println(obj.minOperations(nums)); // 1
		nums = new int[]{7,7,6};
		System.out.println(obj.minOperations(nums)); // -1
		nums = new int[]{1,1,1,1,1};
		System.out.println(obj.minOperations(nums)); // 0
		nums = new int[]{25, 21, 9};
		System.out.println(obj.minOperations(nums)); // -1
		// 105,144,12
		nums = new int[]{105, 144, 12};
		System.out.println(obj.minOperations(nums)); // -1
	}
}
