package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * HashTable Class which stores MapEntry values using Separate Chaining
 * technique.
 * 
 * @author Mike Phelps and Seth Polevoi
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> implements Map<K, V> {

	private final double LOADLIMIT = 10.0;
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int size;
	public int capacity;
	private double loadFactor;

	/**
	 * Creates new HashTable with a specific capacity
	 * 
	 * @param capacity int value
	 */
	public HashTable(int capacity) {
		size = 0;
		this.capacity = capacity;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		updateLoadFactor();
		// Fill AList with initial capacity of linked lists
		for (int i = 0; i < capacity; i++)
			table.add(new LinkedList<MapEntry<K, V>>());

	}

	/**
	 * Creates new HashTable
	 */
	public HashTable() {
		size = 0;
		capacity = 100;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		updateLoadFactor();
		// Fill AList with initial capacity of linked lists
		for (int i = 0; i < capacity; i++) {
			table.add(new LinkedList<MapEntry<K, V>>());
		}
	}

	/**
	 * Updates the load factor for our hash table
	 */
	private void updateLoadFactor() {
		loadFactor = size / capacity;
	}

	/**
	 * Removes all mappings from this map.
	 *
	 */
	@Override
	public void clear() {
		for (LinkedList<MapEntry<K, V>> list : table) {
			list.clear();
		}
		size = 0;
	}

	/**
	 * Determines whether this map contains the specified key.
	 *
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		// Iterate through the map entries inside the linked list associated with the
		// appropriate index value
		for (MapEntry<K, V> item : table.get(getHashCode(key) % capacity)) {
			// If key value within MapEntry item equals key we are looking for, return true
			if (item.getKey().equals(key)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Determines whether this map contains the specified value.
	 *
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	@Override
	public boolean containsValue(V value) {
		// Iterate through each item in each linked list to find if
		// value is found in any MapEntry
		for (LinkedList<MapEntry<K, V>> list : table) {
			for (MapEntry<K, V> entry : list) {
				if (value.equals(entry.getValue())) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Returns an ArrayList view of the mappings contained in this map, where the
	 * ordering of mapping in the list is insignificant.
	 * 
	 * @return an ArrayList object containing all mapping (i.e., entries) in this
	 *         map
	 */
	@Override
	public List<MapEntry<K, V>> entries() {
		// List to be built upon and returned
		ArrayList<MapEntry<K, V>> returnList = new ArrayList<MapEntry<K, V>>();

		// Iterate through all LinkedLists in table
		for (LinkedList<MapEntry<K, V>> list : table) {
			// Iterate through all entries in each LinkedList
			for (MapEntry<K, V> entry : list) {
				// Add each entry to return ArrayList
				returnList.add(entry);
			}
		}
		return returnList;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	@Override
	public V get(K key) {
		// Iterate through entries within the appropriate LinkedList
		for (MapEntry<K, V> entry : table.get(getHashCode(key) % capacity)) {
			// If entry key value equals key, return entry value item
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}

		// If map does not have key, return null
		return null;
	}

	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Calls the objects hashCode method and returns the positive version of the
	 * hash
	 * 
	 * @param key key to get hashCode for
	 * @return returns the hash code of a given key
	 */
	private int getHashCode(K key) {
		// take absolute value of hash
		int hash = Math.abs(key.hashCode());

		// if hash value is still negative
		if (hash < 0) {
			// value is max negative int so add 1 and re absolute value it
			return Math.abs(hash + 1);
		}
		// return normal hash
		return hash;
	}

	/**
	 * Associates the specified value with the specified key in this map. (I.e., if
	 * the key already exists in this map, resets the value; otherwise adds the
	 * specified key-value pair.)
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V put(K key, V value) {
		// Iterate through LinkedList at appropriate index
		for (MapEntry<K, V> entry : table.get(getHashCode(key) % capacity)) {
			// If entry key value already exists, simply updates its value
			if (entry.getKey().equals(key)) {
				V returnVal = entry.getValue();
				entry.setValue(value);
				return returnVal;
			}
		}

		// If key does not already exist, add entry to end of appropriate LinkedList
		// grow and re-hash if necessary.

		if (((size + 1) / capacity) >= LOADLIMIT) {
			growAndRehash();
		}

		// Create MapEntry using values asked to be put into table
		MapEntry<K, V> entry = new MapEntry<K, V>(key, value);

		// Put entry into the LinkedList at appropriate index value
		// Appropriate index value is determined by keys hashcode mod capacity
		// table is ArrayList
		table.get(getHashCode(key) % capacity).add(entry);
		size++;

		return null;
	}

	/**
	 * Helper method used to grow and rehash current map
	 * 
	 */
	private void growAndRehash() {
		// Create new hastable with double the capacity of the current table
		HashTable<K, V> grown = new HashTable<K, V>(capacity * 2);

		// Copy and rehash values into new table
		for (LinkedList<MapEntry<K, V>> list : table) {
			for (MapEntry<K, V> entry : list) {
				grown.put(entry.getKey(), entry.getValue());
			}
		}

		// Set current table with new tables values
		this.table = grown.table;
		this.size = grown.size();
		this.capacity = grown.capacity;
		this.updateLoadFactor();
	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V remove(K key) {
		// get value
		MapEntry<K, V> removedEntry = getEntry(key);

		// if entry exists
		if (removedEntry != null) {
			// get value from the key
			V removedVal = removedEntry.getValue();
			// delete the actual entry now
			table.get(getHashCode(key) % capacity).remove(removedEntry);
			// update size
			size--;
			updateLoadFactor();
			// return the value from the entry we removed
			return removedVal;
		}

		// item does not contain key, return null
		return null;
	}

	/**
	 * Returns the address of a mapEntry of a given key
	 * 
	 * @param key
	 * @return
	 */
	private MapEntry<K, V> getEntry(K key) {
		// Iterate through the map entries inside the linked list associated with the
		// appropriate index value
		for (MapEntry<K, V> item : table.get(getHashCode(key) % capacity)) {
			// If key value within MapEntry item equals key we are looking for, return that
			// entry
			if (item.getKey().equals(key)) {
				return item;
			}
		}

		// no item was found with that key
		return null;
	}

	/**
	 * Determines the number of mappings in this map.
	 *
	 * 
	 * @return the number of mappings in this map
	 */
	@Override
	public int size() {
		return size;
	}

}
