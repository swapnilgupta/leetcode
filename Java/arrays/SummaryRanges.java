package arrays;

import java.util.*;


public class SummaryRanges {
    private Set<Integer> values;

    public SummaryRanges() {
        values = new TreeSet<>();
    }

    public void addNum(int value) {
        // Adding value
        System.out.print("Adding value: " + value + " ");
        // values are as follows
        values.add(value);
        System.out.print("Nums: ");
        for(int i = 0; i < values.size(); ++i) {
            System.out.print(values.toArray()[i] + " ");
        }
        System.out.println();
    }

    public int[][] getIntervals() {
        if (values.isEmpty()) {
            return new int[0][2];
        }
        List<int[]> intervals = new ArrayList<>();
        int left = -1, right = -1;
        for (Integer value : values) {
            if (left < 0) {
                left = right = value;
            } else if (value == right + 1) {
                right = value;
            } else {
                intervals.add(new int[] {left, right});
                left = right = value;
            }
        }
        intervals.add(new int[] {left, right});
        return intervals.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();

        summaryRanges.addNum(1);
        printIntervals(summaryRanges.getIntervals());

        summaryRanges.addNum(3);
        printIntervals(summaryRanges.getIntervals());

        summaryRanges.addNum(7);
        printIntervals(summaryRanges.getIntervals());

        summaryRanges.addNum(2);
        printIntervals(summaryRanges.getIntervals());

        summaryRanges.addNum(6);
        printIntervals(summaryRanges.getIntervals());
    }

    public static void printIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print("[" + interval[0] + ", " + interval[1] + "] ");
        }

        System.out.println();
    }
}


/**
 * Your arrays.SummaryRanges object will be instantiated and called as such:
 * arrays.SummaryRanges obj = new arrays.SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */