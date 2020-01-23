class Solution {
public:
    int integerBreak(int n) {
        // using DP
        vector<int> dp(n+1, 0);
        //dp[i] means output when input = i, e.g. dp[4] = 4 (2*2),dp[8] = 18 (2*2*3)...
        dp[1] = 0;
        dp[2] = 1;
        
        for(int i=3;i<=n;++i) {
            for(int j=1;j<=i/2;j++) {
               //let's say i = 8, we are trying to fill dp[8]:if 8 can only be broken into 2 
               // parts, the answer could be among 1 * 7, 2 * 6, 3 * 5, 4 * 4... but these
              //  numbers can be further broken. so we have to compare 1 with dp[1], 7 with
              //  dp[7], 2 with dp[2], 6 with dp[6]...etc
                dp[i] = max(dp[i], max(j*(i-j), j*dp[i-j]));
            }
        }
        return dp[n];
    }
};
