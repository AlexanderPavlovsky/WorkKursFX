package sample.classes;

import java.util.ArrayList;

/**
 * Class RejectQueue
 */
class RejectQueue {
    /**
     * Array list of Process
     */
    private ArrayList<Process> rejectQueue;

    /**
     * Constructor of reject queue
     */
    RejectQueue() {
        rejectQueue = new ArrayList<>();
    }

    /**
     * Add to reject queue
     * @param process process
     */
    public void addRejectQueue(final Process process) {
        rejectQueue.add(process);
    }

    /**
     * Get reject queue
     * @return reject queue
     */
    public ArrayList<Process> getRejectQueue() {
        return rejectQueue;
    }
}
