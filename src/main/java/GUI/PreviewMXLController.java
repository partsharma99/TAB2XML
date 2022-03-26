package GUI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
//import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
//import javafx.fxml.Initializable;
//
//import javafx.scene.*;

import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.NoteHead2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.Tied2;
import xml.to.sheet.converter.POJOClasses.Time2;
import xml.to.sheet.converter.POJOClasses.XmlToJava;

import java.awt.geom.Arc2D;
import java.io.IOException;

//import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

//import javax.swing.JLabel;
import javax.xml.bind.JAXBException;

public class PreviewMXLController {
	@FXML 
	private AnchorPane anchorPane;
	@FXML private Canvas canvas;
	@FXML TextField gotoMeasureField;
	@FXML Button gotoMeasureButton;

	@FXML Button savePDF;

	public FXMLLoader loader;
	@FXML 
	Button printPDF;
	BooleanProperty printButtonPressed = new SimpleBooleanProperty(false);
	
//	@FXML
//	public void printPDF() {
//	}
	
	
	private MainViewController mvc;
	@FXML private Pane pane;
	
	@FXML
	public <printButtonPressed> void printPDF() {

		Printer p = Printer.getDefaultPrinter();
		PrinterJob sheetToPrint = PrinterJob.createPrinterJob();
		PageLayout l = p.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
		WritableImage snap = anchorPane.snapshot(null, null);
		ImageView view = new ImageView(snap);

		double x = l.getPrintableWidth()/snap.getWidth();
		double y = l.getPrintableHeight()/snap.getHeight();
		
		view.getTransforms().add(new Scale(x, x));

		if (sheetToPrint.showPrintDialog(pane.getScene().getWindow()) && sheetToPrint != null) {
			Translate grid = new Translate(0, 0);
			view.getTransforms().add(grid);
			int currentPage = 0;
			while(currentPage < Math.ceil(x/y)) {
				grid.setY(-currentPage * (l.getPrintableHeight()/ x));
				sheetToPrint.printPage(l, view);
				currentPage++;
			}
			sheetToPrint.endJob();
		}
	}
	
	@FXML
	public void handleGotoMeasure() {
	}


//	public void drawLines() {
//		gc = canvas.getGraphicsContext2D();
//        gc.setFill(Color.WHITE);
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        System.out.println("color set to white and background rectangle drawn");
//        
//        gc.strokeLine(10, 100, 200, 100);
//        gc.strokeLine(10, 110, 200, 110);
//        gc.strokeLine(10, 120, 200, 120);
//        gc.strokeLine(10, 130, 200, 130);
//        gc.strokeLine(10, 140, 200, 140);
//        System.out.println("drawn lines");
//	}
//	
//	public void drawTestRectangle() {
//		gc = canvas.getGraphicsContext2D();
//        gc.setFill(Color.BLACK);
//        System.out.println("color set to black");
//        gc.fillRect(50, 50, 100, 100);
//        System.out.println("draw rectangle");
//	}
	// paint the canvas
//	public void paint(GraphicsContext g) {
//		// set color to red
//		//g.setColor(Color.BLACK);
//		g.setFill(Color.ALICEBLUE);
//
//		// set Font
//		g.setFont(new Font("Bold", 1));
//		g.lineTo(100, 100);
//
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//	}

	
 

	
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
    
