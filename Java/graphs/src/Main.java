import java.util.*;

class Solution {

    public int[] dx = {1, -1, 0, 0};
    public int[] dy = {0, 0, 1, -1};

    boolean isValid(int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }

    public void bfs(Queue<Integer[]> q, boolean[][] visited, int m, int n, int[][] A) {
        while (!q.isEmpty()) {
            // get both new x and y coordinates
            int x = q.peek()[0];
            int y = q.peek()[1];
            q.poll();

            // explore 4 directions
            for (int i = 0; i < 4; ++i) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (!isValid(newX, newY, m, n) || visited[newX][newY])
                    continue;

                // lake flow from lower to higher heights according to question
                if (A[x][y] <= A[newX][newY]) {
                    q.add(new Integer[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
    }

    public ArrayList<ArrayList<Integer>> anagrams(final List<String> a) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < a.size(); ++i) {
            char[] c = a.get(i).toCharArray();
            Arrays.sort(c);
            String t = String.valueOf(c);
            if (map.get(t) == null) {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(i + 1);
                map.put(t, l);
            } else {
                map.get(t).add(i + 1);
            }
        }

        return new ArrayList<>(map.values());
    }

    public int solve(int[][] A) {
        int ans = 0;
        int m = A.length;
        if (m == 0) return 0;
        int n = A[0].length;
        boolean[][] blue_visited = new boolean[m][n];
        boolean[][] red_visited = new boolean[m][n];
        Queue<Integer[]> qBlue = new LinkedList<>();
        Queue<Integer[]> qRed = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    qBlue.add(new Integer[]{i, j});
                    blue_visited[i][j] = true;
                }
                if (i == m - 1 || j == n - 1) {
                    qRed.add(new Integer[]{i, j});
                    red_visited[i][j] = true;
                }
            }
        }


        bfs(qBlue, blue_visited, m, n, A);
        bfs(qRed, red_visited, m, n, A);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (blue_visited[i][j] && red_visited[i][j]) {
                    ++ans;
                }
            }
        }

        return ans;
    }

    public int solve(int A, int[][] B) {
        HashMap<Integer, LinkedList<Integer>> adj = new HashMap<>();
        // Creating adj list
        for (int i = 1; i <= A; ++i) {
            // initialize the adj list
            /*
            1 -> 2, 3
            2 -> 4
            3 -> 4
            4 -> 1
            5 -> 2

            */
            adj.put(i, new LinkedList<>());
        }

        for (int[] edge : B) {
            // adding edge from B[0] --> B[1]
            adj.get(B[0]).add(edge[1]);
        }

//        Stack<Integer> stack = new Stack<>();
//        boolean[] vis = new boolean[A + 1];
//        // As we need to start from 1
//        stack.push(1);
//
//        while(!stack.isEmpty()) {
//            int u = stack.pop();
//            vis[u] = true;
//
//            // Check if we have discovered A
//            if(u == A) return 1;
//
//            for(int v : adj.get(u)) {
//                if(!vis[v]) {
//                    stack.push(v);
//                }
//            }
//        }

        // BFS approach
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[A + 1];

        q.add(1);
        vis[1] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (v == A) return 1;
                if (!vis[v]) {
                    q.add(v);
                    vis[v] = true;
                }
            }
        }
        return 0;
    }

