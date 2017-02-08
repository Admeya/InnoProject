package main.java.ru.innopolis.itcources.lab1;

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
public class RunThreads extends Thread {
    private String fileNameIn;
    private int countThread;

    RunThreads(String fileNameIn, int countThread) {
        this.fileNameIn = fileNameIn;
        this.countThread = countThread;
    }

    public void run() {
        SumCounting countSum = new SumCounting(fileNameIn, countThread);
    }
}
