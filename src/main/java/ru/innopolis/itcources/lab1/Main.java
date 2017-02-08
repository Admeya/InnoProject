package main.java.ru.innopolis.itcources.lab1;

/**
 * Created by Ирина on 08.02.2017.
 */
public class Main {
    public static void main(String args[]) {
        String fileNameIn = null;
        for (int i = 0; i < args.length; i++) {
            fileNameIn = args[i];
            new RunThreads(fileNameIn, i).start();
        }
    }
}
