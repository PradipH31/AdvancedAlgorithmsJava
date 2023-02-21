package org.pradip.algorithm.suffixtrie;

public class TrieNode {


    //better to use hashmap to save the 200 something extra memory
    public final int MAX_ARRAY = 256;
    public TrieNode[] children = new TrieNode[MAX_ARRAY];

    public TrieNode() {
        for (int i = 0; i < MAX_ARRAY; i++) {
            children[i] = null;
        }
    }

    public void insertSuffix(String text) {
        text = text + "$";
        for (int i = 0; i < text.length(); i++) {
            insertSuffix(text, i);
        }
    }

    /**
     * banana
     *
     * @param text
     * @param index
     */
    public void insertSuffix(String text, int index) {

        if (text.length() > index) {
            char current = text.charAt(index);
            if (children[current] == null) {
                children[current] = new TrieNode();
            }
            //++ index adds 1 first
            children[current].insertSuffix(text, ++index);
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int c = 0; c < children.length; c++) {
            if (children[c] != null) s += (char) c + "->" + children[c].toString();
        }
        return s;
    }

}
