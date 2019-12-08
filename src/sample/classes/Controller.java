package sample.classes;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableView<Table> processTableReadyQueue;

    @FXML
    private TableColumn<Table, Integer> processIdReadyQueue;

    @FXML
    private TableColumn<Table, String> processNameReadyQueue;

    @FXML
    private TableColumn<Table, Integer> processPriorityReadyQueue;

    @FXML
    private TableColumn<Table, Integer> processTimeReadyQueue;

    @FXML
    private TableColumn<Table, Integer> processMemoryReadyQueue;

    @FXML
    private TableColumn<Table, Integer> processTimeInReadyQueue;

    @FXML
    private TableColumn<Table, Integer> processBurstTimeReadyQueue;

    @FXML
    private TableColumn<Table, String> processStateReadyQueue;

    @FXML
    private TableView<Table> processTableRejectQueue;

    @FXML
    private TableColumn<Table, Integer> processIdRejectQueue;

    @FXML
    private TableColumn<Table, String> processNameRejectQueue;

    @FXML
    private TableColumn<Table, Integer> processPriorityRejectQueue;

    @FXML
    private TableColumn<Table, Integer> processTimeRejectQueue;

    @FXML
    private TableColumn<Table, Integer> processMemoryRejectQueue;

    @FXML
    private TableColumn<Table, Integer> processTimeInRejectQueue;

    @FXML
    private TableColumn<Table, Integer> processBurstTimeRejectQueue;

    @FXML
    private TableColumn<Table, String> processStateRejectQueue;


    @FXML
    private TableView<Table> processTableFinishedQueue;

    @FXML
    private TableColumn<Table, Integer> processIdFinishedQueue;

    @FXML
    private TableColumn<Table, String> processNameFinishedQueue;

    @FXML
    private TableColumn<Table, Integer> processPriorityFinishedQueue;

    @FXML
    private TableColumn<Table, Integer> processTimeFinishedQueue;

    @FXML
    private TableColumn<Table, Integer> processMemoryFinishedQueue;

    @FXML
    private TableColumn<Table, Integer> processTimeInFinishedQueue;

    @FXML
    private TableColumn<Table, Integer> processBurstTimeFinishedQueue;

    @FXML
    private TableColumn<Table, String> processStateFinishedQueue;

    private String name;
    @FXML
    void contextMenu(ActionEvent event) {
        name  = processTableReadyQueue.getSelectionModel().getSelectedItem().getProcessName();
        System.out.println(name);
    }

    @FXML
    void highLevel(ActionEvent event) {
        ArrayList<Process> processes = rProcesses.getRunningProcesses().getQueue().getReadyQueue().getReadyQueue();
        int i = 0;
        for(Process process: processes){
            i++;
            if(process.getName().equals(name)){
                break;
            }
        }
        rProcesses.getRunningProcesses().getQueue().getReadyQueue().getReadyQueue().get(i).setPriority(1);
        System.out.println(rProcesses.getRunningProcesses().getQueue().getReadyQueue().getReadyQueue().get(i));
    }


    private static RProcesses rProcesses = new RProcesses();
    private RefreshRunningProcesses refreshRunningProcesses = new RefreshRunningProcesses(rProcesses);
    private UpdateTable updateTable = new UpdateTable();
    private ClearQueues clearQueues = new ClearQueues(rProcesses.getRunningProcesses().getQueue().getFinishedQueue(), rProcesses.getRunningProcesses().getQueue().getRejectQueue());

    private static ObservableList<Table> dataReadyQueue = FXCollections.observableArrayList();
    private static ObservableList<Table> dataRejectQueue = FXCollections.observableArrayList();
    private static ObservableList<Table> dataFinishedQueue = FXCollections.observableArrayList();


    @FXML
    void initialize() {
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
        clearQueues.run();
    }

    public static void updateTableReadyQueue() {
        dataReadyQueue.clear();
        if (!rProcesses.getRunningProcesses().getQueue().getReadyQueue().getReadyQueue().isEmpty()) {
            for (Process process : rProcesses.getRunningProcesses().getQueue().getReadyQueue().getReadyQueue()) {
                dataReadyQueue.add(new Table(process));
            }
        }
    }

    public static void updateTableRejectQueue() {
        dataRejectQueue.clear();
        if (!rProcesses.getRunningProcesses().getQueue().getRejectQueue().getRejectQueue().isEmpty()) {
            for (Process process : rProcesses.getRunningProcesses().getQueue().getRejectQueue().getRejectQueue()) {
                dataRejectQueue.add(new Table(process));
            }
        }
    }

    public static void updateTableFinishedQueue() {
        dataFinishedQueue.clear();
        if (!rProcesses.getRunningProcesses().getQueue().getFinishedQueue().getFinishedQueue().isEmpty()) {
            for (Process process : rProcesses.getRunningProcesses().getFinishedQueue().getFinishedQueue()) {
                dataFinishedQueue.add(new Table(process));
            }
        }
    }
}
