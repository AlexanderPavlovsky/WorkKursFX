package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.classes.ClearFinishedQueue;
import sample.classes.Process;
import sample.classes.Queue;
import sample.classes.RunningProcesses;

public class Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableView<Table> processTable;

    @FXML
    private TableColumn<Table, Integer> processId;

    @FXML
    private TableColumn<Table, String> processName;

    @FXML
    private TableColumn<Table, Integer> processPriority;

    @FXML
    private TableColumn<Table, Integer> processTime;

    @FXML
    private TableColumn<Table, Integer> processMemory;

    @FXML
    private TableColumn<Table, Integer> processTimeIn;

    @FXML
    private TableColumn<Table, Integer> processBurstTime;

    @FXML
    private TableColumn<Table, String> processState;

    RunningProcesses runningProcesses = new RunningProcesses();

    private ObservableList<Table> data = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        processId.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processId"));
        processName.setCellValueFactory(new PropertyValueFactory<Table, String>("processName"));
        processPriority.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processPriority"));
        processTime.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTime"));
        processMemory.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processMemory"));
        processTimeIn.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processTimeIn"));
        processBurstTime.setCellValueFactory(new PropertyValueFactory<Table, Integer>("processBurstTime"));
        processState.setCellValueFactory(new PropertyValueFactory<Table, String>("processState"));
       // UpdateTable updateTable = new UpdateTable();
       // updateTable.run();
        ClearFinishedQueue clearFinishedQueue = new ClearFinishedQueue();
        clearFinishedQueue.run();

    }

    public void creatTable() {
        if (Queue.finishedQueue != null) {
            for (Process process : Queue.finishedQueue) {
                data.addAll(new Table(process));
            }
        }

    }

}
