package intervals;

import java.util.Arrays;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        int[][] ans = new int[n][2];
        int idx = 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] prev = intervals[0];
        for(int i = 1; i < n; ++i) {
            int[] curr = intervals[i];
            if(curr[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                ans[idx++] = prev;
                prev = curr;
            }
        }
        ans[idx++] = prev;
        return Arrays.copyOfRange(ans, 0, idx);
    }

}
