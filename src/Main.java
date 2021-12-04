import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dataStructures.*;

public class Main {

	public static void main(String[] args) {
		concQueue();
		invertQueue();
	}
	
	private static void invertQueue() {
		InvertibleQueue<Integer> q1 =
				new InvertibleQueueInList<>();
		
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		
		q1.invert();
		
		q1.enqueue(4);
		
		emptyQueue(q1);
	}
	
	private static void concQueue() {
		ConcatenableQueue<Integer> q1 =
				new ConcatenableQueueInList<>();
		ConcatenableQueue<Integer> q2 =
				new ConcatenableQueueInList<>();
		
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		
		q2.enqueue(3);
		q2.enqueue(2);
		q2.enqueue(1);
		
		emptyQueue(q1);
		emptyQueue(q2);
		
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		
		q2.enqueue(3);
		q2.enqueue(2);
		q2.enqueue(1);
		
		q1.append(q2);
		
		q2.enqueue(1);
		
		emptyQueue(q1);
		emptyQueue(q2);
	}
	
	private static void emptyQueue(Queue<?> queue) {
		while (!queue.isEmpty())
			System.out.print(queue.dequeue() + " ");
		System.out.println();
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private static InvertibleQueue<Integer> load() {
		InvertibleQueue<Integer> queue = new InvertibleQueueInList<>();
		
		try {
			FileInputStream fp = new FileInputStream("test.obj");
			ObjectInputStream op = new ObjectInputStream(fp);
			
			queue = (InvertibleQueue<Integer>) op.readObject();
			
			op.close();
			fp.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return queue;
	}

}
