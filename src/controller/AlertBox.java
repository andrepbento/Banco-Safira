package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox implements Initializable  {
	
	private static String msg;
	private static int width = 0;

	
	@FXML
	private Label labelMessage;
	@FXML
	private static Button okButton;
	@FXML
	private TextField cobaia;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		labelMessage.setText(msg);
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	
	           cobaia.requestFocus();
	        }
	    });
	}
	
	private static Stage alertBox;
	
	public AlertBox(int width){
		AlertBox.width = width;
		
	}
	
	public AlertBox(){
		
	}
	
	@FXML
	public void onEnter(){
		this.close();
	}
	
	
	public  void display(String str){
		
		try{
		msg = str;
		alertBox = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/AlertBox.fxml"));/* Exception */
		alertBox.initModality(Modality.APPLICATION_MODAL);
		Scene scene;
		if(width != 0)
			scene = new Scene(root,width,100);
		
		else
			scene = new Scene(root,400,100);
			
		
		
		alertBox.setScene(scene);
		
		alertBox.showAndWait();
		}catch(IOException e){
			System.out.println("Error ao carregar o AlertBox");
		}
		
		
		
		
	}
	
	public void close(){
		alertBox.close();
		
	}
	
	
}
