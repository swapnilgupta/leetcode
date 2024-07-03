package twoPointer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PaintWalls {

	static class Pair {

		int cost;
		int time;

		Pair(int cost, int time) {
			this.cost = cost;
			this.time = time;
		}
	}

	public int paintWalls(int[] cost, int[] time) {
		int n = cost.length, totalCost = 0;
		List<Pair> cp = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			Pair p = new Pair(cost[i], time[i]);
			cp.add(p);
		}
		cp.sort(Comparator.comparingInt(o -> o.cost));

		int i = 0, j = n - 1;
		while (i < j) {
			int freePaintTime = cp.get(i).time;
			totalCost += cp.get(i).cost;
			j = j - freePaintTime;
			++i;
		}

		return totalCost;
	}

	// write the driver code for the above function


}
