package org.Pradip.algorithm.suffixtree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pradip.algorithm.suffixtree.SuffixTree;

import java.util.Arrays;

public class SuffixTreeTest {

    SuffixTree t;

    @Before
    public void init() {
        t = new SuffixTree("banana".toCharArray());
    }

    @Test
    public void addUniqueTest() {
        String expected = Arrays.toString("bana$".toCharArray());
        String result = Arrays.toString(t.addUnique("bana".toCharArray()));

        Assert.assertEquals(expected, result);
    }
}
