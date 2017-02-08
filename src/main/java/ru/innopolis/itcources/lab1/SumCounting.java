package main.java.ru.innopolis.itcources.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Created by Ирина on 07.02.2017.
 */
public class SumCounting implements Callable<Integer> {
    int sum = 0;

    SumCounting(String fileNameIn, int i) {

        Scanner sc = null;

        File file = new File(fileNameIn);
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                int nextNumber = sc.nextInt();
                synchronized (this) {
                    sum = sum + isPositiveAndEvenNumbers(nextNumber);
                    System.out.println("work thread " + i + " sum = " + sum);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int isPositiveAndEvenNumbers(int num) {
        if ((num % 2 == 0) && (num > 0)) {
            System.out.println("find positive and even number! " + num);
            return num;
        } else {
            System.out.println("This number is unsuitable for this example " + num);
            num = 0;
        }
        return num;
    }

//    public static boolean isCorrectNumber(){
//
//    }

    @Override
    public Integer call() throws Exception {
        return sum;
    }


}
