package org.pradip.algorithm.boyermoore;

/**
 * Boyer Moore Horspool
 * simplified version of Boyer Moore Algorithm
 * only implement the bad character table
 */
public class BoyerMooreHorspool {

    private final int ASCII_TABLE_SIZE = 256;

    public int search(char[] array, char[] pattern) {
        if (pattern == null || pattern.length == 0) return 0;
        if (array == null) return -1;

        int[] table = preprocessTable(pattern);

        //we start comparing the pattern from the right and the subset of the array of length pattern from the right
        //i traverses through the array and starts from the length of pattern
        for (int i = (pattern.length - 1); i < array.length; ) {

            //we go to this loop only if the character at the end of the pattern matches with the character at the
            //i th index of the array, at the start, i == j, but as the program continues, value of i will increase
            //if the substring of the array fails to match and value of j decreases if the substring keeps on matching
            for (int j = (pattern.length - 1); pattern[j] == array[i]; i--, j--) {

                //when the characters at the end of the pattern and the same length of the word keep on matching, we
                //check the characters before them and if j becomes 0, we've got a match
                if (j == 0) return i;
            }

            /**
             * The most important part of the code
             *
             *                  If the last character of the pattern and the character at the i th index of the array
             *                  don't match, then we change the value of i, which traverses the array by the value of
             *                  the ASCII value of the i th index of table array.
             *                  For example,
             *                                  WORD:       learning
             *                                  Pattern:    abc
             *                                  Table:      abcdefghijklmnop...256
             *                                              2133333333333333...333
             *                  Here, abc and lea try to match, first a from lea and c from abc match, they are not
             *                  equal, so we increase i by le[a] the value of a in the table:2
             *                  Next iteration, we match arn with abc. c and n don't match and we add table[n]=3 to i
             *
             */
            i += table[array[i]];
        }
        return -1;
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
    public int[] preprocessTable(char[] pattern) {
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

}
