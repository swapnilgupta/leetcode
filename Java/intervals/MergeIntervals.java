package intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MergeIntervals {

	public int[][] mergeIntervals(int[][] intervals) {
        /*
        Example:
          myList.sort((person1, person2) -> person1.getName().compareTo(person2.getName()));

          myList2.sort((person1, person2) -> {
              int res = person1.first().compareTo(person2.first());
              if(res == 0) res = person1.second().compareTo(person2.second());
              if(res == 0) res = person1.third() - person2.third();
              return res;
          });
        After the quick-fixes are applied:
          myList.sort(Comparator.comparing(Person::getName));

          myList2.sort(Comparator.comparing(Person::first)
                                 .thenComparing(Person::second)
                                 .thenComparingInt(Person::third));
         */
		Arrays.sort(intervals, Comparator.comparing(a -> a[0]));
		LinkedList<int[]> merged = new LinkedList<>();

		for (int[] interval : intervals) {
			if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
				merged.add(interval);
			} else {
				merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
			}
		}

		return merged.toArray(new int[merged.size()][]);
	}

	// driver code for merging the intervals
	public static void main(String[] args) {
		MergeIntervals mi = new MergeIntervals();
		int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
		int[][] mergedIntervals = mi.mergeIntervals(intervals);
		for (int[] interval : mergedIntervals) {
			System.out.println(Arrays.toString(interval));
		}
	}


}
