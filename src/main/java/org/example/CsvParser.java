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

    private boolean isColumnContainsOnlyString = false;

    public Queue<Character> parse(List<Character> line) {
        parsedLine.clear();
        isColumnContainsOnlyString = false;
        int i = getDividerPosition(line);
        if (i == -1) {
            System.out.println("Введён неправильный номер колонки");
            System.exit(1);
        }
        while (i < line.size() && line.get(i) != ',') {
            if (line.get(i) == '"') isColumnContainsOnlyString = true;
            parsedLine.add(Character.toLowerCase(line.get(i)));
            i += 1;
        }
        return parsedLine;
    }

    private int getDividerPosition(List<Character> list) {
        int k = 0;
        for (int i = 0; i < list.size(); i++) {
            if (k == column) {
                return i;
            }
            if (list.get(i) == ',') k++;
        }
        return -1;
    }

    public boolean isColumnContainsOnlyString() {
        return isColumnContainsOnlyString;
    }

}
