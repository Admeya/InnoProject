package main.java.ru.innopolis.itcources.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

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
                synchronized (this) {
                    int nextNumber = isNumberCorrect(sc.next());
                    if (nextNumber > 0) {
                        System.out.print("Work thread '" + countThread + "' sum = " + sum + " + " + nextNumber + ". Sum = ");
                        sum = sum + nextNumber;
                        System.out.println(sum);
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int isNumberCorrect(String number) {
        int num = 0;
        Pattern p = Pattern.compile("(^-\\d*$)|(^\\d*$)");
        if (p.matcher(number).matches()) {
            num = Integer.parseInt(number);

            if ((num % 2 == 0) && (num > 0)) {
                System.out.println("Find positive and even number! This is number " + num + " in '" + this.countThread + "' thread");
                return num;
            } else {
                System.out.println("Number " + num + " in '" + this.countThread + "' thread is unsuitable for this example ");
                num = 0;
            }
        } else {
            throw new IllegalArgumentException("Yoooohooooohooo!!!! And a bottle with Rom!!! This is not correct number '" + number + "'");

        }
        return num;
    }
}
