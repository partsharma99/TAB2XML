package GUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class PreviewMXLController {
	
	private MainViewController mvc;
	@FXML 
    private Pane pane;
	
	public void setMainViewController(MainViewController mvcInput) {
		mvc = mvcInput;
    }
	
	public void savePDF() {
	}
	
	public void handleGotoMeasure() {
	}
	
    
    //Update the SheetMusic GUI
    public void update() throws IOException {
    	//Implement later
	}
	
}
