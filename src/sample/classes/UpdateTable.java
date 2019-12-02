package sample.classes;


import java.util.Timer;
import java.util.TimerTask;

public class UpdateTable extends TimerTask {
    private Timer timer = new Timer();

    @Override
    public void run() {
        timer.schedule(new UpdateTable(), 1000);
    }
}
