package arrays;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // if starting from 'a' you are stuck at 'b' then you cannot pass through from
        int n = gas.length, i = 0;
        while(i < n) {
            int j = i;
            int g = gas[j];
            ++i;
            int start = j;
            while(j < n && g >= cost[j]) {
                g -= cost[j];
                if(g < 0) {
                    i = j + 1;
                    break;
                }
                j = (j + 1) % n;
                if(start == j) {
                    return start;
                }
                g += gas[j];
            }
        }
        return -1;
    }

    // driver code for above
    public static void main(String[] args) {
        GasStation obj = new GasStation();
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        int ans = obj.canCompleteCircuit(gas, cost);
        System.out.println(ans);
    }

}
