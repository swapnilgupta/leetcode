import java.util.*;

class OrderedStream {
    protected Map<Integer, String> hashMap;

    public OrderedStream(int n) {
        hashMap = new HashMap<>();
    }

    public Map<Integer, String> getHashMap() {
        return hashMap;
    }

    public List<String> insert(int idKey, String value) {
        Map<Integer, String> hashMap = getHashMap();
        hashMap.put(idKey, value);
        List<String> orderedStream = hashMap.values().stream().toList();
        System.out.println(orderedStream);
        return orderedStream;
    }

    public int uniqueMorseRepresentations(String[] words) {
        String[] morseDict = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
                "..-", "...-", ".--", "-..-", "-.--", "--.."};
        int res = 0;
        Set<String> transformations = new HashSet<>();
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            String trans = "";
            for (int j = 0; j < word.length(); ++j) {
                int index = word.charAt(j) - 'a';
                trans += morseDict[index];
            }
            if(!trans.isBlank()) {
                transformations.add(trans);
            }
        }
        res = transformations.size();
        return res;
    }

    class Solution {
        public int uniqueMorseRepresentations(String[] words) {
            String[] arr = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..",
                    "--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

            HashSet<String> set = new HashSet<>();
            for (String word : words)
                set.add(transformMorse(word, arr));
            return set.size();
        }
        private String transformMorse(String word, String[] arr) {
            StringBuilder build = new StringBuilder();
            for (char c : word.toCharArray())
                build.append(arr[(int)c - 97]);
            return build.toString();
        }
    }

    public static void main(String[] args) {
        OrderedStream os = new OrderedStream(5);
        // [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
        os.insert(3, "cccccc");
        os.insert(1, "aaaaaa");
        os.insert(2, "bbbbbb");
        os.insert(5, "eeeeee");
        os.insert(4, "dddddd");
    }
}