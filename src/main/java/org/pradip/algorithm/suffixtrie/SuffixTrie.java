package org.pradip.algorithm.suffixtrie;

public class SuffixTrie {

    public TrieNode root = new TrieNode();

    public SuffixTrie(String text) {
        root.insertSuffix(text);
    }
}
