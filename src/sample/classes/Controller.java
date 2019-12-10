package sample.classes;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Class Controller
 */
public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * Table view of ready queue
     */
    @FXML
    private TableView<Table> processTableReadyQueue;
    /**
     * Table column of process id
     */
    @FXML
    private TableColumn<Table, Integer> processIdReadyQueue;
    /**
     * Table column of process of name
     */

    @FXML
    private TableColumn<Table, String> processNameReadyQueue;
    /**
     * Table column of process priority
     */
    @FXML
    private TableColumn<Table, Integer> processPriorityReadyQueue;
    /**
     * Table column of process time
     */
    @FXML
    private TableColumn<Table, Integer> processTimeReadyQueue;
    /**
     * Table column of process memory
     */

    @FXML
    private TableColumn<Table, Integer> processMemoryReadyQueue;
    /**
     * Table column of process time in
     */

    @FXML
    private TableColumn<Table, Integer> processTimeInReadyQueue;
    /**
     * Table column of process burst time
     */

    @FXML
    private TableColumn<Table, Integer> processBurstTimeReadyQueue;
    /**
     * Table column of process state
     */

    @FXML
    private TableColumn<Table, String> processStateReadyQueue;
    /**
     * Table view of reject queue
     */
    @FXML
    private TableView<Table> processTableRejectQueue;
    /**
     * Table column of process id
     */
    @FXML
    private TableColumn<Table, Integer> processIdRejectQueue;
    /**
     * Table column of process of name
     */
    @FXML
    private TableColumn<Table, String> processNameRejectQueue;
    /**
     * Table column of process priority
     */
    @FXML
    private TableColumn<Table, Integer> processPriorityRejectQueue;
    /**
     * Table column of process time
     */
    @FXML
    private TableColumn<Table, Integer> processTimeRejectQueue;
    /**
     * Table column of process memory
     */
    @FXML
    private TableColumn<Table, Integer> processMemoryRejectQueue;
    /**
     * Table column of process time in
     */
    @FXML
    private TableColumn<Table, Integer> processTimeInRejectQueue;
    /**
     * Table column of process burst time
     */
    @FXML
    private TableColumn<Table, Integer> processBurstTimeRejectQueue;
    /**
     * Table column of process state
     */
    @FXML
    private TableColumn<Table, String> processStateRejectQueue;
    /**
     * Table view of finished queue
     */
    @FXML
    private TableView<Table> processTableFinishedQueue;
    /**
     * Table column of process id
     */
    @FXML
    private TableColumn<Table, Integer> processIdFinishedQueue;
    /**
     * Table column of process of name
     */
    @FXML
    private TableColumn<Table, String> processNameFinishedQueue;
    /**
     * Table column of process priority
     */
    @FXML
    private TableColumn<Table, Integer> processPriorityFinishedQueue;
    /**
     * Table column of process time
     */
    @FXML
    private TableColumn<Table, Integer> processTimeFinishedQueue;
    /**
     * Table column of process memory
     */
    @FXML
    private TableColumn<Table, Integer> processMemoryFinishedQueue;
    /**
     * Table column of process time in
     */
    @FXML
    private TableColumn<Table, Integer> processTimeInFinishedQueue;
    /**
     * Table column of process burst time
     */
    @FXML
    private TableColumn<Table, Integer> processBurstTimeFinishedQueue;
    /**
     * Table column of process state
     */
    @FXML
    private TableColumn<Table, String> processStateFinishedQueue;
    /**
     * name of process
     */
    private String name;

    /**
     * Method context menu
     *
     * @param event event
     */
    @FXML
    public void contextMenu(final ActionEvent event) {
        try {
            if (!processTableReadyQueue.getSelectionModel().getSelectedItem().getProcessName().isEmpty()) {
                name = processTableReadyQueue.getSelectionModel().getSelectedItem().getProcessName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set high priority of the process
     *
     * @param event event
     */
    @FXML
    public void highLevel(final ActionEvent event) {
        setPriority(1);
    }

    /**
     * Set above average priority of the process
     *
     * @param event event
     */
    @FXML
    public void aboveAverageLevel(final ActionEvent event) {
        setPriority(6);
    }

    /**
     * Set middle priority of the process
     *
     * @param event event
     */
    @FXML
    public void middleLevel(final ActionEvent event) {
        setPriority(12);
    }

    /**
     * Set below average priority of the process
     *
     * @param event event
     */
    @FXML
    public void belowAverageLevel(final ActionEvent event) {
        setPriority(24);
    }

    /**
     * Set low priority of the process
     *
     * @param event event
     */
    @FXML
    public void lowLevel(final ActionEvent event) {
        setPriority(30);
    }

    /**
     * Button turbo boost
     */
    @FXML
    private Button turboBoostId;

    /**
     * Switch on/off turbo boost
     */
    @FXML
    void turboBoost() {
        if (turboBoostId.getStyle().equals("-fx-background-color: red")) {
            turboBoostId.setStyle("-fx-background-color: defult");
            turboBoostId.setStyle("-fx-border-width: defult");
            runningProcesses.getCreatRunningProcesses().setSpeed(1);
        } else {
            turboBoostId.setStyle("-fx-background-color: red");
            runningProcesses.getCreatRunningProcesses().setSpeed(10);
        }

    }

    /**
     * All processes quantity
     */
    @FXML
    private TextField allProcessesQuantity;
    /**
     * Quantity reject queue
     */

    @FXML
    private TextField quantityRejectQueue;
    /**
     * Quantity finished queue
     */
    @FXML
    private TextField quantityFinishedQueue;
    /**
     * Quantity processes left system
     */
    @FXML
    private TextField quantityProcessesLeftSystem;
    /**
     * Average service wait
     */
    @FXML
    private TextField averageServiceWait;
    /**
     * Processor idle cycles
     */
    @FXML
    private TextField processorIdleCycles;
    /**
     * Average waiting time for a process in a ready queue
     */
    @FXML
    private TextField averageProcessWait;
    /**
     * Average time of the process
     */
    @FXML
    private TextField averageProcessTime;

    /**
     * Object of RunningProcesses
     */
    private static RunningProcesses runningProcesses = new RunningProcesses();
    /**
     * Object of RefreshRunningProcesses
     */
    private RefreshRunningProcesses refreshRunningProcesses = new RefreshRunningProcesses(runningProcesses);
    /**
     * Object of UpdateTable
     */
    private UpdateTable updateTable = new UpdateTable();
    /**
     * Object of ObservableList
     */
    private static ObservableList<Table> dataReadyQueue = FXCollections.observableArrayList();
    /**
     * Object of ObservableList
     */
    private static ObservableList<Table> dataRejectQueue = FXCollections.observableArrayList();
    /**
     * Object of ObservableList
     */
    private static ObservableList<Table> dataFinishedQueue = FXCollections.observableArrayList();


    /**
     * Initialize of Controller
     */
    @FXML
    public void initialize() {
        processIdReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processId"));
        processNameReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, String>("processName"));
        processPriorityReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processPriority"));
        processTimeReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTime"));
        processMemoryReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processMemory"));
        processTimeInReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTimeIn"));
        processBurstTimeReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processBurstTime"));
        processStateReadyQueue.setCellValueFactory(new PropertyValueFactory<Table, String>("processState"));
        processIdRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processId"));
        processNameRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, String>("processName"));
        processPriorityRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processPriority"));
        processTimeRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTime"));
        processMemoryRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processMemory"));
        processTimeInRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTimeIn"));
        processBurstTimeRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processBurstTime"));
        processStateRejectQueue.setCellValueFactory(new PropertyValueFactory<Table, String>("processState"));
        processIdFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processId"));
        processNameFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, String>("processName"));
        processPriorityFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processPriority"));
        processTimeFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTime"));
        processMemoryFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processMemory"));
        processTimeInFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTimeIn"));
        processBurstTimeFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processBurstTime"));
        processStateFinishedQueue.setCellValueFactory(new PropertyValueFactory<Table, String>("processState"));
        processTableReadyQueue.setItems(dataReadyQueue);
        processTableRejectQueue.setItems(dataRejectQueue);
        processTableFinishedQueue.setItems(dataFinishedQueue);
        refreshRunningProcesses.run();
        updateTable.run();
        Statistics();
    }

    @FXML
    void about(final ActionEvent event) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("About");
        alert.setContentText("Task manager\nAlexander Pavlovsky\n2019 \u00A9 copyright");
        alert.show();
    }

    /**
     * Update table of ready queue
     */
    public static void updateTableReadyQueue() {
        dataReadyQueue.clear();
        if (!runningProcesses.getCreatRunningProcesses().getQueue().getReadyQueue().getReadyQueue().isEmpty()) {
            try {
                for (final Process process : runningProcesses.getCreatRunningProcesses().getQueue().getReadyQueue().getReadyQueue()) {
                    dataReadyQueue.add(new Table(process));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Update table of reject queue
     */
    public static void updateTableRejectQueue() {
        dataRejectQueue.clear();
        if (!runningProcesses.getCreatRunningProcesses().getQueue().getRejectQueue().getRejectQueue().isEmpty()) {
            try {
                for (final Process process : runningProcesses.getCreatRunningProcesses().getQueue().getRejectQueue().getRejectQueue()) {
                    dataRejectQueue.add(new Table(process));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update table of finished queue
     */
    public static void updateTableFinishedQueue() {
        dataFinishedQueue.clear();
        if (!runningProcesses.getCreatRunningProcesses().getQueue().getFinishedQueue().getFinishedQueue().isEmpty()) {
            try {
                for (final Process process : runningProcesses.getCreatRunningProcesses().getFinishedQueue().getFinishedQueue()) {

                    dataFinishedQueue.add(new Table(process));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set priority of the process
     *
     * @param priority priority
     */
    private void setPriority(final int priority) {
        final ArrayList<Process> processes = runningProcesses.getCreatRunningProcesses().getQueue().getReadyQueue().getReadyQueue();
        int i = 0;
        for (final Process process : processes) {
            i++;
            if (process.getName().equals(name)) {
                break;
            }
        }
        try {
            runningProcesses.getCreatRunningProcesses().getQueue().getReadyQueue().getReadyQueue().get(i).setPriority(priority);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Statistics() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!runningProcesses.getCreatRunningProcesses().getQueue().getQueue().isEmpty()) {
                    allProcessesQuantity.setText(String.valueOf(runningProcesses.getCreatRunningProcesses().getQueue().getLastID()));
                }
                if (!runningProcesses.getCreatRunningProcesses().getQueue().getRejectQueue().getRejectQueue().isEmpty()) {
                    quantityRejectQueue.setText(String.valueOf(runningProcesses.getCreatRunningProcesses().getQueue().getRejectQueue().getQuantityRejectQueue()));
                }
                if (!runningProcesses.getCreatRunningProcesses().getQueue().getFinishedQueue().getFinishedQueue().isEmpty()) {
                    quantityFinishedQueue.setText(String.valueOf(runningProcesses.getCreatRunningProcesses().getFinishedQueue().getQuantityFinishedQueue()));
                }
                quantityProcessesLeftSystem.setText(String.valueOf(runningProcesses.getCreatRunningProcesses().getQuantityProcessesLeft()));
                if (runningProcesses.getCreatRunningProcesses().getQueue().getAverageServiceWait() != 0) {
                    averageServiceWait.setText(runningProcesses.getCreatRunningProcesses().getQueue().getAverageServiceWait() / runningProcesses.getCreatRunningProcesses().getQueue().getLastID() + " tact(s)");
                }
                processorIdleCycles.setText(runningProcesses.getProcessorIdleCycles() + " tact(s)");
                if (runningProcesses.getCreatRunningProcesses().getAverageProcessWait() != 0) {
                    averageProcessWait.setText(runningProcesses.getCreatRunningProcesses().getAverageProcessWait() / runningProcesses.getCreatRunningProcesses().getQueue().getReadyQueue().getQuantityReadyQueue() + " tact(s)");
                }
                if (runningProcesses.getCreatRunningProcesses().getAverageProcessTime() != 0) {
                    averageProcessTime.setText(runningProcesses.getCreatRunningProcesses().getAverageProcessTime() / runningProcesses.getCreatRunningProcesses().getFinishedQueue().getQuantityFinishedQueue() + " tact(s)");
                }
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 1000L;
        long period = 1000L;
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }
}
