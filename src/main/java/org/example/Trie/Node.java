package org.example.Trie;

import java.util.Comparator;
import java.util.List;

class Node implements Comparable<Node> {

    private char letter;
    private List<Node> children;
    private long offset = -1;

    public Node(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public List<Node> getChildren() {
        return children;
    }

    public long getOffset() {
        return offset;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    @Override
    public int compareTo(Node other) {
        return Character.compare(this.letter, other.getLetter());
    }
}