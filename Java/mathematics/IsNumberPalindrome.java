package mathematics;

public class IsNumberPalindrome {
	public boolean isPalindrome(int x) {
		int y = 0, z = x;
		if (x < 0) return false;
		while (x > 0) {
			int rem = x % 10;
			x /= 10;
			y *= 10;
			y += rem;
		}
		return z == y;
	}

	// write driver code for above
	public static void main(String[] args) {
		IsNumberPalindrome inp = new IsNumberPalindrome();
		int x = 121;
		System.out.println(inp.isPalindrome(x));
	}

}
