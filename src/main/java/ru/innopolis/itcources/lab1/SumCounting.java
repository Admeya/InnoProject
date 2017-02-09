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
    File file = null;

    SumCounting(File file, int countThread) {
        this.countThread = countThread;
        this.file = file;

        countSum();
    }

    public void countSum() {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                synchronized (this) {
                    if (Main.isInterrupt)
                        break;
                    else {
                        if (sc.hasNext()) {
                            String numForAnalisys = sc.next();
                            if (isNumberCorrect(numForAnalisys)) {
                                int nextNumber = getSumCount(numForAnalisys);
                                if (nextNumber > 0) {
                                    System.out.print("Thread '" + countThread + "'. Counting sum... " + sum + " + " + nextNumber + " = ");
                                    sum = sum + nextNumber;
                                    System.out.println(sum);
                                }
                            } else {
                                try {
                                    Main.isInterrupt = true;
                                    throw new InterruptedException("Thread '" + this.countThread + "'. Yoooohooooohooo!!!! And a bottle with Rom!!! This is not correct number '" + numForAnalisys + "'");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isNumberCorrect(String number) {
        Pattern p = Pattern.compile("(^-\\d*$)|(^\\d*$)");
        if (!p.matcher(number).matches()) {
            Main.isInterrupt = true;
        } else
            return true;

        return false;
    }

    public int getSumCount(String number) {
        int num = 0;
        num = Integer.parseInt(number);

        if ((num % 2 == 0) && (num > 0)) {
            System.out.println("Thread '" + this.countThread + "'. Find positive and even number! This is number " + num);
            return num;
        } else {
            System.out.println("Thread '" + this.countThread + "'. Number " + num + " thread is unsuitable for this example ");
            num = 0;
        }
        return num;
    }
}
