package main.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.models.Type;
import main.models.TypeList;
import main.services.TypeFileDataSource;


import java.io.IOException;

public class AddTypeController {
    @FXML   Button BackBtn,ConfirmBtn;
    @FXML
    private TextField TypeTexField;
    private TypeFileDataSource typeFileDataSource;
    private TypeList typeList;


    @FXML
    public void initialize(){
        Platform.runLater((Runnable)new Runnable(){
            @Override
            public void run() {
                typeFileDataSource = new TypeFileDataSource("data", "type.csv");
                typeList = typeFileDataSource.getTaskData();
            }
        });
    }
    @FXML
    public void handleConfirmBtn(){
        Type t1 = new Type(TypeTexField.getText(),0,0,0,0,0);
        typeList.addList(t1);
        typeFileDataSource.setFileData(typeList);
        TypeTexField.clear();
    }
    @FXML
    public void handleBackBtn() {
        try {
            FXRouter.goTo("TypeTable");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
