package org.pradip.algorithm.huffman;

import java.util.PriorityQueue;

/**
 * steps
 * frequency table
 * priority queue
 * Make Huffman tree
 * pull the 2 least used from queue
 */
public class Huffman {

    public final int CHARACTER_LIMIT = 256;

    public int[] createFrequencyTable(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        for (int i = 0; i < text.length; i++) {
            frequencies[text[i]] = frequencies[text[i]] + 1;
        }
        return frequencies;
    }

    public PriorityQueue<HuffmanNode> createPriorityQueue(int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(1, new FrequencyComparator());
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode((char) i, frequencies[i]));
            }
        }
        return queue;
    }

    public HuffmanNode createHuffmanTree(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode root = null;
        while (queue.size() > 0) {
            root = pullLeastUsedAsNode(queue);
            if (queue.size() > 0) queue.add(root);
        }
        return root;
    }

    //we're just returning a node with the top 2 nodes in the queue
    public HuffmanNode pullLeastUsedAsNode(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode node1 = queue.poll();
        HuffmanNode node2 = queue.poll();
        HuffmanNode root = new HuffmanNode('-', node1.frequency + node2.frequency);
        root.left = node1;
        root.right = node2;
        return root;
    }

}
