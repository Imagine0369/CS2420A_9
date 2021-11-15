package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {

	private final double LOADLIMIT = 10.0;
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int size;
	private int capacity;
	private double loadFactor;

	public HashTable(int capacity) {
		size = 0;
		this.capacity = capacity;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		updateLoadFactor();
		// Fill AList with initial capacity of linked lists
		for (int i = 0; i < capacity; i++)
			table.add(new LinkedList<MapEntry<K, V>>());

	}

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

	@Override
	public void clear() {
		for (LinkedList<MapEntry<K, V>> list : table) {
			list.clear();
		}
	}

	@Override
	public boolean containsKey(K key) {
		// Iterate through the map entries inside the linked list associated with the
		// appropriate index value
		for (MapEntry<K, V> item : table.get(key.hashCode() % capacity)) {
			// If key value within MapEntry item equals key we are looking for, return true
			if (item.getKey().equals(key)) {
				return true;
			}
		}

		return false;
	}

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

	@Override
	public V get(K key) {
		// Iterate through entries within the appropriate LinkedList
		for (MapEntry<K, V> entry : table.get(key.hashCode() % capacity)) {
			// If entry key value equals key, return entry value item
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}

		// If map does not have key, return null
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		// Iterate through LinkedList at appropriate index
		for (MapEntry<K, V> entry : table.get(key.hashCode() % capacity)) {
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
		table.get(key.hashCode() % capacity).add(entry);
		size++;

		return null;
	}

	private void growAndRehash() {
		HashTable<K, V> grown = new HashTable<K, V>(capacity * 2);

		// Copy old values, delete and rehash them
		for (LinkedList<MapEntry<K, V>> list : table) {
			for (MapEntry<K, V> entry : list) {
				grown.put(entry.getKey(), entry.getValue());
			}
		}

		this.table = grown.table;
		this.size = grown.size();
		this.capacity = grown.capacity;
		this.updateLoadFactor();
	}

	@Override
	public V remove(K key) {
		// get value
		MapEntry<K, V> removedEntry = getEntry(key);

		// if entry exists
		if (!removedEntry.equals(null)) {
			// get value from the key
			V removedVal = removedEntry.getValue();
			// delete the actual entry now
			table.get(key.hashCode() % capacity).remove(removedEntry);
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
		for (MapEntry<K, V> item : table.get(key.hashCode() % capacity)) {
			// If key value within MapEntry item equals key we are looking for, return that
			// entry
			if (item.getKey().equals(key)) {
				return item;
			}
		}

		// no item was found with that key
		return null;
	}

	@Override
	public int size() {
		return size;
	}

}
