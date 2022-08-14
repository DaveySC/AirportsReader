package org.example;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int column = 0;
        String pathToFile = null;
        if (args.length >= 1) column = Integer.parseInt(args[0]) - 1;
        if (args.length == 2) pathToFile = args[1];
        System.out.println("Подождите немного. Была начата подготовка к чтению.");
        Solver solver = new Solver(column, pathToFile);
        System.out.println("Подготовка закончена.");
        Scanner scanner = new Scanner(System.in);

        String line;
        System.out.println("Введите строку.");
        while(!(line = scanner.nextLine()).equals("!quit")) {
            line = line.toLowerCase(Locale.ROOT);
            long startTime = System.nanoTime();
            solver.findLinesByPrefix(line);
            long endTime = System.nanoTime();
            System.out.println("Затраченное время :  " + (endTime - startTime) / 1000000 + " мс");
        }
        solver.closeFile();
    }


}