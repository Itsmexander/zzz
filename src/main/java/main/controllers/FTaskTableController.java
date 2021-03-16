package main.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.models.*;
import main.services.FTaskFileDataSource;
import main.services.StringConfiguration;
import main.services.TypeFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

import static java.time.format.DateTimeFormatter.ofPattern;

public class FTaskTableController {
    private TaskList taskList;
    private FTaskFileDataSource dataSource;
    private ForwardingTaskData selectedTask;
    private TypeFileDataSource typeFileDataSource;
    private TypeList typeList;
    private ObservableList<ForwardingTaskData> taskDataObservableList;
    @FXML private TableView<ForwardingTaskData> TaskTable;
    @FXML private TextField taskNameTextField,ResTexField;
    @FXML DatePicker sDatePicker;
    @FXML DatePicker fDatePicker;
    @FXML ChoiceBox<Integer> startHChoiceBox,startMChoiceBox,finishHChoiceBox,finishMChoiceBox;
    @FXML ChoiceBox<String> typeChoiceBox,priorityChoiceBox,statusChoiceBox;
    @FXML private ImageView sampleImage;
    @FXML private Button updateButton;
    @FXML private Button addTaskButton;
    @FXML private Label TaskLabel,StartLabel,FinishLabel,PriorityLabel,TypeLabel,StatusLabel,ResLabel;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable(){
            @Override
            public void run() {
                dataSource = new FTaskFileDataSource("data", "Ftask.csv");
                typeFileDataSource = new TypeFileDataSource("data", "type.csv");
                typeList = typeFileDataSource.getTaskData();
                taskList = dataSource.getTaskData();
                for (int i = 0; i <= 23; i++) {
                    startHChoiceBox.getItems().add(i);
                    finishHChoiceBox.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++) {
                    startMChoiceBox.getItems().add(i);
                    finishMChoiceBox.getItems().add(i);
                }
                priorityChoiceBox.getItems().addAll("มากที่สุด","มาก","ปานกลาง","น้อย","น้อยที่สุด");
                statusChoiceBox.getItems().addAll("-","เสร็จแล้ว","กำลังทำ","ยังไม่ทำ");
                addTaskButton.setDisable(false);
                updateButton.setDisable(true);
                sampleImage.setImage(new Image("/1.jpg"));
                for (Type t:typeList.toList())
                    typeChoiceBox.getItems().add(t.getTypeName());
/*                for (ForwardingTaskData w :taskList.toFList())
                    System.out.println(w.toString());*/
                showTaskData();

                TaskTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedTask(newValue);
                    }
                });
            }
        });
    }

    private void showTaskData() {
        taskDataObservableList = FXCollections.observableArrayList(taskList.toFList());
        TaskTable.setItems(taskDataObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ชื่องาน", "field:taskName"));
        configs.add(new StringConfiguration("title:หมวดหมู่", "field:type"));
        configs.add(new StringConfiguration("title:วันและเวลาที่เริ่ม", "field:start"));
        configs.add(new StringConfiguration("title:วันและเวลาที่เสร็จ", "field:finish"));
        configs.add(new StringConfiguration("title:ความสำคัญ", "field:priorityLevel"));
        configs.add(new StringConfiguration("title:สถานะ", "field:workStatus"));
        configs.add(new StringConfiguration("title:ผู้รับผิดชอบ", "field:responsibleName"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            TaskTable.getColumns().add(col);
        }
    }

    private void showSelectedTask(ForwardingTaskData taskData) {
        selectedTask = taskData;
        TaskLabel.setText(taskData.getTaskName());
        TaskLabel.setText(taskData.getTaskName());
        StartLabel.setText(taskData.getStart());
        FinishLabel.setText(taskData.getFinish());
        PriorityLabel.setText(taskData.getPriorityLevel());
        StatusLabel.setText(taskData.getWorkStatus());
        TypeLabel.setText(taskData.getType());
        ResLabel.setText(taskData.getResponsibleName());
        taskNameTextField.setText(taskData.getTaskName());
        ResTexField.setText(taskData.getResponsibleName());
        updateButton.setDisable(false);
        }

    private void clearSelectedTask() {
        selectedTask = null;
        TaskLabel.setText("");
        StartLabel.setText("");
        FinishLabel.setText("");
        PriorityLabel.setText("");
        StatusLabel.setText("");
        TypeLabel.setText("");
        ResLabel.setText("");
        taskNameTextField.clear();
        ResTexField.clear();
        updateButton.setDisable(true);
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {
        selectedTask.setTaskName(taskNameTextField.getText());
        selectedTask.setType(typeChoiceBox.getValue());
        selectedTask.setStart(sDatePicker.getValue().atTime(startHChoiceBox.getValue(),startMChoiceBox.getValue()).format(ofPattern("yyyy-MM-dd HH:mm")));
        selectedTask.setFinish(fDatePicker.getValue().atTime(finishHChoiceBox.getValue(),finishMChoiceBox.getValue()).format(ofPattern("yyyy-MM-dd HH:mm")));
        selectedTask.setWorkStatus(statusChoiceBox.getValue());
        selectedTask.setPriorityLevel(priorityChoiceBox.getValue());
        selectedTask.setResponsibleName(ResTexField.getText());
        clearSelectedTask();
        TaskTable.refresh();
        TaskTable.getSelectionModel().clearSelection();
        dataSource.setFileData(taskList);
    }

    @FXML
    public void handleHomeButton(ActionEvent event){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleAddTaskButton(ActionEvent event){
        TaskTable.getColumns().clear();
        ForwardingTaskData p1 = new ForwardingTaskData("ชื่องาน","-","2021-02-28 00:00","2021-03-01 00:00",
                "ปานกลาง","ยังไม่ทำ","-");
        taskList.addFList(p1);
        dataSource.setFileData(taskList);
        TaskTable.refresh();
        showTaskData();
    }
}
