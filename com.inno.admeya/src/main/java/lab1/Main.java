package lab1;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Быкова Ирина
 * @version 0.25
 *
 * Необходимо разработать программу, которая получает на вход список ресурсов,
 * содержащих набор чисел и считает сумму всех положительных четных.
 * Каждый ресурс должен быть обработан в отдельном потоке, набор должен содержать лишь числа,
 * унарный оператор "-" и пробелы. Общая сумма должна отображаться на экране и изменяться в
 * режиме реального времени. Все ошибки должны быть корректно обработаны, все API покрыто модульными тестами
 *
 * По результатам вычислений полученная сумма должна равняться 1432
 */
public class Main {
    public static boolean isInterrupt = false;

    public static void main(String args[]) {
        getResourcesNamesAndCheck(args);
    }

    /**
     * На этапе извлечения переданных параметров осуществляется проверка на правильность пути к файлу в файловой системе
     *
     * @param args передаются полученные ресурсы
     * @throws FileNotFoundException При получении списка ресурсов сразу проверяется файл на существование
     */
    public static void getResourcesNamesAndCheck(String args[]) {
        String fileNameIn = null;

        for (int i = 0; i < args.length; i++) {
            fileNameIn = args[i];
            File file = new File(fileNameIn);
            try {
                if (file.exists()) {
                    Thread thread = new Thread(new RunThreads(file, i));
                    thread.start();
                } else {
                    isInterrupt = true;
                    throw new FileNotFoundException();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Please, check input parameters, this file is not found " + file.getAbsolutePath());
            } finally {
                if (isInterrupt)
                    break;
            }
        }
    }
}