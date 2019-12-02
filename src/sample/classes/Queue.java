package sample.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
    public static ArrayList<Process> queue;
    private ArrayList<Process> rejectQueue;
    private ArrayList<Process> readyQueue;
    public  static ArrayList<Process> finishedQueue;
    private int lastID;

    public ArrayList<Process> getReadyQueue() {
        return readyQueue;
    }



    public Queue() {
        queue = new ArrayList<>();
        this.rejectQueue = new ArrayList<>();
        this.readyQueue = new ArrayList<>();
        finishedQueue = new ArrayList<>();
        this.lastID = 1;
    }

    public static void addFinishedQueue(Process process) {
        finishedQueue.add(process);
    }

    public void add() {
        queue.add(new Process(this.lastID++));

    }

    public void addReadyQueue() {
        if(sort()) {
            if (rejectQueue.isEmpty()) {
                for (Process process : queue) {
                    if (MemoryScheduler.add(process.getMemory(), process)) {
                        process.setState(State.Waiting);
                        this.readyQueue.add(process);
                    } else {
                        if (MemoryScheduler.findFreeBlock(process.getMemory(), process)) {
                            process.setState(State.Waiting);
                            this.readyQueue.add(process);
                        } else {
                            this.rejectQueue.add(process);
                        }
                    }
                }
            } else {
                rejectQueue.sort(Comparator.comparingInt(Process::getPriority));
                ///???????
                try {

                    for (Process process : rejectQueue) {
                        if (MemoryScheduler.add(process.getMemory(), process)) {
                            rejectQueue.remove(process);
                            process.setState(State.Waiting);
                            this.readyQueue.add(process);
                        } else {
                            if (MemoryScheduler.findFreeBlock(process.getMemory(), process)) {
                                process.setState(State.Waiting);
                                rejectQueue.remove(process);
                                this.readyQueue.add(process);
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public void add(final int N) {
        for (int i = 0; i < N; i++) {
            add();
        }
    }
    public void removeReadyQueue(int index){
        readyQueue.remove(index);
    }

    public boolean sort() {
        queue.sort(Comparator.comparingInt(Process::getPriority));
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
//        for (Process process : queue) {
//            result.append(process).append("\n");
//        }
        //result.append("\n");
        for (Process process : finishedQueue) {
            result.append(process).append("\n");
        }
//        result.append("\n");
//        for (Process process : rejectQueue) {
//            result.append(process).append("\n");
//        }
        return result.toString();
    }
}
