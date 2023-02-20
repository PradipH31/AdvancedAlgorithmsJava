package org.pradip.algorithm.kmp;

public class KnuthMorrisPratt {

    /**
     * returns the index for the first match of the pattern into the index
     * returns -1 in case no match is found
     * <p>
     * i
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13
     * a b a z a c a b a b a  b  a  c
     * <p>
     * j
     * a b a b a c
     * 0 0 1 2 3 0
     *
     * @param array
     * @param pattern
     * @return
     */
    public int search(char[] array, char[] pattern) {
        int[] lsp = computeLSPTable(pattern);
        int j = 0;
        for (int i = 0; i < array.length; i++) {


            //In this while loop, we're comparing the pattern(starting at index 0) to the parts of array of words
            //When we find matches for every subsequent character,
            //                              we traverse the array of words and the pattern by 1 character
            //When we don't find the match for the current character,
            //                      this while loop will move the j index
            //                          to the n(number of unique characters + 1) left of the current index of j
            // Example
            //              Word        a b a b a a b a a b f a b
            //              Pattern     a b a a b f a b
            //              LSP Table   0 0 1 1 2 0 1 2
            //              i traverses only through word and j traverses only through pattern
            //              value of j is changed using the lsp table and the loop
            //              Word        a b a b a a b a a b f a b
            //              Pattern         a b a a b f a b
            //              LSP Table       0 0 1 1 2 0 1 2
            //                  When i is 2, j is 0, pattern[j]=word[i]=a.
            //                  This continues for 5 iterations of i.
            //                  At the 5th iteration, i = 7, j = 5.
            //                  Now, j = lsp[4] = 2, word[7] = pattern[2] = b
            // Here, j has moved by 3 units(the first a b are unique,
            //          2 numbers with LSP table values first continuous 0s) left, restarting the comparison after the
            //          n units of the pattern with the i index of the word


            while (j > 0 && array[i] != pattern[j]) {
                j = lsp[j - 1];
            }
            if (array[i] == pattern[j]) {
                j++;
                if (j == pattern.length) {

                    // i is the index of the word where we found the end of the pattern,
                    // j is the length of the pattern + 1
                    // subtracting j - 1 from i gives the index of the start of the pattern in the word

                    return (i - (j - 1));
                }
            }
        } return -1;
    }

    /**
     * Compute the table of the longest suffix and longest prefix on pattern
     * To be used by Knuth-Morris-Pratt
     *
     * @param pattern
     * @return
     */
    public int[] computeLSPTable(char[] pattern) {
        int lsp[] = new int[pattern.length];
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {


            // In the method to compute LSP table, this while loop resets the value for j to 1 and effectively to 0


            while (j > 0 && pattern[i] != pattern[j]) {
                j = lsp[j - 1];
            }


            if (pattern[i] == pattern[j]) {
                lsp[i] = j + 1;
                j++;
            } else {
                lsp[i] = 0;
            }
        }
        return lsp;
    }

}
