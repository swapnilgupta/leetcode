class Solution {
public:
  int ceilIndex(vector<int> &v, int l, int r, int target) {
    while (r - l > 1) {
      int m = l + (r - l) / 2;
      if (v[m] >= target)
        r = m;
      else
        l = m;
    }
    return r;
  }

  int lengthOfLIS(vector<int> &nums) {
    int n = nums.size();
    if (n == 0 || n == 1)
      return n;
    // length of LIS initially is 1
    int length = 1;

    // initialize tail vector which will be keepking all the tail
    // for the current longest increasing subsequence
    vector<int> tail(n, 0);

    // initially tail element will be the first element of the numbers which
    // given in list

    tail[0] = nums[0];

    // incrementally building the longest increasing subsequence

    for (int i = 1; i < n; ++i) {

      // case1: where smallest element in current LIS is greater than current
      // element then start a new LIS
      if (tail[0] > nums[i])
        tail[0] = nums[i];

      // case2: if greater then greatest element in current LIS
      // add and extend
      else if (tail[length - 1] < nums[i])
        tail[length++] = nums[i];
      else
        // case3: above is when we need to insert in middle of current LIS
        tail[ceilIndex(tail, -1, length - 1, nums[i])] = nums[i];
    }
    return length;
  }
};
