

package GUI;



import java.io.IOException;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//
//import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;
//import javafx.scene.paint.*;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//import javafx.stage.Window;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.XmlToJava;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.xml.bind.JAXBException;

import java.net.URL;
import java.util.ResourceBundle;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import converter.Converter;
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

	
//	@FXML public Canvas canvas;
	@FXML TextField gotoMeasureField;
	@FXML Button gotoMeasureButton;
	@FXML Button savePDF;

	private GraphicsContext gc;

	public FXMLLoader loader;
	
	@FXML
	public void savePDF() {
	}
	
	@FXML
	public void handleGotoMeasure() {
	}



	
    private MainViewController mvc;
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
     	else if (instrument.equalsIgnoreCase("Drumset")) {
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


         t.setFont(Font.font("veranda", 14));

         t.setFont(Font.font("calibri", f));


         pane.getChildren().add(t);
    	
    }
         public void drawCircle(double x, double y) {
         DrawCircle circle = new DrawCircle(x, y); 
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
			 List<Note2> notes = ListOfMeasureAndNote.getlistOfNotes(sc);
		     String instName = sc.getInstrumentName();
		     String cleff = sc.getCleff();
		   //Draw the Music lines on the GUI
		      	int y = 0;
		      	double limit = getlimit();
	    		
		      	//Checking the instrument name
		    	if(instName.equals("Guitar")) {
		    		//Setting the y axis for the notes to 0
		    		y = 0;
		    		//Running the loop for maximum number of notes
		    		for(int i=0;i<notes.size();i++) {
						int yy = notes.get(i).getNotations().getTechnical().getString();

						int f=0;
						
						//Checking if Grace is null
						if(notes.get(i).getGrace()!=null) {
							//If not null, setting the font size to 11
							f=11;
						}
						else {
							//If null, setting the font size to 14
							f=14;

						}
						//Setting the x-axis
						double xpos=NoteInfo.notePos(notes,notes.get(i));
						//Setting the y-axis of the note to the string number and multiplying it with 13 because 13
						//units is the difference between the drawn lines on the screen
						y=0+(yy-1)*13;
						
						//Drawing the notes by passing the x, y and the font size
						drawNotes(xpos, y+5,String.valueOf(notes.get(i).getNotations().getTechnical().getFret()),f);
						
					}
		    	}
		    	//Checking the instrument name
		    	else if(instName.equals("Bass")) {
		    		y = 0;
		    		
		    		//Running the loop for maximum number of notes
		    		for(int i=0;i<notes.size();i++) {
						int yy = notes.get(i).getNotations().getTechnical().getString();
						int x;
						int f=0;
				
						if(notes.get(i).getGrace()!=null) {
							//If not null, setting the font size to 11
							f=11;
						}
						else {
							//If null, setting the font size to 14
							f=14;
						}
						//Setting the x-axis
						double xpos=NoteInfo.notePos(notes,notes.get(i));
						//Setting the y-axis of the note to the string number and multiplying it with 13 because 13
						//units is the difference between the drawn lines on the screen
						y=0+(yy-1)*13;
						
						//Drawing the notes by passing the x, y and the font size
						drawNotes(xpos, y+5,String.valueOf(notes.get(i).getNotations().getTechnical().getFret()),f);
						
					}
		    	}
		    	//Checking the instrument name
		    	else if(instName.equals("Drumset")) {

		    		int x = 50;
		    		int y2 = 0;	    	
		    		int count = 50;

		    		
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
		    		
		    			x+=30;
		    		}
		    		
		    		else {
		    			
		    			if(notes.get(i).getUnpitched().getDisplaystep().equals("C")) {
		    				y2 = 20;
		    			}
		    			else if(notes.get(i).getUnpitched().getDisplaystep().equals("F")){
		    				y2 = 45;
		    			}
		    			else if(notes.get(i).getUnpitched().getDisplaystep().equals("D")) {
		    				y2 = 13;
		    			}
		    			else if(notes.get(i).getUnpitched().getDisplaystep().equals("E")) {
		    				y2 = 7;
		    			}

		    			drawCircle(count, y2);
		    			drawVerticalLines(count +5, y2);
		    	
		    			count+=30;
		    		}
		    		}
		    	}
		    	for (int i = 1; i <= limit; i++) {


		      		instrumentMusicLines(instName, y);
		      		//Draw Clef

		      		System.out.println("run");



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


