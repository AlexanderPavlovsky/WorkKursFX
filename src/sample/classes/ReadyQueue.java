package sample.classes;


import java.util.ArrayList;

public class ReadyQueue {
    private ArrayList<Process> readyQueue;


    public ReadyQueue() {
        readyQueue = new ArrayList<>();
    }

    public ArrayList<Process> getReadyQueue() {
        return readyQueue;
    }

    public void addReadyQueue(Process process) {
        readyQueue.add(process);
    }

    @Override
    public String toString() {
        return "ReadyQueue{" +
                "readyQueue=" + readyQueue +
                '}';
    }
}
