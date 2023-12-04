package trie;

public class Trie {

    public static final int ALPHABET_SIZE = 26;
    static TrieNode root;

    static class TrieNode {

        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
            isEndOfWord = false;
        }
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
