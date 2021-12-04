package dataStructures;

public class ConcatenableQueueInList<E> extends QueueInList<E> implements ConcatenableQueue<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	@Override
	public void append(ConcatenableQueue<E> queue) {
		if (queue instanceof ConcatenableQueueInList<?>) {
			ConcatenableQueueInList<E> q = (ConcatenableQueueInList<E>) queue;
			DoubleList<E> thislist = (DoubleList<E>) list;
			DoubleList<E> otherlist = (DoubleList<E>) q.list;
			thislist.append(otherlist);
		} else
			while (!queue.isEmpty())
				enqueue(queue.dequeue());
	}

}
