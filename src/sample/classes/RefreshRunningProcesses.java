package sample.classes;

import java.util.Timer;
import java.util.TimerTask;

public class RefreshRunningProcesses extends TimerTask {
    private Timer timer = new Timer();
    RProcesses rProcesses;
    public RefreshRunningProcesses (RProcesses rProcesses) {
        this.rProcesses = rProcesses;
    }

    @Override
    public void run() {
        timer.schedule(new RefreshRunningProcesses(rProcesses), 1);
        ClockGenerator.incTime();
        rProcesses.runProcess();
    }
}
