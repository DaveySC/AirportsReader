package org.example.Trie;


import java.util.*;

public class Trie {
    private Node rootNode;

    private static long currentOffset = 0;

    private static List<Long> offsets = new ArrayList<>();

    public Trie() {
        rootNode = new Node(' ');
    }

    public void insert(Queue<Character> word) {
        insert(word, rootNode);
    }

    public void insert(Queue<Character> word, Node node) {
        if (word.size() == 0) {
            node.setOffset(currentOffset);
            return;
        }
        if (node.getChildren() == null) {
            node.setChildren(new ArrayList<>());
        }
        char c = word.poll();
        Node child = findNodeByChar(c, node);
        if (child == null) {
            child = new Node(c);
            node.addChild(child);
        }
        insert(word, child);
    }

    private Node findNodeByChar(char c, Node actualNode) {
        List<Node> children = actualNode.getChildren();
        if (children != null) {
            for(Node node: children) {
                if (node.getLetter() == c) {
                    return node;
                }
            }
        }
        return null;
    }


    public void setCurrentOffset(long offset) {
        currentOffset = offset;
    }

    public void sortNodes() {
        sortNodes(rootNode);
    }

    private void sortNodes(Node node) {
        if (node.getChildren() == null) return;
        Collections.sort(node.getChildren());
        for (Node n : node.getChildren()) {
            sortNodes(n);
        }
    }
    public List<Long> findAllWordsOffsetsByPrefix(String prefix) {
        findAllWordsOffsetsByPrefix(prefix, rootNode);
        return offsets;
    }
    private void findAllWordsOffsetsByPrefix(String prefix, Node node) {
        if (prefix.length() == 0) {
            findAllWordsOffsetsFromNode(node);
            return;
        }
        char c = prefix.charAt(0);
        Node child = findNodeByChar(c, node);
        if (child == null) return;
        findAllWordsOffsetsByPrefix(prefix.substring(1), child);
    }

    private void findAllWordsOffsetsFromNode(Node node) {
        if (node.getOffset() != -1) offsets.add(node.getOffset());
        if (node.getChildren() == null) {
            return;
        }
        for (Node child : node.getChildren()) {
            findAllWordsOffsetsFromNode(child);
        }
    }

    public void clearOffsets() {
        offsets.clear();
    }
}
