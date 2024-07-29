package stack;

import java.util.Stack;

public class Asteroid {

	public int[] asteroidCollision(int[] A) {
		Stack<Integer> st = new Stack<>();
		int n = A.length;
		int j = n - 1;
		while (j >= 0) {
			if (st.isEmpty()) {
				st.push(A[j]);
				--j;
			} else {
				int top = st.peek();
				if (top < 0 && A[j] > 0) { // only way of collision
					if (-top == A[j]) {
						st.pop();
						j--;
					} else if (-top > A[j]) {
						j--; // discard
					} else {
						st.pop();
					}
				} else {
					st.push(A[j]);
					--j;
				}
			}
		}

		int sz = st.size();
		int[] ans = new int[sz];
		for (int i = 0; i < sz; ++i) {
			ans[i] = st.peek();
			st.pop();
		}
		return ans;
	}

	// driver code for above
	public static void main(String[] args) {
		Asteroid a = new Asteroid();
		int[] A = {-2, 2, -1, -2};
		int[] ans = a.asteroidCollision(A);
		for (int i = 0; i < ans.length; ++i) {
			System.out.print(ans[i] + " ");
		}
		System.out.println();
	}

}
