package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShoppingOptimization {

	public static void main(String[] args) {
		Map<String, Set<String>> stores = new HashMap<>();
		stores.put("Store A", new HashSet<>(Arrays.asList("Item 1", "Item 2")));
		stores.put("Store B", new HashSet<>(Arrays.asList("Item 2", "Item 3")));
		stores.put("Store C", new HashSet<>(Arrays.asList("Item 1", "Item 3", "Item 4")));
		stores.put("Store D", new HashSet<>(Arrays.asList("Item 4", "Item 5")));

		Set<String> requiredItems = new HashSet<>(
			Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4"));

		List<String> result = findMinimumStores(stores, requiredItems);
		System.out.println("Visit the following stores: " + result);
	}

	public static List<String> findMinimumStores(Map<String, Set<String>> stores,
		Set<String> requiredItems) {
		List<String> selectedStores = new ArrayList<>();
		Set<String> itemsCovered = new HashSet<>();

		while (!itemsCovered.equals(requiredItems)) {
			String bestStore = null;
			Set<String> itemsCoveredByBest = new HashSet<>();

			for (Map.Entry<String, Set<String>> entry : stores.entrySet()) {
				Set<String> uncoveredItems = new HashSet<>(requiredItems);
				uncoveredItems.removeAll(itemsCovered);
				Set<String> coveredByStore = new HashSet<>(entry.getValue());
				coveredByStore.retainAll(uncoveredItems);

				if (coveredByStore.size() > itemsCoveredByBest.size()) {
					bestStore = entry.getKey();
					itemsCoveredByBest = coveredByStore;
				}
			}

			if (bestStore != null) {
				selectedStores.add(bestStore);
				itemsCovered.addAll(itemsCoveredByBest);
			}
		}

		return selectedStores;
	}
}
