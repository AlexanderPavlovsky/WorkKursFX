package sample.classes;


public class RunningProcess extends Thread {
    private Process process;
    private int index;
    private  RunningProcesses runningProcesses;

    RunningProcess(Process process, int index , RunningProcesses runningProcesses) {
        this.process = process;
        this.index = index;
        this.runningProcesses = runningProcesses;
    }

    @Override
    public void run() {
        process.setTimeIn(ClockGenerator.getTime());
        process.setState(sample.classes.State.Running);
        try {
            sleep(process.getTime());
            process.setBurstTime(process.getTimeIn() + process.getTime());
            process.setState(sample.classes.State.Finished);
            MemoryScheduler.releaseMemoryBlock(process.getMemoryBlock());
            runningProcesses.getFinishedQueue().getFinishedQueue().add(process);
            runningProcesses.getRunningProcessesIsFree().set(index, Boolean.TRUE);
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
