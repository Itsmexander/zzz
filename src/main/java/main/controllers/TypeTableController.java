package main.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.models.Type;
import main.models.TypeList;
import main.services.StringConfiguration;
import main.services.TypeFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class TypeTableController {
    private TypeFileDataSource typeFileDataSource;
    private TypeList typeList;
    private Type selectedType;
    private ObservableList<Type> typeObservableList;
    @FXML private TableView<Type> TypeTable;
    @FXML private Label TNLabel,GTLabel,WTLabel,PTLabel,FTLabel;
    @FXML private Button AddTypeBtn,BackBtn;


    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable(){
            @Override
            public void run() {
                typeFileDataSource = new TypeFileDataSource("data", "type.csv");
                typeList = typeFileDataSource.getTaskData();

                TypeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedType(newValue);
                    }
                });
                showTypeData();
//                TypeTable.getColumns().clear();
            }
        });
    }

    private void showSelectedType(Type type) {
        selectedType = type;
        TNLabel.setText(type.getTypeName());
        GTLabel.setText(String.valueOf(type.getTCount()));
        WTLabel.setText(String.valueOf(type.getWTCount()));
        PTLabel.setText(String.valueOf(type.getPTCount()));
        FTLabel.setText(String.valueOf(type.getFTCount()));
    }

    private void showTypeData() {
        typeObservableList = FXCollections.observableArrayList(typeList.toList());
        TypeTable.setItems(typeObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:ชื่อหมวดหมู่", "field:typeName"));
        configs.add(new StringConfiguration("title:จำนวนงานทั่วไป", "field:tCount"));
        configs.add(new StringConfiguration("title:จำนวนงานรายสัปดาห์", "field:wTCount"));
        configs.add(new StringConfiguration("title:จำนวนงานโครงการ", "field:pTCount"));
        configs.add(new StringConfiguration("title:จำนวนงานส่งต่อ", "field:fTCount"));
        configs.add(new StringConfiguration("title:จำนวนงานทั้งหมด", "field:sTCount"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            TypeTable.getColumns().add(col);
        }
    }
    @FXML public void handleBackBtn(){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML public void handleAddTypeBtn(){
        try {
            FXRouter.goTo("AddType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AddType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
