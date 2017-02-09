package main.java.ru.innopolis.itcources.lab1;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Ирина on 06.02.2017.
 * <p>
 * Необходимо разработать программу, которая получает на вход список ресурсов,
 * содержащих набор чисел и считает сумму всех положительных четных.
 * Каждый ресурс должен быть обработан в отдельном потоке, набор должен содержать лишь числа,
 * унарный оператор "-" и пробелы. Общая сумма должна отображаться на экране и изменяться в
 * режиме реального времени. Все ошибки должны быть корректно обработаны, все API покрыто модульными тестами
 * <p>
 * <p>
 * 1432
 */
public class Main {
    public static boolean isInterrupt = false;

    public static void main(String args[]) {
        String fileNameIn = null;

        for (int i = 0; i < args.length; i++) {
            fileNameIn = args[i];
            File file = new File(fileNameIn);
            try {
                if (file.exists()) {
                    Thread thread = new RunThreads(file, i);
                    thread.start();
                } else {
                    isInterrupt = true;
                    throw new FileNotFoundException();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Please, check input parameters, this file is not found " + fileNameIn);
            } finally {
                if (isInterrupt) {
                    break;
                }
            }
        }
    }
}
