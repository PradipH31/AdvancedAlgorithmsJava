package org.pradip.algorithm.prefixtree;

public class PrefixTree {

    public PrefixNode root;

    public PrefixTree() {
        root = new PrefixNode();
    }

    /**
     * cat
     *
     * @param word
     * @param id
     */
    public void insert(char[] word, int id) {
        PrefixNode current = root;
        for (int i = 0; i < word.length; i++) {
            if (current.hasChildren(word[i])) {
                current = current.getChildren(word[i]);
            } else {
                PrefixNode node = new PrefixNode(word[i], 0);
                current.addChildren(node);
                current = node;
            }
        }
        current.isWord = true;
        current.id = id;
    }

}
