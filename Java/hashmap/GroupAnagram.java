package hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagram {

	public List<List<String>> groupAnagrams(String[] wordList) {
		// sort each string and put it in a hashmap
		HashMap<String, List<String>> map = new HashMap<>(); // sorted string -> list of strings
		for (String s : wordList) {
			char[] arr = s.toCharArray();
			Arrays.sort(arr);
			String sorted = new String(arr);
			if (!map.containsKey(sorted)) {
				map.put(sorted, new ArrayList<>());
			}
			map.get(sorted).add(s);
		}

		// add all the values in the hashmap to the answer
		return new ArrayList<>(map.values());
	}

	// driver code for the group Anagrams
	public static void main(String[] args) {
		String[] wordList = {"eat", "tea", "tan", "ate", "nat", "bat"};
		GroupAnagram ga = new GroupAnagram();
		System.out.println(ga.groupAnagrams(wordList));
	}

}
