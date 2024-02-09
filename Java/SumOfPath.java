import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfPath {

	public static void main(String[] args) {
		List<Integer> parents = Arrays.asList(-1, 0, 1, 2, 2, 3, 4, 4);
		List<Integer> startPoint = Arrays.asList(1, 3, 5);
		List<Integer> jumpLength = Arrays.asList(2, 1, 3);

		List<Long> sumList = sumValues(parents, startPoint, jumpLength);

		// Displaying the result
		for (long sum : sumList) {
			System.out.println("Sum of values: " + sum);
		}
	}

	public static List<Long> sumValues(List<Integer> parents, List<Integer> startPoint, List<Integer> jumpLength) {
		Long[][] memo = new Long[100][100];
		for (Long[] mem : memo) {
			Arrays.fill(mem, -1L);
		}

		int q = startPoint.size();
		List<Long> sumList = new ArrayList<>();
		for (int i = 0; i < q; ++i) {
			Long sum = traverseTreeWithMemoization(parents, startPoint.get(i), jumpLength.get(i), memo);
			sumList.add(sum);
		}
		return sumList;
	}

	public static Long traverseTreeWithMemoization(List<Integer> parent, int start, int jump, Long[][] memo) {
		if (memo[start][jump] != -1) {
			return memo[start][jump];
		}

		int cur = start;
		long sum = 0L;
		sum += start;
		int temp = jump;
		while (cur != -1 && jump > 0) {
			cur = parent.get(cur);
			--jump;
			if (jump == 0) {
				jump = temp;
				if (cur != -1) {
					sum += cur;
				}
			}
		}

		memo[start][temp] = sum;
		return sum;
	}
}
