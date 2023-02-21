package org.Pradip.algorithm.suffixtrie;

import org.junit.Assert;
import org.junit.Test;
import org.pradip.algorithm.suffixtrie.SuffixTrie;

public class SuffixTrieTest {

    @Test
    public void testInsert() {
        SuffixTrie t = new SuffixTrie("banana");
        Assert.assertNotNull(t.root.children['b']);
        Assert.assertNotNull(t.root.children['n']);
        Assert.assertNotNull(t.root.children['a']);
        Assert.assertNull(t.root.children['c']);
        System.out.println(t.root);
    }
}
