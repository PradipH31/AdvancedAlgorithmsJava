package org.pradip.algorithm.zalgorithm;

/**
 * uses z table to improve search performance
 */
public class ZAlgorithm {

    private char SEPARATOR = '$';

    public int search(char[] pattern, char[] array) {
        int[] z = createZTable(pattern, array);
        int i = pattern.length + 1;
        while (i < z.length) {
            if (z[i] == pattern.length) return (i - pattern.length - 1);
            i++;
        }
        return -1;
    }

    public int[] searchAll(char[] pattern, char[] array) {
        int[] result = new int[array.length];
        int[] z = createZTable(pattern, array);
        int i = pattern.length + 1;
        while (i < z.length) {
            if (z[i] == pattern.length) result[i - pattern.length - 1] = z[i];
            i++;
        }
        return result;
    }

    /**
     * regarding the length of z array:
     * pattern = abc
     * word =    aababcba
     * z table=  abc$aababcba
     * abc$aababcba
     * z table output   000012030000
     *
     * @param pattern
     * @param array
     * @return
     */
    public int[] createZTable(char[] pattern, char[] array) {
        int[] z = new int[pattern.length + array.length + 1];
        char[] longString = createLongString(pattern, array);
        int left = 0;
        int right = 0;
        for (int i = 1; i < longString.length; i++) {

            //entry of main program
            //at the start, we start comparing 0th character and 1st character,
            // increasing right by the number of matches
            // then, we subtract right by 1, if right increases only by 1, we can check the immediately next
            // but if it increases more than 1, we go to else as when i increases by 1, right will have increased by 2
            if (i > right) {
                left = right = i;
                while (right < longString.length && longString[right - left] == longString[right]) right++;
                z[i] = right - left;
                right--;
            }
            // inside else, not sure what's going on?
            else {
                int k = i - left;
                //shouldn't i - left be 1 always?
                //so, z[1], minimum 0 should be smaller than (right - i + 1), which is minimum 1
                //this is used when the word and the pattern is very long.
                //in that case, we use the z index of previous values that have the same z-index and replace them
                if (z[k] < right - i + 1) z[i] = z[k];
                    //but if there are further elements after the number of correct preix matches,
                    //then we need to compute the z-index again
                    //https://youtu.be/CpZh4eF8QBw?t=457
                else {
                    left = i;
                    while (right < longString.length && longString[right - left] == longString[right]) right++;
                    z[i] = right - left;
                    right--;
                }
            }
        }

        return z;
    }

    public char[] createLongString(char[] pattern, char[] array) {
        char[] s = new char[pattern.length + array.length + 1];
        for (int i = 0; i < pattern.length; i++) {
            s[i] = pattern[i];
        }
        s[pattern.length] = SEPARATOR;
        for (int i = 0; i < array.length; i++) {
            s[pattern.length + 1 + i] = array[i];
        }
        return s;
    }

}
