package org.Pradip.algorithm.boyermoore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pradip.algorithm.boyermoore.BoyerMooreHorspool;

public class BoyerMooreHorspoolTest {

    BoyerMooreHorspool b = null;

    @Before
    public void init() {
        b = new BoyerMooreHorspool();
    }

    @Test
    public void searchTest() {
        Assert.assertEquals(4, b.search("learning".toCharArray(), "nin".toCharArray()));
        Assert.assertEquals(7, b.search("learning".toCharArray(), "g".toCharArray()));
        Assert.assertEquals(0, b.search("learning".toCharArray(), "learning".toCharArray()));
        Assert.assertEquals(-1, b.search("learning".toCharArray(), "x".toCharArray()));
        Assert.assertEquals(0, b.search("learning".toCharArray(), null));
        Assert.assertEquals(-1, b.search(null, "abc".toCharArray()));
        Assert.assertEquals(0, b.search(null, null));
    }

    @Test
    public void preprocessTableTest() {
        int[] table = b.preprocessTable("test".toCharArray());
        Assert.assertEquals(1, table['t']);
        Assert.assertEquals(2, table['e']);
        Assert.assertEquals(1, table['s']);
        Assert.assertEquals(4, table['x']);

        table = b.preprocessTable("abc".toCharArray());
        Assert.assertEquals(2, table['a']);
        Assert.assertEquals(1, table['b']);
        Assert.assertEquals(3, table['c']);
        Assert.assertEquals(3, table['x']);

        table = b.preprocessTable("abcdb".toCharArray());
        Assert.assertEquals(4, table['a']);
        Assert.assertEquals(1, table['b']);
        Assert.assertEquals(2, table['c']);
        Assert.assertEquals(5, table['x']);
    }

}
