package hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromicArray {

	private static final Map<List<String>, Boolean> memo = new HashMap<>();

	public static boolean isPalindrome(String[] arr) {
		return isPalindrome(Arrays.asList(arr));
	}

	public static boolean isPalindrome(List<String> words) {
		if (words.isEmpty() || words.size() == 1) {
			return true;
		}
		if (memo.containsKey(words)) {
			System.out.println("memo hit");
			return memo.get(words);
		}
		if (words.size() == 2) {
			return words.get(0).equals(words.get(1)) ||
				words.get(0).substring(0, words.get(0).length() - 1)
					.equals(words.get(0).substring(words.get(0).length() - 1) + words.get(1)) ||
				(words.get(0) + words.get(1).substring(0, 1)).equals(words.get(1).substring(1));

		}
		boolean result = false;
		String first = words.get(0);
		String last = words.get(words.size() - 1);
		String second = words.get(1);
		String secondLast = words.get(words.size() - 2);
		int wSize = words.size();

		if (first.equals(last)) {
			List<String> filtered = copy(words, 1, wSize - 1);
			result = result || isPalindrome(filtered);
		}

		String firstShort = first.substring(0, first.length() - 1);
		String secondLong = first.charAt(first.length() - 1) + second;
		String firstLong = first + second.charAt(second.length() - 1);
		String secondShort = second.substring(1);
		String lastShort = last.substring(1);
		String secondLastLong = secondLast + last.charAt(0);
		String lastLong = secondLast.substring(secondLast.length() - 1) + last;
		String secondLastShort = secondLast.substring(0, secondLast.length() - 1);

		if (firstShort.equals(last)) {
			List<String> filtered = copy(words, 1, wSize - 1);
			filtered.add(0, secondLong);
			result = result || isPalindrome(filtered);
		}

		if (firstShort.equals(lastShort)) {
			List<String> filtered = copy(words, 2, wSize - 2);
			filtered.add(0, secondLong);
			filtered.add(secondLastLong);
			result = result || isPalindrome(filtered);
		}

		if (firstShort.equals(lastLong)) {
			List<String> filtered = copy(words, 2, wSize - 2);
			filtered.add(0, secondLong);
			filtered.add(secondLastShort);
			result = result || isPalindrome(filtered);
		}

		if (first.equals(lastShort)) {
			List<String> filtered = copy(words, 1, wSize - 2);
			filtered.add(secondLastLong);
			result = result || isPalindrome(filtered);
		}

		if (first.equals(lastLong)) {
			List<String> filtered = copy(words, 1, wSize - 2);
			filtered.add(secondLastShort);
			result = result || isPalindrome(filtered);
		}

		if (firstLong.equals(last)) {
			List<String> filtered = copy(words, 2, wSize - 1);
			filtered.add(0, secondShort);
			result = result || isPalindrome(filtered);
		}

		if (firstLong.equals(lastShort)) {
			List<String> filtered = copy(words, 2, wSize - 2);
			filtered.add(0, secondShort);
			filtered.add(secondLastLong);
			result = result || isPalindrome(filtered);
		}

		if (firstLong.equals(lastLong)) {
			List<String> filtered = copy(words, 2, wSize - 2);
			filtered.add(0, secondShort);
			filtered.add(secondLastShort);
			result = result || isPalindrome(filtered);
		}

		memo.put(words, result);
		return result;
	}

	public static List<String> copy(List<String> elements, int start, int end) {
		List<String> result = new ArrayList<String>();
		for (int i = start; i < end; i++) {
			result.add(elements.get(i));
		}
		return result;
	}


	public static void main(String[] args) {
		String[] arr1 = {"aa", "bab", "cde", "aba", "ab"};
		System.out.println(isPalindrome(arr1));  // Output: true

		String[] arr2 = {"palindrome"};
		System.out.println(isPalindrome(arr2));  // Output: true

		String[] arr3 = {"aaaaaa", "aa"};
		System.out.println(isPalindrome(arr3));  // Output: false
	}
}