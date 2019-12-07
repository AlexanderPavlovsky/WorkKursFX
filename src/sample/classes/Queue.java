package sample.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
    private ArrayList<Process> queue;
    private RejectQueue rejectQueue;
    private ReadyQueue readyQueue;
    private FinishedQueue finishedQueue;
    private int lastID;

    public Queue() {
        queue = new ArrayList<>();
        rejectQueue = new RejectQueue();
        readyQueue = new ReadyQueue();
        finishedQueue = new FinishedQueue();
        lastID = 1;
    }

    private void add() {
        queue.add(new Process(lastID++));
    }

    public void creatReadyQueue() {
        queue.sort(Comparator.comparingInt(Process::getPriority));
        for (Process process : queue) {
            if (MemoryScheduler.add(process.getMemory(), process) && process.getState() != State.Finished) {
                process.setState(State.Waiting);
                readyQueue.addReadyQueue(process);
            } else {
                if (MemoryScheduler.findFreeBlock(process.getMemory(), process) && process.getState() != State.Finished) {
                    process.setState(State.Waiting);
                    readyQueue.addReadyQueue(process);
                } else if (process.getState() != State.Finished) {
                    rejectQueue.addRejectQueue(process);
                }
            }
        }
    }

    public void add(final int N) {
        for (int i = 0; i < N; i++) {
            add();
        }
    }

    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }

    public FinishedQueue getFinishedQueue() {
        return finishedQueue;
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
