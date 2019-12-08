package sample.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

public class RProcesses {
    private RunningProcesses runningProcesses;


    public RProcesses() {
        runningProcesses = new RunningProcesses();
        AddQueue addQueue = new AddQueue(runningProcesses);
        addQueue.run();
    }

    public RunningProcesses getRunningProcesses() {
        return runningProcesses;
    }

    public void runProcess() {
        Process runningProcess;
        for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
            if (runningProcesses.getRunningProcessesIsFree().get(i) == Boolean.TRUE) {
                if (!runningProcesses.getQueue().getReadyQueue().getReadyQueue().isEmpty()) {
                    runningProcesses.getRunningProcessesIsFree().set(i, Boolean.FALSE);
                    runningProcess = runningProcesses.getQueue().getReadyQueue().getReadyQueue().get(0);
                    runningProcesses.getQueue().getReadyQueue().getReadyQueue().remove(0);
                    ArrayList<Process> tempProcesses = runningProcesses.getQueue().getReadyQueue().getReadyQueue();
                    tempProcesses.sort(Comparator.comparingInt(Process::getPriority));
                    if(!tempProcesses.isEmpty()){
                        runningProcesses.getRunningProcesses().set(i, new RunningProcess(runningProcess, i, runningProcesses, tempProcesses.get(0).getPriority()));
                    }else {
                        runningProcesses.getRunningProcesses().set(i, new RunningProcess(runningProcess, i, runningProcesses));
                    }
                    runningProcesses.getRunningProcesses().get(i).start();
                }
            }
        }
    }
}
