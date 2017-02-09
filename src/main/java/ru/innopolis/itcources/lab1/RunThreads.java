package main.java.ru.innopolis.itcources.lab1;

import java.io.File;

public class RunThreads extends Thread {
    private volatile File fileNameIn;
    private volatile int countThread;

    RunThreads(File fileNameIn, int countThread) {
        this.fileNameIn = fileNameIn;
        this.countThread = countThread;
    }

    @Override
    public void run() {
        new OperationsOnResources(fileNameIn, countThread);
    }
}
