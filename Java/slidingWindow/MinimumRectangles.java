package slidingWindow;

import java.util.Arrays;

public class MinimumRectangles {

	public int minRectanglesToCoverPoints(int[][] points, int w) {
		Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		int n = points.length;
		int res = 0;
		int i = 0;
		// take all the points in 1 rectangle from x = i to x = i + w
		while (i < n) {
			int j = i;
			while (j < n && points[j][0] <= points[i][0] + w) {
				j++;
			}
			res++;
			i = j;
		}
		return res;
	}

	public static void main(String[] args) {
		MinimumRectangles mr = new MinimumRectangles();
		// points = [[2,1],[1,0],[1,4],[1,8],[3,5],[4,6]], w = 1
		int[][] points = {{2, 1}, {1, 0}, {1, 4}, {1, 8}, {3, 5}, {4, 6}};
		int w = 1;
		System.out.println(mr.minRectanglesToCoverPoints(points, w)); // prints 2
	}
}
