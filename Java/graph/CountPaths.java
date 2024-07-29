package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountPaths {

	private long mul(long a, long b) {
		return (a * b);
	}

	private class Pair {

		int first;
		int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	long countPaths(int n, int[][] edges) {
		// Prime n numbers in an Array store
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		for (int p = 2; p * p <= n; ++p) {
			if (prime[p]) {
				for (int i = p * p; i <= n; i += p) {
					prime[i] = false;
				}
			}
		}

		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n + 1; ++i) {
			adj.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		long[] r = {0};
		dfs(1, 0, adj, prime, r);
		return r[0];
	}

	private Pair dfs(int x, int f, List<List<Integer>> adj, boolean[] prime, long[] r) {
		Pair v = new Pair(!prime[x] ? 1 : 0, prime[x] ? 1 : 0);
		for (int y : adj.get(x)) {
			if (y == f) {
				continue;
			}
			Pair p = dfs(y, x, adj, prime, r);
			r[0] += mul(p.first, v.second) + mul(p.second, v.first);
			if (prime[x]) {
				v.second += p.first;
			} else {
				v.first += p.first;
				v.second += p.second;
			}
		}
		return v;
	}


	// driver code for above
	// for the following example n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
	// output should be 4
	public static void main(String[] args) {
		CountPaths cp = new CountPaths();
		int n = 5;
		int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {2, 5}};
		System.out.println(cp.countPaths(n, edges));

		// add example of n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
		n = 6;
		edges = new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 5}, {3, 6}};
		System.out.println(cp.countPaths(n, edges));
		// 5
		//[[1,5],[2,1],[4,5],[3,2]]
		n = 5;
		edges = new int[][]{{1, 5}, {2, 1}, {4, 5}, {3, 2}};
		System.out.println(cp.countPaths(n, edges));

	}

}
