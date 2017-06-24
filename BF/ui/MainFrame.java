package ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFrame extends Stage{
	AnchorPane root;
	
	public MainFrame() throws IOException{
		
		root = FXMLLoader.load(getClass().getResource("MainFrame.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		//scene.getStylesheets().add(getClass().getResource("MainFrame.css").toExternalForm());
		this.setScene(scene);
		this.initStyle(StageStyle.DECORATED);
		this.show();
	}

}
