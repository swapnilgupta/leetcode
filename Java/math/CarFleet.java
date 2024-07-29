package math;

import java.util.Arrays;
import java.util.Comparator;

public class CarFleet {

	public static void main(String[] args) {
		CarFleet cf = new CarFleet();
		int fleets = cf.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3});
		System.out.println(fleets);
	}

	public int carFleet(int target, int[] position, int[] speed) {
		int n = position.length;
		Car[] cars = new Car[n];
		for (int i = 0; i < n; ++i) {
			cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
		}
		Arrays.sort(cars, Comparator.comparingInt(a -> a.position));
		int fleets = 0;
		double curTime = 0;
		for (int i = n - 1; i >= 0; --i) {
			if (cars[i].time > curTime) {
				curTime = cars[i].time;
				fleets++;
			}
		}
		return fleets;
	}

	static class Car {

		int position;
		double time;

		Car(int position, double time) {
			this.position = position;
			this.time = time;
		}
	}
}