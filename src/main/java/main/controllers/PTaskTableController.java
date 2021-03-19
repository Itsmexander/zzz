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
import main.services.ProjectTaskFileDataSource;
import main.services.StringConfiguration;
import main.services.TaskFileDataSource;
import main.services.TypeFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

import static java.time.format.DateTimeFormatter.ofPattern;

public class PTaskTableController {
    private TaskList taskList;
    private ProjectTaskFileDataSource dataSource;
    private ProjectTaskData selectedTask;
    private TypeFileDataSource typeFileDataSource;
    private TypeList typeList;
    private ObservableList<ProjectTaskData> taskDataObservableList;
    @FXML private TableView<ProjectTaskData> TaskTable;
    @FXML private TextField taskNameTextField,MngTexField;
    @FXML DatePicker sDatePicker;
    @FXML DatePicker fDatePicker;
    @FXML ChoiceBox<String> typeChoiceBox,priorityChoiceBox,statusChoiceBox,startHChoiceBox,startMChoiceBox,finishHChoiceBox,finishMChoiceBox;
    @FXML private ImageView sampleImage;
    @FXML private Button updateButton;
    @FXML private Button addTaskButton;
    @FXML private Label TaskLabel,StartLabel,FinishLabel,PriorityLabel,TypeLabel,StatusLabel,MngLabel,errMsgLabel;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable(){
            @Override
            public void run() {
                dataSource = new ProjectTaskFileDataSource("data", "Ptask.csv");
                typeFileDataSource = new TypeFileDataSource("data", "type.csv");
                for (int i = 0; i <= 9; i++) {
                    startHChoiceBox.getItems().add("0"+i);
                    finishHChoiceBox.getItems().add("0"+i);
                    startMChoiceBox.getItems().add("0"+i);
                    finishMChoiceBox.getItems().add("0"+i);
                }
                for (int i = 10; i <= 24; i++) {
                    startHChoiceBox.getItems().add(""+i);
                    finishHChoiceBox.getItems().add(""+i);
                    startMChoiceBox.getItems().add(""+i);
                    finishMChoiceBox.getItems().add(""+i);
                }
                for (int i = 25; i <=59 ; i++) {
                    startMChoiceBox.getItems().add(""+i);
                    finishMChoiceBox.getItems().add(""+i);
                }
                priorityChoiceBox.getItems().addAll("มากที่สุด","มาก","ปานกลาง","น้อย","น้อยที่สุด");
                statusChoiceBox.getItems().addAll("-","เสร็จแล้ว","กำลังทำ","ยังไม่ทำ");
                addTaskButton.setDisable(false);
                updateButton.setDisable(true);
                sampleImage.setImage(new Image("/1.jpg"));
                typeList = typeFileDataSource.getTaskData();
                for (Type t:typeList.toList())
                    typeChoiceBox.getItems().add(t.getTypeName());
                taskList = dataSource.getTaskData();
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
        taskDataObservableList = FXCollections.observableArrayList(taskList.toPList());
        TaskTable.setItems(taskDataObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ชื่องาน", "field:taskName"));
        configs.add(new StringConfiguration("title:หมวดหมู่", "field:type"));
        configs.add(new StringConfiguration("title:วันและเวลาที่เริ่ม", "field:start"));
        configs.add(new StringConfiguration("title:วันและเวลาที่เสร็จ", "field:finish"));
        configs.add(new StringConfiguration("title:ความสำคัญ", "field:priorityLevel"));
        configs.add(new StringConfiguration("title:สถานะ", "field:workStatus"));
        configs.add(new StringConfiguration("title:หัวหน้าโครงการ", "field:managerName"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            TaskTable.getColumns().add(col);
        }
    }

    private void showSelectedTask(ProjectTaskData taskData) {
        selectedTask = taskData;
        TaskLabel.setText(taskData.getTaskName());
        TaskLabel.setText(taskData.getTaskName());
        StartLabel.setText(taskData.getStart());
        FinishLabel.setText(taskData.getFinish());
        PriorityLabel.setText(taskData.getPriorityLevel());
        StatusLabel.setText(taskData.getWorkStatus());
        TypeLabel.setText(taskData.getType());
        MngLabel.setText(taskData.getManagerName());
        taskNameTextField.setText(taskData.getTaskName());
        MngTexField.setText(taskData.getManagerName());
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
        MngLabel.setText("");
        taskNameTextField.clear();
        MngTexField.clear();
        updateButton.setDisable(true);
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {
        if ((taskNameTextField.getText() == null)||(typeChoiceBox.getValue()==null)||(sDatePicker.getValue()==null)||(fDatePicker.getValue()==null)||(startHChoiceBox.getValue()==null)||(startMChoiceBox.getValue()==null)||(finishHChoiceBox.getValue()==null)||(finishMChoiceBox.getValue()==null)||(typeChoiceBox.getValue()==null)||(priorityChoiceBox.getValue()==null)||(MngTexField.getText()==null)){
            errMsgLabel.setText("โปรดกรอกข้อมูลให้ครบก่อนจะกดปุ่มอัพเดท");

        }
        else {
            if (((sDatePicker.getValue().atTime(Integer.parseInt(startHChoiceBox.getValue()), Integer.parseInt(startMChoiceBox.getValue()))).isAfter(fDatePicker.getValue().atTime(Integer.parseInt(finishHChoiceBox.getValue()),Integer.parseInt(finishMChoiceBox.getValue()))))){
                errMsgLabel.setText("วัน-เวลาเริ่มต้นอยู่หลังวัน-เวลาสิ้นสุด");
                }
            else {
                selectedTask.setTaskName(taskNameTextField.getText());
                selectedTask.setType(typeChoiceBox.getValue());
                selectedTask.setStart(sDatePicker.getValue().atTime(Integer.parseInt(startHChoiceBox.getValue()), Integer.parseInt(startMChoiceBox.getValue())).format(ofPattern("HH:mm dd-MM-yyyy")));
                selectedTask.setFinish(fDatePicker.getValue().atTime(Integer.parseInt(finishHChoiceBox.getValue()), Integer.parseInt(finishMChoiceBox.getValue())).format(ofPattern("HH:mm dd-MM-yyyy")));
                selectedTask.setWorkStatus(statusChoiceBox.getValue());
                selectedTask.setPriorityLevel(priorityChoiceBox.getValue());
                selectedTask.setManagerName(MngTexField.getText());
                typeList.changeSelectedTypeCount(selectedTask.getType(),typeChoiceBox.getValue(),"Project");
                clearSelectedTask();
                TaskTable.refresh();
                TaskTable.getSelectionModel().clearSelection();
                dataSource.setFileData(taskList);
                typeFileDataSource.setFileData(typeList);
            }
        }
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
        ProjectTaskData p1 = new ProjectTaskData("ชื่องาน","-","00:00 28-02-2021","00:00 01-03-2021",
                "ปานกลาง","ยังไม่ทำ","-");
        taskList.addPList(p1);
        typeList.addSelectedTypeCount("-","Project");
        dataSource.setFileData(taskList);
        typeFileDataSource.setFileData(typeList);
        TaskTable.refresh();
        showTaskData();
    }
}
