package org.example;

import org.example.Trie.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private Trie trie;
    private String pathToFile = ".\\airports.dat";

    private RandomAccessFile randomAccessFile;

    List<Character> line = new ArrayList<>();

    private CsvParser csvParser;

    public Solver(int column, String path)  {
        csvParser = new CsvParser(column);
        if (path != null) this.pathToFile = path;
        try {
            randomAccessFile = new RandomAccessFile(this.pathToFile, "r");
        } catch (FileNotFoundException e) {
            System.out.println("Файл по этому пути не найден : " + pathToFile);
            System.exit(1);
        }

        trie = new Trie();
        readFile();
        trie.sortNodes();
    }

    private void readFile() {
        try {
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                readLine();
                trie.insert(csvParser.parse(line));
                clearLine();
                trie.setCurrentOffset(randomAccessFile.getFilePointer());
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочесть данные о файле : (положение курсора или длину файла)");
            System.exit(1);
        }
    }

    private void readLine() {
        try {
            char c;
            while (randomAccessFile.getFilePointer() < randomAccessFile.length() &&
                    (c = (char) randomAccessFile.readByte()) != '\n') {
                line.add(c);
            }
        } catch (IOException e) {
            System.out.println("Не удалось прочесть байт из файла");
            System.exit(1);
        }
    }

    private void readLineWithBuffer() {
        try {
            byte[] buffer = new byte[1024];
            randomAccessFile.read(buffer);
            int i = 0;
            char c;
            while ((c = (char) buffer[i]) != '\n') {
                line.add(c);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearLine() {
        line.clear();
    }


    public void closeFile() {
        try {
            randomAccessFile.close();
        } catch (IOException e) {
            System.out.println("Не удалось закрыть файл");
            System.exit(1);
        }

    }

    public void findLinesByPrefix(String prefix) {
        if (csvParser.isColumnContainsOnlyString()) {
            prefix = "\"" + prefix;
        }
        findLines(prefix);
    }

    private void findLines(String prefix) {
        try {
            int counter = 0;
            for (Long offset : trie.findAllWordsOffsetsByPrefix(prefix)) {
                randomAccessFile.seek(offset);
                readLineWithBuffer();
                printLine();
                clearLine();
                counter++;
            }
            trie.clearOffsets();
            System.out.print("Количество найденных строк : " + counter + "   ");
        } catch (IOException e) {
            System.out.println("При поиске строк с этим префиксом произошла ошибка : " + prefix);
        }

    }

    private void printLine() {
        for (Character c : csvParser.parse(line)) {
            System.out.print(c);
        }
        System.out.print('[');
        for (Character c : line) {
            System.out.print(c);
        }
        System.out.println(']');
    }


}
