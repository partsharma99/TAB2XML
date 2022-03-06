package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.text.Text;
import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;

import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.XmlToJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.xml.bind.JAXBException;

public class PreviewMXLController {
	
	@FXML public Canvas canvas;
	@FXML TextField gotoMeasureField;
	@FXML Button gotoMeasureButton;
	@FXML Button savePDF;
	public FXMLLoader loader;
	
	
	private MainViewController mvc;
	@FXML private Pane pane;
	private double startx;
	
	
	@FXML
	public void savePDF() {
	}
	
	@FXML
	public void handleGotoMeasure() {
	}
	
	public void setMainViewController(MainViewController mvcInput) {
		mvc = mvcInput;
    }
	
	//Draws the Music lines based on the instrument and adds them to the GUI
    public void instrumentMusicLines(String instrument, double y) {	
     	if (instrument.equalsIgnoreCase("guitar")) {
     		int i = 1;
     		while (i <= 6) {
             	DrawLine dl = new DrawLine(0.0, y, this.pane.getMaxWidth(), y);
            	pane.getChildren().add(dl.getLine());
            	i++;
            	y += 13;
        	}
     	}
     	else if (instrument.equalsIgnoreCase("drumset")) {
     		int i = 1;
     		while (i <= 5) {
             	DrawLine dl = new DrawLine(0.0, y, this.pane.getMaxWidth(), y);
            	pane.getChildren().add(dl.getLine());
            	i++;
            	y += 13;
        	}
     	}
     	else if (instrument.equalsIgnoreCase("bass")) {
     		int i = 1;
     		while (i <= 4) {
             	DrawLine dl = new DrawLine(0.0, y, this.pane.getMaxWidth(), y);
            	pane.getChildren().add(dl.getLine());
            	i++;
            	y += 13;
        	}
     	}
 	}	

    //TAB = guitar; (II) = bass and drum;
    public void drawClef(String symbol, double x, double y) {
        if (symbol.equalsIgnoreCase("TAB")) {
        	for (int i = 0; i < symbol.length(); i++, y += 22) {
                Text t = new Text(x, y, symbol.substring(i, i+1));
                t.setFont(Font.font("impact", 24));
                pane.getChildren().add(t);
            }
        }
        else if (symbol.equalsIgnoreCase("percussion")) {
        	//Move the Clef down for every staff
        	y = y + 19;
        	//Since we're outputting "II" instead of percussion, we have to change symbol
        	symbol = "II";
        	for (int i = 0; i < symbol.length(); i++, x += 12) {
                Text t = new Text(x, y, symbol.substring(i, i+1));
                t.setFont(Font.font("impact", 33));
                pane.getChildren().add(t);
            }
        }
        startx = x;
    }

    //Change the bar line length depending on the instrument
    private void barLines(double x, double y, String instrument) throws JAXBException {
    	if (instrument.equalsIgnoreCase("Guitar")) {
    		DrawLine middleBar = new DrawLine(x, y, x, y + 64);
    		pane.getChildren().add(middleBar.getLine());
        	DrawLine endBar = new DrawLine(x + 470, y, x + 470, y + 64);
          	pane.getChildren().add(endBar.getLine());
    	}
    	else if (instrument.equalsIgnoreCase("Drumset")) {
    		DrawLine middleBar = new DrawLine(x, y, x, y + 52);
        	DrawLine endBar = new DrawLine(x + 470, y, x + 470, y + 52);
        	pane.getChildren().add(middleBar.getLine());
          	pane.getChildren().add(endBar.getLine());
    	}
    	else if (instrument.equalsIgnoreCase("Bass")) {
    		DrawLine middleBar = new DrawLine(x, y, x, y + 40);
        	DrawLine endBar = new DrawLine(x + 470, y, x + 470, y + 40);
        	pane.getChildren().add(middleBar.getLine());
          	pane.getChildren().add(endBar.getLine());
    	}
    }
    
    public void drawNotes(double x, double y, String a, double fontsize) {
    	Text t = new Text(x, y, a);
        t.setFont(Font.font("Times New Roman", fontsize));
        pane.getChildren().add(t);
    	
    }
    
    public void drawCircle(double x, double y) {
        DrawCircle circle = new DrawCircle(x, y); 
    	pane.getChildren().add(circle.getCircle());
        }
    
