package system.dsaaca2.Datastructures;

import system.dsaaca2.Models.Hashable;
import static java.lang.Math.abs;

public class HashMap<E extends Hashable> {
    SillyList<E>[] hashTable;

    public HashMap(int size) {
        hashTable = (SillyList<E>[]) new SillyList[size];
        for (int i=0; i<size; i++)
            hashTable[i] = new SillyList<>();
    }

    public int hashFunction(String key) {
        long total = 0;

        for (int i = 0; i < key.length(); i++) {
            total += key.charAt(i)*((long)Math.pow(128, i)); //ascii value by "digit value" (BASE 128 - ALL ASCII CHARACTERS)
        }
        int toReturn = 0;
        toReturn += (abs(total))%hashTable.length; //changes long to int because java forgets LOL, abs because the long can get so big it turns negative
        return toReturn;
    }

    public int add(String key, E object) {
        key = key.toLowerCase();
        int home = hashFunction(key);
        hashTable[home].add(object);
        return home;
    }

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

    public void remove(String key) {
        key = key.toLowerCase();
        int home = hashFunction(key);
        SillyList<E> list = hashTable[home];

        int index = findIndex(list, key);

        if (index != -1)
            list.remove(list.get(index));
    }
}
