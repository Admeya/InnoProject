package main.java.ru.innopolis.itcources.lab1;

/**
 * Created by Ирина on 08.02.2017.
 */
public class Main {
    public static void main(String args[]) {
        String fileNameIn = null;
        for (int i = 0; i < args.length; i++) {
            fileNameIn = args[i];
            RunThreads thread = new RunThreads(fileNameIn, i);
            thread.start();
//            try {
//                thread.join();
//                // действия после завершения работы потока
//            } catch (InterruptedException x) {}
        }
    }
}
