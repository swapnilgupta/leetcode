// https://www.youtube.com/watch?v=BXCEFAzhxGY&ab_channel=BackToBackSWE
// https://www.youtube.com/watch?v=V5-7GzOfADQ&ab_channel=AbdulBari
public class KMP {
    private String text;
    private String pattern;
    private int[] lps;

    private void createLPSTable(String pattern) {
        int n = pattern.length();
        lps = new int[n];
        if (n < 2)
            return;
        int i = 0, j = 1;
        System.out.println("LPS: ");
        System.out.print(lps[0] + " ");
        while (j < n) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[j] = i + 1;
                i++;
            }
            System.out.print(lps[j] + " ");
            j++;
        }
        System.out.println();
    }

    public int findStr(String text, String pattern) {
        int M = pattern.length();
        int N = text.length();
        int index = -1;
        // Pre-processing step
        // The longest prefix which is also the suffix
        createLPSTable(pattern);

        // Applying the KMP algorithm
        int i = 0; // index for txt[]
        int j = 0;
        while (i < N) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern " + "at index " + (i - j));
                index = i - j;
                j = lps[j - 1]; // look for next match
            }

            // mismatch after j matches then lps[j - 1] knows where to start now
            else if (i < N && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        String text = "adsgwadsxdsgwadsgz";
        String pattern = "dsgwadsgz";
        int index = kmp.findStr(text, pattern);
        System.out.println("Last occurrence found at: " + index);
    }
}
