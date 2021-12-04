package dataStructures;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {
    protected DoubleList.DoubleListNode<Entry<K, V>> head;
    protected DoubleList.DoubleListNode<Entry<K, V>> tail;
    protected int currentSize;

    public OrderedDoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Finds the first node whose key is larger or equal than the provided key
     * Requires: key is between head and tail
     *
     * @param key the key to search for
     * @return node with a key larger or equal than the one provided
     */
    protected DoubleList.DoubleListNode<Entry<K, V>> findNode(K key) {
        DoubleList.DoubleListNode<Entry<K, V>> current = head;

        while (current != null && key.compareTo(current.getElement().getKey()) > 0)
            current = current.getNext();

        return current;
    }

    /**
     * Determines if a key is before the head
     *
     * @param key - the key to compare
     * @return <code>true</code> if key comes before the head's key,
     * <code>false</code> otherwise
     */
    protected boolean beforeHead(K key) {
        return key.compareTo(head.getElement().getKey()) < 0;
    }

    /**
     * Determines if a key is after the tail
     *
     * @param key - the key to compare
     * @return <code>true</code> if key comes after the tail's key,
     * <code>false</code> otherwise
     */
    protected boolean afterTail(K key) {
        return key.compareTo(tail.getElement().getKey()) > 0;
    }

    @Override
    public V find(K key) {
        if (isEmpty() || beforeHead(key) || afterTail(key))
            return null;

        Entry<K, V> elem = findNode(key).getElement();

        if (elem.getKey().compareTo(key) != 0)
            return null;

        return elem.getValue();
    }

    /**
     * Adds at the head of the list
     *
     * @param entry the entry to add
     */
    protected void addFirst(Entry<K, V> entry) {
        DoubleList.DoubleListNode<Entry<K, V>> node =
                new DoubleList.DoubleListNode<>(entry, null, head);

        if (isEmpty())
            tail = node;
        else
            head.setPrevious(node);

        head = node;
        currentSize++;
    }

    /**
     * Adds at the tail of the list
     *
     * @param entry the entry to add
     */
    protected void addLast(Entry<K, V> entry) {
        DoubleList.DoubleListNode<Entry<K, V>> node =
                new DoubleList.DoubleListNode<>(entry, tail, null);

        if (isEmpty())
            head = node;
        else
            tail.setNext(node);

        tail = node;
        currentSize++;
    }

    /**
     * Adds in the middle of the list
     * Pre-condition: entry must not be first or last element
     *
     * @param entry the entry to add
     * @return the old value, if entry replaces an existing one
     */
    protected V addMiddle(Entry<K, V> entry) {
        DoubleList.DoubleListNode<Entry<K, V>> next = findNode(entry.getKey());
        DoubleList.DoubleListNode<Entry<K, V>> prev = next.getPrevious();
        DoubleList.DoubleListNode<Entry<K, V>> node =
                new DoubleList.DoubleListNode<>(entry, prev, next);

        if (entry.getKey().compareTo(next.getElement().getKey()) == 0) {
            if (prev != null)
                prev.setNext(node);
            else
                head = node;

            V result = next.getElement().getValue();
            next = next.getNext();

            node.setNext(next);
            if (next != null)
                next.setPrevious(node);
            else
                tail = node;

            return result;
        }
        else {
            prev.setNext(node);
            next.setPrevious(node);
            currentSize++;
            return null;
        }
    }

    @Override
    public V insert(K key, V value) {
        Entry<K, V> entry = new EntryClass<>(key, value);

        if (isEmpty() || beforeHead(key))
            addFirst(entry);
        else if (afterTail(key))
            addLast(entry);
        else
            return addMiddle(entry);

        return null;
    }

    @Override
    public V remove(K key) {
        if (isEmpty() || beforeHead(key) || afterTail(key))
            return null;

        DoubleList.DoubleListNode<Entry<K, V>> node = findNode(key);

        if (key.compareTo(node.getElement().getKey()) != 0)
            return null;

        DoubleList.DoubleListNode<Entry<K, V>> prev = node.getPrevious();
        DoubleList.DoubleListNode<Entry<K, V>> next = node.getNext();

        if (prev == null)
            head = next;
        else
            prev.setNext(node.getNext());

        if (next == null)
            tail = prev;
        else
            next.setPrevious(node.getPrevious());

        currentSize--;

        return node.getElement().getValue();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoubleListIterator<>(head, tail);
    }

    @Override
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        if (isEmpty())
            throw new EmptyDictionaryException();

        return head.getElement();
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        if (isEmpty())
            throw new EmptyDictionaryException();

        return tail.getElement();
    }
}
