package dp;

import java.util.*;
public class WordBreak {
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String > words = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] seen = new boolean[s.length() + 1];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if(start == s.length()) return true;

            for(int end = start + 1; end <= s.length(); end++) {
                if(seen[end]) continue;
                if(words.contains(s.substring(start, end))) {
                    queue.add(end);
                    seen[end] = true;
                }
            }
        }
        return false;
    }

    // add a driver code for the above functions
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>(Arrays.asList("leet", "code"));
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreakBFS(s, wordDict));
    }

}
