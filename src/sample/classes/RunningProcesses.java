package sample.classes;

import java.util.ArrayList;

public class RunningProcesses {
    private static ArrayList<RunningProcess> runningProcesses;
    public static ArrayList<Boolean> runningProcessesIsFree;
    private static int quantityRunningProcesses = 3;
    private static RefreshRunningProcesses refreshRunningProcesses = new RefreshRunningProcesses();
    private static ReadyQueue readyQueue;

    public RunningProcesses() {
        runningProcesses = new ArrayList<>();
        for (int i = 0; i < quantityRunningProcesses; i++) {
            runningProcesses.add(null);
        }
        runningProcessesIsFree = new ArrayList<>();
        for (int i = 0; i < quantityRunningProcesses; i++) {
            runningProcessesIsFree.add(Boolean.TRUE);
        }
        readyQueue = new ReadyQueue();
        refreshRunningProcesses.run();
    }

    static void runProcess() {
        Process runningProcess;
        for (int i = 0; i < quantityRunningProcesses; i++) {
            if (runningProcessesIsFree.get(i) == Boolean.TRUE) {
                try {
                    if (readyQueue.getQueue().getReadyQueue().get(0) != null) {
                        runningProcessesIsFree.set(i, Boolean.FALSE);
                        runningProcess = readyQueue.getQueue().getReadyQueue().get(0);
                        readyQueue.getQueue().removeReadyQueue(0);
                        runningProcesses.set(i, new RunningProcess(runningProcess, i));
                        runningProcesses.get(i).start();
                    }
                } catch (Exception e) {

                }
            }
        }
        if (!Queue.finishedQueue.isEmpty()) {
            System.out.println(Queue.finishedQueue);
        }
    }
}
