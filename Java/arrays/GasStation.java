package arrays;

public class GasStation {

//    static int getCircuitCity(int n, int[] gas, int[] cost) {
//        // Code goes here —
//        int currRemaining = 0;
//        int totalRemaining = 0;
//        int start = 0;
//        for (int i = 0; i < n; i++) {
//            int remaining = gas[i] - cost[i];
//            if (currRemaining < 0) {
//                start = i;
//                currRemaining = remaining;
//            } else {
//                currRemaining += remaining;
//            }
//            totalRemaining += remaining;
//        }
//        if (totalRemaining < 0) {
//            return -1;
//        } else {
//            return start;
//        }
//    }

    static int getCircuitCity(int n, int[] gas, int[] cost) {
        // Code goes here —
        int currRemaining = 0;
        int totalRemaining = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int remaining = gas[i] - cost[i];
            if (currRemaining < 0) {
                start = i;
                currRemaining = remaining;
            } else {
                currRemaining += remaining;
            }
            totalRemaining += remaining;
        }
        if (totalRemaining < 0) {
            return -1;
        } else {
            return start;
        }
    }


    public static void main(String[] args) {
        int n = 5;
        int[] gas = {2, 3, 4, 5, 1};
        int[] cost = {4, 5, 1, 2, 3};

        // edge case where total gas is less than total cost
        // n = 5;
        // gas = new int[]{2, 3, 4, 5, 1};
        // cost = new int[]{9, 5, 1, 2, 3};
        int cityIndex = getCircuitCity(n, gas, cost);
        System.out.println(cityIndex);
    }
}

// driver code for ab