package GUI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.property.ObjectProperty;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;


import javafx.scene.Cursor;
import javafx.scene.Node;

import javafx.scene.canvas.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

 import xml.to.sheet.converter.POJOClasses.Measure2;

import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.XmlToJava;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

import xml.to.sheet.converter.POJOClasses.NoteHead2;
import xml.to.sheet.converter.POJOClasses.Tied2;
import java.awt.geom.Arc2D;
import javax.swing.JLabel;

public class PreviewMXLController {
	
	@FXML public Canvas canvas;
	@FXML TextField gotoMeasureField;
	@FXML Button gotoMeasureButton;
	@FXML Button savePDF;
	public FXMLLoader loader;
	private ArrayList<measureinfo> notePositions;
	@FXML public ScrollPane scrollPane;
	@FXML private Pane pane;
	@FXML private AnchorPane anchorPane;
	
	@FXML TextField spaceBetweenNotesField;
	@FXML TextField gotoMeasureField2;
	@FXML TextField xField;
	
	@FXML 
 	Button printPDF;
 	Button playMusic;
 	Button pauseMusic;
 	BooleanProperty printButtonPressed = new SimpleBooleanProperty(false);
	
	private double font = 10;
	private double xInc = 24;
	
	private MainViewController mvc;
	private ScorePartwise2 sc;
	private PlayTabController ptc;
    
	public PreviewMXLController(ScorePartwise2 inputSC, MainViewController inputmvc) {
        mvc = inputmvc;
        sc = inputSC;
        ptc = new PlayTabController(sc,mvc);
    }
	
	public double getxInc() {
		return xInc;
	}

	public void setxInc(double xInc) {
		this.xInc = xInc;
	}

	public double getFont() {
		return font;
	}

	public void setFont(double font) {
		this.font = font;
	}

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
    public void playMusic() throws JAXBException {
         ptc.play();

    }


    @FXML
    public void pauseMusic() {
        ptc.pause();
    }
    
