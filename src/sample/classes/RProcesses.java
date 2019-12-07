package sample.classes;

import java.util.Comparator;

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
                if (runningProcesses.getQueue().getReadyQueue().getReadyQueue() != null) {
                    runningProcesses.getQueue().getReadyQueue().getReadyQueue().sort(Comparator.comparingInt(Process::getPriority));
                    runningProcesses.getRunningProcessesIsFree().set(i, Boolean.FALSE);
                    runningProcess = runningProcesses.getQueue().getReadyQueue().getReadyQueue().get(0);
                    runningProcesses.getQueue().getReadyQueue().getReadyQueue().remove(0);
                    runningProcesses.getRunningProcesses().set(i, new RunningProcess(runningProcess, i, runningProcesses));
                    runningProcesses.getRunningProcesses().get(i).start();
                }
            }
        }
        System.out.println(runningProcesses.getQueue());
    }
}
