package GUI;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;

public class DrawQuad {
	@FXML 
 	private QuadCurve quadcurve = new QuadCurve();
	
	public DrawQuad(double startX, double startY,  double controlX, double controlY, double endX, double endY) {
		this.quadcurve.setStartX(startX);
		this.quadcurve.setStartY(startY);
		this.quadcurve.setControlX(controlX);
		this.quadcurve.setControlY(controlY);
		this.quadcurve.setEndX(endX);
		this.quadcurve.setEndY(endY);
		this.quadcurve.setStroke(Color.BLACK);
		this.quadcurve.setFill(Color.TRANSPARENT);
	}

	public QuadCurve getQuadcurve() {
		return quadcurve;
	}

	public void setQuadcurve(QuadCurve quadcurve) {
		this.quadcurve = quadcurve;
	}
	
}
