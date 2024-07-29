package math;

public class LargestSquareArea {

	// bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
	public static void main(String[] args) {
		LargestSquareArea lsa = new LargestSquareArea();
		int[][] bottomLeft = {{1, 1}, {2, 2}, {3, 1}};
		int[][] topRight = {{3, 3}, {4, 4}, {6, 6}};
		System.out.println(lsa.largestSquareArea(bottomLeft, topRight));
	}

	private boolean isRectangleOverlap(Rectangle r1, Rectangle r2) {
		// if one rectangle is on the left side of the other
		if (r1.topRightX <= r2.bottomLeftX || r2.topRightX <= r1.bottomLeftX) {
			return false;
		}
		// if one rectangle is above the other
		if (r1.topRightY <= r2.bottomLeftY || r2.topRightY <= r1.bottomLeftY) {
			return false;
		}
		return true;
	}

	// find the min side of the rectangle formed by the overlapping rectangles
	private int minSide(Rectangle r1, Rectangle r2) {
		int x1 = Math.max(r1.bottomLeftX, r2.bottomLeftX);
		int y1 = Math.max(r1.bottomLeftY, r2.bottomLeftY);
		int x2 = Math.min(r1.topRightX, r2.topRightX);
		int y2 = Math.min(r1.topRightY, r2.topRightY);
		return Math.min(x2 - x1, y2 - y1);
	}

	public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
		int n = bottomLeft.length;
		Rectangle[] rectangles = new Rectangle[n];
		for (int i = 0; i < n; i++) {
			rectangles[i] = new Rectangle(bottomLeft[i][0], bottomLeft[i][1], topRight[i][0],
				topRight[i][1]);
		}
		long maxArea = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (isRectangleOverlap(rectangles[i], rectangles[j])) {
					int minSide = minSide(rectangles[i], rectangles[j]);
					maxArea = Math.max(maxArea, (long) minSide * minSide);
				}
			}
		}
		return maxArea;
	}

	static class Rectangle {

		int bottomLeftX;
		int bottomLeftY;
		int topRightX;
		int topRightY;

		Rectangle(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
			this.bottomLeftX = bottomLeftX;
			this.bottomLeftY = bottomLeftY;
			this.topRightX = topRightX;
			this.topRightY = topRightY;
		}
	}
}