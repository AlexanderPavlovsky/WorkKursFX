package sample.classes;


import java.util.ArrayList;
import java.util.Comparator;

public class RunningProcess extends Thread {
    private Process process;
    private int index;
    private RunningProcesses runningProcesses;
    private int priority = 0;

    RunningProcess(Process process, int index, RunningProcesses runningProcesses) {
        this.process = process;
        this.index = index;
        this.runningProcesses = runningProcesses;
    }

    RunningProcess(Process process, int index, RunningProcesses runningProcesses, int priority) {
        this.process = process;
        this.index = index;
        this.runningProcesses = runningProcesses;
        this.priority = priority;
    }

    @Override
    public void run() {
        process.setState(sample.classes.State.Running);
        if (process.getTimeIn() == 0) {
            process.setTimeIn(ClockGenerator.getTime());
        }
        try {
            sleep(process.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (priority != 0) {
            if (priority > process.getPriority()) {
                process.setBurstTime(ClockGenerator.getTime());
                process.setState(sample.classes.State.Finished);
                MemoryScheduler.releaseMemoryBlock(process.getMemoryBlock());
                runningProcesses.getFinishedQueue().addFinishedQueue(process);
            } else {
                runningProcesses.getQueue().getReadyQueue().getReadyQueue().add(process);
            }
        } else {
            process.setBurstTime(ClockGenerator.getTime());
            process.setState(sample.classes.State.Finished);
            MemoryScheduler.releaseMemoryBlock(process.getMemoryBlock());
            runningProcesses.getFinishedQueue().addFinishedQueue(process);
        }
        runningProcesses.getRunningProcessesIsFree().set(index, Boolean.TRUE);
    }
}
