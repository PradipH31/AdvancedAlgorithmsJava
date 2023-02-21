package org.Pradip.algorithm.huffman;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pradip.algorithm.huffman.Huffman;
import org.pradip.algorithm.huffman.HuffmanNode;

import java.util.PriorityQueue;

public class HuffmanTest {

    Huffman h;

    @Before
    public void init() {
        h = new Huffman();
    }

    @Test
    public void createFrequencyTableTest() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        Assert.assertEquals(h.CHARACTER_LIMIT, frequencies.length);
        Assert.assertEquals(2, frequencies[97]);
        Assert.assertEquals(3, frequencies[98]);
        Assert.assertEquals(1, frequencies[99]);
        Assert.assertEquals(1, frequencies[100]);
    }

    @Test
    public void createPriorityQueueTest() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue<HuffmanNode> queue = h.createPriorityQueue(frequencies);
        Assert.assertEquals('c', queue.peek().c);
        Assert.assertEquals(1, queue.poll().frequency);

        Assert.assertEquals('d', queue.peek().c);
        Assert.assertEquals(1, queue.poll().frequency);

        Assert.assertEquals('a', queue.peek().c);
        Assert.assertEquals(2, queue.poll().frequency);

        Assert.assertEquals('b', queue.peek().c);
        Assert.assertEquals(3, queue.poll().frequency);

        Assert.assertEquals(0, queue.size());
    }

    @Test
    public void pullLeastUsedAsNodeTest() {
        int[] frequencies = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue<HuffmanNode> queue = h.createPriorityQueue(frequencies);

        //c=1 d=1 a=2 b=3
        HuffmanNode root = h.pullLeastUsedAsNode(queue);
        //a=2 b=3
        Assert.assertEquals('-',root.c);
        Assert.assertEquals(2,root.frequency);

        Assert.assertEquals('c',root.left.c);
        Assert.assertEquals(1,root.left.frequency);
        Assert.assertEquals(null, root.left.left);
        Assert.assertEquals(null, root.left.right);

        //2nd call now with only a and b

        root = h.pullLeastUsedAsNode(queue);
        //a=2 b=3
        Assert.assertEquals('-',root.c);
        Assert.assertEquals(5,root.frequency);

        Assert.assertEquals('a',root.left.c);
        Assert.assertEquals(2,root.left.frequency);
        Assert.assertEquals(null, root.left.left);
        Assert.assertEquals(null, root.left.right);
    }

}
