import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 */

/**
 * @author Mariah
 *
 */
public class MakeAShoppingList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		boolean listContinue;
		List<String> items = new ArrayList<>();
		List<Double> prices = new ArrayList<>();

		System.out.println("Welcome to Guenther's Market!");

		Hashtable<String, Double> groceryMenu = hashTableForMenu();

		boolean onList = false;

		do {
			do {
				// displays menu of 8 item names and prices
				groceryOptionsTable(groceryMenu);

				// ask user to enter an item name
				System.out.print("What item would you like to order? ");
				String addToList = userInput.nextLine().toLowerCase();

				// method to check if item is on menu
				if (groceryMenu.containsKey(addToList)) {
					// put method for adding item to list
					groceryOrder(groceryMenu, addToList, items, prices);
					// since item exists, print item name and price on console
					System.out.println("Item: " + addToList + "\tPrice: " + groceryMenu.get(addToList));
					onList = true;
				} else {
					// will simply loop to what to order, not ask about ordering anything else
					System.out.println("Sorry, we don't have those. Please try again.");
					onList = false;
				}
			} while (!onList);

			// ask user if they'd like to add more to list
			System.out.print("Would you like to order anything else (y/n)? ");
			listContinue = userInput.nextLine().toLowerCase().startsWith("y");
		} while (listContinue);

		// print completed order
		printFinalOrder(items, prices);

		// calculates average price
		Double avgPrice = getAverageItemPrice(prices);

		System.out.println("The average price of your items was: " + avgPrice);

		// prints the most expensive item purchase
		getIndexExpensiveItem(prices, items);
		getIndexCheapestItem(prices);

	}

	// method to create Hashtable for recall
	public static Hashtable<String, Double> hashTableForMenu() {
		Hashtable<String, Double> groceryOptions = new Hashtable<>();

		groceryOptions.put("bland oatmeal", 1.49);
		groceryOptions.put("winking owl wine", 2.89);
		groceryOptions.put("petoskey stone", 40.00);
		groceryOptions.put("beer 4pk", 15.00);
		groceryOptions.put("canned tamales", 10.00);
		groceryOptions.put("bagged milk", 5.00);
		groceryOptions.put("skinless avocados", 0.50);
		groceryOptions.put("seedless pumpkin", 25.00);

		return groceryOptions;
	}

	// TODO method for fancy table
	public static void groceryOptionsTable(Hashtable<String, Double> groceryList) {
		Hashtable<String, Double> groceryOptions = hashTableForMenu();

		System.out.println("\nItem\t\t\t\tPrice\t");
		System.out.println("==========================================");
		Set<String> groceryLists = groceryOptions.keySet();

		for (String groceryItem : groceryLists) {
			System.out.println(groceryItem + "\t\t\t$" + groceryOptions.get(groceryItem));
		}
	}

	// create a list based on user's input
	public static void groceryOrder(Hashtable<String, Double> groceryOptions, String addToList, List<String> items,
			List<Double> prices) {

		// get price values from the Menu and add to the new arraylist
		groceryOptions = hashTableForMenu();
		prices.add(groceryOptions.get(addToList));

		// put new variable into the list of the grocery order and prices
		items.add(addToList);

	}

	public static Double getAverageItemPrice(List<Double> prices) {

		double sum = 0;
		for (int i = 0; i < prices.size(); i++) {
			sum = sum + prices.get(i);
		}
		Double avgPrice = sum / prices.size();
		return avgPrice;
	}

	public static void printFinalOrder(List<String> items, List<Double> prices) {
		for (int i = 0; i < items.size(); i++) {
			System.out.println("ITEM: " + items.get(i) + "\t\tPRICE: " + prices.get(i));
		}
	}

	// reads the price of the most expensive item
	public static void getIndexExpensiveItem(List<Double> prices, List<String> items) {

		System.out.println("Your most expensive item today was: $" + Collections.max(prices));

	}

	// reads the price of the cheapest purchase
	public static void getIndexCheapestItem(List<Double> prices) {
		System.out.println("Your cheapest item today was: $" + Collections.min(prices));
	}

}
