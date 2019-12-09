package sample.classes;

import java.util.ArrayList;

/**
 * Class FinishedQueue
 */
class FinishedQueue {
    /**
     * Array list of processes
     */
    private ArrayList<Process> finishedQueue;

    FinishedQueue() {
        finishedQueue = new ArrayList<>();
    }

    /**
     * Get finished queue
     * @return finished queue
     */
    public ArrayList<Process> getFinishedQueue() {
        return finishedQueue;
    }

    /**
     * Add to array list process
     * @param process process
     */
    public void addFinishedQueue(final Process process) {
        finishedQueue.add(process);
    }
}
