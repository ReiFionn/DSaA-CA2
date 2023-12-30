package system.dsaaca2.Datastructures;

import system.dsaaca2.Models.Hashable;

public class HashMap<E extends Hashable> {
    SillyList<E>[] hashTable;
    String keyType;

    public HashMap(int size, String keyType) {
        hashTable = (SillyList<E>[]) new SillyList[size];
        for (int i=0; i<size; i++)
            hashTable[i] = new SillyList<>();

        this.keyType = keyType;
    }

    public int hashFunction(String key) {
        long total = 0;

        for (int i = 0; i < key.length(); i++) {
            total += key.charAt(i)*((long)Math.pow(128, i)); //ascii value by "digit value" (BASE 128 - ALL ASCII CHARACTERS)
        }
        int toReturn = 0;
        toReturn += total%hashTable.length; //changes long to int because java forgets LOL
        return toReturn;
    }

    public int add(String key, E object) {
        int home = hashFunction(key.toLowerCase());
        hashTable[home].add(object);
        return home;
    }

    private int findIndex(SillyList<E> list, String key) {
        int index = -1;
        for(int i = 0; i<list.size();i++) {
            if (list.get(i).matchKey(key, keyType))
                index = i;
        }

        return index;
    }

    public E find(String key) {
        int home = hashFunction(key.toLowerCase());
        SillyList<E> list = (SillyList<E>) hashTable[home].get(home);

        int index = findIndex(list, key);

        if (index == -1)
            return null;
        else
            return list.get(index);
    }

    public void remove(String key) {
        int home = hashFunction(key);
        SillyList<E> list = (SillyList<E>) hashTable[home].get(home);

        int index = findIndex(list, key);

        if (index != -1)
            list.remove(index);
    }
}
