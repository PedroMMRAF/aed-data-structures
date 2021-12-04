package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

import dataStructures.*;

public class InsertODLTest {

	@Test
	public void insertSimpleTest() {
		OrderedDictionary<Integer, Integer> od = new OrderedDoubleList<>();

		od.insert(5, 5); // insercao a cabeca
		od.insert(10, 10); // insercao a cauda
		od.insert(-3, -3); // insercao a cabeca
		od.insert(4, 4); // insercao do segundo
		od.insert(9, 9); // insercao do penultimo
		od.insert(7, 77); // insercao no meio
		od.insert(7, 7); // substituição do valor

		assertEquals(od.size(),6);

		TwoWayIterator<Entry<Integer, Integer>> it = (TwoWayIterator<Entry<Integer, Integer>>) od.iterator();

		assertTrue(it.hasNext());
		assertEquals(-3, (int) it.next().getKey());
		assertEquals(4, (int) it.next().getKey());
		assertEquals(5, (int) it.next().getKey());
		assertEquals(7, (int) it.next().getKey());
		assertEquals(9, (int) it.next().getKey());
		assertEquals(10, (int) it.next().getKey());

		it.fullForward();
		assertTrue(it.hasPrevious());
		assertEquals(10, (int) it.previous().getKey());
		assertEquals(9, (int) it.previous().getKey());
		assertEquals(7, (int) it.previous().getKey());
		assertEquals(5, (int) it.previous().getKey());
		assertEquals(4, (int) it.previous().getKey());
		assertEquals(-3, (int) it.previous().getKey());
		assertFalse(it.hasPrevious());	
	}
	
	@Test
	public void insertinsertRandomTest() {
		OrderedDictionary<Integer, Integer> od = new OrderedDoubleList<>();
		
		insertRandomElems(od, 100);
		
		Iterator<Entry<Integer, Integer>> it = od.iterator();
		Entry<Integer, Integer> previous = it.next();
		Entry<Integer, Integer> current;
		while (it.hasNext()) { // lista esta ordenada
			current = it.next();
			assertTrue(previous.getKey().compareTo(current.getKey()) < 0); 
			previous = current;
		}
		
		
		it = od.iterator();
		Entry<Integer, Integer> max = od.maxEntry();
		Entry<Integer, Integer> min = od.minEntry();
		while (it.hasNext()) { 
			current = it.next();
			// maximo 'e maior ou igual a todos os elementos
			assertTrue(current.getKey().compareTo(max.getKey()) <= 0);  
			// minimo 'e menor ou igual a todos os elementos
			assertTrue(current.getKey().compareTo(min.getKey()) >= 0); 
		}
		
	}

	private static void insertRandomElems(OrderedDictionary<Integer, Integer> od, int elems) {
		Random rand = new Random();
		for (int i = 0; i < elems; i++) {
			int e = rand.nextInt();
			od.insert(e, e);
		}
	}
}
