package org.pradip.algorithm.suffixtree;

public class ActivePoint {

    SuffixNode activeNode;
    int activeEdge;
    int activeLength;

    ActivePoint(SuffixNode node) {
        this.activeNode = node;
        this.activeEdge = -1;
        this.activeLength = 0;
    }

    ActivePoint(SuffixNode activeNode, int activeEdge, int activeLength) {
        this.activeEdge = activeEdge;
        this.activeLength = activeLength;
        this.activeNode = activeNode;
    }

    @Override
    public String toString() {
        return "ActivePoint[node=]" + activeNode + ", edge=" + activeEdge + ", length" + activeLength + "]";
    }
}
