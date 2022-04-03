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
import xml.to.sheet.converter.POJOClasses.NoteHead2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.Tied2;
import xml.to.sheet.converter.POJOClasses.XmlToJava;

import java.awt.geom.Arc2D;
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
		        
	        	double startx = 66;
	        	double starty = 66;
	        	double xInc = 24;
	        	double yInc = 13;
	        	double basexInc = 10;
	        	int diffbwstaves = 6;
		        
		        if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
		        	ArrayList<ArrayList<NoteAndPos>> gBNPlist = InstCordPos2.getListofPositions(sc, instName, notelist, 
		        																	 startx, starty, xInc, yInc, basexInc, diffbwstaves, this.pane.getMaxWidth(), pane);
		        	DrawGuitarOrBass.drawGBNotes(gBNPlist, pane);
		        	ArrayList<NoteAndPos> gracelist = ComponentClass.getGracList(gBNPlist);
		        	DrawGuitarOrBass.drawGBGraces(gracelist, pane);
		        	ArrayList<NoteAndPos> tielist = ComponentClass.getTieList(gBNPlist, instName);
		        	DrawGuitarOrBass.drawGBTies(tielist, this.pane.getMaxWidth(), pane);
		        	ArrayList<NoteAndPos> slurlist = ComponentClass.getSlurList(gBNPlist, instName);
		        	DrawGuitarOrBass.drawGBSlurs(slurlist, this.pane.getMaxWidth(), pane);
		        	ArrayList<NoteAndPos> beamlist = ComponentClass.getBeamList(gBNPlist, sc);
		        }
		        
		        else if(instName.equalsIgnoreCase("Drumset")) {
		        	ArrayList<ArrayList<NoteAndPos>> drumsetNPlist = InstCordPos2.getListofPositions(sc, instName, notelist, 
		        																		  startx, starty, xInc, yInc, basexInc, diffbwstaves, this.pane.getMaxWidth(), pane);
		        	DrawDrumset.drawDrumNotesAndStems(drumsetNPlist, pane);
		        	ArrayList<NoteAndPos> tielist = ComponentClass.getTieList(drumsetNPlist, instName);
		        	DrawDrumset.drawDrumTies(tielist, this.pane.getMaxWidth(), pane);
		        	ArrayList<NoteAndPos> slurlist = ComponentClass.getSlurList(drumsetNPlist, instName);
		        	DrawDrumset.drawDrumSlurs(slurlist, this.pane.getMaxWidth(), pane);
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
