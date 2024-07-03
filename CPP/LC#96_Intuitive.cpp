class Solution {
public:
  int numTrees(int n) {
    // By using DP blindly :/
    vector<int> ans;
    ans.push_back(1);

    for (int i = 1; i <= n; ++i) {
      int t = 0;
      for (int j = 0; j < i; ++j) {
        t += ans[j] * ans[i - j - 1];
      }
      ans.push_back(t);
    }
    return ans[n];
  }
};
