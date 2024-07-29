package trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {

	public static final int ALPHABET_SIZE = 26;
	static TrieNode root;

	static class TrieNode {

		TrieNode[] children;
		boolean isEndOfWord; // Integer isEndOfWord is used to mark the end of the word

		public TrieNode() {
			children = new TrieNode[ALPHABET_SIZE];
			isEndOfWord = false;
		}
	}

	Trie() {
		root = new TrieNode();
	}

	public static void insert(String key) {
		TrieNode pCrawl = root;

		for (int level = 0; level < key.length(); ++level) {
			int index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null) {
				pCrawl.children[index] = new TrieNode();
			}
			pCrawl = pCrawl.children[index];
		}
		// mark the last node as leaf
		pCrawl.isEndOfWord = true;
	}

	public static List<String> searchSuggestions(String prefix) {
		TrieNode pCrawl = root;
		List<String> suggestions = new ArrayList<>();
		for (int level = 0; level < prefix.length(); ++level) {
			int index = prefix.charAt(level) - 'a';
			if (pCrawl.children[index] == null) {
				return suggestions; // no words with this prefix
			}
			pCrawl = pCrawl.children[index];
		}

		// now pCrawl points to the last node of the prefix
		// find all the words with this prefix
		findWords(pCrawl, new StringBuilder(prefix), suggestions);
		return suggestions;
	}

	private static void findWords(TrieNode pCrawl, StringBuilder prefix, List<String> suggestions) {
		if (suggestions.size() == 3) {
			return;
		}
		if (pCrawl.isEndOfWord) {
			suggestions.add(prefix.toString());
		}

		for (int i = 0; i < ALPHABET_SIZE; i++) {
			if (pCrawl.children[i] != null) {
				// using StringBuilder is faster than String concatenation
				// this approach is similar to backtracking
				findWords(pCrawl.children[i], prefix.append((char) ('a' + i)), suggestions);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
	}

	public static boolean search(String key) {
		TrieNode pCrawl = root;

		for (int level = 0; level < key.length(); ++level) {
			int index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null) {
				return false;
			}
			pCrawl = pCrawl.children[index];
		}

		return pCrawl.isEndOfWord;
	}

	// Driver
	public static void main(String[] args) {
		// Input keys (use only 'a' through 'z' and lower case)
		String[] keys = {"the", "a", "there", "answer", "any",
			"by", "bye", "their"};

		String[] output = {"Not present in trie", "Present in trie"};

		root = new TrieNode();

		// Construct trie
		int i;
		for (i = 0; i < keys.length; i++) {
			insert(keys[i]);
		}

		// Search for different keys
		if (search("the")) {
			System.out.println("the --- " + output[1]);
		} else {
			System.out.println("the --- " + output[0]);
		}

		if (search("these")) {
			System.out.println("these --- " + output[1]);
		} else {
			System.out.println("these --- " + output[0]);
		}

		if (search("their")) {
			System.out.println("their --- " + output[1]);
		} else {
			System.out.println("their --- " + output[0]);
		}

		if (search("thaw")) {
			System.out.println("thaw --- " + output[1]);
		} else {
			System.out.println("thaw --- " + output[0]);
		}

	}
}
