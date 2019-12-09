package sample.classes;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class RunningProcesses
 */
class RunningProcesses {
    /**
     * Object of CreatRunningProcesses
     */
    private CreatRunningProcesses creatRunningProcesses;


    RunningProcesses() {
        creatRunningProcesses = new CreatRunningProcesses();
        final AddQueue addQueue = new AddQueue(creatRunningProcesses);
        addQueue.run();
    }

    /**
     * Get creat running processes
     * @return creat running processes
     */
    public CreatRunningProcesses getCreatRunningProcesses() {
        return creatRunningProcesses;
    }

    /**
     * Run of processes
     */
    public void runProcess() {
        Process runningProcess;
        for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
            if (creatRunningProcesses.getRunningProcessesIsFree().get(i) == Boolean.TRUE) {
                if (!creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().isEmpty()) {
                    creatRunningProcesses.getRunningProcessesIsFree().set(i, Boolean.FALSE);
                    runningProcess = creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().get(0);
                    creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().remove(0);
                    final ArrayList<Process> tempProcesses = creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue();
                    tempProcesses.sort(Comparator.comparingInt(Process::getPriority));
                    if(!tempProcesses.isEmpty()){
                        creatRunningProcesses.getCreatRunningProcesses().set(i, new RunningProcess(runningProcess, i, creatRunningProcesses, tempProcesses.get(0).getPriority()));
                    }else {
                        creatRunningProcesses.getCreatRunningProcesses().set(i, new RunningProcess(runningProcess, i, creatRunningProcesses));
                    }
                    creatRunningProcesses.getCreatRunningProcesses().get(i).start();
                }
            }
        }
    }
}
