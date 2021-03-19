package main.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    @FXML
    public void handlePublisherButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("publisher");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pub ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleTaskTableButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("taskTable");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า taskTable ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleWTaskTableButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("weekTable");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า weekTable ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleFTaskTableButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("forwardTaskTable");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า forwardTaskTable ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handlePTaskTableButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("projectTaskTable");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า projectTaskTable ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleTypeTableButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("TypeTable");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า TypeTable ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleHelpBTN(ActionEvent actionEvent){
        try {
            FXRouter.goTo("Help");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Help ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}