package sample.classes;

import java.util.ArrayList;

public class RejectQueue {
    private ArrayList<Process> rejectQueue;

    public RejectQueue() {
        rejectQueue = new ArrayList<>();
    }

    public void addRejectQueue(Process process) {
        rejectQueue.add(process);
    }

    public ArrayList<Process> getRejectQueue() {
        return rejectQueue;
    }
}
