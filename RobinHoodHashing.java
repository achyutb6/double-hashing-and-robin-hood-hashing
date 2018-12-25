package aab180004;

import java.util.Comparator;

/**
 * @author Mythri Thippareddy
 * The class implements Robinhood Hashing
 * @param <K>
 *            : K is type of elements inserted into the hashtable
 */
public class RobinHoodHashing<K extends Comparable<? super K>> {

	private int size;// Number of elements in the hashTable
	private int capacity;// The size of the hashTable
	private Entry[] hashTable; // HashTable of type Entry class
	private int maxDisplacement = 0; // Keep track of the maximum displacement of the elements in the hash table
	static final int DEFAULT_INITIAL_CAPACITY = 16;
	static final float DEFAULT_LOAD_FACTOR = (float) 0.6;
	private float loadFactor;

	/**
	 * @author Mythri Thippareddy
	 *
	 * @param <K>
	 *            : K is type of elements inserted into the hashtable
	 */
	static class Entry<K extends Comparable<? super K>> {
		K key;
		boolean isDeleted;
		int displacement;

		public Entry(K key) {
			this.key = key; // The key element inserted
			this.isDeleted = false; // Whether an element is deleted or not
			this.displacement = 0; // Displacement by the element which it's original index
		}
	}

	/**
	 * Constructor where the hastable is initialized
	 */
	public RobinHoodHashing() {
		this.capacity = DEFAULT_INITIAL_CAPACITY;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.hashTable = new Entry[this.capacity];
	}

	/**
	 * @param h
	 *            : value calculated for indexFor Method
	 * @return
	 */
	private static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	/**
	 * @param h
	 *            : from the hash method
	 * @param length
	 *            : the capacity of the hashTable
	 * @return
	 */
	private static int indexFor(int h, int length) {
		return h & (length - 1);
	}

	/**
	 * @param x
	 *            : The element to be hashed
	 * @return
	 */
	private int hashCode(K x) {
		return indexFor(hash(x.hashCode()), this.capacity);
	}

	/**
	 * @param x:
	 *            if x exists in the hashTable, return true, else false
	 * @return
	 */
	public boolean contains(K x) {
		if (find(x) == null) {
			return false;
		}
		return true;
	}

	/**
	 * @param x
	 *            : If x exists in hashtable, return the index. Else return null
	 * @return
	 */
	private Integer find(K x) {
		int hx = hashCode(x);
		int d = 0;
		while (this.hashTable[hx] != null) {
			if (this.hashTable[hx].displacement >= d || d < this.maxDisplacement) {
				if ((this.hashTable[hx].isDeleted == false && ((K) this.hashTable[hx].key).compareTo((K) x) == 0)) {
					return hx;
				} else {
					hx = (hx + 1) % this.capacity;
					d = d + 1;
				}
			} else
				break;
		}
		return null;
	}

	/**
	 * @param x
	 *            Return true if x exists and is marked as delete. Else return false
	 * @return
	 */
	public boolean remove(K x) {
		Integer hx = find(x);
		if (hx == null) {
			return false;
		} else {
			this.hashTable[hx].isDeleted = true;
			return true;
		}
	}

	/**
	 * Rehashing the hashTable when the loadfactor reaches the limit
	 */
	private void rehash() {
		Entry[] oldHashTable = this.hashTable;
		this.capacity = this.capacity * 2;
		this.hashTable = new Entry[this.capacity];
		this.size = 0;
		this.maxDisplacement = 0;
		for (Entry eachEntry : oldHashTable) {
			if (eachEntry != null) {
				if (eachEntry.isDeleted == false) {
					this.addElement((K) eachEntry.key);
				}
			}
		}
		return;
	}

	/**
	 * @return the current loadFactor
	 */
	private float calculateLoadFactor() {
		return (float) this.size / this.capacity;
	}

	/**
	 * @return true if the table needs to be rehashed
	 */
	private boolean needToRehash() {
		if (this.calculateLoadFactor() >= this.loadFactor) {
			return true;
		}
		return false;
	}

	/**
	 * @return number of unique keys in the hashtable
	 */
	public int numberOfKeys() {
		int numberOfKey = 0;
		for (Entry eachEntry : this.hashTable) {
			if (eachEntry != null) {
				if (eachEntry.isDeleted == false) {
					numberOfKey++;
				}
			}
		}
		return numberOfKey;
	}

	/**
	 * @param x:
	 *            add element x
	 */
	private void addElement(K x) {
		int hx = hashCode(x);
		int displacementCount = 0;
		while (true) {
			if (this.hashTable[hx] == null) {
				this.hashTable[hx] = new Entry(x);
				this.hashTable[hx].displacement = displacementCount;
				if (displacementCount > this.maxDisplacement) {
					this.maxDisplacement = displacementCount;
				}
				this.size++;
				if (this.needToRehash()) {
					rehash();
				}
				break;
			} else if (this.hashTable[hx].isDeleted == true) {
				this.hashTable[hx].key = x;
				this.hashTable[hx].displacement = displacementCount;
				this.hashTable[hx].isDeleted = false;
				if (displacementCount > this.maxDisplacement) {
					this.maxDisplacement = displacementCount;
				}
				break;
			} else if (this.hashTable[hx].displacement < displacementCount) {
				K xOld = (K) this.hashTable[hx].key;
				this.hashTable[hx].key = x;
				this.hashTable[hx].displacement = displacementCount;
				if (displacementCount > this.maxDisplacement) {
					this.maxDisplacement = displacementCount;
				}
				x = xOld;
			}
			displacementCount++;
			hx = (hx + 1) % this.capacity;
		}
	}

	/**
	 * @param x
	 * @return
	 */
	public boolean add(K x) {
		if (this.contains(x)) {
			return false;
		} else {
			addElement(x);
			return true;
		}
	}

	/**
	 * @param arr : Return the number of unique elements
	 * @return
	 */
	static <T extends Comparable<? super T>> int distinctElements(T[] arr) {
		RobinHoodHashing<T> hashMap = new RobinHoodHashing<T>();
		for (int i = 0; i < arr.length; i++) {
			hashMap.add((T) arr[i]);
		}
		return hashMap.numberOfKeys();
	}

}