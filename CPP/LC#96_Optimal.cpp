class Solution {
public:
  // By using DP + DFS

  map<int, int> M;

  int dfs(int n) {
    if (n == 0 || n == 1)
      return 1;

    if (M.find(n) != M.end())
      return M[n];
    int ans = 0;
    for (int i = 0; i < n; ++i) {
      ans += dfs(i) * dfs(n - i - 1);
    }
    M[n] = ans;
    return ans;
  }

  int numTrees(int n) {
    M[0] = M[1] = 1;
    if (n == 0)
      return 0;
    return dfs(n);
  }
};
