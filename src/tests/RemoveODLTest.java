package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.*;

public class RemoveODLTest {

	@Test
	public void removeSimpleTest() {
		OrderedDictionary<Integer, Integer> od = new OrderedDoubleList<>();

		assertNull(od.remove(4));
		od.insert(5, 5);
		od.insert(10, 10);
		od.insert(-3, -3);
		od.insert(4, 4);
		od.insert(9, 9);
		od.insert(7, 7);

		assertEquals(od.size(),6);

		assertNull(od.remove(19));
		assertEquals(4, (int) od.remove(4)); //remover no meio
		assertEquals(-3, (int) od.remove(-3)); //remover no inicio
		assertEquals(10, (int) od.remove(10)); //remover no fim
		
		assertEquals(od.size(),3);

		Iterator<Entry<Integer, Integer>> it = od.iterator();

		assertTrue(it.hasNext());
		assertEquals(5, (int) it.next().getKey());
		assertEquals(7, (int) it.next().getKey());
		assertEquals(9, (int) it.next().getKey());

		assertEquals(5, (int) od.remove(5));
		assertEquals(9, (int) od.remove(9));
		assertEquals(7, (int) od.remove(7));

		assertNull(od.remove(7));
	}
}
