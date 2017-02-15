package lab1;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Выполняются операции по подсчету суммы, проверки на корректность полученных значений,
 * проверки на четность и неотрицательность *
 */
public class OperationsOnResources {
    static Logger logger = Logger.getLogger(OperationsOnResources.class);

    public static int sum = 0;
    File file = null;

    public OperationsOnResources(File file) {
        this.file = file;
        readSymbolAndCountSum();
    }

    @Override
    public String toString() {
        return "OperationsOnResources{" + ", file=" + file + '}';
    }

    /**
     * Подсчет суммы чисел со всех потоков согласно условию
     *
     * @throws InterruptedException если число не пройдено на валидность
     */
    private void readSymbolAndCountSum() {
        logger.trace(this.toString());
        try {
            synchronized (this) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    if (Main.isInterrupt)
                        break;
                    else {
                        if (sc.hasNext()) {
                            String numForAnalisys = sc.next();
                            if (isNumberCorrect(numForAnalisys)) {
                                int nextNumber = isSuitable(numForAnalisys);
                                countingSum(nextNumber, sum);
                            } else {
                                try {
                                    Main.isInterrupt = true;
                                    throw new InterruptedException("This is not correct number '" + numForAnalisys + "'");
                                } catch (InterruptedException e) {
                                    logger.error(e);
                                }
                            }
                        }
                    }
                }
                sc.close();
            }
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
    public static boolean isNumberCorrect(String number) {
        Pattern p = Pattern.compile("(^-\\d*$)|(^\\d*$)");
        if (!p.matcher(number).matches()) {
            Main.isInterrupt = true;
        } else
            return true;

        return false;
    }

    public static int countingSum(int nextNumber, int previousSum) {
        if (nextNumber > 0) {
            logger.trace("Counting sum... " + previousSum + " + " + nextNumber + " = " + (sum = previousSum + nextNumber));
        }
        return sum;
    }

    /**
     * Проверка условия "Число, которое войдет в сумму должно быть четным и положительным"
     *
     * @param number передается строка, преобразовывается в число и проверяется на нужное условие
     * @return num = 0 если число не подходит под условие и приведенная к типу int строка, если условие пройдено
     */
    public static int isSuitable(String number) {
        int num = 0;
        num = Integer.parseInt(number);

        if ((num % 2 == 0) && (num > 0)) {
            logger.trace("Find positive and even number! This is number " + num);
            return num;
        } else {
            logger.trace("Number " + num + " thread is unsuitable for this example ");
            num = 0;
        }
        return num;
    }
}