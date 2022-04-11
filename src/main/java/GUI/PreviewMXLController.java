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
 //import javafx.scene.text.FontWeight;
 import javafx.scene.text.Text;
 import javafx.scene.transform.Scale;
 import javafx.scene.transform.Translate;
 import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Measure2;
import xml.to.sheet.converter.POJOClasses.Note2;
 //import javafx.scene.paint.*;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//import javafx.stage.Window;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.XmlToJava;
import java.io.IOException;
//import java.io.IOException;
import java.util.List;
//import javax.swing.JLabel;
import javax.xml.bind.JAXBException;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import org.fxmisc.flowless.VirtualizedScrollPane;
//import org.fxmisc.richtext.CodeArea;
//import org.fxmisc.richtext.LineNumberFactory;
//
//import converter.Converter;
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
 //	private GraphicsContext gc;
 	public FXMLLoader loader;
 	@FXML 
 	Button printPDF;
 	Button playMusic;
 	Button pauseMusic;
 	BooleanProperty printButtonPressed = new SimpleBooleanProperty(false);
 	
 	ScorePartwise2 sc;
    MainViewController mvc;
 	
 //	@FXML
 //	public void printPDF() {
 //	}
 	

 	@FXML
 	public void savePDF() {}
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
 	
 	private Pattern getDrumPattern() {
    	Pattern pattern =new Pattern("V9");
    	List<Measure2> allMeasures = sc.getListOfParts().get(0).getListOfMeasures();

    	for(int i=0; i < allMeasures.size(); i++) {
    		List<Note2> notes = allMeasures.get(i).getListOfNotes();
    		int divisions = allMeasures.get(i).getAttributes().getDivisions();

    		for(int j=0; j < notes.size(); j++) {
    			String thisID = notes.get(j).getInstrument().getId();
    			int lastNoteIndex = notes.size()-1;
    			
    			boolean lastNoteIsChord = false;
				if(notes.get(notes.size()-1).getChord() != null) lastNoteIsChord=true;
    			
    			if( ((j!= lastNoteIndex) && (notes.get(j+1).getChord() != null)) || ((j== lastNoteIndex) && (lastNoteIsChord == true)) ){
    				int k=j; String chord1=""; String chord="";
    				
    				while((k!= lastNoteIndex) && (notes.get(k+1).getChord() != null || notes.get(k).getChord() != null)) {
    					chord1+=(getIDDigit(notes.get(k).getInstrument().getId()) + getDurationLetter(notes.get(k).getType()) + " ");
    					k+=1;
    				}
    				if((k== lastNoteIndex) && (lastNoteIsChord == true) && (j!= lastNoteIndex)) {
        				//append the note to the end of the last chord in the pattern 
    					chord1+=(getIDDigit(notes.get(k).getInstrument().getId()) + getDurationLetter(notes.get(k).getType()) + " ");
        				
        			}
    					 chord1=chord1.replace(' ', '+');
    					 for(int l=0; l<chord1.length()-1; l++) chord+=chord1.charAt(l);
    				
    				pattern.add(chord);
    			}
    			 
    			else if(notes.get(j).getChord() != null) continue;
    			else{
    				pattern.add(getIDDigit(thisID) + getDurationLetter(notes.get(j).getType()));		
    			}

    			/*
				System.out.println("The string is: " + numStr);
                System.out.println("THe instrument is " + notes.get(j).getInstrument().toString());
    			 */	
    		}
    	}
    	System.out.println("pattern from drum: " + pattern.getPattern());
    	return pattern;
 	}
 	
 	private String getDurationLetter(String type) {
    	String duration="";
    	switch(type) {
    	case ("one-twenty-eighth"): duration="o";break;
    	case ("128th"): duration="o";break;
    	case ("sixty-fourth"): duration="x";break;
    	case ("64th"): duration="x";break;
    	case ("thirty-second"): duration="t";break;
    	case ("32nd"): duration="t";break;
    	case ("sixteenth"): duration="s";break;
    	case ("16th"): duration="s";break;
    	case ("eighth"): duration="i";break;
    	case ("quarter"): duration="q";break;
    	case ("half"): duration="h";break;
    	case ("whole"): duration="w";break;
    	}
    	return duration;
    }
    private int getIDDigit(String thisID) {
    	/*
		 * usual ID is in the format P1-I[ID]
		 * so the following code will get the integers 
		 * from the last two characters in thisID
		 */
    	
    	String numStr = "";
		numStr += thisID.charAt(thisID.length()-2);
		numStr += thisID.charAt(thisID.length()-1);
		int note = Integer.parseInt(numStr);
		
		return note;
    }
    

 	@FXML
	public void playMusic() {
 		try {
 	        sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
 	    } catch (JAXBException e) {
 	        e.printStackTrace();
 	        System.out.println("Failed to unmarshall the musicXML file.");
 	    }
 		Player player = new Player();
 		player.play(this.getDrumPattern()); 
 		//Screenshare with GL and get her to paste her code here, it works
 		//She already completed the play and pause
	}
	
	@FXML
	public void pauseMusic() {
		//Screenshare with GL and get her to paste her code here, it works
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
	
	@FXML 
    private Pane pane;
	
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
    	int barx = 0;
    	ScorePartwise2 sc;
		try {
			sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
			// int numMeasures = ListOfMeasureAndNote.getlistOfMeasures(sc).size();
			 List<Note2> notes = ListOfMeasureAndNote.getlistOfNotes(sc);
		     String instName = sc.getPartlist().getScorepart().get(0).getPartname();
		     String cleff = sc.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getClef().getSign();
		   //Draw the Music lines on the GUI
		      	int y = 0;
		      	double limit = getlimit();
	    		
		    	if(instName.equals("Guitar")) {
		    		y = 0;
					int counter=1;
		    		for(int i=0;i<notes.size();i++) {
						int yy = notes.get(i).getNotations().getTechnical().getString();
						int x;
						int f=0;
					//	System.out.println(notes.get(i).getChord()!=null);
					//	System.out.println(counter);
						if(notes.get(i).getGrace()!=null) {
							f=11;
						//	System.out.println("ran");
							
						}
						else {
							f=14;
						}
						double xpos=NoteInfo.notePos(notes,notes.get(i));
						y=0+(yy-1)*13;
						
						drawNotes(xpos, y+5,String.valueOf(notes.get(i).getNotations().getTechnical().getFret()),f);
						
					}
		    	}
		    	else if(instName.equals("Drumset")) {
		    		int x = 50;
		    		int y2 = 0;
//		    		y = 0;
		    		int count = 50;
//		    		int x2 = x;
		    		for(int i = 0; i < notes.size(); i++) {
		    		if(notes.get(i).getNotehead() != null) {
		    			if(notes.get(i).getInstrument().getId().equals("P1-I50")) {
		    				drawSmallLine(x);
		    				
		    				drawNotes(x, y,String.valueOf(notes.get(i).getNotehead()),14);
		    			}
		    			else {
		    				drawNotes(x, y,String.valueOf(notes.get(i).getNotehead()),14);
		    				drawVerticalLines(x+5, y);
		    			}
		    			
		    			count = x;
//		    			System.out.println(notes.get(i).getInstrument());
		    			x+=30;
		    		}
		    		
		    		else {
		    			if(notes.get(i).getUnpitched().getDisplaystep().equals("C")) {
		    				y2 = 20;
		    			}
		    			else if(notes.get(i).getUnpitched().getDisplaystep().equals("F")){
		    				y2 = 45;
		    			}
//		    			if(notes.get(i).getInstrument().getId().equals("P1-I36")) {
//		    				drawCircle(count + 10, y2);
//		    			}
//		    			else {
		    				drawCircle(count, y2);
			    			drawVerticalLines(count +5, y2);
//		    			}
		    			
		    			//drawNotes(count, y2,String.valueOf(notes.get(i).getNotations().getTechnical().getFret()));
		    			count+=20;
		    		}
		    		}
		    	}
		    	for (int i = 1; i <= limit; i++) {
		    		y=0;
		    		instrumentMusicLines(instName, y);
		      		//Draw TAB
		      		instrumentMusicLines(instName, y);
		      		//Draw Clef
		        	drawClef(cleff, 6, 20+y);
		        	//Draw Bar lines
		        	if(limit!=1) {
		        	barLines(barx, y, instName);
		        	}
		        	barLines(450, y, instName);
		      		y += 120;
		      		
		      	}
		    	
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}


