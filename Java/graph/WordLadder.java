package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {

	public static boolean isEdge(String s, String t) {
		int count = 0;
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) != t.charAt(i)) {
				++count;
			}
			if (count > 1) {
				return false;
			}
		}
		return count == 1;
	}

	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		// create a graph's adjList
		// edge when there is only 1 letter is different
		boolean present = false;
		for (String word : wordList) {
			if (word.equals(endWord)) {
				present = true;
				break;
			}
		}
		if (!present) {
			return 0;
		}

		present = false;
		int idx = -1;
		for (String word : wordList) {
			++idx;
			if (word.equals(beginWord)) {
				present = true;
				break;
			}
		}

		if (!present) {
			wordList.add(beginWord);
		}
		int n = wordList.size();
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(n);

		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < n; ++i) {
			String s = wordList.get(i);
			for (int j = i; j < n; ++j) {
				String t = wordList.get(j);
				if (isEdge(s, t)) {
					// i -> j
					adjList.get(i).add(j);
					// j -> i
					adjList.get(j).add(i);
				}
			}
		}

		// BFS - starting node beginWord i.e. n - 1

		int start = present ? idx : n - 1;

		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		q.add(start);
		visited[start] = true;

		int steps = 1;
		while (!q.isEmpty()) {
			int sz = q.size();
			for (int i = 0; i < sz; ++i) {
				try {
					Integer temp = q.poll();
					int curr = temp == null ? -1 : temp;
					if (endWord.equals(wordList.get(curr))) {
						return steps;
					}
					ArrayList<Integer> neighbors = adjList.get(curr);
					for (int v : neighbors) {
						if (!visited[v]) {
							q.add(v);
							visited[v] = true;
						}
					}
				} catch (NullPointerException e) {
					System.out.println("Failed to poll: " + e);
				}

			}
			++steps;
		}
		return 0;
	}

	// write a driver code for above word ladder
	// ["hot","dot","dog","lot","log","cog"] = wordList
	// beginWord = "hit"
	// endWord = "cog"
	// driver code ["a","b","c"]
	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");

		int ans = ladderLength(beginWord, endWord, wordList);
		System.out.println(ans);
	}

}
