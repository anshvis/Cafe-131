package foodManagement;

/**
 *  A class that represents a restaurant. This class facilitates orders being 
 *  placed, deliveries being made to the inventory, and entrees being added to 
 *  the menu.
 *  
 *  A Restaurant object has a name (String), a menu (SortedListOfImmutables) 
 *  which lists Entree objects, an inventory (SortedListOfImmutables) which 
 *  lists Food objects, and an amount of cash (int) on hand measured in pennies.
 *  
 *  @author Ansh Viswanathan
 */
public class Restaurant {

	private String name;
	private SortedListOfImmutables menu;       // A list of Entree objects	
	private SortedListOfImmutables inventory;  // A list of Food objects
	private int cash;

	/**
	 * Standard constructor.
	 * 
	 * @param nameIn name of the restaurant
	 * @param startingCash cash amount that the restaurant will have, measured
	 * in pennies
	 */
	public Restaurant(String nameIn, int startingCash) {
		this.name = nameIn;
		this.cash = startingCash;
		menu = new SortedListOfImmutables();
		inventory = new SortedListOfImmutables();
	}

	/**
	 * Getter for the name of the restaurant.
	 * 
	 * @postcondition
	 * @return reference to the name of the restaurant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the menu.
	 * 
	 * @postcondition
	 * @return a reference to a copy of the menu
	 */
	public SortedListOfImmutables getMenu() {
		SortedListOfImmutables newMenu = new SortedListOfImmutables(menu);
		return newMenu;
	}

	/**
	 * Adds an entree to the menu.
	 * 
	 * @postcondition
	 * @param entreeToAdd reference to the entree to be added to the menu
	 */
	public void addEntree(Entree entreeToAdd) {
		menu.add(entreeToAdd);
	}
	
	/**
	 * Getter for the inventory.
	 * 
	 * @postcondition
	 * @return a reference to a copy of the inventory
	 */
	public SortedListOfImmutables getInventory() {
		SortedListOfImmutables newInventory = new 
				SortedListOfImmutables(inventory);
		return newInventory;
	}

	/**
	 * Getter for the current amount of cash on hand
	 * 
	 * @postcondition
	 * @return the current amount of cash, measured in pennies
	 */
	public int getCash() {
		return cash;
	}

	/**
	 * Checks if the Food items contained in the specified Entree are 
	 * actually contained in the restaurant's inventory.
	 * 
	 * @param entree Entree that we are checking against the inventory
	 * 
	 * @postcondition
	 * @return true if the list of Food items contained in the Entree are
	 * all present in the inventory, false otherwise.
	 */
	public boolean checkIfInInventory(Entree entree) {
		if (inventory.checkAvailability(entree.getFoodList())) {
			return true;
		} else {
			return false;
		}
	}

	
	//FAILED TEST
	/**
	 * @precondition The total wholesale cost of all the food items combined
	 * does not exceed the amount of cash on hand. 
	 * 
	 * Adds the specified list of food items to the inventory, and the amount of
	 * cash on hand is reduced by the wholesale cost of the shipment.
	 * 
	 * @param list food items to be added to the inventory
	 * 
	 * @postcondition
	 * @return true if the food items are added; false if the food items are
	 * not added because their wholesale cost exceeds the current cash
	 * on hand
	 */
	public boolean addShipmentToInventory(SortedListOfImmutables list) {
		if (list.getWholesaleCost() <= cash) {
			inventory.add(list);
			cash -= list.getWholesaleCost();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes the food items contained in the specified Entree from the 
	 * inventory. If the inventory does not contain all of the items required 
	 * for this Entree, then nothing is removed from the inventory. If the 
	 * inventory contains all of the required items, then the amount of cash on 
	 * hand is increased by the retail value of the Entree.
	 *  
	 * @param entree Entree that has been ordered
	 * 
	 * @postcondition
	 * @return true if the food items are removed from the inventory; false
	 * if the food items were not removed because one or more required items
	 * were not contained in the inventory
	 */
	public boolean placeOrder(Entree entree) {
		if (this.checkIfInInventory(entree)) {
			cash += entree.getRetailValue();
			inventory.remove(entree.getFoodList());
			return true;
		} else {
			return false;
		}
	}

}
