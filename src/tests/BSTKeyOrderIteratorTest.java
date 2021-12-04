package tests;

import dataStructures.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class BSTKeyOrderIteratorTest {
    @Test
    public void iterate() {
        OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

        bst.insert(46, 3);
        bst.insert(23, 2);
        bst.insert(82, 7);
        bst.insert(15, 0);
        bst.insert(57, 6);
        bst.insert(105, 9);
        bst.insert(18, 1);
        bst.insert(47, 4);
        bst.insert(92, 8);
        bst.insert(118, 10);
        bst.insert(52, 5);

        Iterator<Entry<Integer, Integer>> iter = bst.iterator();
        int i = 0;

        while (iter.hasNext()) {
            Entry<Integer, Integer> entry = iter.next();

            System.out.printf("%d: %d\n", entry.getKey(), entry.getValue());
            assertEquals(i++, (int) entry.getValue());
        }
    }
}
