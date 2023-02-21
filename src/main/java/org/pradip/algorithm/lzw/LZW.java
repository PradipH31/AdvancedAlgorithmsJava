package org.pradip.algorithm.lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {

    public List<Integer> compress(String text) {
        int dictionarySize = 256;
        if (text == null) return null;
        List<Integer> result = new ArrayList<Integer>();
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put("" + (char) i, i);
        }

        String previous = "";
        for (char c : text.toCharArray()) {
            String combined = previous + c;
            if (dictionary.containsKey(combined)) {
                previous = combined;
            } else {
                //we add the value of the previous key every time we find a new one
                //we will always find a new one as we are combining the characters
                result.add(dictionary.get(previous));
                dictionary.put(combined, dictionarySize++);
                previous = "" + c;
            }
        }

        if (!previous.equals("")) result.add(dictionary.get(previous));
        return result;
    }

    public String decompress(List<Integer> compressed) {
        if (compressed == null) return null;
        int dictionarySize = 256;

        Map<Integer, String> dictionary = new HashMap<Integer, String>();
        for (int i = 0; i < dictionarySize; i++) {
            dictionary.put(i, "" + (char) i);
        }

        //first element in the list, doesn't need to get a new value in the key map pair
        String previous = "" + (char) (int) compressed.remove(0);
        StringBuilder result = new StringBuilder(previous);

        for (int j : compressed) {
            String combined;
            if (dictionary.containsKey(j)) {
                combined = dictionary.get(j);
            } else if (j == dictionarySize) {
                combined = previous + previous.charAt(0);
            } else {
                return "-1";
            }
            result.append(combined);
            dictionary.put(dictionarySize++, previous + combined.charAt(0));
            previous = combined;
        }
        return result.toString();
    }

}