	@FXML
	private void handleSpaceBetweenNotes() {

		double space = Double.parseDouble( spaceBetweenNotesField.getText() );
		setFont(space);
		try {
			pane.getChildren().clear();
			this.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handlex() {
		double x = Double.parseDouble(xField.getText());
		
		try {
			ScorePartwise2 sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
			String instName = sc.getPartlist().getScorepart().get(0).getPartname();
			if(instName.equalsIgnoreCase("Drumset") && x < 45 && x > 0) {
				setxInc(x);
			}
			else if(!instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")){
				Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Error");
		        alert.setHeaderText("Out of bound");
		        alert.setContentText("Please choose a number between 0 and 45");
		        alert.showAndWait();
			}
			if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass") && x < 29 && x > 0) {
				setxInc(x);
			}
			else if(!instName.equalsIgnoreCase("Drumset")){
				Alert alert = new Alert(Alert.AlertType.WARNING);
		        alert.setTitle("Error");
		        alert.setHeaderText("Out of bound");
		        alert.setContentText("Please choose a number between 0 and 29");
		        alert.showAndWait();
			}
			
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		setxInc(x);
		try {
			pane.getChildren().clear();
			this.update();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	private void spaceBetweenNotes(double space) {
//		this.setFont(space);
//	}
	
	public ArrayList<measureinfo> getNotePositions() {
		return notePositions;
	}

	public void setNotePositions(ArrayList<measureinfo> notePositions) {
		this.notePositions = notePositions;
	}

	
	public boolean goToMeasure(int measureCount) {
		int measure = 0;
		for(int i = 0; i< this.notePositions.size(); i++) {
			if(i == measureCount) {
				measure = i;
			}
		}
		
		 
		 double x = notePositions.get(measure).getStartof().getXpos();
		 double y = notePositions.get(measure).getStartof().getTopofstaff();
	     double y2 = pane.getHeight();
	     double yg = y/y2;
	     scrollPane.setVvalue(yg);
	     

	     scrollPane.getVvalue();

	   
	     return true;
	}
	
	@FXML
	private void handleGotoMeasure() {
		
		int measureNumber = Integer.parseInt( gotoMeasureField2.getText() );
		if (!goToMeasure(measureNumber)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Measure " + measureNumber + " could not be found.");
			alert.setHeaderText(null);
			alert.show();
		}
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
    
//    public void drawCircle(double x, double y) {
//        DrawCircle circle = new DrawCircle(x, y); 
//    	pane.getChildren().add(circle.getCircle());
//    }
    
    public void drawQuad(double startX, double startY,  double controlX, double controlY, double endX, double endY) {
    	DrawQuad quad = new DrawQuad(startX, startY, controlX, controlY, endX, endY);
    	pane.getChildren().add(quad.getQuadcurve());
    }
    
    public void update() throws IOException {
    	ScorePartwise2 sc;
		try {
	      		sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
	      		double numMeasures = sc.getListOfParts().get(0).getListOfMeasures().size();
			    String instName = sc.getPartlist().getScorepart().get(0).getPartname();
			    String cleff = sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getClef().getSign();
			    List <Note2> notelist  = ListOfMeasureAndNote.getlistOfNotes(sc);
			    
//			    int y = 50;
//		        double limit = Math.ceil(numMeasures/2);
//		  
//		        for (int i = 1; i <= limit; i++) {
//		        	instrumentMusicLines(instName, y);
//		            //Draw TAB
//		            drawClef(cleff, 6, 20+y);
//		            //Draw Bar lines
////		            barLines(450, y, instName);
////		              y += 120;
//		          }
		        
	        	double startx = 50;
	        	double starty = 50;
	        	
	        	double yInc = 30;
	        	double basexInc = 10;
	        	int diffbwstaves = 6;
//	        	font = 20;
//	        	setFont(20);
	        	double gracefont = font/2;
	        	
		        
		        if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
		        	yInc = font;
		        	ArrayList<measureinfo> gBNPlist = InstCordPos2.getListofPositions(sc, instName, notelist, 
		        																	 startx, starty, xInc, yInc, basexInc, diffbwstaves, this.pane.getMaxWidth(), font, pane);
		        	this.setNotePositions(gBNPlist);
		        	DrawGuitarOrBass.drawGBNotes(gBNPlist, font, gracefont, pane);
		        	ArrayList<ArrayList<NoteAndPos>> gracelist = ComponentClass.getGracList(gBNPlist);
		        	DrawGuitarOrBass.drawGBGraces(gracelist, font, gracefont, yInc, pane);
		        	
		        	ArrayList<NoteAndPos> tielist = ComponentClass.getTieList(gBNPlist, instName);
		        	DrawGuitarOrBass.drawGBTies(tielist, font, yInc, this.pane.getMaxWidth(), pane);
		        	
		        	ArrayList<NoteAndPos> slurlist = ComponentClass.getSlurList(gBNPlist, instName);
		        	DrawGuitarOrBass.drawGBSlurs(slurlist, font, yInc, this.pane.getMaxWidth(), pane);
		       
		        	ArrayList<ArrayList<NoteAndPos>> beamlist = ComponentClass.getBeamList(gBNPlist, sc, instName, pane);

		        	ArrayList<NoteAndPos> slidelist = ComponentClass.getSlidelist(gBNPlist);
                    DrawGuitarOrBass.drawSlide(slidelist, font, xInc, yInc,this.pane.getMaxWidth(), pane);
                    GeneralDrawing.drawemptymeasures(gBNPlist, instName, yInc, pane);
                    GeneralDrawing.draclefAndTime(sc, gBNPlist, instName, xInc, yInc, pane);
		        }
		        
		        else if(instName.equalsIgnoreCase("Drumset")) {
		        	yInc = font;
		        	ArrayList<measureinfo> drumsetNPlist = InstCordPos2.getListofPositions(sc, instName, notelist, 
		        																		  startx, starty, xInc, yInc, basexInc, diffbwstaves, this.pane.getMaxWidth(), font, pane);
		        	this.setNotePositions(drumsetNPlist);
//		        	DrawDrumset.drawDrumNotesAndStems(drumsetNPlist, pane);
		        	ArrayList<NoteAndPos> tielist = ComponentClass.getTieList(drumsetNPlist, instName);
                    DrawDrumset.drawDrumTies(tielist, font, yInc, this.pane.getMaxWidth(), pane);
                    ArrayList<NoteAndPos> slurlist = ComponentClass.getSlurList(drumsetNPlist, instName);
                    DrawDrumset.drawDrumSlurs(slurlist, font, yInc, this.pane.getMaxWidth(), pane);
                    DrawDrumset.drawdots(drumsetNPlist, xInc, yInc, pane);
		        }
		        
//				else if(instName.equalsIgnoreCase("bass")) {
//				for(int i=0; i<numOfMeasures; i++) {
//					if(ListOfMeasureAndNote.getNotesInMeasureI(sc, i)==null) {
//						measuremarker+=(3*(nplist.get(index-2).getNote().getDuration()+basexInc));
//						continue;
//					}
//					else {
//						measureI = ListOfMeasureAndNote.getNotesInMeasureI(sc, i);
//						while(temp<measureI.size()) {
//							durationtotalI+=measureI.get(temp).getDuration();
//							temp++;
//						}
//						tempdurationtotalI = durationtotalI;
//						durationtotalI=0;
//						temp=0;
//					}
//					for(int j=0; j<measureI.size(); j++) {
//						if(j==0) {
//							if((measuremarker+((measureI.size()*basexInc) + tempdurationtotalI))<=maxX){
//								if(index==0) {
//									nplist.get(index).setX(startx);
//									firstnotestaffI.add(nplist.get(index));
//								}
//								else {
//									if(nplist.get(index).getNote().getChord()!=null) {
//										nplist.get(index).setX(nplist.get(index-1).getX());
//									}
//									else {
//										nplist.get(index).setX(nplist.get(index-1).getX() + 
//												(nplist.get(index-1).getNote().getDuration()+basexInc));
//									}
//								}
//							}
//							else {
//								nplist.get(index).setX(startx);
//								firstnotestaffI.add(nplist.get(index-1));
//								firstnotestaffI.add(null);
//								firstnotestaffI.add(nplist.get(index));
//							}
//						}
//						else {
//							if(nplist.get(index).getNote().getChord()!=null) {
//								nplist.get(index).setX(nplist.get(index-1).getX());
//							}
//							else {
//								nplist.get(index).setX(nplist.get(index-1).getX() + 
//										(nplist.get(index-1).getNote().getDuration()+basexInc));
//							}
//						}
//						if(j+1==measureI.size()) {
//							measuremarker = nplist.get(index).getX() + (nplist.get(index).getNote().getDuration()+basexInc);
//						}
//						if(i+1==numOfMeasures && j+1==measureI.size()) {
//							firstnotestaffI.add(nplist.get(index));
//							firstnotestaffI.add(null);
//						}
//						nplist.get(index).setMeasureNum(i);
//						index++;
//					}
//				} 
		} catch (JAXBException e) {
			e.printStackTrace();
		} 
    }
}