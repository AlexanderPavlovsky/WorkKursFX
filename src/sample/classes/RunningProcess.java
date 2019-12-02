package sample.classes;


public class RunningProcess extends Thread {
    private Process process;
    private int index;
    RunningProcess(Process process, int index){
        this.process = process;
        this.index = index;
    }
    @Override
    public void run() {
        process.setTimeIn(ClockGenerator.getTime());
        process.setState(sample.classes.State.Running);
        try {
            sleep(process.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        process.setBurstTime(process.getTimeIn() + process.getTime());
        process.setState(sample.classes.State.Finished);
        Queue.addFinishedQueue(process);
        MemoryScheduler.releaseMemoryBlock(process.getMemoryBlock());
        RunningProcesses.runningProcessesIsFree.set(index, Boolean.TRUE);
    }
}
