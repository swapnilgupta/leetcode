package bitmanipulation;

public class BitManipulationBasics {

	// Number of set bits in a number
	public int hammingWeight(int n) {
		int count = 0;
		while (n > 0) {
			n &= (n - 1);
			++count;
		}
		return count;
	}


}
