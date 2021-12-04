package dataStructures;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {
    private final BSTNode<K, V> root;
    private BSTNode<K, V> current;
    private Stack<BSTNode<K, V>> entries;

    BSTKeyOrderIterator(BSTNode<K, V> root) {
        this.root = root;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return entries.size() > 1 || current != null;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();

        if (current != null) {
            while (current.getLeft() != null) {
                entries.push(current);
                current = current.getLeft();
            }
            entries.push(current);
        }

        BSTNode<K, V> result = entries.pop();
        current = result.getRight();
        return result.getEntry();
    }

    @Override
    public void rewind() {
        current = root;
        entries = new StackInList<>();
        entries.push(root);
    }
}
