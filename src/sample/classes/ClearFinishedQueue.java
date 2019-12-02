package sample.classes;

import java.util.Timer;
import java.util.TimerTask;

public class ClearFinishedQueue extends TimerTask {
    private Timer timer = new Timer();
    @Override
    public void run() {
        timer.schedule(new ClearFinishedQueue(), 1000);
        if(Queue.finishedQueue.size() > 10) {
            Queue.finishedQueue.remove(0);
        }
    }
}
