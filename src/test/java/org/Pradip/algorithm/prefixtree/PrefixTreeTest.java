package org.Pradip.algorithm.prefixtree;

import org.junit.Assert;
import org.junit.Test;
import org.pradip.algorithm.prefixtree.PrefixTree;

public class PrefixTreeTest {

    @Test
    public void insertTest() {
        PrefixTree tree = new PrefixTree();
        tree.insert("cat".toCharArray(), 1);
        tree.insert("door".toCharArray(), 2);
        tree.insert("cats".toCharArray(), 3);
        Assert.assertTrue(tree.root.getChildren('c').getChildren('a').getChildren('t').isWord);
        Assert.assertEquals('c', tree.root.getChildren('c').c);
    }
}
