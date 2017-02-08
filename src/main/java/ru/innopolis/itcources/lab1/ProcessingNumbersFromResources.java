package main.java.ru.innopolis.itcources.lab1;

import java.util.concurrent.*;

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
public class ProcessingNumbersFromResources extends Thread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(args.length);
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            String fileNameIn = args[i];
            Callable<Integer> callable = new SumCounting(fileNameIn, i);
            Future<Integer> future = executor.submit(callable);
            sum = sum + (int) future.get();
            System.out.println("sum = " + sum);
        }
    }


}