//    boolean isValid(int i, int j, int m, int n) {
//        return i >= 0 && j >= 0 && i < m && j < n;
//    }

    int dfs(int[][] grid, boolean[][] vis, int i, int j, int m, int n) {
        if (!isValid(i, j, m, n) || vis[i][j] || grid[i][j] == 0) return 0;
        vis[i][j] = true;
        return 1 + dfs(grid, vis, i + 1, j, m, n) +
                dfs(grid, vis, i, j + 1, m, n) +
                dfs(grid, vis, i - 1, j, m, n) +
                dfs(grid, vis, i, j - 1, m, n);
    }

    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!vis[i][j] && grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(grid, vis, i, j, m, n));
                }
            }
        }
        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m * n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int curr = matrix[mid / n][mid % n];
            if (curr == target) return true;
            else if (curr < target) {
                // go right
                lo = mid + 1;
            } else {
                // go left
                hi = mid - 1;
            }
        }
        return false;
    }

    public int divide(int dividendInt, int divisorInt) {
        long dividend = dividendInt;
        long divisor = divisorInt;
        long quotient = 0, temp = 0;
        long sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

        // Update both divisor and
        // dividend positive
        dividend = (int) Math.abs(dividend);
        divisor = (int) Math.abs(divisor);
        for (int i = 31; i >= 0; --i) {
            if (divisor << i < dividend - temp) {
                temp += divisor << i; // current multiple value which is in range
                quotient = quotient | 1L << i;
            }
        }
        if (sign == -1) quotient = -quotient;
        return (int) quotient;
    }

    public int solve1(int A, int[][] B) {
        int ans = 0;
        HashMap<Integer, LinkedList<Integer>> adj = new HashMap<>();
        // Creating Adjacency list by using HashMap and LinkedList implementation
        for (int i = 1; i <= A; ++i) {
            // Initializing all the nodes with empty list
            adj.put(i, new LinkedList<>());
        }
        for (int[] edge : B) {
            // adding edge from B[0] ---> B[1]
            adj.get(edge[0]).add(edge[1]);
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] vis = new boolean[A + 1];
        stack.push(1);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            vis[u] = true;
            if (u == A) return 1;
            for (int v : adj.get(u)) {
                if (!vis[v]) {
                    stack.push(v);
                }
            }
        }
        return 0;
    }



    public int totalFruit(int[] tree) {
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0, j;
        for (j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            if (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                count.remove(tree[i++], 0);
            }
        }
        return j - i;
    }

    public int maxSatisfaction(int[] satisfaction) {
        int sum = 0;
        Arrays.sort(satisfaction);

        int n = satisfaction.length;
        int[] dp = new int[n];

        int x = 0;
        int count = 0;
        for(int i = n - 1; i > 0; --i) {
            x += satisfaction[i];
            dp[i] = x;
            ++count;
            if(dp[i] < 0) {
                --count;
                break;
            }
        }

        int k = n - 1;
        while (count-- > 0) {
            sum += satisfaction[k--] * count;
        }
        return sum;
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1; i <= n; ++i) {
            int max = arr[i - 1];
            for(int j = i; j > 0 && j > (i - k); --j) {
                max = Math.max(max, arr[j - 1]);
                dp[i] = Math.max(dp[i], dp[j - 1] + max * (i - j + 1));
            }
        }
        return dp[n - 1];
    }

    void reverse(int[] nums, int i, int j) {
        while(i < j) {
            // how many ways to swap two numbers
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            ++i;
            --j;
        }
    }
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, n - k - 1, n - 1);
        reverse(nums, 0, n - 1 - k);
        reverse(nums, 0, n - 1);
    }

    public List<String> generateParenthesis(int n) {
        if(n == 0) {
            return new ArrayList<>(List.of(""));
        }
        List<String> ans = new ArrayList<>();
        // Divide and conquer algorithm
        for (int leftCount = 0; leftCount < n; ++leftCount)
            // leftString is PART-1 (DIVIDE STEP)
            for (String leftString: generateParenthesis(leftCount))
                // rightString is PART-2 (DIVIDE STEP)
                for (String rightString: generateParenthesis(n - 1 - leftCount))
                    // Joining PART-1 & PART-2 (Conquer Step)
                    ans.add("(" + leftString + ")" + rightString);
        return ans;
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int[] flight : flights) {
            adj.computeIfAbsent(flight[0], value -> new ArrayList<>()).add(new int[] {flight[1], flight[2]});
        }
        // create dist array with initialized with zero
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {src, 0});

        while (!queue.isEmpty() && k > 0) {
            k--;
            int sz = queue.size();
            for(int i = 0; i < sz; ++i) {
                int[] u = queue.poll();
                int node = u[0];
                int distance = dist[node];
                for(int[] v : adj.get(u[0])) {
                    int neighbor = v[0];
                    int price = v[1];
                    if(dist[neighbor] > distance + price) {
                        dist[neighbor] = distance + price;
                        queue.offer(new int[] {neighbor, price});
                    }
                }
            }

        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

}

public class Main {


    static public void main(String[] args) {
        Solution sol = new Solution();
        sol.maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3);
    }

//    public static void main(String[] args) {
////        int[][] A = new int[][]{
////                {1, 2, 2, 3, 5},
////                {3, 2, 3, 4, 4},
////                {2, 4, 5, 3, 1},
////                {6, 7, 1, 4, 5},
////                {5, 1, 1, 2, 4}
////        };
////
////        int[][] B = new int[][]{
////                {2, 2},
////                {2, 2}
////        };
////        Solution sol = new Solution();
////        int ans = sol.solve(A);
////        System.out.println("Ans: " + ans);
////
////        ans = sol.solve(B);
////        System.out.println("Ans: " + ans);
//
////        Scanner sc = new Scanner(System.in);
////
////        String p = sc.next();
////        int totalLength = p.length();
////
////        String q = sc.next();
////        totalLength += q.length();
////
////        System.out.println(totalLength);
////        if(q.compareTo(p) > 0) {
////            System.out.println("No");
////        } else {
////            System.out.println("Yes");
////        }
////
//        Solution sol = new Solution();
//        int[] nums = new int[]{3,4,-1};
//        sol.countOperationsToEmptyArray(nums);
////        int quo = sol.divide(-2147483648
////                , -1);
////        System.out.println("Quotient: " + quo);
////        System.out.print(p.toUpperCase() + " " + q.toUpperCase());
//    }

}