package foodManagement;

import java.util.Arrays;
/**
 * A mutable class that represents a sorted collection of immutable objects that
 * all implement the Listable interface (such as the Food and Entree classes). 
 * 
 * A SortedListOfImmutables contains an internal array of references to Listable
 * objects that is used to represent the sorted list. Items in this list are 
 * kept in alphabetical order based on the names of the items.
 * 
 * @author Ansh Viswanathan
 */
public class SortedListOfImmutables{

	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.
	 */
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  Note that the copied list is a new object, so future changes to either 
	 *  of the two lists will not affect the other.
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		this.items = new Listable[other.items.length];
		for (int index = 0; index<items.length; index++) {
			this.items[index] = other.items[index];
		}
	}

	/**
	 * Returns the number of items in the list.
	 * 
	 * @postcondition
	 * @return number of items in the list
	 */
	public int getSize() {
		return items.length;
	}
	
	/**
	 * Returns a reference to the item in the ith position in the list.
	 * 
	 * Note that Indexing is 0-based, so the first element is element 0.
	 * 
	 * @param i index of item requested
	 * 
	 * @postcondition
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {
		return items[i];
	}
	
	/**
	 * @precondition This method assumes that the current list is already sorted
	 * in alphabetical order based on the names of the items in the list.
	 * 
	 * Adds an itemToAdd to the list. 
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 * 
	 * @postcondition itemToAdd will be inserted into the list in the 
	 * appropriate place so that the list will remain alphabetized by names, 
	 * with the list size being additionally larger to accommodate itemToAdd.
	 */
	public void add(Listable itemToAdd) {
		Listable[] newArr = new Listable[items.length+1];
		if (items.length == 0) {
			newArr[0] = itemToAdd;
		} else {
			/*
			 * This array traverses through the array, comparing the itemToAdd
			 * to each Listable in the array. Since they are ordered 
			 * alphabetically, the array will add values from the items array
			 * into the new array (newArr) at its respective index in the items 
			 * array if itemToAdd comes after it alphabetically. However, if 
			 * itemToAdd comes before the items value at a respective index, 
			 * itemToAdd is inserted at that index in newArr, and every other 
			 * value in newArr is populated from the remaining values of the 
			 * items array.  
			 */
			for (int index = 0; index<items.length; index++) {
				try {
					if (itemToAdd.toString().compareTo
							(items[index].toString())>= 0) {
						newArr[index] = items[index];
					} else if (itemToAdd.toString().compareTo
							(items[index].toString())<0) {
						newArr[index] = itemToAdd;
						if (index != newArr.length-1) {
							for (int outerIndex = index+1; 
									outerIndex<newArr.length; 
									outerIndex++) {
								newArr[outerIndex] = items[outerIndex-1];
							}
						}
						break;
					} 
				} catch (NullPointerException e) {
					newArr[index] = itemToAdd;
				}
			}
			if (newArr[newArr.length-1] == null) {
				newArr[newArr.length-1] = itemToAdd;
			}
		}
		items = newArr;
	}

	/**
	 * @precondition This method assumes that the current list is already sorted
	 * in alphabetical order based on the names of the items in the list.
	 * 
	 * Adds an entire list of items to the current list.
	 * 
	 * @param listToAdd a list of items that are to be added to the current 
	 * object
	 * 
	 * @postcondition listToAdd will be inserted to the current list, with the 
	 * size of the list accommodating to the size of the items in the list, all
	 * while maintaining the alphabetical ordering of the list by the names of 
	 * the items
	 */
	public void add(SortedListOfImmutables listToAdd) {
		/*
		 * This array traverses through the SortedListOfImmutables listToAdd, 
		 * using the prior add method to populate the items array with 
		 * Listables from the listToAdd array.
		 */
		for (int listIndex = 0; listIndex<listToAdd.getSize(); listIndex++) {
			Listable itemToAdd = listToAdd.get(listIndex);
			this.add(itemToAdd);
		}
	}
	
	/**
	 * @precondition This method assumes that the current list is already sorted
	 * in alphabetical order based on the names of the items in the list.
	 *  
	 * Removes an item from the list. If the list contains the same item that 
	 * the parameter refers to, it will be removed from the list. If the item 
	 * appears in the list more than once, just one instance will be removed. If
	 * the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the 
	 * list
	 * 
	 * @postcondition the list will be smaller to accommodate to the item
	 * removed, while maintaining alphabetical order.
	 */
	public void remove(Listable itemToRemove) {
		boolean isThere = false;
		if (items.length > 0) {
			for (Listable comp : items) {
				if(comp.equals(itemToRemove)) {
					isThere = true;
				}
			}
			if (isThere) {
				Listable[] newArr = new Listable[items.length-1];
				/*
				 * This array traverse through values of the items array and
				 * compares them with the itemToRemove. If itemToRemove comes 
				 * after the Listable indexed, then that Listable is added to
				 * newArr at that index. If the Listable indexed is equal to
				 * itemToRemove, the rest of newArr is populated with the rest
				 * of the values from the items array, not including the value
				 * equal to itemToRemove.
				 */
				for (int index = 0; index<items.length; index++) {
					if (items[index].toString().compareTo
							(itemToRemove.toString())<0) {
						newArr[index] = items[index];
					} else if (items[index].equals(itemToRemove)) {
						for (int outerIndex = index; outerIndex<newArr.length; 
								outerIndex++) {
							newArr[outerIndex] = items[outerIndex+1];
						}
						break;
					}
				}
				items = newArr;
			} 
		}
	}

	/**
	 * @precondition This method assumes that the current list is already sorted
	 * in alphabetical order based on the names of the items in the list. 
	 * 
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 * 
	 * @postcondition the original list will be reduced based on listToRemove, 
	 * removing all values from listToRemove in the original list.
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		/*
		 * This array traverses through the SortedListOfImmutables listToRemove, 
		 * using the prior remove method to remove Listables from the items 
		 * array.
		 */
		for (int listIndex = 0; listIndex<listToRemove.getSize(); listIndex++) {
			Listable itemToRemove = listToRemove.get(listIndex);
			this.remove(itemToRemove);
			Arrays.toString(items);
		}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @postcondition
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int wholesaleCost = 0;
		/*
		 * This array traverses through every Listable in the items array and 
		 * adds its wholesale cost value to the wholesaleCost int. 
		 */
		for (int index = 0; index<items.length; index++) {
			wholesaleCost += items[index].getWholesaleCost();
		}
		return wholesaleCost;
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @postcondition
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {
		int retailValueSum = 0;
		/*
		 * This array traverses through every Listable in the items array and 
		 * adds its retail value to the retailValueSum int. 
		 */
		for (int index = 0; index<items.length; index++) {
			retailValueSum += items[index].getRetailValue();
		}
		return retailValueSum;
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * 
	 * @postcondition
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		/*
		 * This array traverses through every value in the items array, and 
		 * returns true if the Listable itemToFind exists in the array.
		 */
		for (Listable check : items) {
			if (check.equals(itemToFind)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @precondition This method assumes that the current list is already sorted
	 * in alphabetical order based on the names of the items in the list. 
	 * 
	 * Checks if a list of items is contained in the current list.
	 * If more than one copy of a particular element appear in the 
	 * parameter, then the current list must contain at least that many as well.
	 * 
	 * @param listToCheck list of items that may or may not appear in the
	 * current list
	 * 
	 * @postcondition
	 * @return true if all items in the parameter are contained in the current 
	 * list, false if not all the items or the correct number of each item 
	 * exists in the original list.
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		/*
		 * This array traverses through the listToCheck, instantiating a 
		 * Listable named check that will be used to compare against the 
		 * listToCheck as well as the original items array. 
		 */
		for (int index = 0; index<listToCheck.getSize(); index++) {
			Listable check = listToCheck.get(index);
			int itemsCount = 0;
			int listCount = 0;
			/*
			 * This array traverses through the items array, comparing each 
			 * value with check. If they are the same, the int itemsCount is
			 * incremented.
			 */
			for (Listable comp : items) {
				if (check.equals(comp)) {
					itemsCount++;
				}
			}
			/*
			 * This array traverses through listToCheck, comparing each value
			 * with check. If they are the same, the int listCount is 
			 * incremented.
			 */
			for (int listIndex = 0; listIndex<listToCheck.getSize(); listIndex++) {
				if (check.equals(listToCheck.get(listIndex))) {
					listCount++;
				}
			}
			if (listCount>itemsCount) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the String value of a SortedListOfImmutables object
	 * 
	 * @return a String representing the list
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
	
}
