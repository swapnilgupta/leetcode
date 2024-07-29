package string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LastNonEmptyString {

	public static void main(String[] args) {
		LastNonEmptyString lnes = new LastNonEmptyString();
		System.out.println(lnes.lastNonEmptyString("nnonxxnmsmmxxxj"));
		// "aabcbbca" driver code
		System.out.println(lnes.lastNonEmptyString("aabcbbca"));
		System.out.println(lnes.lastNonEmptyString("abcd"));
	}

	public String lastNonEmptyString(String s) {
		char[] arr = s.toCharArray();
		Map<Character, List<Integer>> map = new HashMap<>();
		int maxSZ = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], new ArrayList<>());
			}
			map.get(arr[i]).add(i);
			maxSZ = Math.max(maxSZ, map.get(arr[i]).size());
		}

		List<Pair> store = new ArrayList<>();
		for (char c : map.keySet()) {
			if (map.get(c).size() == maxSZ) {
				store.add(new Pair(c, map.get(c).get(maxSZ - 1)));
			}
		}

		store.sort(Comparator.comparingInt(a -> a.index));
		StringBuilder sb = new StringBuilder();
		for (Pair pair : store) {
			sb.append(pair.c);
		}

		return sb.toString();
	}

	static class Pair {

		char c;
		int index;

		Pair(char c, int index) {
			this.c = c;
			this.index = index;
		}
	}
}