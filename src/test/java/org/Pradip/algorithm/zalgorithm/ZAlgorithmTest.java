package org.Pradip.algorithm.zalgorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pradip.algorithm.zalgorithm.ZAlgorithm;

public class ZAlgorithmTest {

    ZAlgorithm z = null;

    @Before
    public void init() {
        z = new ZAlgorithm();
    }

    @Test
    public void createLongStringTest() {
        Assert.assertArrayEquals("abc$edfabc".toCharArray(), z.createLongString("abc".toCharArray(), "edfabc".toCharArray()));
    }

    @Test
    public void searchTest() {
        Assert.assertEquals(-1, z.search("cc".toCharArray(), "dfabfabds".toCharArray()));
        Assert.assertEquals(2, z.search("ab".toCharArray(), "dfabfabds".toCharArray()));
    }

    @Test
    public void searchAllTest() {
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, z.searchAll("cc".toCharArray(), "dfabfabds".toCharArray()));
        Assert.assertArrayEquals(new int[]{0, 0, 2, 0, 0, 2, 0, 0, 0}, z.searchAll("ab".toCharArray(), "dfabfabds".toCharArray()));
    }

    @Test
    public void createZTableTest() {
        Assert.assertArrayEquals(new int[]{0, 0, 0, 1, 2, 0, 0}, z.createZTable("ab".toCharArray(), "aabb".toCharArray()));
        z.createZTable("abcd".toCharArray(), "aababcbaabcd".toCharArray());
    }
}
