package GUI;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DrawRectangle {
	@FXML 
 	private Rectangle rectangle = new Rectangle();

	public DrawRectangle() {
	}

	public DrawRectangle(double x, double y, double width, double height) {
		this.rectangle.setX(x);
		this.rectangle.setX(y);
		this.rectangle.setWidth(width);
		this.rectangle.setHeight(height);
		this.rectangle.setFill(Color.BLACK);
		this.rectangle.setStroke(Color.BLACK);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
}
