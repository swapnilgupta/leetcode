package arrays;

import java.util.Arrays;
import java.util.Comparator;

public class NumberOfPairs {

	public boolean isAnyPointNotComesInSquare(int[] topLeft, int[] bottomRight, int[][] points) {
		int x1 = topLeft[0];
		int y1 = topLeft[1];
		int x2 = bottomRight[0];
		int y2 = bottomRight[1];

		// keep x1 < x2 and y1 < y2
		if (x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if (y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		int count = 0;
		for (int[] point : points) {
			int x = point[0];
			int y = point[1];
			// point should be other than topLeft and bottomRight and should be inside the square
			if ((x != x1 && x != x2) && (y != y1 && y != y2) && (x1 < x && x < x2) && (y1 < y
				&& y < y2)) {
				count++;
			}
		}
		return count == 0;
	}

	public int numberOfPairs(int[][] points) {
		int res = 0;
		int n = points.length;
		Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				int[] topLeft = points[i];
				int[] bottomRight = points[j];
				if (topLeft[0] == bottomRight[0] || topLeft[1] == bottomRight[1]
					|| topLeft[1] < bottomRight[1]) {
					continue;
				} else {
					if (isAnyPointNotComesInSquare(topLeft, bottomRight, points)) {
						res++;
					}
				}
			}
		}
		return res;
	}

	// Input: points = [[6,2],[4,4],[2,6]]
	//Output: 2

	// driver code for the above function
	public static void main(String[] args) {
		NumberOfPairs np = new NumberOfPairs();
		int[][] points = {{6, 2}, {4, 4}, {2, 6}};
		System.out.println(np.numberOfPairs(points));
		// [[3,1],[1,3],[1,1]]
		int[][] points2 = {{3, 1}, {1, 3}, {1, 1}};
		System.out.println(np.numberOfPairs(points2));

	}

}
