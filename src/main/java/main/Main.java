package main;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this,primaryStage,"Task Tracker v0.1",1280,1024);

        configRoute();

        FXRouter.goTo("home");

    }

    private static void configRoute() {
        FXRouter.when("home", "home.fxml");
        FXRouter.when("publisher", "publisher.fxml");
        FXRouter.when("taskTable", "TaskTable.fxml");
        FXRouter.when("weekTable", "WTaskTable.fxml");
        FXRouter.when("projectTaskTable", "PTaskTable.fxml");
        FXRouter.when("forwardTaskTable", "FTaskTable.fxml");
        FXRouter.when("TypeTable", "TypeTable.fxml");
        FXRouter.when("AddType", "AddType.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}