package sample.classes;

import java.util.ArrayList;

/**
 * Class CreatRunningProcesses
 */
class CreatRunningProcesses {
    /**
     * Array list of RunningProcess
     */
    private ArrayList<RunningProcess> creatRunningProcesses;
    /**
     * Array list of Boolean
     */
    private ArrayList<Boolean> runningProcessesIsFree;
    /**
     * Object of Queue
     */
    private Queue queue;

    /**
     * Constructor of CreatRunningProcesses
     */
   public CreatRunningProcesses() {
        creatRunningProcesses = new ArrayList<>();
        queue = new Queue();
        for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
            creatRunningProcesses.add(null);
        }
        runningProcessesIsFree = new ArrayList<>();
        for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
            runningProcessesIsFree.add(Boolean.TRUE);
        }
    }

    /**
     * Get runningProcessesIsFree
     * @return runningProcessesIsFree
     */

    public ArrayList<Boolean> getRunningProcessesIsFree() {
        return runningProcessesIsFree;
    }

    /**
     * Get queue
     * @return queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * Add processes to queue, and creat ready queue
     */
    public void addQueue() {
        queue.add(20);
        queue.creatReadyQueue();
    }

    /**
     * Get creat processes
     * @return creat processes
     */
    public ArrayList<RunningProcess> getCreatRunningProcesses() {
        return creatRunningProcesses;
    }

    /**
     * Get finished queue
     * @return finished queue
     */
    public FinishedQueue getFinishedQueue() {
        return queue.getFinishedQueue();
    }
}
