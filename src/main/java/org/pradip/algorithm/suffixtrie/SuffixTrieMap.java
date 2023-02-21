package org.pradip.algorithm.suffixtrie;

public class SuffixTrieMap {

    public TrieNodeMap root = new TrieNodeMap();

    public SuffixTrieMap(String text) {
        root.insertSuffix(text);
    }
}
