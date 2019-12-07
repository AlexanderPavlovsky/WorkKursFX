package sample.classes;

import java.util.ArrayList;

public class FinishedQueue {
    private ArrayList<Process> finishedQueue;

    public FinishedQueue() {
        finishedQueue = new ArrayList<>();
    }

    public ArrayList<Process> getFinishedQueue() {
        return finishedQueue;
    }

    public void addFinishedQueue(Process process) {
        finishedQueue.add(process);
    }
}
