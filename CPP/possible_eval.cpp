#include <iostream>
#include <vector>
using namespace std;

int eval(int a, char op, int b) {
  if (op == '*')
    return a * b;
  if (op == '-')
    return a - b;
  if (op == '+')
    return a + b;
}
vector<int> evaluateAll(string expr, int low, int high) {
  vector<int> res;
  if (low == high) {
    int val = expr[low] - '0';
    res.push_back(val);
    return res;
  }

  if (low == high - 2) {
    int val = eval(expr[low] - '0', expr[low + 1], expr[low + 2] - '0');
    res.push_back(val);
    return res;
  }

  for (int i = low + 1; i <= high; i = i + 2) {
    vector<int> l = evaluateAll(expr, low, i - 1);

    vector<int> r = evaluateAll(expr, i + 1, high);

    for (int s1 = 0; s1 < l.size(); s1++) {
      for (int s2 = 0; s2 < r.size(); s2++) {
        int val = eval(l[s1], expr[i], r[s2]);
        res.push_back(val);
      }
    }
  }
  return res;
}

int main() {
  string expr = "1*2+3*4";
  int len = expr.length();
  vector<int> ans = evaluateAll(expr, 0, len - 1);

  for (int i = 0; i < ans.size(); i++)
    cout << ans[i] << endl;

  return 0;
}
