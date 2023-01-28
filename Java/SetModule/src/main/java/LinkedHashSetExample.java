import java.util.*;

public class LinkedHashSetExample {
    public static void main(String[] args){
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("India");
        linkedHashSet.add("Japan");
        linkedHashSet.add("Australia");

        System.out.println(linkedHashSet);

        linkedHashSet.remove("Japan");
        linkedHashSet.add("Japan");
        System.out.println(linkedHashSet);

        Iterator<String> i = linkedHashSet.iterator();
        System.out.println();
        while(i.hasNext()) {
            System.out.print(i.next() + " ");
        }
    }
}