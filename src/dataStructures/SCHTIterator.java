package dataStructures;

public class SCHTIterator<K, V> implements Iterator<Entry<K, V>> {
    private final Dictionary<K, V>[] table;
    private Iterator<Entry<K, V>> current;
    private int next;

    SCHTIterator(Dictionary<K, V>[] table) {
        this.table = table;
        rewind();
    }

    @Override
    public boolean hasNext() {
        while ((current == null || !current.hasNext()) && next < table.length)
            current = table[next++].iterator();

        assert current != null;
        return current.hasNext();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();

        return current.next();
    }

    @Override
    public void rewind() {
        next = 0;
        current = null;
    }
}
