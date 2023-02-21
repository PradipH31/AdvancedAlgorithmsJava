package org.pradip.algorithm.suffixtrie;

import java.util.LinkedList;
import java.util.List;

public class TrieNode {


    //better to use hashmap to save the 200 something extra memory
    public final int MAX_ARRAY = 256;
    public TrieNode[] children = new TrieNode[MAX_ARRAY];
    List<Integer> indexes = new LinkedList<Integer>();

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
            //if a node already exists, we add the index of the new occurence of the series of characters that are
            //exactly same
            children[current].indexes.add(index);
            //++ index adds 1 first
            children[current].insertSuffix(text, ++index);
        }
    }

    public List<Integer> search(String pattern) {
        return search(pattern, 0);
    }

    public boolean isSuffix(String pattern) {
        return isSuffix(pattern, 0);
    }

    public boolean isSuffix(String pattern, int startPosition) {
        //if the last node in the tree does not have $, ther are still characters remaining
        //so, it is not a suffix, but maybe a substring
        if (pattern.length() == startPosition) {
            return (children['$'] != null);
        }
        if (children[pattern.charAt(startPosition)] != null) {
            return children[pattern.charAt(startPosition)].isSuffix(pattern, ++startPosition);
        }
        return false;
    }

    public boolean isSubstring(String pattern) {
        List<Integer> indexes = search(pattern);
        return (indexes != null);
    }

    private List<Integer> search(String pattern, int startPosition) {
        //we're only returning this linkedlist of indexes
        //the recursion checks for the exact order of the characters and ends up in this line
        //at the end, this will be returned recursively
        if (pattern.length() == startPosition) return indexes;
        if (children[pattern.charAt(startPosition)] != null) {
            return children[pattern.charAt(startPosition)].search(pattern, ++startPosition);
        }
        return null;
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
