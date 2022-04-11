package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.Part2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

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

	public static void drawX(double xcord, double ycord, double font, Pane pane) {
		double x1 = xcord - font; 
		double y1 = ycord - font;
		double x1end = xcord + font;
		double y1end = ycord + font;
		drawLine(x1, y1, x1end, y1end, pane);

		double x2 = xcord - font;
		double y2 = ycord + font;
		double x2end = xcord + font;
		double y2end = ycord - font;
		drawLine(x2, y2, x2end, y2end, pane);
	}

	public static void drawLine(double x1cord, double y1cord, double x2cord, double y2cord, Pane pane) {
		DrawLine linelast = new DrawLine(x1cord, y1cord, x2cord, y2cord);
		pane.getChildren().add(linelast.getLine());
	}

	public static void drawCircle(double xcord, double ycord, double font, Pane pane) {
		DrawCircle circle = new DrawCircle(xcord, ycord, font); 
		pane.getChildren().add(circle.getCircle());
	}

	private static void drawInstLines(double firstliney, int numoflines, double yInc, double maxX, Pane pane) {
		for(int i=0; i<numoflines; i++) {
			drawLine(0, firstliney+(i*yInc), maxX, firstliney+(i*yInc), pane);
		}
	}

	public static void drawInstLinesHelper(ArrayList<barlineinfo> barlineholder, String instName, double yInc, double maxX, Pane pane) {
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
		for(int i=0; i<barlineholder.size(); i++) {
			if(barlineholder.get(i).getXpos()==0) {
				drawInstLines(barlineholder.get(i).getTopofstaff(), numoflines, yInc, maxX, pane);
			}
		}
	}

	public static void drawBarLinesHelper(ArrayList<barlineinfo> barlineholder, double lengthofbar, Pane pane) {
		for(int i=0; i<barlineholder.size(); i++) {
			drawLine(barlineholder.get(i).getXpos(), 
					barlineholder.get(i).getTopofstaff(), 
					barlineholder.get(i).getXpos(), 
					barlineholder.get(i).getTopofstaff()+lengthofbar, 
					pane);
		}
	}

	public static void drawRectangle(double x, double y, double width, double height, Pane pane) {
		DrawRectangle rectangle = new DrawRectangle(x, y, width, height);
		pane.getChildren().add(rectangle.getRectangle());
	}

	public static void drawemptymeasures(ArrayList<measureinfo> m, String instName, double yInc, Pane pane) {
		double lengthofbar = 0;
		if(instName.equalsIgnoreCase("Guitar")) {
			lengthofbar = 5 * yInc;
		}
		else if(instName.equalsIgnoreCase("Drumset")) {
			lengthofbar = 4 * yInc;
		}
		else if(instName.equalsIgnoreCase("Bass")) {
			lengthofbar = 3 * yInc;
		}
		
		
		for(int i=0; i<m.size(); i++) {
			if(m.get(i).getMeasure()==null) {
				double xc = m.get(i).getStartof().getXpos() + ((m.get(i).getEndof().getXpos() - m.get(i).getStartof().getXpos())/2);
				double yc = m.get(i).getStartof().getTopofstaff()+(lengthofbar/2);
				DrawLine d1 = new DrawLine(xc-(0.5*yInc), yc, xc+(0.5*yInc), yc);
				d1.setWidth(0.25*yInc);
				d1.setFill(Color.GRAY);
				pane.getChildren().add(d1.getLine());
			}
		}
		
	}

	public static void draclefAndTime(ScorePartwise2 sc, ArrayList<measureinfo> m, String instName, double xInc, double yInc,
			Pane pane) {
		
		List<Part2> partlist = sc.getListOfParts();
		int val1 = 0;
		int val2 = 0;
		double lengthofbar = 0;
		if(instName.equalsIgnoreCase("Guitar")) {
			lengthofbar = 5 * yInc;
		}
		else if(instName.equalsIgnoreCase("Drumset")) {
			lengthofbar = 4 * yInc;
		}
		else if(instName.equalsIgnoreCase("Bass")) {
			lengthofbar = 3 * yInc;
		}
		
		for(int i=0; i<partlist.size(); i++) {
			if(partlist.get(i)!=null && partlist.get(i).getListOfMeasures()!=null) {
				for(int j=0; j<partlist.get(i).getListOfMeasures().size(); j++) {
					if(partlist.get(i).getListOfMeasures().get(j).getAttributes()!=null && partlist.get(i).getListOfMeasures().get(j).getAttributes().getTime()!=null) {
						val1 = partlist.get(i).getListOfMeasures().get(j).getAttributes().getTime().getBeats();
						val2 = partlist.get(i).getListOfMeasures().get(j).getAttributes().getTime().getBeattype();
						drawclefAndtTimeH1(val1, m.get(j).getStartof().getXpos()+0.5*xInc, m.get(j).getStartof().getTopofstaff()+(0.25*lengthofbar), yInc, pane);
						drawclefAndtTimeH1(val2, m.get(j).getStartof().getXpos()+0.5*xInc, m.get(j).getStartof().getTopofstaff()+(0.75*lengthofbar), yInc, pane);
					}
				}
			}
		}
	}
	public static void drawclefAndtTimeH1(int num, double x, double y, double size, Pane pane){
		Text t = new Text(x, y, String.valueOf(num));
		t.setFont(Font.font("impact", size));
		pane.getChildren().add(t);
	}

}
