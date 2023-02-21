package org.pradip.algorithm.suffixtrie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TrieNodeMap {


    public Map<Character, TrieNodeMap> children = new HashMap<Character, TrieNodeMap>(1);
    List<Integer> indexes = new LinkedList<Integer>();


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
            if (children.get(current) == null) {
                children.put(current, new TrieNodeMap());
            }
            children.get(current).indexes.add(index);
            //++ index adds 1 first
            children.get(current).insertSuffix(text, ++index);
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
            return (children.get('$') != null);
        }
        if (children.get(pattern.charAt(startPosition)) != null) {
            return children.get(pattern.charAt(startPosition)).isSuffix(pattern, ++startPosition);
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
        if (children.get(pattern.charAt(startPosition)) != null) {
            return children.get(pattern.charAt(startPosition)).search(pattern, ++startPosition);
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (Character c : children.keySet()) {
            s += c + "->" + children.get(c).toString();
        }
        return s;
    }

}
