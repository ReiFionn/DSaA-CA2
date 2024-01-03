package system.dsaaca2.Datastructures;

import system.dsaaca2.Models.Hashable;
import static java.lang.Math.abs;

/**
 * A hash map implementation that stores objects of type E for generic Hashmaps.
 * The hash map uses a hash function to determine the index of each object in the hash table.
 *
 * @param <E> The type of objects to be stored in the hash map, must extend Hashable interface.
 */
public class HashMap<E extends Hashable> {
    /**
     * The hash table storing lists of objects.
     */
    SillyList<E>[] hashTable;

    /* Constructs a new hash map with the specified size.
     * size is the size of the hash table.
    */
    public HashMap(int size) {
        hashTable = (SillyList<E>[]) new SillyList[size];
        for (int i=0; i<size; i++)
            hashTable[i] = new SillyList<>();
    }

    /**
     * Computes the hash value for a given key.
     *
     * @param key The key to be hashed.
     * @return The hash value.
     */
    public int hashFunction(String key) {
        long total = 0;

        for (int i = 0; i < key.length(); i++) {
            total += key.charAt(i)*((long)Math.pow(128, i)); //ascii value by "digit value" (BASE 128 - ALL ASCII CHARACTERS)
        }
        int toReturn = 0;
        toReturn += (abs(total))%hashTable.length; //changes long to int because java forgets LOL, abs because the long can get so big it turns negative
        return toReturn;
    }

    /**
     * Adds an object to the hash map using the specified key.
     * @param key    The key for hashing.
     * @param object The object to be added.
     * @return The index in the hash table where the object is added.
     */
    public int add(String key, E object) {
        key = key.toLowerCase();
        int home = hashFunction(key);
        hashTable[home].add(object);
        return home;
    }

    /**
     * Finds the index of an object with the specified key in a given list.
     * @param list The list to search.
     * @param key  The key to match.
     * @return The index of the matching object or -1 if not found.
     */
    private int findIndex(SillyList<E> list, String key) {
        int index = -1;
        for(int i = 0; i<list.size();i++) {
            if (list.get(i).matchKey(key)) {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * Finds an object in the hash map using the specified key.
     *
     * @param key The key for searching.
     * @return The matching object or null if not found.
     */
    public E find(String key) {
        key = key.toLowerCase();
        int home = hashFunction(key);
        SillyList<E> list = hashTable[home];

        int index = findIndex(list, key);

        if (index == -1)
            return null;
        else
            return list.get(index);
    }

    /**
     * Removes an object from the hash map using the specified key.
     * @param key The key for removal.
     */
    public void remove(String key) {
        key = key.toLowerCase();
        int home = hashFunction(key);
        SillyList<E> list = hashTable[home];

        int index = findIndex(list, key);

        if (index != -1)
            list.remove(list.get(index));
    }
}
