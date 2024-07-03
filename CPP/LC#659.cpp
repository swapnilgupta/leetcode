class Solution {
public:
  bool isPossible(vector<int> &nums) {
    unordered_map<int, int> count, tail;

    // Having the frequency in count vector
    for (int num : nums) {
      count[num]++;
    }

    for (int num : nums) {
      if (!count[num])
        continue;
      count[num]--;
      if (tail[num - 1] > 0) { // Extend the previously exsisting list
        tail[num - 1]--;
        tail[num]++;
      } else if (count[num + 1] &&
                 count[num + 2]) { // Create a new list of at least 3 elements
        count[num + 1]--;
        count[num + 2]--;
        tail[num + 2]++;
      } else
        return false; // Not possible to create any list then not possible to
                      // split
    }

    // If passed in every case then we can split array in consecutive
    // subsequences
    return true;
  }
};
