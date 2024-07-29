package matrix;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentPrime {


	public static void main(String[] args) {
		MostFrequentPrime mfp = new MostFrequentPrime();
		int[][] mat = {
			{1, 1},
			{9, 9},
			{1, 1}
		};
		System.out.println(mfp.mostFrequentPrime(mat));
	}

	boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}
		int sqrtN = (int) Math.sqrt(n);
		for (int i = 2; i <= sqrtN; ++i) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public int mostFrequentPrime(int[][] mat) {
		// you can go to 8 directions then form a number each mat[i][j] is the digit then count the frequency of each number if it's prime > 10
		int m = mat.length;
		int n = mat[0].length;
		int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
		int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};
		Map<Integer, Integer> freq = new HashMap<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 8; k++) {
					int x = i;
					int y = j;
					int num = 0;
					while (x >= 0 && x < m && y >= 0 && y < n) {
						num = num * 10 + mat[x][y];
						if (num > 10 && isPrime(num)) {
							freq.put(num, freq.getOrDefault(num, 0) + 1);
						}
						x += dx[k];
						y += dy[k];
					}
				}
			}
		}

		int maxFreq = 0;
		int res = 0;
		for (int key : freq.keySet()) {
			if (freq.get(key) > maxFreq) {
				maxFreq = freq.get(key);
				res = key;
			} else if (freq.get(key) == maxFreq) {
				res = Math.max(res, key);
			}
		}
		return res;
	}

}
