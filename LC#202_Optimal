class Solution {
public:
    int digitSquareSum(int n) {
        int sum = 0;
        while(n > 0) {
            int x = n % 10;
            sum += x*x;
            n = n/10;
        }
        return sum;
    }
    
    bool isHappy(int n) {
    // Floyd Cycle detection algorithm
        int slow, fast;
        slow = fast = n;
        
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while(slow != fast);
        
        if(slow == 1) return true;
        return false;
    }
};
