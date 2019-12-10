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
    /**
     * Processor idle cycles
     */
    private int processorIdleCycles = 0;

    RunningProcesses() {
        creatRunningProcesses = new CreatRunningProcesses();
        final AddQueue addQueue = new AddQueue(creatRunningProcesses);
        addQueue.run();
        ClearQueues clearQueues = new ClearQueues(creatRunningProcesses);
        clearQueues.run();
    }

    /**
     * Get creat running processes
     *
     * @return creat running processes
     */
    public CreatRunningProcesses getCreatRunningProcesses() {
        return creatRunningProcesses;
    }

    public int getProcessorIdleCycles() {
        return processorIdleCycles;
    }

    /**
     * Run of processes
     */
    public void runProcess() {
        Process runningProcess;
        final ArrayList<Process> tempProcesses = new ArrayList<>();
        for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
            if (creatRunningProcesses.getRunningProcessesIsFree().get(i) == Boolean.TRUE) {
                if (!creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().isEmpty()) {
                    creatRunningProcesses.getRunningProcessesIsFree().set(i, Boolean.FALSE);
                    runningProcess = creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().get(0);
                    creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().remove(0);
                    tempProcesses.clear();
                    tempProcesses.addAll(creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue());
                    tempProcesses.sort(Comparator.comparingInt(Process::getPriority));
                    if (!tempProcesses.isEmpty()) {
                        creatRunningProcesses.getCreatRunningProcesses().set(i, new RunningProcess(runningProcess, i, creatRunningProcesses, tempProcesses.get(0).getPriority()));
                    } else {
                        creatRunningProcesses.getCreatRunningProcesses().set(i, new RunningProcess(runningProcess, i, creatRunningProcesses));
                    }
                    creatRunningProcesses.getCreatRunningProcesses().get(i).start();
                }
            }
        }
        if (creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().isEmpty()){
            int checkFreeRunProcess = 0;
            for (int i = 0; i < Configuration.quantityRunningProcesses; i++) {
                if(creatRunningProcesses.getRunningProcessesIsFree().get(i) == Boolean.TRUE){
                    checkFreeRunProcess++;
                }
            }
            if(checkFreeRunProcess == Configuration.quantityRunningProcesses){
                processorIdleCycles++;
            }
        }
    }
}
