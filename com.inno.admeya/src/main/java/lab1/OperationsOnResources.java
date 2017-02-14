package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Выполняются операции по подсчету суммы, проверки на корректность полученных значений,
 * проверки на четность и неотрицательность *
 */
public class OperationsOnResources {
    public static volatile int sum = 0;
    int countThread;
    File file = null;

    OperationsOnResources(File file, int countThread) {
        this.countThread = countThread;
        this.file = file;

        readSymbolAndCountSum();
    }

    @Override
    public String toString() {
        return "OperationsOnResources{" +
                "countThread=" + countThread +
                ", file=" + file +
                '}';
    }

    /**
     * Подсчет суммы чисел со всех потоков согласно условию
     *
     * @throws InterruptedException если число не пройдено на валидность
     */
    public void readSymbolAndCountSum() {
        System.out.println(this.toString());
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
                                countingSum(nextNumber, sum);
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

    /**
     * Проверка переданного значения на валидность
     *
     * @param number Передается считанный из входного потока набор символов
     * @return true если значение подходит указанной маске (положительные и отрицательные целые числа)
     */
    public boolean isNumberCorrect(String number) {
        Pattern p = Pattern.compile("(^-\\d*$)|(^\\d*$)");
        if (!p.matcher(number).matches()) {
            Main.isInterrupt = true;
        } else
            return true;

        return false;
    }

    public int countingSum(int nextNumber, int previousSum) {
        if (nextNumber > 0) {
            System.out.print("Thread '" + countThread + "'. Counting sum... " + previousSum + " + " + nextNumber + " = ");
            sum = sum + nextNumber;
            System.out.println(sum);
        }
        return sum;
    }

    /**
     * Проверка условия "Число, которое войдет в сумму должно быть четным и положительным"
     *
     * @param number передается строка, преобразовывается в число и проверяется на нужное условие
     * @return num = 0 если число не подходит под условие и приведенная к типу int строка, если условие пройдено
     */
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