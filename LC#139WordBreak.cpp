class Solution {
public:
    bool wordBreak(string s, vector<string>& dict) {
        int n = s.length();
        bool dp[n+1];
        memset(dp, false, sizeof(bool)*(n+1));
        
        dp[0] = true;
        
        // first DP
        
        for(int i=1;i<=n;++i) {
            for(int j=i-1;j>=0;j--) {
                if(dp[j]) {
                    string word = s.substr(j, i - j);
                    if(find(dict.begin(), dict.end(), word) != dict.end()) {
                        dp[i] = true;
                        break; // next i
                    }
                }
            }
        }
        return dp[n];
    }
};
