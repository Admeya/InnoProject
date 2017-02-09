package main.java.ru.innopolis.itcources.lab1;

import java.io.File;
import java.io.FileNotFoundException;

public class RunThreads extends Thread {
    private File fileNameIn;
    private int countThread;


    RunThreads(File fileNameIn, int countThread) throws FileNotFoundException {
        this.fileNameIn = fileNameIn;
        this.countThread = countThread;
    }

    public void run() {
        try {
            new SumCounting(fileNameIn, countThread);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
