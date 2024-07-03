import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
	public static void main(String[] args) {
		Set<String> treeSet = new TreeSet<>();
		treeSet.add("India");
		treeSet.add("Australia");
		treeSet.add("South Africa");

		System.out.println(treeSet);

		treeSet.remove("Australia");
		System.out.println(treeSet);
	}
}