package com.example.batbro;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application
{
    @Override
    public void start(Stage stage) {
        try {
            ViewManager manager = new ViewManager();
            stage = manager.getMainStage();
            stage.setTitle("Bat Bro");
            stage.show();
        } catch(Exception e) {
           e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
