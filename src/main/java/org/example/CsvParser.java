package org.example;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CsvParser {

    private final int column;

    public CsvParser(int column) {
        this.column = column;
    }

    private Queue<Character> parsedLine = new ArrayDeque<>();

    public Queue<Character> parse(List<Character> line) {
        parsedLine.clear();
        int i = getDividerPosition(line);
        while (i < line.size() && line.get(i) != ',') {
            if (line.get(i) == '"') {
                i++;
                continue;
            }
            parsedLine.add(Character.toLowerCase(line.get(i)));
            i += 1;
        }
        return parsedLine;
    }

    private int getDividerPosition(List<Character> list) {
        int k = 0;
        for (int i = 0; i < list.size(); i++) {
            if (k == column) {
                k = i;
                break;
            }
            if (list.get(i) == ',') k++;
        }
        return k;
    }

}