    //Update the GUI
    
//    public double getlimit() throws JAXBException {
//    	ScorePartwise2 sc;
//		sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
//
//    	 int numMeasures = ListOfMeasureAndNote.getlistOfMeasures(sc).size();
//    	 double limit = Math.ceil(numMeasures/2);
//    			 if(limit==0) {
// 	    			limit=1;
// 	    		}
//
//		return limit;
//    	
//    	
//    }
    public void update() throws IOException {
    	ScorePartwise2 sc;
		try {
	      		sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
	      		int numMeasures = sc.getListOfParts().get(0).getListOfMeasures().size();
			    String instName = sc.getPartlist().getScorepart().get(0).getPartname();
			    String cleff = sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getClef().getSign();
			    List <Note2> notelist  = ListOfMeasureAndNote.getlistOfNotes(sc);
			    
			    int y = 0;
		        double limit = Math.ceil(numMeasures/2);
		  
		        for (int i = 1; i <= limit; i++) {
		        	instrumentMusicLines(instName, y);
		            //Draw TAB
		            drawClef(cleff, 6, 20+y);
		            //Draw Bar lines
//		            barLines(450, y, instName);
//		              y += 120;
		          }
		        
		        double xcord;
		        double ycord;
		        
		        if(instName.equalsIgnoreCase("Guitar")) {
		        	List<NoteAndPos> guitarNPlist = InstCordPos.setAndGetNotesPos(instName, notelist);
		        	String notenum;
		        	for(int k=0; k < guitarNPlist.size(); k++) {
		        		xcord = guitarNPlist.get(k).getX();
		        		ycord = guitarNPlist.get(k).getY();
		        		notenum = "" + guitarNPlist.get(k).getNote().getNotations().getTechnical().getFret();
		        		if(guitarNPlist.get(k).getNote().getGrace()!=null) {
		        			drawNotes(xcord, ycord, notenum, 6.5);
		        		}
		        		else {
		        			drawNotes(xcord, ycord, notenum, 13);
		        		}
		        		System.out.println("fret: " + notenum + ", x=" + xcord + ",  y=" + ycord);
		        	}
		        }
		        
		        else if(instName.equalsIgnoreCase("Drumset")) {
		        	String notehead;
		        	List<NoteAndPos> drumsetNPlist = InstCordPos.setAndGetNotesPos(instName, notelist);
		        	for(int k=0; k < drumsetNPlist.size(); k++) {
		        		xcord = drumsetNPlist.get(k).getX();
		        		ycord = drumsetNPlist.get(k).getY();
		        		notehead = drumsetNPlist.get(k).getNote().getNotehead();
		        		//drawing x at specified note
		        		if(notehead!=null && notehead.equalsIgnoreCase("x")){
		        			//line1
		        			double x1 = xcord - 5; 
		        			double y1 = ycord - 5;
		        			double x1end = xcord + 5;
		        			double y1end = ycord + 5;
		        			DrawLine linex1 = new DrawLine(x1, y1, x1end, y1end);
		        			//line2
		        			double x2 = xcord - 5;
		        			double y2 = ycord + 5;
		        			double x2end = xcord + 5;
		        			double y2end = ycord - 5;
		        			DrawLine linex2 = new DrawLine(x2, y2, x2end, y2end);
		        			pane.getChildren().add(linex1.getLine());
		        			pane.getChildren().add(linex2.getLine());
		        			//check to see if we need to draw horizontal line through x
		        			if(drumsetNPlist.get(k).getNote().getInstrument().getId().equalsIgnoreCase("P1-I50")) {
		        				DrawLine linelast = new DrawLine((x1-1), ycord, (x1end+1), ycord);
		        				pane.getChildren().add(linelast.getLine());
		        			}
		        		}
		        		//we are drawing circles as our notes otherwise
		        		else {
		        			drawCircle(xcord, ycord);
		        		}
		        	}
		        }
		        
		        else if(instName.equalsIgnoreCase("Bass")) {
		        	String notenum;
		        	List<NoteAndPos> bassNPlist = InstCordPos.setAndGetNotesPos(instName, notelist);
		        	for(int k=0; k < bassNPlist.size(); k++) {
		        		xcord = bassNPlist.get(k).getX();
		        		ycord = bassNPlist.get(k).getY();
		        		notenum = "" + bassNPlist.get(k).getNote().getNotations().getTechnical().getFret();
		        		if(bassNPlist.get(k).getNote().getGrace()!=null) {
		        			drawNotes(xcord, ycord, notenum, 6.5);
		        		}
		        		else {
		        			drawNotes(xcord, ycord, notenum, 13);
		        		}
		        	}
		        }
		        
		        
		} catch (JAXBException e) {
			e.printStackTrace();
		} 

    }
}
