package sample.classes;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
    private ArrayList<Process> queue;
    private RejectQueue rejectQueue;
    private ReadyQueue readyQueue;
    private FinishedQueue finishedQueue;
    private int lastID;
    private MemoryScheduler memoryScheduler;
    private boolean checkMemory;

    public Queue() {
        queue = new ArrayList<>();
        rejectQueue = new RejectQueue();
        readyQueue = new ReadyQueue();
        finishedQueue = new FinishedQueue();
        lastID = 1;
        checkMemory = true;
        memoryScheduler = new MemoryScheduler();
    }

    private void add() {
        queue.add(new Process(lastID++));
    }

    public void creatReadyQueue() {
        queue.sort(Comparator.comparingInt(Process::getPriority));
        for (int i = 0; i < queue.size(); i++) {
            if (checkMemory) {
                checkMemory = memoryScheduler.add(queue.get(0).getMemory(), queue.get(0));
                queue.get(0).setState(State.Waiting);
                readyQueue.addReadyQueue(queue.get(0));
            } else {
                if (memoryScheduler.findFreeBlock(queue.get(0).getMemory(), queue.get(0))) {
                    queue.get(0).setState(State.Waiting);
                    readyQueue.addReadyQueue(queue.get(0));
                } else {
                    rejectQueue.addRejectQueue(queue.get(0));
                }
            }
            queue.remove(0);
        }
    }

    public void add(final int N) {
        for (int i = 0; i < N; i++) {
            add();
        }
    }

    public MemoryScheduler getMemoryScheduler() {
        return memoryScheduler;
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public FinishedQueue getFinishedQueue() {
        return finishedQueue;
    }

    public RejectQueue getRejectQueue() {
        return rejectQueue;
    }

    public ArrayList<Process> getQueue() {
        return queue;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Process process : queue) {
            result.append(process).append("\n");
        }
        return result.toString();
    }
}
