package sample.classes;

import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

public class ClearQueues extends TimerTask {
    private Timer timer = new Timer();
    private FinishedQueue finishedQueue;
    private RejectQueue rejectQueue;

    public ClearQueues(FinishedQueue finishedQueue, RejectQueue rejectQueue) {
        this.finishedQueue = finishedQueue;
        this.rejectQueue = rejectQueue;
    }

    @Override
    public void run() {
        timer.schedule(new ClearQueues(finishedQueue, rejectQueue), 1000);
        if (finishedQueue.getFinishedQueue().size() > 10) {
            finishedQueue.getFinishedQueue().remove(0);
        } else if (finishedQueue.getFinishedQueue().size() > 50) {
            for (int i = 0; i < 20; i++) {
                finishedQueue.getFinishedQueue().remove(0);
            }
        }
        if (rejectQueue.getRejectQueue().size() > 10) {
            rejectQueue.getRejectQueue().remove(0);
        } else if (rejectQueue.getRejectQueue().size() > 50) {
            for (int i = 0; i < 20; i++) {
                rejectQueue.getRejectQueue().remove(0);
            }
        }
    }
}
