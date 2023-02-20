package org.pradip.algorithm.boyermoore;

/**
 * Boyer Moore Algorithm
 * searches for a pattern in a chara array
 * uses 2 tables to improve O(time complexity)
 */
public class BoyerMoore {

    private final int ASCII_TABLE_SIZE = 256;

    /**
     * After creating the suffix table and bad char table from horspool, we can now select the one with the max
     * value that gives us the maximum no. of characters we can skip
     * we skip the characters by moving the i value(which traverses through the word from the right to the left,0)
     *
      * @param array
     * @param pattern
     * @return
     */
    public int search(char[] array, char[] pattern) {
        if (pattern == null || pattern.length == 0) return 0;
        if (array == null) return -1;

        int[] badCharTable = preComputeBadCharTable(pattern);
        int[] suffixTable = preprocessSuffixTable(pattern);

        for (int i = pattern.length - 1, j; i < array.length; ) {
            for (j = pattern.length - 1; pattern[j] == array[i]; i--, j--) {
                if (j == 0) return i;
            }
            i += Math.max(suffixTable[pattern.length - 1 - j], badCharTable[i]);
        }

        return -1;
    }

    public int[] preprocessSuffixTable(char[] pattern) {
        int[] table = new int[pattern.length];
        computePrefix(pattern, table);
        computeSuffix(pattern, table);
        return table;
    }

    /**
     * creates bad character table.
     * adds pattern.length to every character that is not in the pattern
     * also adds pattern.length to the last character of the pattern in case it is unique
     * <p>
     * Example
     * max(1,p.length - index -1)
     * <p>
     * In repeating last character
     * test -> tes* -> tes
     * 3214 -> 3214 -> 121
     * <p>
     * abc -> ab*
     * 213 -> abc
     *
     * @param pattern
     * @return
     */
    public int[] preComputeBadCharTable(char[] pattern) {
        int[] table = new int[ASCII_TABLE_SIZE];

        //table represents all 265 ascii values
        //we're putting the length of the pattern as the value for all the ASCII characters
        for (int i = 0; i < ASCII_TABLE_SIZE; i++) {
            table[i] = pattern.length;
        }

        //looping through the pattern, we set the value of indices with ASCII values of characters in pattern with the
        //formula max(1, length - t- 1)
        //but we only do this upto the second last number
        //for the last number, we don't change in this lpp[
        for (int t = 0; t < pattern.length - 1; t++) {
            table[pattern[t]] = Math.max(1, pattern.length - t - 1);
        }

        //we check the last number in this loop, if the value of the index with ASCII value of last character is less
        //than the initially set max value, then the last character is repeating.
        //in this case, the value of pattern.length - t - 1 = 0, and max(1,0) is 1
        //so, we set the value of the index with ASCII value of last character as 1
        if (table[pattern[pattern.length - 1]] < pattern.length) {
            table[pattern[pattern.length - 1]] = 1;
        }

        return table;
    }

    /**
     * baidai
     * 1st round 1,0,5,0,0,0
     * the formula is table[matchlength] = length - 1 - prefixIndex + matchLength
     * So, table[2] = 6-1-2+2 = 5-2+2 = 5
     * table[0] = 5-prefixIndex+0=1
     * prefixIndex=4
     * The value in index 0 gives the last non suffix index by (length-1-value at index 0)
     *
     * @param pattern
     * @param table
     */
    public void computeSuffix(char[] pattern, int[] table) {
        for (int i = 0; i < pattern.length - 1; i++) {
            int len = suffixLength(pattern, i);
            table[len] = pattern.length - 1 - i + len;
        }
    }

    /**
     * baidai
     * 1st round 6,7,8,9,10,11
     * Here, no prefix as bai cannot equal dai
     * So, index 0 of table 6(length of pattern) and keeps on increasing for subsequent indices
     *
     * @param pattern
     * @param table
     */
    public void computePrefix(char[] pattern, int[] table) {
        int lastPrefixPosition = pattern.length;
        for (int i = pattern.length; i > 0; --i) {
            if (isPrefix(pattern, i)) lastPrefixPosition = i;
            table[pattern.length - i] = lastPrefixPosition - i + pattern.length;
        }
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
