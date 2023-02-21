package org.Pradip.algorithm.suffixtrie;

import org.junit.Assert;
import org.junit.Test;
import org.pradip.algorithm.suffixtrie.SuffixTrieMap;

public class SuffixTrieMapTest {

    @Test
    public void testInsert() {
        SuffixTrieMap t = new SuffixTrieMap("banana");
        Assert.assertNotNull(t.root.children.get('b'));
        Assert.assertNotNull(t.root.children.get('n'));
        Assert.assertNotNull(t.root.children.get('a'));
        Assert.assertNull(t.root.children.get('c'));
        System.out.println(t.root);
    }
}
