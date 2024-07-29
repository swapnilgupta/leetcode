package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class EvaluateDivision {

	/**
	 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries =
	 * [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]] Output:
	 * [6.00000,0.50000,-1.00000,1.00000,-1.00000]
	 */
	// driver code for the above calcDivision function
	public static void main(String[] args) {
		EvaluateDivision ed = new EvaluateDivision();

		// test with example mentioned in the code comment
		List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"),
			Arrays.asList("b", "c"));
		double[] values = {2.0, 3.0};
		List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"),
			Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));

		double[] result = ed.calcEquation(equations, values, queries);

		// printing the calculated result
		System.out.println(Arrays.toString(result));
	}

	public double[] calcEquation(List<List<String>> equations, double[] values,
		List<List<String>> queries) {
		Map<String, Map<String, Double>> graph = new HashMap<>();
		int n = values.length;

		double[] ans = new double[queries.size()];
		// construct the graph by using equations and values (weights)
		for (int i = 0; i < n; ++i) {
			String a = equations.get(i).get(0);
			String b = equations.get(i).get(1);
			double w = values[i];
			graph.putIfAbsent(a, new HashMap<>());
			graph.putIfAbsent(b, new HashMap<>());
			graph.get(a).put(b, w);
			graph.get(b).put(a, 1.0 / w);
		}

		for (int i = 0; i < queries.size(); ++i) {
			Set<String> seen = new HashSet<>();
			List<String> q = queries.get(i);
			String a = q.get(0);
			String c = q.get(1);

			if (!graph.containsKey(a) || !graph.containsKey(c)) {
				ans[i] = -1.0;
			}

			ans[i] = divide(a, c, seen, graph);
		}
		return ans;
	}

	private double divide(String a, String c, Set<String> seen,
		Map<String, Map<String, Double>> graph) {
		if (a.equals(c)) {
			return 1.0;
		}

		seen.add(a);

		for (String b : graph.get(a).keySet()) {
			if (seen.contains(b)) {
				continue;
			}
			final double res = divide(b, c, seen, graph);
			if (res > 0) {
				return 1.0 * graph.get(a).get(b);
			}
		}
		return -1.0; // invalid result
	}


}
