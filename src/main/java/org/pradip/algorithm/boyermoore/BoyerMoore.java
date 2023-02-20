package org.pradip.algorithm.boyermoore;

/**
 * Boyer Moore Algorithm
 * searches for a pattern in a chara array
 * uses 2 tables to improve O(time complexity)
 */
public class BoyerMoore {

    /**
     * baidai
     * 1st round 6,7,8,9,10,11
     * Here, no prefix as bai cannot equal dai
     * So, index 0 of table 6(length of pattern) and keeps on increasing for subsequent indices
     *
     * @param pattern
     * @return
     */
    public int[] preprocessSuffixTable(char[] pattern) {
        int[] table = new int[pattern.length];
        int lastPrefixPosition = pattern.length;
        for (int i = pattern.length; i > 0; --i) {
            if (isPrefix(pattern, i)) lastPrefixPosition = i;
            table[pattern.length - i] = lastPrefixPosition - i + pattern.length;
        }
        return table;
    }

    /**
     * Just a method to check the prefix starting from given index and the whole char from 0
     * <p>
     * Example
     * baibai
     * j    i
     * Keep matching i and j, moving i left
     * Once a match has been found, start moving j and i to the right
     * baibai -> baibai -> baibai -> baibai
     * j    i    j  i       j  i       j  i
     * If i reaches the end, return true
     * baicbaid
     * i won't reach end, returns false
     *
     * @param pattern
     * @param index
     * @return
     */
    public boolean isPrefix(char[] pattern, int index) {
        for (int i = index, j = 0; i < pattern.length; ++i, ++j) {
            if (pattern[i] != pattern[j]) return false;
        }
        return true;
    }

    /**
     * return the length of substring from the given index of the pattern that matches the suffix of the
     * pattern (substring of the pattern from the last index, going backwards)
     *
     * @param pattern
     * @param index
     * @return
     */
    public int suffixLength(char[] pattern, int index) {
        int len = 0;
        int j = pattern.length - 1;
        for (int i = index; i >= 0 && pattern[i] == pattern[j]; --i, --j) {
            len++;
        }
        return len;
    }

}
