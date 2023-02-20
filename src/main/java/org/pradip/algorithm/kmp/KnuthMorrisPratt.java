package org.pradip.algorithm.kmp;

public class KnuthMorrisPratt {

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
