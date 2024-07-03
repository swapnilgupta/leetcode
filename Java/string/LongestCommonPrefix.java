package string;

public class LongestCommonPrefix {

	public static final int ALPHABET_SIZE = 10;
	static TrieNode root;

	public static void insert(String key) {
		TrieNode pCrawl = root;

		for (int level = 0; level < key.length(); ++level) {
			int index = key.charAt(level) - '0';
			if (pCrawl.children[index] == null) {
				pCrawl.children[index] = new TrieNode();
			}
			pCrawl = pCrawl.children[index];
		}
		// mark the last node as leaf
		pCrawl.isEndOfWord = true;
	}

	// driver code for Input: arr1 = [1,10,100], arr2 = [1000]
	public static void main(String[] args) {
		LongestCommonPrefix lcp = new LongestCommonPrefix();
		int[] arr1 = {1, 2, 3};
		int[] arr2 = {4, 4, 4};
		System.out.println(lcp.longestCommonPrefix(arr1, arr2));
	}

	public int longestCommonPrefix(int[] arr1, int[] arr2) {
		int n = arr1.length;
		int m = arr2.length;
		int res = 0;
		// create a Trie from all the elements of arr2 and search for prefix of each
		// element of arr1 in the Trie
		// if found, update the result
		root = new TrieNode();
		for (int j : arr2) {
			insert(String.valueOf(j));
		}
		for (int i : arr1) {
			TrieNode pCrawl = root;
			String s = String.valueOf(i);
			for (int level = 0; level < s.length(); ++level) {
				int index = s.charAt(level) - '0';
				if (pCrawl.children[index] == null) {
					break;
				}
				res = Math.max(res, level + 1);
				pCrawl = pCrawl.children[index];
			}
		}

		return res;
	}

	static class TrieNode {

		TrieNode[] children;
		boolean isEndOfWord;

		public TrieNode() {
			children = new TrieNode[ALPHABET_SIZE];
			isEndOfWord = false;
		}
	}
}
