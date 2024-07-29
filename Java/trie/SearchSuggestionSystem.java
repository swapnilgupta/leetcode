package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import trie.Trie.TrieNode;

public class SearchSuggestionSystem {

	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		Arrays.sort(products);
		TrieNode root = new TrieNode();
		Trie trie = new Trie();
		for (String product : products) {
			Trie.insert(product);
		}

		List<List<String>> result = new ArrayList<>();
		for (int i = 0; i < searchWord.length(); i++) {
			String prefix = searchWord.substring(0, i + 1);
			List<String> suggestions = Trie.searchSuggestions(prefix);
			List<String> top3 = new ArrayList<>();
			for (int j = 0; j < 3 && j < suggestions.size(); j++) {
				top3.add(suggestions.get(j));
			}
			result.add(top3);
		}
		return result;
	}

	public static void main(String[] args) {
		String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
		String searchWord = "mouse";
		SearchSuggestionSystem obj = new SearchSuggestionSystem();
		List<List<String>> result = obj.suggestedProducts(products, searchWord);
		System.out.println(result);
	}
}
