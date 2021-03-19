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
import main.services.StringConfiguration;
import main.services.TypeFileDataSource;
import main.services.WeeklyTaskFileDataSource;

import java.io.IOException;
import java.util.ArrayList;


public class WTaskTableController{
    private TaskList taskList;
    private WeeklyTaskFileDataSource weeklyTaskFileDataSource;
    private WeeklyTaskData selectedWTask;
    private TypeFileDataSource typeFileDataSource;
    private TypeList typeList;
    private ObservableList<WeeklyTaskData> taskDataObservableList;
    private ArrayList<String> dayA = new ArrayList<>() ;
    private String dayS="";
    @FXML private TableView<WeeklyTaskData> TaskTable;
    @FXML private TextField taskNameTextField;
    @FXML ChoiceBox<String> typeChoiceBox,priorityChoiceBox,statusChoiceBox,startHChoiceBox,startMChoiceBox,finishHChoiceBox,finishMChoiceBox;
    @FXML private ImageView sampleImage;
    @FXML private Button updateButton;
    @FXML private Button addTaskButton;
    @FXML private Label TaskLabel,StartLabel,FinishLabel,PriorityLabel,TypeLabel,StatusLabel,DaysLabel,errMsgLabel;
    @FXML private CheckBox MonChkBox,TueChkBox,WedChkBox,ThuChkBox,FriChkBox,SatChkBox,SunChkBox;

