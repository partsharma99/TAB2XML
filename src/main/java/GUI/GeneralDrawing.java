package GUI;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GeneralDrawing {
	
	public static void drawNotes(double xcord, double ycord, String notenum, double font, Pane pane) {
	   	Text t = new Text(xcord, ycord, notenum);
        t.setFont(Font.font("Times New Roman", font));
        pane.getChildren().add(t);
	}
	
    public static void drawQuad(double startX, double startY,  double controlX, double controlY, double endX, double endY, Pane pane) {
    	DrawQuad quad = new DrawQuad(startX, startY, controlX, controlY, endX, endY);
    	pane.getChildren().add(quad.getQuadcurve());
    }

	public static void drawX(double xcord, double ycord, Pane pane) {
		double x1 = xcord - 5; 
		double y1 = ycord - 5;
		double x1end = xcord + 5;
		double y1end = ycord + 5;
		drawLine(x1, y1, x1end, y1end, pane);
		
		double x2 = xcord - 5;
		double y2 = ycord + 5;
		double x2end = xcord + 5;
		double y2end = ycord - 5;
		drawLine(x2, y2, x2end, y2end, pane);
	}

	public static void drawLine(double x1cord, double y1cord, double x2cord, double y2cord, Pane pane) {
		DrawLine linelast = new DrawLine(x1cord, y1cord, x2cord, y2cord);
		pane.getChildren().add(linelast.getLine());
	}

	public static void drawCircle(double xcord, double ycord, Pane pane) {
		DrawCircle circle = new DrawCircle(xcord, ycord); 
    	pane.getChildren().add(circle.getCircle());
	}
	
	private static void drawInstlines(Double firstliney, int numoflines, double yInc, double maxX, Pane pane) {
		for(int i=0; i<numoflines; i++) {
			drawLine(0, firstliney+(i*yInc), maxX, firstliney+(i*yInc), pane);
		}
	}

	public static void drawInstLinesHelper(ArrayList<Double> topofeachstaff, String instName, double yInc, double maxX, Pane pane) {
		int numoflines = 0;
		if(instName.equalsIgnoreCase("Guitar")) {
			numoflines = 6;
		}
		else if(instName.equalsIgnoreCase("Drumset")) {
			numoflines = 5;
		}
		else if(instName.equalsIgnoreCase("Bass")) {
			numoflines = 4;
		}
		for(int i=0; i<topofeachstaff.size(); i++) {
			drawInstlines(topofeachstaff.get(i), numoflines, yInc, maxX, pane);
		}
	}
}
