package GUI;

import javafx.fxml.FXML;
import javafx.scene.shape.Line;

public class MusicLine {
	
	@FXML 
	private Line line = new Line();

 	public MusicLine(double startX, double startY, double endX, double endY) {
     	this.line.setStartX(startX); 
     	this.line.setStartY(startY);         
     	this.line.setEndX(endX); 
     	this.line.setEndY(endY);
 	}

 	public Line getLine() {
 		return line;
 	}

 	public void setLine(Line line) {
 		this.line = line;
 	}

}
