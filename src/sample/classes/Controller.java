package sample.classes;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableView<Process> processTable;

    @FXML
    private TableColumn<Process, Integer> processId;

    @FXML
    private TableColumn<Process, String> processName;

    @FXML
    private TableColumn<Process, Integer> processPriority;

    @FXML
    private TableColumn<Process, Integer> processTime;

    @FXML
    private TableColumn<Process, Integer> processMemory;

    @FXML
    private TableColumn<Process, Integer> processTimeIn;

    @FXML
    private TableColumn<Process, Integer> processBurstTime;

    @FXML
    private TableColumn<Process, String> processState;

    private RProcesses rProcesses = new RProcesses();
    private RefreshRunningProcesses refreshRunningProcesses = new RefreshRunningProcesses();

    private  ObservableList<Process> data = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        processId.setCellValueFactory(new PropertyValueFactory<Process, Integer>("processId"));
        processName.setCellValueFactory(new PropertyValueFactory<Process, String>("processName"));
        processPriority.setCellValueFactory(new PropertyValueFactory<Process, Integer>("processPriority"));
        processTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("processTime"));
        processMemory.setCellValueFactory(new PropertyValueFactory<Process, Integer>("processMemory"));
        processTimeIn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("processTimeIn"));
        processBurstTime.setCellValueFactory(new PropertyValueFactory<Process, Integer>("processBurstTime"));
        processState.setCellValueFactory(new PropertyValueFactory<Process, String>("processState"));
        processTable.setItems(data);
        refreshRunningProcesses.run2(rProcesses);
       // data.addAll(rProcesses.getRunningProcesses().getQueue().getFinishedQueue().getFinishedQueue());
        System.out.println(rProcesses.getRunningProcesses().getQueue());


//        ClearFinishedQueue clearFinishedQueue = new ClearFinishedQueue();
//        clearFinishedQueue.run();

    }
}
