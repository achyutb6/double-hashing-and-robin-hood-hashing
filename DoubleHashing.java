package aab180004;
/**
@author Achyut Arun Bhandiwad - aab180004
Implementation of double hasing.
 */

import java.util.NoSuchElementException;

public class DoubleHashing< K extends Comparable< ? super K> & DoubleHashing.HashCodeInterface,V extends Comparable< ? super V>> {

    int capacity;
    Entry<K,V>[] hashTable;
    int size;

    public interface HashCodeInterface {
        public int hashCode2();
    }

    static class Entry< K extends Comparable< ? super K> & DoubleHashing.HashCodeInterface,V extends Comparable< ? super V>>{
        K key;
        V value;
        boolean isDeleted;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Entry){
                if(((Entry) obj).key.compareTo(this.key) == 0){
                    return true;
                }
            }
            else{
                if(key.compareTo((K)obj) ==0){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * check if full
     * @return isFull
     */
    public boolean isFull(){
        return size == capacity;
    }

    /**
     * Default constructor
     */
    public DoubleHashing(){
        this.capacity = 16;
        this.hashTable = new Entry[capacity];
        this.size = 0;
    }

    // Code extracted from Java’s HashMap:
    static int hash(int h) {
    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    static int indexFor(int h, int length) { // length = table.length is a power of 2
        return h & (length-1);
    }
    // Key x is stored at table[ hash( x.hashCode( ) ) & ( table.length − 1 ) ]

    /**
     * hash1 function for double hashing
     * @param key
     * @return hash1
     */
    private int hash1(K key){
        return indexFor(hash(key.hashCode()), capacity);
    }

    /**
     * hash2 function for double hashing
     * @param key
     * @return hash2
     */
    private int hash2(K key){
        return indexFor(hash(key.hashCode2()), capacity);
    }

    private int getIndex(int k,K key){
        return (hash1(key)+ k*hash2(key))% capacity;
    }

    /**
     * find key in the hash table
     * @param key
     * @return location
     */
    public int find(K key){
        int k =0;
        int ik;
        while(true){
            ik = getIndex(k,key);
            if(hashTable[ik] == null || hashTable[ik].equals(key))
                return ik;
            else if(hashTable[ik].isDeleted){ // deleted
                break;
            }else{
                k++;
            }
        }
        int xspot = ik;
        while(true){
            k++;
            if(hashTable[ik]!=null && hashTable[ik].equals(key)){
                return ik;
            }
            if(hashTable[ik] == null){
                return xspot;
            }
        }
    }

    /**
     * check if hashtable contains key
     * @param key
     * @return
     */
    public boolean contains(K key){
        int loc = find(key);
        if((hashTable[loc]!= null) && hashTable[loc].equals(key))
            return true;
        else
            return false;
    }

    /**
     * add key,value to the hash table
     * @param key
     * @param value
     * @return true if success, false if failed
     */
    public boolean add(K key, V value){
        int loc = find(key);
        if(needToRehash()){
            rehash();
        }
        Entry<K,V> newEntry = new Entry<>(key,value);
        if(hashTable[loc]!= null && hashTable[loc].equals(key))
            return false;
        else{
            hashTable[loc] = newEntry;
            size++;
            return true;
        }
    }

    /**
     * find key and remove if exists
     * @param key
     * @return
     */
    public Entry remove(K key){
        int loc = find(key);
        if(hashTable[loc]!=null && hashTable[loc].equals(key)){
            Entry<K,V> result = hashTable[loc];
            hashTable[loc].isDeleted = true;
            size--;
            return result;
        }
        else
            return null;
    }

    /**
     * rehash the hashtable if load factor is greater than threshold
     */
    private void rehash() {
        Entry[] oldHashTable = this.hashTable;
        this.capacity = this.capacity * 2;
        this.hashTable = new Entry[this.capacity];
        this.size = 0;
        for (Entry eachEntry : oldHashTable) {
            if (eachEntry != null) {
                if (!eachEntry.isDeleted) {
                    this.add((K)eachEntry.key,(V)eachEntry.value);
                }
            }
        }
        return;
    }

    /**
     * calculate load factor
     * @return
     */
    private float loadFactor() {
        return (float) this.size / this.capacity;
    }

    /**
     * check loadfactor
     * @return
     */
    private boolean needToRehash() {
        if (this.loadFactor() >= 0.75) {
            return true;
        }
        return false;
    }
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
     * @param arr : Return the number of unique elements
     * @return
     */
    public int distinctElements(Integer[] arr) {
        DoubleHashing<K,V> hashMap = new DoubleHashing<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.add((K)new MyInteger(i),(V)new MyInteger(i));
        }
        return hashMap.numberOfKeys();
    }

}
