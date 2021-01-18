import java.util.*;

/**
 * 
 * @author Wendy Li
 *Student number 251026390
 *CS 2211 Assignment 2
 * @param <C>
 * @param <S>
 */

public class Dictionary<C, S> implements DictionaryADT{
	private int size;
	private int numElements;
	private List<LinkedList<Record>> hashtable;

	/**
	 * Constructor for class Dictionary and initializes the local
	 * variables within the dictionary
	 * @param size
	 */
	public Dictionary(int size) {
		/**
		 * 
		 */
		this.size = size;
		numElements = 0;
		
		hashtable = new ArrayList<LinkedList<Record>>(); //initializing hashtable
		
		for (int i = 0; i < size; i++) {
			hashtable.add(new LinkedList<Record>());
		}
	}
	
	/**
	 * This method stores the record pair in hashtable
	 * @param config
	 * @return hash number
	 */
	private int hashCode(String config) {
		/**
		 * 
		 */
		int hash = 0;
		int val = 0;
		for (int i = 0; i < config.length(); i++)
		{
			val = (val*537 + (int)config.charAt(i)) % size;
		}
		return val % size;
		
	}
	
	/**
	 * This function inserts pair into hash table
	 * 
	 * @param pair
	 * @return returns 1 if pair produces a collision and returns 
	 * 0 otherwise
	 */
	public int insert(Record pair) throws DictionaryException{
		int pos = hashCode(pair.getConfig());

		LinkedList<Record> list = hashtable.get(pos);
		
		for (int count = 0; count < list.size(); count++) {
			if(list.get(count).getConfig().contentEquals(pair.getConfig())) {
				throw new DictionaryException(); //throws DictionaryException if Record pair is already in Dictionary
			}
		}
		list.addFirst(pair);
		numElements += 1; //incrementing the number of elements

		hashtable.set(pos, list);
		if (list.size() <= 1) {
			return 0;
		}else {
			return 1;
		}
	}
	
	/**
	 * This function removes record from hashtable
	 * @param config: the key value of dictionary
	 */
	public void remove(String config) throws DictionaryException{
		int pos = hashCode(config);
		int index = 0; 
		LinkedList<Record> list = hashtable.get(pos);
		boolean inList = false;
		
		for (int count = 0; count < list.size(); count++) {
			if (list.get(count).getConfig().contentEquals(config)) { //looking if config is in dictionary
				inList = true; 
				index = count; //index of where config is in dictionary
			}
		}
		
		if (inList == true) { //if config is in dictionary
			list.remove(index); //removing the record pair with key config 
			hashtable.set(pos, list);
			numElements--; //decreasing number of elements
		}else {
			throw new DictionaryException(); //throws an exception if config is not in dictionary
		}
	}
	
	/**
	 * This function gets the corresponding score of config from dictionary
	 * @param config: the key value of dictionary
	 * @return returns score for the given configuration stored in 
	 * dictionary. If configuration is not in dictionary it returns -1
	 */
	public int get(String config) {
		int pos = hashCode(config);
		LinkedList<Record> list = hashtable.get(pos);
		if (list.size()==0) { //if the list is empty
			return -1;
		}
		for (int count = 0; count < list.size(); count++) {
			if (list.get(count).getConfig().contentEquals(config)) { 
				if(list.get(count).getScore() == -1){
					return 0;
				}
				return list.get(count).getScore(); //returning the associated score with config from hashtable
			}
		}
		
		return -1;
	}
	
	/**
	 * This method returns the number of elements
	 * @return: numElements
	 */
	public int numElements() {
		return numElements;
	}
}


