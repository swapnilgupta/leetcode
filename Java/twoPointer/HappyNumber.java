package twoPointer;

public class HappyNumber {

	int nextNumber(int n) {
		int newNumber = 0;
		while (n != 0) {
			int d = n % 10; // remainder -> digit
			newNumber += (d * d); // digit square sum added
			n /= 10; // update n
		}
		return newNumber;
	}

	public boolean isHappy(int n) {
		// Math + HashSet
		// Create a set to keep track of the numbers that have already been seen
		int s = n, f = n;
		s = nextNumber(s);
		f = nextNumber(nextNumber(f));
		while (s != 1 && s != f) {
			s = nextNumber(s);
			f = nextNumber(nextNumber(f));
		}
		return s == 1;
	}

	// driver code for above
	public static void main(String[] args) {
		HappyNumber obj = new HappyNumber();
		System.out.println(obj.isHappy(19));
	}

}
