package org.example;

import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int column = 1;
        String pathToFile = null;

        if (args.length >= 1) column = Integer.parseInt(args[0]) - 1;
        if (args.length == 2) pathToFile = args[1];
        System.out.println("Подождите немного. Была начата подготовка к чтению.");
        Solver solver = new Solver(column, pathToFile);
        System.out.println("Подготовка закончена.");
        Scanner scanner = new Scanner(System.in);
        try {
            solver.readFile();
            String line;
            System.out.println("Введите строку.");
            while(!(line = scanner.nextLine()).equals("!quit")) {
                line = line.toLowerCase(Locale.ROOT);
                long startTime = System.currentTimeMillis();
                solver.findLinesByPrefix(line);
                long endTime = System.currentTimeMillis();
                System.out.println("Затраченное время :  " + (endTime - startTime) + " мс");
            }
            solver.closeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}