package sample.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class RunningProcesses {
    private ArrayList<RunningProcess> runningProcesses;
    private ArrayList<Boolean> runningProcessesIsFree;
    private Queue queue;


    public RunningProcesses( ) {
        runningProcesses = new ArrayList<>();
        queue = new Queue();
        for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
            runningProcesses.add(null);
        }
        runningProcessesIsFree = new ArrayList<>();
        for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
            runningProcessesIsFree.add(Boolean.TRUE);
        }
    }

    public ArrayList<Boolean> getRunningProcessesIsFree() {
        return runningProcessesIsFree;
    }

    public Queue getQueue() {
        return queue;
    }

    public void addQueue() {
        queue.add(5);
        queue.creatReadyQueue();
    }

    public ArrayList<RunningProcess> getRunningProcesses() {
        return runningProcesses;
    }

    public FinishedQueue getFinishedQueue() {
        return queue.getFinishedQueue();
    }
}
