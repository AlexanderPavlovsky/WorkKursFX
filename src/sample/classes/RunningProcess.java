package sample.classes;

/**
 * Class RunningProcess
 */
public class RunningProcess extends Thread {
    /**
     * Object of Process
     */
    private Process process;
    /**
     * index
     */
    private int index;
    /**
     * Object of  CreatRunningProcesses
     */
    private CreatRunningProcesses creatRunningProcesses;
    /**
     * priority
     */
    private int priority = 0;

    /**
     * Constructor of running process
     *
     * @param process               process
     * @param index                 index
     * @param creatRunningProcesses creatRunningProcesses
     */
    RunningProcess(final Process process, final int index, final CreatRunningProcesses creatRunningProcesses) {
        this.process = process;
        this.index = index;
        this.creatRunningProcesses = creatRunningProcesses;
    }

    /**
     * Constructor of running process
     *
     * @param process               process
     * @param index                 index
     * @param creatRunningProcesses creatRunningProcesses
     * @param priority              priority
     */
    RunningProcess(final Process process, final int index, final CreatRunningProcesses creatRunningProcesses, final int priority) {
        this.process = process;
        this.index = index;
        this.creatRunningProcesses = creatRunningProcesses;
        this.priority = priority;
    }

    @Override
    public void run() {
        creatRunningProcesses.setAverageProcessWait(ClockGenerator.getTime() - process.getTimeInReadyQueue());
        process.setState(sample.classes.State.Running);
        try {
            sleep(process.getTime() / creatRunningProcesses.getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (priority != 0) {
            if (priority > process.getPriority()) {
                process.setBurstTime(ClockGenerator.getTime());
                process.setState(sample.classes.State.Finished);
                creatRunningProcesses.setAverageProcessTime(process.getBurstTime() - process.getTimeIn());
                creatRunningProcesses.getQueue().getMemoryScheduler().releaseMemoryBlock(process.getMemoryBlock());
                creatRunningProcesses.getFinishedQueue().addFinishedQueue(process);
            } else {
                creatRunningProcesses.getQueue().getReadyQueue().getReadyQueue().add(process);
            }
        } else {
            process.setBurstTime(ClockGenerator.getTime());
            process.setState(sample.classes.State.Finished);
            creatRunningProcesses.setAverageProcessTime(process.getBurstTime() - process.getTimeIn());
            creatRunningProcesses.getQueue().getMemoryScheduler().releaseMemoryBlock(process.getMemoryBlock());
            creatRunningProcesses.getFinishedQueue().addFinishedQueue(process);
        }
        creatRunningProcesses.getRunningProcessesIsFree().set(index, Boolean.TRUE);
    }
}
