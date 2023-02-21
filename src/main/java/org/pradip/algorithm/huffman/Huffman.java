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
    public StringBuilder header = new StringBuilder();

    public int[] createFrequencyTable(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        for (int i = 0; i < text.length; i++) {
            frequencies[text[i]] = frequencies[text[i]] + 1;
        }
        return frequencies;
    }

    //headers separate the encoding information from the encoded message
    public PriorityQueue<HuffmanNode> createPriorityQueue(int[] frequencies) {
        header = new StringBuilder();
        header.append((char) 1);
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(1, new FrequencyComparator());
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode((char) i, frequencies[i]));
                header.append(":").append((char) i).append(frequencies[i]);
            }
        }
        header.append((char) 2);
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

    public String compress(char[] text) {
        int[] frequencies = createFrequencyTable(text);
        PriorityQueue<HuffmanNode> queue = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(queue);
        String compressed = header.toString() + encodeString(text, root);
        return compressed;
    }

    public char[] decompress(char[] encodedText) {
        if (encodedText[0] != (char) 1) return null;
        int[] frequencies = parseHeaderAsFrequency(encodedText);
        PriorityQueue<HuffmanNode> queue = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(queue);
        String decompressed = decodeString(encodedText, root);
        return decompressed.toCharArray();
    }


    public String encodeString(char[] text, HuffmanNode root) {
        StringBuilder s = new StringBuilder();
        String array[] = new String[CHARACTER_LIMIT];
        generateBites(array, root, new StringBuilder());
        for (int i = 0; i < text.length; i++) {
            s.append(array[text[i]]);
        }
        return s.toString();
    }

    /**
     * \u0001:a234\u0002010101010
     * aabbbcd
     *
     * @param text
     * @param root
     * @return
     */
    public String decodeString(char[] text, HuffmanNode root) {
        StringBuilder s = new StringBuilder();
        HuffmanNode currentNode = root;
        for (int i = header.length(); i < text.length; i++) {
            if (text[i] - '0' == 0) currentNode = currentNode.left;
            else if (text[i] - '0' == 1) currentNode = currentNode.right;
            if (currentNode.left == null && currentNode.right == null) {
                s.append(currentNode.c);
                currentNode = root;
            }
        }
        return s.toString();
    }

    public void generateBites(String[] array, HuffmanNode root, StringBuilder s) {
        if (root.c == '-') {
            s.append("0");
            generateBites(array, root.left, s);
            s.append("1");
            generateBites(array, root.right, s);
        } else {
            System.out.println(root.c + " - " + s.toString());
            array[root.c] = s.toString();
            s.deleteCharAt(s.length() - 1);
        }
    }


    /**
     * \u0001:a234\u0002010101010
     *
     * @param text
     * @return
     */
    public int[] parseHeaderAsFrequency(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        int i = 0;
        for (; i < text.length && text[i] != (char) 2; i++) {
            header.append(text[i]);
            if (text[i] == ':') {
                i++;
                header.append(text[i]);
                int f = 0;
                int m = 1;
                int j = i + 1;
                for (; j < text.length && text[j] != (char) 2 && text[j] != ':'; j++) {
                    f = (f * m) + text[j] - '0';
                    if (f != 0) m = 10;
                    header.append(text[i] - '0');
                }
                frequencies[text[i]] = f;
                i = j - 1;
            }
        }
        return frequencies;
    }

}