    public WTaskTableController() {
    }

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable(){
            @Override
            public void run() {
                weeklyTaskFileDataSource = new WeeklyTaskFileDataSource("data", "w_task.csv");
                typeFileDataSource = new TypeFileDataSource("data", "type.csv");
                typeList = typeFileDataSource.getTaskData();
                taskList = weeklyTaskFileDataSource.getTaskData();
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
                MonChkBox.setDisable(true);
                TueChkBox.setDisable(true);
                WedChkBox.setDisable(true);
                ThuChkBox.setDisable(true);
                FriChkBox.setDisable(true);
                SatChkBox.setDisable(true);
                SunChkBox.setDisable(true);
                sampleImage.setImage(new Image("/1.jpg"));
                for (Type t:typeList.toList())
                    //System.out.println(t.toString());
                    typeChoiceBox.getItems().add(t.getTypeName());
/*                for (WeeklyTaskData w :taskList.toWList())
                    System.out.println(w.toString());*/

                TaskTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedTask(newValue);
                    }
                });
                showTaskData();
            }
        });
    }
    private void showTaskData() {
        taskDataObservableList = FXCollections.observableArrayList(taskList.toWList());
        TaskTable.setItems(taskDataObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ชื่องาน", "field:taskName"));
        configs.add(new StringConfiguration("title:หมวดหมู่", "field:type"));
        configs.add(new StringConfiguration("title:เวลาที่เริ่ม", "field:start"));
        configs.add(new StringConfiguration("title:เวลาที่เสร็จ", "field:finish"));
        configs.add(new StringConfiguration("title:ความสำคัญ", "field:priorityLevel"));
        configs.add(new StringConfiguration("title:สถานะ", "field:workStatus"));
        configs.add(new StringConfiguration("title:วัน", "field:day"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            TaskTable.getColumns().add(col);
        }
    }

    private void showSelectedTask(WeeklyTaskData weeklyTaskData) {
        selectedWTask = weeklyTaskData;
        TaskLabel.setText(weeklyTaskData.getTaskName());
        TypeLabel.setText(weeklyTaskData.getType());
        StartLabel.setText(weeklyTaskData.getStart());
        FinishLabel.setText(weeklyTaskData.getFinish());
        PriorityLabel.setText(weeklyTaskData.getPriorityLevel());
        StatusLabel.setText(weeklyTaskData.getWorkStatus());
        DaysLabel.setText(weeklyTaskData.getDay());
        taskNameTextField.setText(weeklyTaskData.getTaskName());
        MonChkBox.setDisable(false);
        TueChkBox.setDisable(false);
        WedChkBox.setDisable(false);
        ThuChkBox.setDisable(false);
        FriChkBox.setDisable(false);
        SatChkBox.setDisable(false);
        SunChkBox.setDisable(false);
        updateButton.setDisable(false);
        }

    private void clearSelectedTask() {
        selectedWTask = null;
        TaskLabel.setText("");
        StartLabel.setText("");
        FinishLabel.setText("");
        PriorityLabel.setText("");
        StatusLabel.setText("");
        TypeLabel.setText("");
        DaysLabel.setText("");
        taskNameTextField.clear();
        MonChkBox.setDisable(true);
        TueChkBox.setDisable(true);
        WedChkBox.setDisable(true);
        ThuChkBox.setDisable(true);
        FriChkBox.setDisable(true);
        SatChkBox.setDisable(true);
        SunChkBox.setDisable(true);
        updateButton.setDisable(true);
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {
        if (Integer.parseInt(startHChoiceBox.getValue())< Integer.parseInt(finishHChoiceBox.getValue())){
            if ((!MonChkBox.isSelected())&&(!TueChkBox.isSelected())&&(!WedChkBox.isSelected())&&(!ThuChkBox.isSelected())&&(!FriChkBox.isSelected())&&(!SatChkBox.isSelected())&&(!SunChkBox.isSelected())){
                errMsgLabel.setText("โปรดเลือกวัน");
            }
            else {
                dayA.clear();
                selectedWTask.setTaskName(taskNameTextField.getText());
                selectedWTask.setType(typeChoiceBox.getValue());
                selectedWTask.setStart(Integer.parseInt(startHChoiceBox.getValue())+":"+Integer.parseInt(startMChoiceBox.getValue()));
                selectedWTask.setFinish(Integer.parseInt(finishHChoiceBox.getValue())+":"+Integer.parseInt(finishMChoiceBox.getValue()));
                selectedWTask.setPriorityLevel(priorityChoiceBox.getValue());
                if (MonChkBox.isSelected()) dayA.add("Mon");
                if (TueChkBox.isSelected()) dayA.add("Tue");
                if (WedChkBox.isSelected()) dayA.add("Wed");
                if (ThuChkBox.isSelected()) dayA.add("Thu");
                if (FriChkBox.isSelected()) dayA.add("Fri");
                if (SatChkBox.isSelected()) dayA.add("Sat");
                if (SunChkBox.isSelected()) dayA.add("Sun");
                for (int i = 0; i <=dayA.size() ; i++) {
                    dayS = dayS+dayA.get(0);
                    dayA.remove(0);;
                    if (!dayA.isEmpty()){
                        dayS = dayS + "-";
                    }
                }
                selectedWTask.setDay(dayS);
                typeList.changeSelectedTypeCount(selectedWTask.getType(),typeChoiceBox.getValue(),"Weekly");
                clearSelectedTask();
                TaskTable.refresh();
                TaskTable.getSelectionModel().clearSelection();
                weeklyTaskFileDataSource.setFileData(taskList);
                typeFileDataSource.setFileData(typeList);
            }
        }
        else if (Integer.parseInt(startHChoiceBox.getValue())== Integer.parseInt(finishHChoiceBox.getValue())){
            if (Integer.parseInt(startMChoiceBox.getValue())< Integer.parseInt(finishMChoiceBox.getValue())) {
                if ((!MonChkBox.isSelected())&&(!TueChkBox.isSelected())&&(!WedChkBox.isSelected())&&(!ThuChkBox.isSelected())&&(!FriChkBox.isSelected())&&(!SatChkBox.isSelected())&&(!SunChkBox.isSelected())){
                    errMsgLabel.setText("โปรดเลือกวัน");
                }
                else {
                    selectedWTask.setTaskName(taskNameTextField.getText());
                    selectedWTask.setType(typeChoiceBox.getValue());
                    selectedWTask.setStart(Integer.parseInt(startHChoiceBox.getValue())+":"+Integer.parseInt(startMChoiceBox.getValue()));
                    selectedWTask.setFinish(Integer.parseInt(finishHChoiceBox.getValue())+":"+Integer.parseInt(finishMChoiceBox.getValue()));
                    selectedWTask.setPriorityLevel(priorityChoiceBox.getValue());
                    if (MonChkBox.isSelected()) dayA.add("Mon");
                    if (TueChkBox.isSelected()) dayA.add("Tue");
                    if (WedChkBox.isSelected()) dayA.add("Wed");
                    if (ThuChkBox.isSelected()) dayA.add("Thu");
                    if (FriChkBox.isSelected()) dayA.add("Fri");
                    if (SatChkBox.isSelected()) dayA.add("Sat");
                    if (SunChkBox.isSelected()) dayA.add("Sun");
                    for (int i = 0; i <=dayA.size() ; i++) {
                        dayS = dayS+dayA.get(0);
                        dayA.remove(0);;
                        if (!dayA.isEmpty()){
                            dayS = dayS + "-";
                        }
                    }
                    selectedWTask.setDay(dayS);
                    typeList.changeSelectedTypeCount(selectedWTask.getType(),typeChoiceBox.getValue(),"Weekly");
                    clearSelectedTask();
                    TaskTable.refresh();
                    TaskTable.getSelectionModel().clearSelection();
                    weeklyTaskFileDataSource.setFileData(taskList);
                    typeFileDataSource.setFileData(typeList);
                }
            }
        }
        else {
            errMsgLabel.setText("เวลาเริ่มต้นอยู่หลังเวลาสิ้นสุด");
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
        WeeklyTaskData n1 = new WeeklyTaskData("ชื่องาน","-","00:00","00:00",
                "ปานกลาง","ยังไม่ทำ","-");
        typeList.addSelectedTypeCount("-","Weekly");
        taskList.addWList(n1);
        typeFileDataSource.setFileData(typeList);
        weeklyTaskFileDataSource.setFileData(taskList);
        TaskTable.refresh();
        showTaskData();
    }
}
