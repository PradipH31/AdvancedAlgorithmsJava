package org.pradip.algorithm.suffixtree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree {

    public final char UNIQUE = '$';

    public SuffixNode root;
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

    /**
     * build suffix tree and set indexes on edges
     * <p>
     * banana$
     * 0123456
     */
    public void buildSuffixTree() {
        root = new SuffixNode(1, new End(0));
        root.index = -1;
        this.end = new End(-1);
        activePoint = new ActivePoint(root);
        for (int i = 0; i < input.length; i++) {
            startPhase(i);
        }
        setIndex(root, 0, input.length);
    }

    /**
     * banana$
     * 0123456
     * E000111
     * L000123
     *
     * @param i
     */
    public void startPhase(int i) {
        SuffixNode lastInternalNode = null;
        end.end++;
        remaining++;
        while (remaining > 0) {
            if (activePoint.activeLength == 0) {
                //Rule 3 extension
                //checking if the active point and active node have anything
                if (activePoint.activeNode.children[input[i]] != null) {
                    //start is the starting index of the current suffix
                    activePoint.activeEdge = activePoint.activeNode.children[input[i]].start;
                    activePoint.activeLength++;
                    System.out.println("Phase(" + input[i] + ") Rule 3 Ext - applied(" + activePoint.activeNode.start + ":" + input[i] + ")");
                    break;
                } else {
                    //if no existing node for the given character,
                    root.children[input[i]] = new SuffixNode(i, end);
                    remaining--;
                    System.out.println("Phase(" + input[i] + ") Rule 2 Ext - applied(" + activePoint.activeNode.start + ":" + input[i] + ")");
                }
            } else {
                //walk through the active points
                char c = getNextCharacter(i);
                if (c != 0) {
                    if (c == input[i]) {
                        SuffixNode edge = selectEdge();
                        if (lastInternalNode != null) {
                            lastInternalNode.suffixLink = edge;
                        }
                        System.out.println("Phase(" + input[i] + ") Rule 3 Ext - start(" +
                                edge.start + ":" + input[edge.start] + ") Next Char match: " + i + ":" + input[i] + "-" + c);
                        walkDown(i);
                        break;
                    } else {
                        SuffixNode edge = selectEdge();
                        //anana$ [1,6]
                        int currentStart = edge.start;
                        // change 1 to 4 - na$ [4,6]
                        edge.start += activePoint.activeLength;
                        //new node [1,3] - [4,6]
                        SuffixNode internalNode = new SuffixNode(currentStart, new End(currentStart + activePoint.activeLength - 1));
                        // new node [6]
                        SuffixNode leafNode = new SuffixNode(i, end);

                        internalNode.children[input[edge.start]] = edge;
                        internalNode.children[input[leafNode.start]] = leafNode;
                        internalNode.index = -1;

                        activePoint.activeNode.children[input[internalNode.start]] = internalNode;
                        if (lastInternalNode != null) {
                            lastInternalNode.suffixLink = internalNode;
                        }

                        lastInternalNode = internalNode;
                        internalNode.suffixLink = root;

                        System.out.println("Phase(" + input[i] + ") Rule 2 Ext - changed node (" + internalNode.start + "-" + internalNode.end.end + "):" + input[internalNode.start]);
                        System.out.println("Phase(" + input[i] + ") Rule 2 Ext --- children[edge] " + edge.start + ":" + input[edge.start]);
                        System.out.println("Phase(" + input[i] + ") Rule 2 Ext --- children[leafNode] " + leafNode.start + ":" + input[leafNode.start]);

                    }
                } else {
                    SuffixNode edge = selectEdge();
                    edge.children[input[i]] = new SuffixNode(i, end);
                    if (lastInternalNode != null) {
                        lastInternalNode.suffixLink = edge;
                    }
                    lastInternalNode = edge;
                }
                if (activePoint.activeNode != root) {
                    activePoint.activeNode = activePoint.activeNode.suffixLink;
                } else {
                    activePoint.activeEdge = activePoint.activeEdge + 1;
                    activePoint.activeLength--;
                }
                remaining--;
            }
        }
    }

    public char getNextCharacter(int i) {
        SuffixNode edge = selectEdge();

        if (edgeSize(edge) >= activePoint.activeLength) {
            return input[edge.start + activePoint.activeLength];
        } else if (edgeSize(edge) + 1 == activePoint.activeLength) {
            if (edge.children[input[i]] != null) {
                return input[i];
            }
        } else {
            activePoint.activeNode = edge;
            activePoint.activeEdge = activePoint.activeEdge + edgeSize(edge) + 1;
            activePoint.activeLength = activePoint.activeLength - edgeSize(edge) - 1;
            return getNextCharacter(i);
        }
        return 0;
    }

    public int edgeSize(SuffixNode edge) {
        return (edge.end.end - edge.start);
    }

    public SuffixNode selectEdge() {
        return activePoint.activeNode.children[input[activePoint.activeEdge]];
    }

    public void walkDown(int index) {
        SuffixNode edge = selectEdge();
        if (edgeSize(edge) < activePoint.activeLength) {
            activePoint.activeNode = edge;
            activePoint.activeLength = activePoint.activeLength - edgeSize(edge);
            activePoint.activeLength = edge.children[input[index]].start;
        } else {
            activePoint.activeLength++;
        }
    }

    public void dfsTraversal() {
        List<Character> result = new ArrayList<Character>();
        for (SuffixNode node : root.children) {
            dfsTraversal(node, result);
        }
    }

    /**
     * @param node
     * @param result
     */
    private void dfsTraversal(SuffixNode node, List<Character> result) {
        if (node == null) return;
        int end = node.end.end;
        if (node.index != -1) {
            for (int i = node.start; i <= end; i++) {
                result.add(input[i]);
            }

            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
            System.out.println(" index = " + node.index);

            for (int i = node.start; i <= end; i++) {
                result.remove(result.size() - 1);
            }
            return;
        }
        for (int i = node.start; i <= end; i++) {
            result.add(input[i]);
        }

        for (SuffixNode s : node.children) {
            dfsTraversal(s, result);
        }

        for (int i = node.start; i <= end; i++) {
            result.remove(result.size() - 1);
        }
    }

    private void setIndex(SuffixNode root, int val, int size) {
        if (root == null) return;
        val = val + root.end.end - root.start + 1;
        if (root.index != -1) {
            root.index = size - val;
            return;
        }

        for (SuffixNode node : root.children) {
            setIndex(node, val, size);
        }
    }
}
