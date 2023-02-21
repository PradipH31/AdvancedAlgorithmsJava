package org.pradip.algorithm.suffixtrie;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeMap {


    public Map<Character, TrieNodeMap> children = new HashMap<Character, TrieNodeMap>(1);


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
            //++ index adds 1 first
            children.get(current).insertSuffix(text, ++index);
        }
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
