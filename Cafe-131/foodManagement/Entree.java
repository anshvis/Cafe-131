package foodManagement;


/**
 * An immutable class that represents the ingredients and name of a dish of a
 * "entree" on a menu.
 * 
 * This class implements the Listable interface. Each Entree object has a
 * food list (SortedListOfImmutables), as well as a name (String). 
 * 
 * @author Ansh Viswanathan
 */
public class Entree implements Listable {

	private String name;
	private SortedListOfImmutables foodList; // will contain Food objects
	
	/**
	 * Standard constructor.  
	 * 
	 * @param nameIn desired name for this Entree
	 * @param foodListIn desired list of Food for this Entree
	 */
	public Entree(String nameIn, SortedListOfImmutables foodListIn) {
		this.name = nameIn;
		this.foodList = new SortedListOfImmutables(foodListIn);
	}
	
	/**
	 * Getter for name of Entree
	 * 
	 * @postcondition
	 * @return reference to the name of Entree
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *  Getter for FoodList for this entree.
	 *  
	 *  @postcondition
	 *  @return reference to a copy of the FoodList for this entree
	 */
	public SortedListOfImmutables getFoodList() {
		return new SortedListOfImmutables(foodList);
	}
	
	/**
	 * Returns the wholesale cost of the food in this entree
	 * 
	 * @postcondition
	 * @return wholesale cost of the food in this entree
	 */
	public int getWholesaleCost() {
		return foodList.getWholesaleCost();
	}
	
	/**
	 * Returns the retail value of the food in this entree
	 * 
	 * @postcondition
	 * @return retail value of the food in this entree
	 */
	public int getRetailValue() {
		return foodList.getRetailValue();
	}
	
	/**
	 * Compares the current object to the parameter and returns true if they
	 * have the same name.
	 * 
	 * @param other Entree to be compared to the current object
	 * 
	 * @postcondition
	 * @return true if the current object and the parameter have the same name, 
	 * false otherwise
	 */
	public boolean equals(Entree other) {
		if (this.name.equals(other.name)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the String value of an Entree object
	 * 
	 * @return a String representing an entree
	 */
	public String toString() {
		String retValue = "< ";
		for (int i = 0; i < foodList.getSize(); i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += foodList.get(i);
		}
		retValue += " >";
		return retValue;
	}
}
