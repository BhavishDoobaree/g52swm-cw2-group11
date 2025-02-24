package com.neet.DiamondHunter.MapViewerController;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MapEditor extends Application {

	public static void main(String[] args) {
        Application.launch();
    }
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("com/neet/DiamondHunter/MapViewerController/GUILayout.fxml"));
        primaryStage.setTitle("Map Editor");
        primaryStage.setScene(new Scene(root, 940, 700));
        primaryStage.setResizable(true);
        primaryStage.show();
    }


	
	
}

