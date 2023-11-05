package math;
import java.util.*;

public class MaxPointsOnLine {

    public int maxPoints(int[][] points) {
        final int len = points.length;
        if (len <= 2) {
            return len;
        }

        int max = 2;

        for (int i = 0; i < len; i++) {
            max = Math.max(max, getMax(points[i][0], points[i][1], i + 1, points, len));
        }
        return max;
    }

    int getMax(int x, int y, int j, int[][] points, int len) {
        int max = 2;
        // Slope of line
        HashMap<Double, int[]> lines = new HashMap<>(len - j + 1, .95f);
        for (; j < len; j++) {
            Double slope = points[j][1] == y ? 0.0
                : points[j][0] == x ? Double.POSITIVE_INFINITY
                    : (double) (points[j][1] - y) / (points[j][0] - x);
            //System.out.format("%d %d %f\n", i, j, slope);
            int[] count = lines.get(slope);
            if (count == null) {
                lines.put(slope, new int[]{2});
            } else {
                max = Math.max(max, ++count[0]);
            }
        }
        return max;
    }


}
