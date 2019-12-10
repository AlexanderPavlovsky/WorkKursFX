package sample.classes;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class ClearQueues
 */
public class ClearQueues extends TimerTask {
    /**
     * Variable timer
     */
    private Timer timer = new Timer();
    /**
     * Object of CreatRunningProcesses
     */
    private CreatRunningProcesses creatRunningProcesses;

    ClearQueues(final CreatRunningProcesses creatRunningProcesses) {
        this.creatRunningProcesses = creatRunningProcesses;
    }

    @Override
    public void run() {
        timer.schedule(new ClearQueues(creatRunningProcesses), 100);
        if (creatRunningProcesses.getFinishedQueue().getFinishedQueue().size() > 10) {
            creatRunningProcesses.setQuantityProcessesLeft(creatRunningProcesses.getQuantityProcessesLeft() + 1);
            creatRunningProcesses.getFinishedQueue().getFinishedQueue().remove(0);
        } else if (creatRunningProcesses.getFinishedQueue().getFinishedQueue().size() > 50) {
            creatRunningProcesses.setQuantityProcessesLeft(creatRunningProcesses.getQuantityProcessesLeft() + 20);
            creatRunningProcesses.getFinishedQueue().getFinishedQueue().subList(0, 20).clear();
        }
        if (creatRunningProcesses.getQueue().getRejectQueue().getRejectQueue().size() > 10) {
            creatRunningProcesses.setQuantityProcessesLeft(creatRunningProcesses.getQuantityProcessesLeft() + 1);
            creatRunningProcesses.getQueue().getRejectQueue().getRejectQueue().remove(0);
        } else if (creatRunningProcesses.getQueue().getRejectQueue().getRejectQueue().size() > 50) {
            creatRunningProcesses.setQuantityProcessesLeft(creatRunningProcesses.getQuantityProcessesLeft() + 20);
            creatRunningProcesses.getQueue().getRejectQueue().getRejectQueue().subList(0, 20).clear();
        }
    }
}
