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

    @Test
    public void findTest() {
        PrefixTree tree = new PrefixTree();
        tree.insert("cat".toCharArray(), 1);
        tree.insert("door".toCharArray(), 2);
        tree.insert("cats".toCharArray(), 3);

        Assert.assertEquals(3, tree.find("cats".toCharArray()).id);
        Assert.assertEquals(2, tree.find("door".toCharArray()).id);
        Assert.assertEquals(1, tree.find("cat".toCharArray()).id);
        Assert.assertTrue(tree.find("cats".toCharArray()).isWord);

    }

    @Test
    public void deleteTest() {
        PrefixTree tree = new PrefixTree();
        tree.insert("cat".toCharArray(), 1);
        tree.insert("door".toCharArray(), 2);
        tree.insert("cats".toCharArray(), 3);
        tree.insert("door".toCharArray(), 3);
        tree.insert("horse".toCharArray(), 3);
        System.out.println(tree.root);

        tree.delete("horse".toCharArray());
        System.out.println(tree.root);

        tree.delete("cat".toCharArray());
        System.out.println(tree.root);
        tree.delete("cats".toCharArray());
        System.out.println(tree.root);
    }
}
