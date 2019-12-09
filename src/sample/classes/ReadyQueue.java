package sample.classes;


import java.util.ArrayList;

/**
 * Class ReadyQueue
 */
public class ReadyQueue {
    /**
     * Array list of Process
     */
    private ArrayList<Process> readyQueue;

    /**
     * Constructor of ready queue
     */
    ReadyQueue() {
        readyQueue = new ArrayList<>();
    }

    /**
     * Get ready queue
     * @return ready queue
     */
    public ArrayList<Process> getReadyQueue() {
        return readyQueue;
    }

    /**
     * Add to ready queue
     * @param process process
     */
    public void addReadyQueue(final Process process) {
        readyQueue.add(process);
    }

    @Override
    public String toString() {
        return "ReadyQueue{" +
                "readyQueue=" + readyQueue +
                '}';
    }
}
