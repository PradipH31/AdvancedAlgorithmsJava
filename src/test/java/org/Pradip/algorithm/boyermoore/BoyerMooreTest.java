package org.Pradip.algorithm.boyermoore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pradip.algorithm.boyermoore.BoyerMoore;

import java.util.Arrays;

public class BoyerMooreTest {

    BoyerMoore b = null;

    @Before
    public void init() {
        b = new BoyerMoore();
    }

    @Test
    public void isPrefixTest() {
        Assert.assertFalse(b.isPrefix("baibai".toCharArray(), 1));
        Assert.assertFalse(b.isPrefix("baibai".toCharArray(), 2));
        Assert.assertTrue(b.isPrefix("baibai".toCharArray(), 3));
        Assert.assertFalse(b.isPrefix("baibai".toCharArray(), 4));
        Assert.assertFalse(b.isPrefix("baibai".toCharArray(), 5));
        Assert.assertTrue(b.isPrefix("baibai".toCharArray(), 6));
    }

    @Test
    public void suffixLengthTest() {
        Assert.assertEquals(2, b.suffixLength("baidai".toCharArray(), 2));
        Assert.assertEquals(3, b.suffixLength("kaikai".toCharArray(), 2));
        Assert.assertEquals(1, b.suffixLength("mytestmytest".toCharArray(), 2));
        Assert.assertEquals(6, b.suffixLength("mytestmytest".toCharArray(), 5));
    }

    @Test
    public void preprocessingSuffxTableTest() {
        System.out.println(Arrays.toString(b.preprocessSuffixTable("baidai".toCharArray())));
    }

}
