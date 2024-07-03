package greedy;

public class KillMonsters {

	// Java code
	public int eliminateMaximum(int[] dist, int[] speed) {
		// get the length of the monsters, which is the same as either dist or speed
		int n = dist.length;

		// create an array to hold the numbers of monsters based on their arrival time
		int[] monsters = new int[n];

		// for each monster, calculate its arrival time
		for (int i = 0; i < n; i++) {
			int arrival = (int) Math.ceil((double) dist[i] / speed[i]);

			// if the arrival time is less than the total number of monster
			//  increments, the count of monsters that will arrive at that time
			if (arrival < n) {
				monsters[arrival]++;
			}
		}

		// initialize the counter for the number of eliminated monsters
		int eliminated = 0;

		// for each time unit, check if the number of arrived monsters is greater
		// than the time unit itself (which also indicates the number of monsters that can be eliminated)
		for (int i = 0; i < n; i++) {
			// if the total number of arrived monsters is more than the monsters we can eliminate until now,
			// it indicates that we will be overwhelmed by monsters and should return the current time unit
			if (eliminated + monsters[i] > i) {
				return i;
			}

			// add the arrived monsters to the eliminated counter
			eliminated += monsters[i];
		}

		// if we have checked all time units and can still handle the monsters,
		// then return the total number of time units (also the total number of monsters)
		return n;
	}

	public static void main(String[] args) {
		KillMonsters killMonsters = new KillMonsters();

		// Example usage
		int[] dist = {1, 3, 4};
		int[] speed = {1, 1, 1};
		int result = killMonsters.eliminateMaximum(dist, speed);
		System.out.println(result);
	}
}
