import java.util.HashSet;
import java.util.Set;

public class BasicSets {
	public static void main(String[] args) {
		// declare a set
		Set<String> hashSet = new HashSet<>();
		hashSet.add("Geeks");
		hashSet.add("For");
		hashSet.add("Geeks");
		hashSet.add("swapnil");


		// 2nd Geeks will not be printed as it was present already.
		System.out.println(hashSet);

		if (hashSet.contains("swapnil")) {
			System.out.println("This contains swapnil");
		} else {
			System.out.println("Does not contains swapnil.");
		}

	}
}
