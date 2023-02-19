package org.Pradip.algorithm.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pradip.algorithm.utils.SimpleUtils;

public class SimpleUtilsTest {

    SimpleUtils utils = null;

    @Before
    public void init(){
        utils = new SimpleUtils();
    }

    @Test
    public void stringToBooleanTrueTest() {
        Assert.assertTrue(utils.stringToBoolean("y"));
    }

    @Test
    public void stringToBooleanFalseTest() {
        Assert.assertFalse(utils.stringToBoolean("n"));
    }

    @Test
    public void getFileTypeByCode(){
        Assert.assertEquals(SimpleUtils.FileType.PDF, utils.getFileTypeByCode(3));
        Assert.assertEquals(SimpleUtils.FileType.JPEG, utils.getFileTypeByCode(1));
        Assert.assertNull(utils.getFileTypeByCode(999));
    }

    @Test
    public void getFileTypeByName() {
        Assert.assertEquals(SimpleUtils.FileType.TEXT, utils.getFileTypeByName("text"));
        Assert.assertNull(utils.getFileTypeByName(("zebra")));
        Assert.assertNull(utils.getFileTypeByName((null)));
    }


}
