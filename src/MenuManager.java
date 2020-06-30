
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import javafx.scene.text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;





/**
 * @author niallcollinson
 * manages buttons in the main menu 
 *
 */
public class MenuManager {

		MenuManager(){
		
	
		helpManager();
		
		
	}

	
	
	
	
	/**
	 * creates help alert
	 */
	public void helpManager() {
		
		
		Alert alert = new Alert(AlertType.INFORMATION);				// define what box is
	    alert.setTitle("Help");									// say is About
	    alert.setHeaderText(null);
	    alert.setContentText("This is a Drone Simulator developed by Niall Collinson. "
	    		+ "If you are having trouble try restarting."
	    		+ "If you cannot resolve the issue contact Niall Collinson on 087482937291."
	    		+ "For a user guide email niallbenjamincollinson@pleasedontemailme.com.");			// give text
	    alert.showAndWait();
	    
		
	}
	
	
	

	
	
}
