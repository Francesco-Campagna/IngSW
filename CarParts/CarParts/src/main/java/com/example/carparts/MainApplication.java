package com.example.carparts;

import com.example.carparts.Database.DBConnection;
import com.example.carparts.Model.Buono;
import com.example.carparts.View.SceneHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.sql.SQLException;


public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Buono.creaBuoni();
            DBConnection.getInstance().createConnection();
            SceneHandler.getInstance().init(primaryStage);
        } catch(Exception e) {
            e.printStackTrace();
        }
        primaryStage.setOnCloseRequest(event -> {
            Platform.runLater(() -> {
                try {
                    DBConnection.getInstance().closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}