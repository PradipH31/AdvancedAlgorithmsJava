package org.Pradip.algorithm.euclid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pradip.algorithm.euclid.Euclid;

public class EuclidTest {

    Euclid e = null;

    @Before
    public void init() {
        e = new Euclid();
    }

    @Test
    public void gcdTest(){
        Assert.assertEquals(2,e.gcd(10,2));
        Assert.assertEquals(2, e.gcd(22,6));
    }

    @Test
    public void gcdNoRecusrionTest(){
        Assert.assertEquals(2,e.gcdNoRecursion(10,2));
        Assert.assertEquals(2, e.gcdNoRecursion(22,6));
    }

    @Test
    public void gcdVsGcdNoRecusrionTest() {
        Assert.assertEquals(e.gcd(10,2),e.gcdNoRecursion(10,2));
        Assert.assertEquals(e.gcd(22,6), e.gcdNoRecursion(22,6));
        Assert.assertEquals(e.gcd(182,74),e.gcdNoRecursion(182,74));
    }
}
