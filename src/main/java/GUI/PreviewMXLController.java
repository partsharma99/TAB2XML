package GUI;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.property.ObjectProperty;

import javafx.fxml.FXML;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

 import javafx.fxml.FXMLLoader;
 import javafx.print.PageLayout;
 import javafx.print.PageOrientation;
 import javafx.print.Paper;
 import javafx.print.Printer;
 import javafx.print.PrinterJob;

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

/*
Sample tab
|-----------0-----|-0---------------|
|---------0---0---|-0---------------|
|-------1-------1-|-1---------------|
|-----2-----------|-2---------------|
|---2-------------|-2---------------|
|-0---------------|-0---------------|


*/
 
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
 	Button playMusic;
 	Button pauseMusic;
 	BooleanProperty printButtonPressed = new SimpleBooleanProperty(false);
 	
 	ScorePartwise2 sc;
    MainViewController mvc;
    PlayTabController ptc;
    
    @FXML public ScrollPane scrollPane;
 

    public PreviewMXLController(ScorePartwise2 inputSC, MainViewController inputmvc) {
    	this.mvc = inputmvc;
    	sc = inputSC;
    	ptc = new PlayTabController(sc,mvc);
    }
 	
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
	 	
		//Screenshare with GL and get her to paste her code here, it works
	}

	private ArrayList<measureinfo> notePositions;

	@FXML 
    private Pane pane;

	public ArrayList<measureinfo> getNotePositions() {
		return notePositions;
	}

	public void setNotePositions(ArrayList<measureinfo> notePositions) {
		this.notePositions = notePositions;
	}

	@FXML
	public void savePDF() {
	}
	
	@FXML
	private void handleSpaceBetweenNotes() {
		
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
		
		int measureNumber = Integer.parseInt( gotoMeasureField.getText() );
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
    public void drawSmallLine(int x) {
    	DrawLine dl = new DrawLine(x-5 , -5,x+10 , -5);
    	pane.getChildren().add(dl.getLine());
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
    		if(getlimit()!=1) {
    			System.out.println(getlimit());
    		DrawLine middleBar = new DrawLine(x, y, x, y + 64);
    		pane.getChildren().add(middleBar.getLine());
    		}
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
    
    public void drawNotes(double x, double y,String a,int f) {
    	String num = a;
    	 Text t = new Text(x, y, a);
         t.setFont(Font.font("calibri", f));
         pane.getChildren().add(t);
    	
    }
         public void drawCircle(double x, double y) {
         DrawCircle circle = new DrawCircle(x, y,1); 
    	pane.getChildren().add(circle.getCircle());
         }
         
         public void drawVerticalLines(int x, int y) {
        	 DrawLine line = new DrawLine(x, -25, x, y);
        	 pane.getChildren().add(line.getLine());
         }
    //Update the GUI
    
    public double getlimit() throws JAXBException {
    	ScorePartwise2 sc;
		sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
    	 int numMeasures = ListOfMeasureAndNote.getlistOfMeasures(sc).size();
    	 double limit = Math.ceil(numMeasures/2);
    			 if(limit==0) {
 	    			limit=1;
 	    		}
		return limit;
    	
    	
    }
    public void update() throws IOException {
    	double startx = 50;
    	double starty = 50;
    	double xInc = 25;
    	double yInc = 15;
    	double basexInc = 10;
    	int diffbwstaves = 8;
    	double font = 15;
    	double gracefont = font/2;

    	double numMeasures = sc.getListOfParts().get(0).getListOfMeasures().size();
	    String instName = sc.getPartlist().getScorepart().get(0).getPartname();
	    String cleff = sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getClef().getSign();
	    List <Note2> notelist  = ListOfMeasureAndNote.getlistOfNotes(sc);

    	if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
    		yInc = font;
    		ArrayList<measureinfo> gBNPlist = InstCordPos2.getListofPositions(sc, instName, notelist, 
    				startx, starty, xInc, yInc, basexInc, diffbwstaves, this.pane.getMaxWidth(), font, pane);
    		DrawGuitarOrBass.drawGBNotes(gBNPlist, font, gracefont, pane);
    		ArrayList<ArrayList<NoteAndPos>> gracelist = ComponentClass.getGracList(gBNPlist);
    		DrawGuitarOrBass.drawGBGraces(gracelist, font, gracefont, yInc, pane);
    		gracelist = null;
    		ArrayList<NoteAndPos> tielist = ComponentClass.getTieList(gBNPlist, instName);
    		DrawGuitarOrBass.drawGBTies(tielist, font, yInc, this.pane.getMaxWidth(), pane);
    		tielist = null;
    		ArrayList<NoteAndPos> slurlist = ComponentClass.getSlurList(gBNPlist, instName);
    		DrawGuitarOrBass.drawGBSlurs(slurlist, font, yInc, this.pane.getMaxWidth(), pane);
    		slurlist = null;
    		ArrayList<ArrayList<NoteAndPos>> beamlist = ComponentClass.getBeamList(gBNPlist, sc, instName, pane);
    		beamlist = null;
    	}

    	else if(instName.equalsIgnoreCase("Drumset")) {
    		yInc = font;
    		ArrayList<measureinfo> drumsetNPlist = InstCordPos2.getListofPositions(sc, instName, notelist, 
    				startx, starty, xInc, yInc, basexInc, diffbwstaves, this.pane.getMaxWidth(), font, pane);	      
    		ArrayList<ArrayList<NoteAndPos>> beamlist = ComponentClass.getBeamList(drumsetNPlist, sc, instName, pane);
    	}



    }
		        
    
}


