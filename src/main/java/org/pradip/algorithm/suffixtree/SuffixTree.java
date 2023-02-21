package org.pradip.algorithm.suffixtree;

public class SuffixTree {

    public final char UNIQUE = '$';

    private SuffixNode root;
    private ActivePoint activePoint;
    private char input[];
    private int remaining;
    private End end;

    /**
     * banana -> banana$
     *
     * @param input
     */
    public SuffixTree(char[] input) {
        this.input = addUnique(input);
    }

    public char[] addUnique(char[] input) {
        char c[] = new char[input.length + 1];
        for (int x = 0; x < input.length; x++) {
            c[x] = input[x];
        }
        c[input.length] = UNIQUE;
        return c;
    }
}
