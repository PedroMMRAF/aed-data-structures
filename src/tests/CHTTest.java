package tests;

import dataStructures.*;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CHTTest {

    private static void insertRandomElems(Dictionary<Integer, Integer> cht, int elems) {
        Random rand = new Random();
        int i = 0;
        while (i < elems) {
            int e = rand.nextInt();
            cht.insert(e, e);
            i = cht.size();
        }
    }

    @Test
    public void insertTest() {
        Dictionary<Integer, Integer> cht = new SepChainHashTable<>();

        assertNull(cht.insert(5, 5));
        assertNull(cht.insert(10, 10));
        assertNull(cht.insert(-3, -3));
        assertNull(cht.insert(4, 4));
        assertNull(cht.insert(9, 9));
        assertNull(cht.insert(7, 77));
        assertEquals(77, (int) cht.insert(7, 7));

        assertEquals(cht.size(), 6);

        Iterator<Entry<Integer, Integer>> it = cht.iterator();

        OrderedDictionary<Integer, Integer> entries = new OrderedDoubleList<>();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            entries.insert(entry.getKey(), entry.getValue());
        }

        assertEquals(cht.size(), entries.size());

        Iterator<Entry<Integer, Integer>> itEntries = entries.iterator();

        assertTrue(itEntries.hasNext());
        assertEquals(-3, (int) itEntries.next().getKey());
        assertEquals(4, (int) itEntries.next().getKey());
        assertEquals(5, (int) itEntries.next().getKey());
        assertEquals(7, (int) itEntries.next().getKey());
        assertEquals(9, (int) itEntries.next().getKey());
        assertEquals(10, (int) itEntries.next().getKey());
        assertFalse(itEntries.hasNext());
    }

    @Test
    public void insertRandomTest() {
        Dictionary<Integer, Integer> cht = new SepChainHashTable<>(20);

        insertRandomElems(cht, 200);

        Iterator<Entry<Integer, Integer>> it = cht.iterator();

        int counter = 0;
        while (it.hasNext()) {
            it.next().getKey();
            counter++;
        }
        assertEquals(cht.size(), counter);
    }

    @Test
    public void findTest() {
        Dictionary<Integer, Integer> cht = new SepChainHashTable<>();
        assertTrue(cht.isEmpty());
        assertNull(cht.insert(5, 5));
        assertEquals(5, (int) cht.find(5));
        assertFalse(cht.isEmpty());
        assertEquals(1, cht.size());
        assertNull(cht.insert(10, 10));
        assertEquals(10, (int) cht.find(10));
        assertEquals(2, cht.size());
        assertNull(cht.insert(-3, -3));
        assertEquals(-3, (int) cht.find(-3));
        assertEquals(3, cht.size());
        assertNull(cht.insert(4, 4));
        assertEquals(4, (int) cht.find(4));
        assertEquals(4, cht.size());
        assertNull(cht.insert(9, 9));
        assertEquals(9, (int) cht.find(9));
        assertEquals(5, cht.size());
        assertNull(cht.insert(7, 77));
        assertEquals(77, (int) cht.find(7));
        assertEquals(9, (int) cht.find(9));
        assertEquals(6, cht.size());
        assertEquals(77, (int) cht.insert(7, 7));
        assertEquals(7, (int) cht.find(7));
        assertEquals(6, cht.size());
        assertEquals(cht.size(), 6);

        Iterator<Entry<Integer, Integer>> it = cht.iterator();

        OrderedDictionary<Integer, Integer> entries = new OrderedDoubleList<>();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            entries.insert(entry.getKey(), entry.getValue());
        }

        assertEquals(cht.size(), entries.size());

        Iterator<Entry<Integer, Integer>> itEntries = entries.iterator();

        assertTrue(itEntries.hasNext());
        assertEquals(-3, (int) itEntries.next().getKey());
        assertEquals(4, (int) itEntries.next().getKey());
        assertEquals(5, (int) itEntries.next().getKey());
        assertEquals(7, (int) itEntries.next().getKey());
        assertEquals(9, (int) itEntries.next().getKey());
        assertEquals(10, (int) itEntries.next().getKey());
        assertFalse(itEntries.hasNext());
    }

    @Test
    public void removeTest() {
        Dictionary<Integer, Integer> cht = new SepChainHashTable<>();

        assertNull(cht.insert(5, 5));
        assertNull(cht.insert(10, 10));
        assertNull(cht.insert(-3, -3));
        assertNull(cht.insert(4, 4));
        assertNull(cht.insert(9, 9));
        assertNull(cht.insert(7, 77));
        assertEquals(77, (int) cht.insert(7, 7));

        assertEquals(cht.size(), 6);

        assertEquals(5, (int) cht.remove(5));
        assertNull(cht.remove(5));
        assertEquals(10, (int) cht.remove(10));
        assertEquals(-3, (int) cht.remove(-3));
        assertEquals(4, (int) cht.remove(4));
        assertEquals(9, (int) cht.remove(9));
        assertEquals(7, (int) cht.remove(7));

        assertEquals(cht.size(), 0);
        Iterator<Entry<Integer, Integer>> it = cht.iterator();
        assertFalse(it.hasNext());

        assertNull(cht.insert(5, 5));
        assertNull(cht.insert(10, 10));
        assertNull(cht.insert(-3, -3));
        assertNull(cht.insert(4, 4));
        assertNull(cht.insert(9, 9));
        assertNull(cht.insert(7, 77));
        assertEquals(77, (int) cht.insert(7, 7));

        assertEquals(cht.size(), 6);

        it = cht.iterator();

        OrderedDictionary<Integer, Integer> entries = new OrderedDoubleList<>();
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            entries.insert(entry.getKey(), entry.getValue());
        }

        assertEquals(cht.size(), entries.size());

        Iterator<Entry<Integer, Integer>> itEntries = entries.iterator();

        assertTrue(itEntries.hasNext());
        assertEquals(-3, (int) itEntries.next().getKey());
        assertEquals(4, (int) itEntries.next().getKey());
        assertEquals(5, (int) itEntries.next().getKey());
        assertEquals(7, (int) itEntries.next().getKey());
        assertEquals(9, (int) itEntries.next().getKey());
        assertEquals(10, (int) itEntries.next().getKey());
        assertFalse(itEntries.hasNext());
    }

}
