package com.neet.DiamondHunter.Main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {
	
	Stage primaryStage= new Stage();
	private static JFrame dhdiag;


	@FXML
	public void rungame(ActionEvent event) {
		System.out.println("built");
		dhdiag = new JFrame("Diamond Hunter");	
		
		dhdiag.add(new GamePanel());
		
		dhdiag.setResizable(false);
		dhdiag.pack();
		
		dhdiag.setLocationRelativeTo(null);
		dhdiag.setVisible(true);
		dhdiag.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

	}
	
	public static JFrame getWindow(){
		return dhdiag;
	}
	
	@FXML
	public void mapviewer() throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("../MapViewerController/GUILayout.fxml"));
        primaryStage.setTitle("Map Editor");        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
	
}

}
