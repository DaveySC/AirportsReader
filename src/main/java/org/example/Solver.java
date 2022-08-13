package org.example;

import org.example.Trie.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private Trie trie;
    private String pathToFile = "src/main/resources/airports.dat";
    List<Long> offsets = new ArrayList<>();

    private RandomAccessFile randomAccessFile;

    List<Character> line = new ArrayList<>();

    private CsvParser csvParser;
    public Solver(int column, String path) throws Exception {
        csvParser = new CsvParser(column);
        if (path != null) this.pathToFile = path;
        randomAccessFile = new RandomAccessFile(this.pathToFile, "r");
        trie = new Trie();
        readFile();
        trie.sortNodes();
    }

    public void readFile() throws IOException {
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            readLine();
            trie.insert(csvParser.parse(line));
            clearLine();
            trie.setCurrentOffset(randomAccessFile.getFilePointer());
        }
    }

    private void readLine() throws IOException {
        char c;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length() && (c = (char) randomAccessFile.readByte()) != '\n') {
            line.add(c);
        }
    }

    private void clearLine() {
        line.clear();
    }


    public void closeFile() throws IOException {
        randomAccessFile.close();
    }

    public void findLinesByPrefix(String prefix) throws IOException {
        int counter = 0;
        for (Long offset : trie.findAllWordsOffsetsByPrefix(prefix)) {
            randomAccessFile.seek(offset);
            readLine();
            printLine();
            clearLine();
            counter++;
        }
        trie.clearOffsets();
        System.out.println("Количество найденных строк : " + counter + "   ");
    }

    private void printLine() {
        for (Character c : line) {
            System.out.print(c);
        }
        System.out.println();
    }


}
