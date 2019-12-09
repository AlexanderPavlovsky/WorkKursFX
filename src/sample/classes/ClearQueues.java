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
     * Object of FinishedQueue
     */
    private FinishedQueue finishedQueue;
    /**
     * Object of RejectQueue
     */
    private RejectQueue rejectQueue;

    ClearQueues(final FinishedQueue finishedQueue, final RejectQueue rejectQueue) {
        this.finishedQueue = finishedQueue;
        this.rejectQueue = rejectQueue;
    }

    @Override
    public void run() {
        timer.schedule(new ClearQueues(finishedQueue, rejectQueue), 1000);
        if (finishedQueue.getFinishedQueue().size() > 10) {
            finishedQueue.getFinishedQueue().remove(0);
        } else if (finishedQueue.getFinishedQueue().size() > 50) {
            finishedQueue.getFinishedQueue().subList(0, 20).clear();
        }
        if (rejectQueue.getRejectQueue().size() > 10) {
            rejectQueue.getRejectQueue().remove(0);
        } else if (rejectQueue.getRejectQueue().size() > 50) {
            rejectQueue.getRejectQueue().subList(0, 20).clear();
        }
    }
}
