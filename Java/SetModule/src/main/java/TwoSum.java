import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TwoSum {

	TwoSum() {

	}

	public static int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		Map<Integer, Integer> hmap = new HashMap<>();
		for (int i = 0; i < nums.length; ++i) {
			int num = nums[i];
			if (hmap.containsKey(num)) {
				res[0] = hmap.get(num);
				res[1] = i;
				return res;
			}
			hmap.put(target - num, i);
		}
		return res;
	}

	public int distinctIntegers(int n) {
		int res = 0;

		return res;
	}

	public int power(int y, int n) {
		long mod = 1000000007;
		long x;
		x = y;
		long result = 1;
		while (n > 0) {
			if (n % 2 != 0) // y is odd
			{

				result = (result * x);
			}
			x = (x * x);
			n = n >> 1; // y=y/2;
		}
		return (int) (result % mod);
	}

	public int monkeyMove(int n) {

		return power(2, 500000003) - 2;
	}


}

class BestTeamScore {

	public HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Integer, Integer>> list =
			new LinkedList<Map.Entry<Integer, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1,
				Map.Entry<Integer, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
		for (Map.Entry<Integer, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public int bestTeamScore(int[] scores, int[] ages) {
		int ans = 0;
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < ages.length; ++i) {
			hashMap.put(i, ages[i]);
		}

		hashMap = sortByValue(hashMap);
		int max = 0;
		Iterator<Integer> iterator = hashMap.keySet().iterator();
		while (iterator.hasNext()) {
			int index = iterator.next();
			if (max <= scores[index]) {
				ans += scores[index];
				max = scores[index];
			}
		}
		return ans;
	}
}


class Main1 {

	class Interval {

		int start, end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

//    public HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hashMap) {
//        // copy to list
//        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(hashMap.entrySet());
//
//
//        // sort a list
//        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                return o1.getValue().compareTo(o2.getValue());
//            }
//        });
//        // copy back list to map
//        Iterator<Map.Entry<Integer, Integer>> iterator = list.iterator();
//        HashMap<Integer, Integer> sortedHashMap = new HashMap<>();
//        while (iterator.hasNext()) {
//            Map.Entry<Integer, Integer> entry = iterator.next();
//            sortedHashMap.put(entry.getKey(), entry.getValue());
//        }
//
//        return sortedHashMap;
//    }

	public HashMap<Interval, Integer> sortByValue(HashMap<Interval, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Interval, Integer>> list =
			new LinkedList<Map.Entry<Interval, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Interval, Integer>>() {
			public int compare(Map.Entry<Interval, Integer> o1,
				Map.Entry<Interval, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<Interval, Integer> temp = new LinkedHashMap<Interval, Integer>();
		for (Map.Entry<Interval, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
//    }

	}

	public int numJewelsInStones(String jewels, String stones) {
		HashMap<Character, Integer> jMap = new HashMap<>();
		char[] jc = jewels.toCharArray();
		char[] sc = stones.toCharArray();
		for (char j : jc) {
			jMap.put(j, 1);
		}
		int res = 0;
		for (char s : sc) {
			if (jMap.containsKey(s)) {
				++res;
			}
		}
		return res;
	}

	public int maxCount(int[] banned, int n, int maxSum) {
		int res = 0;
		Set<Integer> hashMap = new HashSet<>();

		for (int ban : banned) {
			hashMap.add(ban);
		}
		int sum = 0;
		for (int i = 1; i <= n; ++i) {
			if (!hashMap.contains(i)) {
				if ((i + sum) <= maxSum) {
					++res;
				}
			}
		}

		return res;
	}

	public int[] separateDigits(int[] nums) {
		int[] ans = new int[6000];
		ArrayList<Integer> res = new ArrayList<>();
		int n = nums.length;
		for (int i = n; i >= 0; ++i) {
			int num = nums[i];
			while (num > 0) {
				int d = num % 10;
				res.add(d);
				num = num / 10;
			}
		}
		n = res.size();
		int j = 0;
		for (int i = n - 1; i >= 0; ++i) {
			ans[j] = res.get(i);
		}
		return ans;
	}


	public static void main(String[] args) {
//        int[] nums = new int[]{2, 7, 11, 15};
//        int target = 9;
//        int[] ans = TwoSum.twoSum(nums, target);
//        System.out.println(ans[0] + " " + ans[1]);
//        TwoSum twoSum = new TwoSum();
//        System.out.println(twoSum.monkeyMove(55));

		BestTeamScore bts = new BestTeamScore();
		int[] ages = new int[]{1, 2, 3, 4, 5};
		int[] scores = new int[]{1, 3, 5, 10, 15};
		int teamScore = bts.bestTeamScore(scores, ages);
		System.out.println("Team Max Score: " + teamScore);

		ages = new int[]{8, 9, 10, 1};
		scores = new int[]{1, 2, 3, 5};
		teamScore = bts.bestTeamScore(scores, ages);
		System.out.println("Team Max Score: " + teamScore);

	}
}