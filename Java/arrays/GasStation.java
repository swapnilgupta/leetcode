package arrays;

public class GasStation {

	public static int canCompleteCircuit(int[] gas, int[] cost) {
		// if starting from 'a' you are stuck at 'b' then you cannot pass through from
		int n = gas.length, cGain = 0, tGain = 0, ans = 0;
		for (int i = 0; i < n; ++i) {
			tGain += (gas[i] - cost[i]);
			cGain += (gas[i] - cost[i]);

			// If we meet a "valley", start over from the next station
			if (cGain < 0) {
				ans = i + 1;
				cGain = 0;
			}
		}
		return tGain >= 0 ? ans : -1;
	}


	public static void main(String[] args) {
		int[] gas = {2, 3, 4, 5, 1};
		int[] cost = {4, 5, 1, 2, 3};

		// edge case where total gas is less than total cost
		int cityIndex = canCompleteCircuit(gas, cost);
		System.out.println(cityIndex);
		gas = new int[]{2, 3, 4, 5, 1};
		cost = new int[]{9, 5, 1, 2, 3};
		cityIndex = canCompleteCircuit(gas, cost);
		System.out.println(cityIndex);

	}
}

// driver code for ab