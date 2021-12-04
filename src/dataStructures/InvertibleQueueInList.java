package dataStructures;

public class InvertibleQueueInList<E> extends QueueInList<E> implements InvertibleQueue<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;
	
	protected boolean inverted = false;

	@Override
	public void invert() {
		inverted = !inverted;
	}
	
	@Override
	public void enqueue(E element) {
		if (inverted)
			list.addFirst(element);
		else
			list.addLast(element);
	}
	
	@Override
	public E dequeue() throws EmptyQueueException {
		if (list.isEmpty())
			throw new EmptyQueueException();
		
		if (inverted)
			return list.removeLast();
		else
			return list.removeFirst();
	}
}
