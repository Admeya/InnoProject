package main.java.ru.innopolis.itcources.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Ирина on 07.02.2017.
 */
public class SumCounting {
    public static volatile int sum = 0;
    int countThread;

    SumCounting(String fileNameIn, int countThread) {
        this.countThread = countThread;
        Scanner sc = null;

        File file = new File(fileNameIn);
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                int nextNumber = sc.nextInt();
                synchronized (this) {
                    sum = sum + isPositiveAndEvenNumbers(nextNumber);
                    System.out.println("work thread " + countThread + " sum = " + sum);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int isPositiveAndEvenNumbers(int num) {
        if ((num % 2 == 0) && (num > 0)) {
            System.out.println("find positive and even number! " + num + " in " + this.countThread + " thread");
            return num;
        } else {
            System.out.println("Number " + num + " in " + this.countThread + " thread is unsuitable for this example ");
            num = 0;
        }
        return num;
    }
}
