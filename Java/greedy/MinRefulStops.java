package greedy;

public class MinRefulStops {

	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		int ans = -1;
		int currPos = startFuel;
		int i = 0;
		while (currPos < target) {
			int j = i;
			int maxCanGo = currPos;
			while (stations[j][0] <= currPos) {
				maxCanGo = Math.max(maxCanGo, currPos + stations[j][1]);
				if (maxCanGo < currPos + stations[j][1]) {
					maxCanGo = currPos + stations[j][1];
					i = j;
				}
				++j;
			}
			currPos = maxCanGo;
		}
		return ans;
	}

	// driver code for the above function
	public static void main(String[] args) {
		MinRefulStops mrs = new MinRefulStops();
		int target = 100;
		int startFuel = 10;
		int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
		System.out.println(mrs.minRefuelStops(target, startFuel, stations));
	}

}
