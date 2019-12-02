package sample.classes;

import java.util.Timer;
import java.util.TimerTask;

public class RefreshRunningProcesses extends TimerTask {
    private Timer timer = new Timer();

    @Override
    public void run() {
        timer.schedule(new RefreshRunningProcesses(), 1);
        ClockGenerator.incTime();
        RunningProcesses.runProcess();
    }
}