    public void drawQuad(double startX, double startY,  double controlX, double controlY, double endX, double endY) {
    	DrawQuad quad = new DrawQuad(startX, startY, controlX, controlY, endX, endY);
    	pane.getChildren().add(quad.getQuadcurve());
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
//    }
    
    public void drawTimeSignature(double x, double y, String instrument, int beats, int beatType) {
    	if (instrument.equalsIgnoreCase("Guitar")) {
    		y = y + 3.5;
    		Text topNum = new Text(x, y, Integer.toString(beats));
    		topNum.setFont(Font.font("calibri", 40));
     		y += 31;
     		Text lowerNum = new Text(x, y, Integer.toString(beatType));
     		lowerNum.setFont(Font.font("calibri", 40));
     		pane.getChildren().add(topNum);
     		pane.getChildren().add(lowerNum);
    	}
    	else if (instrument.equalsIgnoreCase("Drumset")) {
    		y = y - 4;
    		Text topNum = new Text(x, y, Integer.toString(beats));
    		topNum.setFont(Font.font("calibri", 30));
     		y += 24;
     		Text lowerNum = new Text(x, y, Integer.toString(beatType));
     		lowerNum.setFont(Font.font("calibri", 30));
     		pane.getChildren().add(topNum);
     		pane.getChildren().add(lowerNum);
    	}
    	else {
    		y = y - 9;
    		Text topNum = new Text(x, y, Integer.toString(beats));
    		topNum.setFont(Font.font("calibri", 24));
     		y += 18;
     		Text lowerNum = new Text(x, y, Integer.toString(beatType));
     		lowerNum.setFont(Font.font("calibri", 24));
     		pane.getChildren().add(topNum);
     		pane.getChildren().add(lowerNum);
    	}
 	}
    
    public void update() throws IOException {
    	ScorePartwise2 sc;
		try {
	      		sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
	      		double numMeasures = sc.getListOfParts().get(0).getListOfMeasures().size();
			    String instName = sc.getPartlist().getScorepart().get(0).getPartname();
			    String cleff = sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getClef().getSign();
			    List <Note2> notelist  = ListOfMeasureAndNote.getlistOfNotes(sc);
			    Time2 time = sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getTime();
			    
			    
			    int y = 0;
		        double limit = Math.ceil(numMeasures/2);
		  
		        for (int i = 1; i <= limit; i++) {
		        	instrumentMusicLines(instName, y);
		            //Draw TAB
		            drawClef(cleff, 6, 20+y);
		            //Draw Bar lines
//		            barLines(450, y, instName);
//		              y += 120;
		            if(time != null) {
		            	drawTimeSignature( 35, 28+y, instName, sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getTime().getBeats(), sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getTime().getBeattype());
		            }
		          }
		        
		        double xcord;
		        double ycord;
		        
		        if(instName.equalsIgnoreCase("Guitar")) {
		        	
		        	//drawing the notes in their positions
		        	List<NoteAndPos> guitarNPlist = InstCordPos.setAndGetNotesPos(instName, notelist, this.pane.getMaxWidth());
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
		        	}
		        	
		        	//setting up the grace list = contains the 
		        	NoteAndPos current = null;
		        	NoteAndPos prev = null;
		        	ArrayList<NoteAndPos> gracelist = new ArrayList<NoteAndPos>();
		        	for(int k=0; k<guitarNPlist.size(); k++) {
		        		current = guitarNPlist.get(k);
		        		if(k!=0) {
		        			prev = guitarNPlist.get(k-1);
		        			//current note has a grace (do not need to check previous note)
		        			//add current note
		        			if(current.getNote().getGrace()!=null) {
		        				gracelist.add(current);
		        			}
		        			//current does not have a grace (need to check previous note)
		        			//previous note has grace 
		        			//add current note
		        			//check to see if it is the last in the grace group by checking to see if the next note has a chord
		        			//if next note has a chord keep checking
		        			//if next note does not have a chord then end the search and add null to grace list to show end of grace group
		        			else if(current.getNote().getGrace()==null && prev.getNote().getGrace()!=null) {
		        				gracelist.add(current);
		        				int l = k + 1;
		        				while(l<guitarNPlist.size() && guitarNPlist.get(l).getNote().getChord()!=null) {
		        					gracelist.add(guitarNPlist.get(l));
		        					l++;
		        				}
		        				gracelist.add(null);
		        				k=l-1;
		        			}
		        		}
		        		else {
		        			//add first note if it has a grace
		        			if(current.getNote().getGrace()!=null) {
		        				gracelist.add(current);
		        			}
		        		}
		        	}
		        	
		        	//drawing the graces
		        	double middledis = 0;
		        	double middlex = 0;
		        	double middley = 0;
		        	for(int k=0; k<gracelist.size(); k++) {
		        		current = gracelist.get(k);
		        		if(k!=0) {
		        			prev = gracelist.get(k-1);
		        			if(prev!=null && current!=null) {
		        				if(prev.getNote().getNotations().getTechnical().getString()<3) {
		        					//previous and current just have a grace 
		        					if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
		        					   current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
		        						middledis = (current.getX() - prev.getX())/2;
		        						middlex = prev.getX() + middledis;
		        						middley = prev.getY() - 15;
		        						drawQuad(prev.getX(), prev.getY()-3.5, middlex, middley, current.getX(), current.getY()-3.5);
		        					}
		        					//previous has a grace and chord and current has only a grace
		        					else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
		        							current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
		        						middledis = (current.getX() - prev.getX())/2;
		        						middlex = prev.getX() + middledis;
		        						middley = current.getY() - 15;
		        						drawQuad(prev.getX(), current.getY()-3.5, middlex, middley, current.getX(), current.getY()-3.5);
		        					}
		        					//previous has a grace and chord and current has no grace and no chord
		        					else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
				        				    current.getNote().getGrace()==null && current.getNote().getChord()==null) {
		        						middledis = (current.getX() - prev.getX())/2;
		        						middlex = prev.getX() + middledis;
		        						middley = current.getY() - 15;
		        						drawQuad(prev.getX(), current.getY()-3.5, middlex, middley, current.getX(), current.getY()-5);
		        					}
		        					//previous has a grace and current has no grace and no chord
		        					else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
				        				    current.getNote().getGrace()==null && current.getNote().getChord()==null) {
		        						middledis = (current.getX() - prev.getX())/2;
		        						middlex = prev.getX() + middledis;
		        						middley = prev.getY() - 15;
		        						drawQuad(prev.getX(), prev.getY()-3.5, middlex, middley, current.getX(), current.getY()-5);
		        					}
		        				}
		        				else if (prev.getNote().getNotations().getTechnical().getString()>=3){
		        					//previous has a grace and current has a grace
		        					if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
				        			   current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
				        				middledis = (current.getX() - prev.getX())/2;
				        				middlex = prev.getX() + middledis;
				        				middley = prev.getY() + 15;
		        						drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5);
				        			}
				        			//previous has a grace and chord and current has only a grace
				        			else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
				        					current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
				        				middledis = (current.getX() - prev.getX())/2;
				        				middlex = prev.getX() + middledis;
				        				middley = prev.getY() + 15;
		        						drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), prev.getY()+3.5);
				        			}
				        			//previous has a grace and chord and current has no grace and no chord
				        			else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
						        		    current.getNote().getGrace()==null && current.getNote().getChord()==null) {
				        				middledis = (current.getX() - prev.getX())/2;
				        				middlex = prev.getX() + middledis;
				        				middley = prev.getY() + 15;
		        						drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), prev.getY()+3.5);
				        			}
				        			//previous has a grace and current has no grace and no chord
				        			else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
						        		    current.getNote().getGrace()==null && current.getNote().getChord()==null) {
				        				middledis = (current.getX() - prev.getX())/2;
				        				middlex = prev.getX() + middledis;
				        				middley = prev.getY() + 15;
		        						drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5);
				        			}		        					
		        				}
		        			}
		        		}
		        	}
		        	
		        	//setting up the list of tied notes
		        	ArrayList<NoteAndPos> tiedlist = new ArrayList<NoteAndPos>();
		        	ArrayList<NoteAndPos> orderedtiedlist = new ArrayList<NoteAndPos>();
		        	for(int k=0; k<guitarNPlist.size(); k++) {
		        		current = guitarNPlist.get(k);
		        		if(current.getNote().getNotations().getListOfTieds()!=null) {
		        			tiedlist.add(current);
		        		}
		        	}
		        	
		        	NoteAndPos next = null;
		        	//sorting the list of tied notes (adjacent elements must have the same string value for guitar
		        	for(int k = 0; k<tiedlist.size(); k++) {
		        		current = tiedlist.get(k);
		        		
		        		//current note has a tied and it is a start
		        		if(current.getNote().getNotations().getListOfTieds().size()==1 &&
		        		   current.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("start")) {
		        			
		        			//add current note that has a tied and is a start into the ordered tied list
		        			//find all the notes that have a tied that are linked to current and add them to the ordered tied list in the
		        			//order that they appear
		        			//start finding from the next note in the tied list
		        			
		        			orderedtiedlist.add(current);
		        			
		        			int l = k + 1;
		        			while(l<tiedlist.size()) {
		        				next = tiedlist.get(l);
		        				
		        				//if the next note has already been added to the ordered tied list then skip the check
		        				if(!(orderedtiedlist.contains(next))) {
		        					
		        					//if the current note and the next note are not on the same string then do nothing (do not add the next note)
		        					if(current.getNote().getNotations().getTechnical().getString()==
		        					   next.getNote().getNotations().getTechnical().getString()) {
		        						
		        						//if next note is on the same string and is a start and stop then add
		        						if(next.getNote().getNotations().getListOfTieds().size()==2 &&
		        						   next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop") &&
		        						   next.getNote().getNotations().getListOfTieds().get(1).getType().equalsIgnoreCase("start")){
		        							orderedtiedlist.add(next);
		        						}
		        						//if next note is on the same string and is a stop then add
		        						//after this is reached that means that all the notes that are tied have been added in the same order
		        						//break from the loop to then stop the check as it is completed
		        						else if(next.getNote().getNotations().getListOfTieds().size()==1 &&
				        						next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop")){
		        							orderedtiedlist.add(next);
		        							orderedtiedlist.add(null);
				        					break;		
		        						}
		        					}
		        				}
		        				l++;
		        			}
		        		}
		        	}
		        	
		        	//draw ties
		        	for(int k=0; k<orderedtiedlist.size(); k++) {
		        		current = orderedtiedlist.get(k);
		        		if(k!=0) {
		        			prev = orderedtiedlist.get(k-1);
		        			if(prev!=null && current!=null) {
		        				
		        				//if notes are above string 3 then draw ties that are upwards
		        				if(prev.getNote().getNotations().getTechnical().getString()<3) {
		        					middledis = (current.getX() - prev.getX())/2;
		        					middlex = prev.getX() + middledis;
		        					middley = prev.getY() - 13;
		        					drawQuad(prev.getX(), prev.getY()-3.5, middlex, middley, current.getX(), current.getY()-3.5);
		        				}
		        				
		        				//if ties are below string 3 then draw ties that are downwards
		        				else if(prev.getNote().getNotations().getTechnical().getString()>=3) {
		        					middledis = (current.getX() - prev.getX())/2;
		        					middlex = prev.getX() + middledis;
		        					middley = prev.getY() + 13;
		        					drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5);
		        				}
		        			}
		        		}
		        	}
		        	
		        	//setting the list of slurs
		        	ArrayList<NoteAndPos> onlyslurlist = new ArrayList<NoteAndPos>();
		        	ArrayList<NoteAndPos> orderedslurlist = new ArrayList<NoteAndPos>();
		        	for(int k=0; k<guitarNPlist.size(); k++) {
		        		current = guitarNPlist.get(k);
		        		if(current.getNote().getNotations().getListOfSlurs()!=null && current.getNote().getGrace()==null) {
		        			onlyslurlist.add(current);
		        		}
		        	}
		        	
		        	for(int k=0; k<onlyslurlist.size(); k++) {
		        		current = onlyslurlist.get(k);
		        		
		        		//only checking for starting slurs
		        		if(current.getNote().getNotations().getListOfSlurs().size()==1 &&
		        		   current.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
		        			
		        			orderedslurlist.add(current);
		        			
		        			int l = k + 1;
		        			while(l<onlyslurlist.size()) {
		        				next = onlyslurlist.get(l);
		        				
		        				//if the next note has already been added to the ordered slur list then skip the check
		        				if(!(orderedslurlist.contains(next))) {
		        							        						
		        					//if next note is stop and start then add
		        					//the search for the last note in the slur continues
		        					if(next.getNote().getNotations().getListOfSlurs().size()==2 &&
		        					   next.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start") &&
		        					   next.getNote().getNotations().getListOfSlurs().get(1).getType().equalsIgnoreCase("stop")){
		        						orderedslurlist.add(next);
		        					}
		        					//if next note is a stop then add
		        					//after this is reached that means that all the notes that are tied have been added in the same order
		        					//break from the loop to then stop the check as it is completed
		        					else if(next.getNote().getNotations().getListOfSlurs().size()==1 &&
		        							next.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("stop")){
		        						orderedslurlist.add(next);
		        						orderedslurlist.add(null);
				        				break;		
		        					}
		        				}
		        				l++;
		        			}
		        		}
		        	}
		        	
		        	//drawing the slurs (being a pull-off slur or hammer-on slur does not matter)
		        	//for guitar we must consider the placement attribute of 
		        	String placement = "";
		        	for(int k=0; k<orderedslurlist.size(); k++) {
		        		current = orderedslurlist.get(k);
		        		if(k!=0) {
		        			prev = orderedslurlist.get(k-1);
		        			if(prev!=null && current!=null) {
		        				if(prev.getNote().getNotations().getListOfSlurs().size()==1 &&
		        				   prev.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
		        					placement = prev.getNote().getNotations().getListOfSlurs().get(0).getPlacement();
		        				}
		        				if(placement==null || placement.equalsIgnoreCase("above")) {
		        					middledis = (current.getX() - prev.getX())/2;
		        					middlex = prev.getX() + middledis;
		        					middley = prev.getY() - 13;
		        					drawQuad(prev.getX(), prev.getY()-3.5, middlex, middley, current.getX(), current.getY()-3.5);
		        				}
		        				else if(placement!=null && !(placement.equalsIgnoreCase("above"))) {
		        					middledis = (current.getX() - prev.getX())/2;
		        					middlex = prev.getX() + middledis;
		        					middley = prev.getY() + 13;
		        					drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5);
		        				}
		        			}
		        		}
		        	}
		        }
		        
		        else if(instName.equalsIgnoreCase("Drumset")) {
		        	NoteAndPos current = null;
		        	NoteAndPos prev = null;
		        	NoteHead2 notehead;
		        	
		        	//drawing the X and O note-heads
		        	List<NoteAndPos> drumsetNPlist = InstCordPos.setAndGetNotesPos(instName, notelist, this.pane.getMaxWidth());
		        	for(int k=0; k < drumsetNPlist.size(); k++) {
		        		xcord = drumsetNPlist.get(k).getX();
		        		ycord = drumsetNPlist.get(k).getY();
		        		notehead = drumsetNPlist.get(k).getNote().getNotehead();
		        		
		        		if(notehead!=null) {
		        			//drawing X
		        			if(notehead.getText().equalsIgnoreCase("x")){
		        				
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
		        				
		        				//drawing X with bar 
		        				if(drumsetNPlist.get(k).getNote().getInstrument().getId().equalsIgnoreCase("P1-I50")) {
		        					DrawLine linelast = new DrawLine((x1-1), ycord, (x1end+1), ycord);
		        					pane.getChildren().add(linelast.getLine());
		        				}
		        			}
		        			
		        			//drawing O that has a note-head
	        				else if(notehead.getText().equalsIgnoreCase("normal")) {
	        					drawCircle(xcord, ycord);
	        				}
		        		}
		        		//drawing O
		        		else if(notehead==null){
		        			drawCircle(xcord, ycord);
		        		}
		        	}
		        	
		        	//bracket list
		        	ArrayList<NoteAndPos> bracketlist = new ArrayList<NoteAndPos>();
		        	for(int k=0; k<drumsetNPlist.size(); k++) {
		        		current = drumsetNPlist.get(k);
		        		if(current.getNote().getNotehead()!=null) {
		        			if(current.getNote().getNotehead().getParentheses()!=null && 
		        			   current.getNote().getNotehead().getParentheses().equalsIgnoreCase("yes")) {
		        				bracketlist.add(current);
		        			}
		        		}
		        	}
		        	
		        	//drawing brackets
		        	for(int k=0; k<bracketlist.size(); k++) {
		        		xcord = bracketlist.get(k).getX();
		        		ycord = bracketlist.get(k).getY();
		        		
		        		double x1, y1, x1end, y1end, midx1, midy1;
	        			double x2, y2, x2end, y2end, midx2, midy2;
	        			
	        			//left arc
	        			x1 = xcord - 3.5;
	        			y1 = ycord + 7.5;
	        			midx1 = xcord - 15;
	        			midy1 = ycord;
	        			x1end = xcord - 3.5;
	        			y1end = ycord - 7.5;
	        			drawQuad(x1, y1, midx1, midy1, x1end, y1end);
	        			
	        			//right arc
	        			x2 = xcord + 3.5;
	        			y2 = ycord + 7.5;
	        			midx2 = xcord + 15;
	        			midy2 = ycord;
	        			x2end = xcord + 3.5;
	        			y2end = ycord - 7.5;
	        			drawQuad(x2, y2, midx2, midy2, x2end, y2end);
		        	}
		        	
		        	double middlex, middley, middledis;
		        	NoteAndPos next = null;
		        	
		        	//setting up the list of tied notes
		        	ArrayList<NoteAndPos> tiedlist = new ArrayList<NoteAndPos>();
		        	ArrayList<NoteAndPos> orderedtiedlist = new ArrayList<NoteAndPos>();
		        	for(int k=0; k<drumsetNPlist.size(); k++) {
		        		current = drumsetNPlist.get(k);
		        		if(current.getNote().getNotations()!=null && current.getNote().getNotations().getListOfTieds()!=null && current.getNote().getRest()==null) {
		        			tiedlist.add(current);
		        		}
		        	}
		        	
		        	
		        	//sorting the list of tied notes (adjacent elements must be the same instrument value for guitar
		        	for(int k = 0; k<tiedlist.size(); k++) {
		        		current = tiedlist.get(k);
		        		
		        		//current note has a tied and it is a start
		        		if(current.getNote().getNotations().getListOfTieds().size()==1 &&
		        		   current.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("start")) {
		        			
		        			//add current note that has a tied and is a start into the ordered tied list
		        			//find all the notes that have a tied that are linked to current and add them to the ordered tied list in the
		        			//order that they appear
		        			//start finding from the next note in the tied list
		        			
		        			orderedtiedlist.add(current);
		        			
		        			int l = k + 1;
		        			while(l<tiedlist.size()) {
		        				next = tiedlist.get(l);
		        				
		        				//if the next note has already been added to the ordered tied list then skip the check
		        				if(!(orderedtiedlist.contains(next))) {
		        					
		        					//if the current note and the next note are not on the same string then do nothing (do not add the next note)
		        					if(current.getNote().getInstrument().getId().equalsIgnoreCase(next.getNote().getInstrument().getId())) {
		        						
		        						//if next note is played by the same instrument and it is a start and stop then add
		        						if(next.getNote().getNotations().getListOfTieds().size()==2 &&
		        						   next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop") &&
		        						   next.getNote().getNotations().getListOfTieds().get(1).getType().equalsIgnoreCase("start")){
		        							orderedtiedlist.add(next);
		        						}
		        						//if next note is played by the same instrument and is a stop then add
		        						//after this is reached that means that all the notes that are tied have been added in the same order
		        						//break from the loop to then stop the check as it is completed
		        						else if(next.getNote().getNotations().getListOfTieds().size()==1 &&
				        						next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop")){
		        							orderedtiedlist.add(next);
		        							orderedtiedlist.add(null);
				        					break;		
		        						}
		        					}
		        				}
		        				l++;
		        			}
		        		}
		        	}
		        	
		        	//draw ties
		        	for(int k=0; k<orderedtiedlist.size(); k++) {
		        		current = orderedtiedlist.get(k);
		        		if(k!=0) {
		        			prev = orderedtiedlist.get(k-1);
		        			if(prev!=null && current!=null) {
		        				
		        				//if notes are above string 3 then draw ties that are upwards
		        				if(prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I50") ||
		        				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I43") ||
		        				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I52") ||
		        				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I48") ||
		        				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I46") ||
		        				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I39")) {
		        					middledis = (current.getX() - prev.getX())/2;
		        					middlex = prev.getX() + middledis;
		        					middley = prev.getY() - 13;
		        					drawQuad(prev.getX(), prev.getY()-3.5, middlex, middley, current.getX(), current.getY()-3.5);
		        				}
		        				
		        				//if ties are below string 3 then draw ties that are downwards
		        				else {
		        					middledis = (current.getX() - prev.getX())/2;
		        					middlex = prev.getX() + middledis;
		        					middley = prev.getY() + 13;
		        					drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5);
		        				}
		        			}
		        		}
		        	}
		        	
		        	//setting the list of slurs
		        	ArrayList<NoteAndPos> onlyslurlist = new ArrayList<NoteAndPos>();
		        	ArrayList<NoteAndPos> orderedslurlist = new ArrayList<NoteAndPos>();
		        	for(int k=0; k<drumsetNPlist.size(); k++) {
		        		current = drumsetNPlist.get(k);
		        		if(current.getNote().getNotations()!=null &&
		        		   current.getNote().getNotations().getListOfSlurs()!=null) {
		        			onlyslurlist.add(current);
		        		}
		        	}
		        	
		        	for(int k=0; k<onlyslurlist.size(); k++) {
		        		current = onlyslurlist.get(k);
		        		
		        		//only checking for starting slurs
		        		if(current.getNote().getNotations().getListOfSlurs().size()==1 &&
		        		   current.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
		        			
		        			orderedslurlist.add(current);
		        			
		        			int l = k + 1;
		        			while(l<onlyslurlist.size()) {
		        				next = onlyslurlist.get(l);
		        				
		        				//if the next note has already been added to the ordered slur list then skip the check
		        				if(!(orderedslurlist.contains(next))) {
		        							        						
		        					//if next note is stop and start then add
		        					//the search for the last note in the slur continues
		        					if(next.getNote().getNotations().getListOfSlurs().size()==2 &&
		        					   next.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start") &&
		        					   next.getNote().getNotations().getListOfSlurs().get(1).getType().equalsIgnoreCase("stop")){
		        						orderedslurlist.add(next);
		        					}
		        					//if next note is a stop then add
		        					//after this is reached that means that all the notes that are tied have been added in the same order
		        					//break from the loop to then stop the check as it is completed
		        					else if(next.getNote().getNotations().getListOfSlurs().size()==1 &&
		        							next.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("stop")){
		        						orderedslurlist.add(next);
		        						orderedslurlist.add(null);
				        				break;		
		        					}
		        				}
		        				l++;
		        			}
		        		}
		        	}
		        	
		        	//drawing the slurs (being a pull-off slur or hammer-on slur does not matter)
		        	//for guitar we must consider the placement attribute of 
		        	String placement = "";
		        	for(int k=0; k<orderedslurlist.size(); k++) {
		        		current = orderedslurlist.get(k);
		        		if(k!=0) {
		        			prev = orderedslurlist.get(k-1);
		        			if(prev!=null && current!=null) {
		        				if(prev.getNote().getNotations().getListOfSlurs().size()==1 &&
		        				   prev.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
		        					placement = prev.getNote().getNotations().getListOfSlurs().get(0).getPlacement();
		        				}
		        				if(placement==null) {
		        					if(prev.getY()==current.getY()) {
		        						middledis = (current.getX() - prev.getX())/2;
		        						middlex = prev.getX() + middledis;
		        						middley = prev.getY() + 13;
		        						drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5);
		        					}
		        					else if(prev.getY()<current.getY()) {
		        						System.out.println(k);
		        						middledis = (current.getX() - prev.getX())/2;
		        						middlex = prev.getX() + middledis;
		        						middley = current.getY() + 13;
		        						drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5);
		        					}
		        				}
		        			}
		        		}
		        	}
		        	
		        	//drawing stems
		        	for(int k=0; k<drumsetNPlist.size(); k++) {
		        		current = drumsetNPlist.get(k);
		        		if(k!=0) {
		        			prev = drumsetNPlist.get(k-1);
		        			//if current note has a chord the previous note matters
		        			if(current.getNote().getChord()!=null && current.getNote().getStem()!=null) {
		        				
		        				//previous note = X 
		        				//current note = O
		        				//current note has chord
		        				if(prev.getNote().getNotehead()!=null && 
		        				   prev.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
		        				   current.getNote().getNotehead()==null &&
		        				   current.getNote().getChord()!=null && 
		 		        		   current.getNote().getStem().equalsIgnoreCase("up")) {
		        					double startx = current.getX()+5;
		 		        			double starty = current.getY();
		 		        			double endx = prev.getX()+5;
		 		        			double endy = prev.getY()-5;
		 		        			DrawLine stemline = new DrawLine(startx, starty, endx, endy);
		 		        			pane.getChildren().add(stemline.getLine());
		 		        		}
		 		        		//previous note = O
		        				//current note = O
		        				//current note has chord
		 		        		else if(prev.getNote().getNotehead()==null && 
		 		        				current.getNote().getNotehead()==null &&
		 		        				current.getNote().getChord()!=null && 
		 				        		current.getNote().getStem().equalsIgnoreCase("up")) {
		 		        			double startx = current.getX()+5;
		 				        	double starty = current.getY();
		 				        	double endx = prev.getX()+5;
		 				        	double endy = prev.getY();
		 				        	DrawLine stemline = new DrawLine(startx, starty, endx, endy);
		 				        	pane.getChildren().add(stemline.getLine());
		 				        }
		        				//previous note = X
		        				//current note = X
		        				//current note has chord
		 		        		else if(prev.getNote().getNotehead()!=null && 
		 		        				prev.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
		 		        				current.getNote().getNotehead()!=null &&
		 		        				current.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
		 		        				current.getNote().getChord()!=null && 
		 				        		current.getNote().getStem().equalsIgnoreCase("up")) {
		 		        			double startx = current.getX()+5;
		 				        	double starty = current.getY()-5;
		 				        	double endx = prev.getX()+5;
		 				        	double endy = prev.getY()-5;
		 				        	DrawLine stemline = new DrawLine(startx, starty, endx, endy);
		 				        	pane.getChildren().add(stemline.getLine());
		 		        		}
		        			}
		        			//current does not have a chord so previous does not matter
		        			else if(current.getNote().getChord()==null && current.getNote().getStem()!=null) {
		        				
		        				//current note = X
		        				//current has no chord
		        				if(current.getNote().getNotehead()!=null &&
		        				   current.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
		        				   current.getNote().getStem().equalsIgnoreCase("up")) {
		        					double startx = current.getX()+5;
		 		        			double starty = current.getY()-5;
		 		        			double endx = current.getX()+5;
		 		        			double endy = current.getY()-10;
		 		        			DrawLine stemline = new DrawLine(startx, starty, endx, endy);
		 		        			pane.getChildren().add(stemline.getLine());
		        				}
		        				//current note = O
		        				//current has no chord
		        				else if(current.getNote().getNotehead()==null &&
		        						current.getNote().getStem().equalsIgnoreCase("up")) {
		        					double startx = current.getX()+5;
		 		        			double starty = current.getY();
		 		        			double endx = current.getX()+5;
		 		        			double endy = current.getY()-10;
		 		        			DrawLine stemline = new DrawLine(startx, starty, endx, endy);
		 		        			pane.getChildren().add(stemline.getLine());
		        				}
		        			}
		        		}
		        		//first note cannot be chord so code for non-chorded notes is repeated
		        		else {
		        			//current note is an x with no chord
		        			if(current.getNote().getStem()!=null) {
		        				//current note = X
		        				//current has no chord
		        				if(current.getNote().getNotehead()!=null &&
		        				   current.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
		        				   current.getNote().getStem().equalsIgnoreCase("up")) {
		        					double startx = current.getX()+5;
		 		        			double starty = current.getY()-5;
		 		        			double endx = current.getX()+5;
		 		        			double endy = current.getY()-10;
		 		        			DrawLine stemline = new DrawLine(startx, starty, endx, endy);
		 		        			pane.getChildren().add(stemline.getLine());
		        				}
	        				//current note is a circle with no chord
		        				//current note = O
		        				//current has no chord
		        				else if(current.getNote().getNotehead()==null &&
		        						current.getNote().getStem().equalsIgnoreCase("up")) {
		        					double startx = current.getX()+5;
		 		        			double starty = current.getY();
		 		        			double endx = current.getX()+5;
		 		        			double endy = current.getY()-10;
		 		        			DrawLine stemline = new DrawLine(startx, starty, endx, endy);
		 		        			pane.getChildren().add(stemline.getLine());
		        				}
		        			}
		        		}
		        	}
		        }
		        
		        else if(instName.equalsIgnoreCase("Bass")) {
		        	String notenum;
		        	List<NoteAndPos> bassNPlist = InstCordPos.setAndGetNotesPos(instName, notelist, this.pane.getMaxWidth());
		        	for(int k=0; k < bassNPlist.size(); k++) {
		        		xcord = bassNPlist.get(k).getX();
		        		ycord = bassNPlist.get(k).getY();
		        		notenum = "" + bassNPlist.get(k).getNote().getNotations().getTechnical().getFret();
		        		drawNotes(xcord, ycord, notenum, 13);
		        	}
		        }   
		} catch (JAXBException e) {
			e.printStackTrace();
		} 

    }
}
