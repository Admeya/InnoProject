package main.java.ru.innopolis.itcources.lab1;

public class RunThreads extends Thread {
    private String fileNameIn;
    private int countThread;

    RunThreads(String fileNameIn, int countThread) {
        this.fileNameIn = fileNameIn;
        this.countThread = countThread;
    }

    public void run() {
        new SumCounting(fileNameIn, countThread);
    }
}
