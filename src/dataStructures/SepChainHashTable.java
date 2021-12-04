package dataStructures;

/**
 * Separate Chaining Hash table implementation
 *
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 * @author AED  Team
 * @version 1.0
 */

public class SepChainHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The array of dictionaries.
     */
    protected Dictionary<K, V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     *
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];

        for (int i = 0; i < arraySize; i++)
            table[i] = new OrderedDoubleList<>();

        maxSize = capacity;
        currentSize = 0;
    }


    public SepChainHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     *
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public V find(K key) {
        return table[this.hash(key)].find(key);
    }

    @Override
    public V insert(K key, V value) {
        if (this.isFull())
            this.rehash();

        V result = table[this.hash(key)].insert(key, value);

        if (result == null)
            currentSize++;

        return result;
    }

    @SuppressWarnings("unchecked")
    protected void rehash() {
        int arraySize = HashTable.nextPrime(table.length * 2);
        Dictionary<K, V>[] reHashed = (Dictionary<K, V>[]) new Dictionary[arraySize];

        for (int i = 0; i < arraySize; i++)
            reHashed[i] = new OrderedDoubleList<>();

        maxSize = (int) (arraySize * 0.75);

        Iterator<Entry<K, V>> it = iterator();

        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            reHashed[this.hash(entry.getKey())].insert(entry.getKey(), entry.getValue());
        }

        table = reHashed;
    }

    @Override
    public V remove(K key) {
        V result = table[this.hash(key)].remove(key);

        if (result != null)
            currentSize--;

        return result;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new SCHTIterator<>(table);
    }
}
































