import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args){
      // this is main function
        // creating an object of Set class
        // declaring an object of Integer type
        Set<Integer> a = new HashSet<>();

        a.addAll(Arrays.asList(new Integer[] { 1, 3, 2, 4, 8, 9, 0 }));

        // another set b
        Set<Integer> b = new HashSet<>();
        b.addAll(Arrays.asList(new Integer[] { 1, 3, 7, 5, 4, 0, 7, 5 }));

        // Union
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("Union of a and b: " + union);
        // Intersection
        Set<Integer> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        System.out.println("Intersection of a and b: " + intersection);
        // difference
        Set<Integer> difference = new HashSet<>(a);
        difference.removeAll(b);
        System.out.println("Difference of a and b: " + difference);

        // Iterating over the Set - a
        System.out.println("Iterating over an Set: ");
        for(Integer el : a) {
            System.out.println(el + " ");
        }
    }
}