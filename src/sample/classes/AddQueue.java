package sample.classes;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AddQueue extends TimerTask {
    private Timer timer = new Timer();
    private RunningProcesses runningProcesses;

    public AddQueue(RunningProcesses runningProcesses) {
        this.runningProcesses = runningProcesses;
    }

    @Override
    public void run() {
        timer.schedule(new AddQueue(runningProcesses), 1000);
        runningProcesses.addQueue();
    }
}
