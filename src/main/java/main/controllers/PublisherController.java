package main.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;

public class PublisherController {
    @FXML
    private ImageView image;
    @FXML
    public void initialize() {
        image.setImage(new Image("/IMG_1909.jpg"));
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}