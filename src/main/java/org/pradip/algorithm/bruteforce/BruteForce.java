package org.pradip.algorithm.bruteforce;

import java.util.Arrays;

/**
 * Brute Force Algorithm
 * Search for a pattern from an array and
 * returns the index if the pattern is found
 * Returns -1 if no match are found
 */
public class BruteForce {
    /**
     * array = abcadef
     * pattern = def
     * firstMatch -> 4
     *
     * @param array
     * @param pattern
     * @return
     */
    public int firstMatch(char[] array, char[] pattern) {
        for (int a = 0; a <= array.length - pattern.length; a++) {
            for (int p = 0; p < pattern.length; p++) {
                if (array[a + p] != pattern[p]) break;
                if (p == pattern.length - 1) return a;
            }
        }
        return -1;
    }

    /**
     * returns the array of indices where the pattern matches the string
     *
     * @param array
     * @return
     */
    public int[] everyMatch(char[] array, char[] pattern) {
        int[] found = new int[array.length];
        Arrays.fill(found, -1);
        int index = 0;
        for (int a = 0; a <= array.length - pattern.length; a++) {
            for (int p = 0; p < pattern.length; p++) {
                if (array[a + p] != pattern[p]) break;
                if (p == pattern.length - 1) {
                    found[index++] = a;
                }
            }
        }
        return found;
    }
}
