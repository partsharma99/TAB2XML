package GUI;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import xml.to.sheet.converter.POJOClasses.NoteHead2;

public class DrawDrumset {
	
	public static void drawDrumNotesAndStems(ArrayList<NoteAndPos> nplist, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double xcord = 0;
		double ycord = 0;
		double maxStemheight = 0;
		NoteHead2 notehead = null;
		for(int k=0; k < nplist.size(); k++) {
			xcord = nplist.get(k).getX();
			ycord = nplist.get(k).getY();
			notehead = nplist.get(k).getNote().getNotehead();

			if(notehead!=null) {
				if(notehead.getText().equalsIgnoreCase("x")) {
					GeneralDrawing.drawX(xcord, ycord, pane);

					if(nplist.get(k).getNote().getInstrument().
							getId().equalsIgnoreCase("P1-I50")) {
						GeneralDrawing.drawLine(xcord-6, ycord, xcord+6, ycord, pane);
					}
				}
				else if(notehead.getText().equalsIgnoreCase("normal")){
					GeneralDrawing.drawCircle(xcord, ycord, pane);
				}
				
				if(notehead.getParentheses()!=null) {
					GeneralDrawing.drawQuad(xcord-3.5, ycord+7.5, xcord-15,
											ycord, xcord-3.5, ycord-7.5, pane);
					GeneralDrawing.drawQuad(xcord+3.5, ycord+7.5, xcord+15,
											ycord, xcord+3.5, ycord-7.5, pane);
				}
			}
			else if(notehead==null){
				GeneralDrawing.drawCircle(xcord, ycord, pane);
			}
		}
		
		for(int k=0; k<nplist.size(); k++) {
			current = nplist.get(k);
    		if(k!=0) {
    			prev = nplist.get(k-1);
    			
    			//current note has a chord
    			if(current.getNote().getChord()!=null) {
    				//stem is up
    				if(current.getNote().getStem()!=null && current.getNote().getStem().equalsIgnoreCase("up")) {
    					//current has a notehead
    					if(current.getNote().getNotehead()!=null) {
    						//prev has a notehead
    						//prev = x
    						//current = x
    						if(prev.getNote().getNotehead()!=null && 
    						   prev.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
    						   current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
    							GeneralDrawing.drawLine(current.getX()+5, current.getY()-5, prev.getX()+5, prev.getY()-5, pane);
    						}
    						//prev has a notehead
    						//prev = x
    						//current = (o)
    						else if (prev.getNote().getNotehead()!=null && 
    	    						 prev.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
    	    						 current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
    							GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY()-5, pane);
    						}
    						//prev does or does not have a notehead
    						//prev = o
    						//OR
    						//prev = (o)
    						//current = (o)
    						else if ((prev.getNote().getNotehead()==null || 
    								  prev.getNote().getNotehead().getText().equalsIgnoreCase("normal")) &&
   	    						      current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
    							GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY(), pane);
   							}
    					}
    					else if(current.getNote().getNotehead()==null){
    						//prev has a notehead
    						//prev = x
    						//current = o
    						if(prev.getNote().getNotehead()!=null && 
    						   prev.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
    							GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY()-5, pane);
    						}
    						//prev does or does not have a notehead
    						//prev = o
    						//OR
    						//prev = (o)
    						//current = o
    						else if ((prev.getNote().getNotehead()==null || 
  								      prev.getNote().getNotehead().getText().equalsIgnoreCase("normal"))) {
    							GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY(), pane);
 							}
    					}
    				}
    			}
    			else if(current.getNote().getChord()==null) {
    				if(current.getNote().getStem()!=null && current.getNote().getStem().equalsIgnoreCase("up")) {
    					if(current.getNote().getNotehead()!=null) {
    						if(current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
    							GeneralDrawing.drawLine(current.getX()+5, current.getY()-5, current.getX()+5, current.getY()-20, pane);
    						}
    						else if(current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
    							GeneralDrawing.drawLine(current.getX()+5, current.getY(), current.getX()+5, current.getY()-20, pane);
    						}
    					}
    					else if(current.getNote().getNotehead()==null){
    						GeneralDrawing.drawLine(current.getX()+5, current.getY(), current.getX()+5, current.getY()-20, pane);
    					}
    				}
    			}
    		}
		}
		
		
	}

	public static void drawDrumTies(ArrayList<NoteAndPos> orderedtiedlist, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middlex = 0;
		double middley = 0;
		double middledis = 0;
    	for(int k=0; k<orderedtiedlist.size(); k++) {
    		current = orderedtiedlist.get(k);
    		if(k!=0) {
    			prev = orderedtiedlist.get(k-1);
    			if(prev!=null && current!=null) {
    				if(prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I50") ||
    				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I43") ||
    				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I52") ||
    				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I48") ||
    				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I46") ||
    				   prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I39")) {
    					middledis = (current.getX() - prev.getX())/2;
    					middlex = prev.getX() + middledis;
    					middley = prev.getY() - 13;
    					GeneralDrawing.drawQuad(prev.getX(), prev.getY()-3.5, middlex, middley, current.getX(), current.getY()-3.5, pane);
    				}
    				else {
    					middledis = (current.getX() - prev.getX())/2;
    					middlex = prev.getX() + middledis;
    					middley = prev.getY() + 13;
    					GeneralDrawing.drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5, pane);
    				}
    			}
    		}
    	}
	}

	public static void drawDrumSlurs(ArrayList<NoteAndPos> orderedslurlist, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middlex = 0;
		double middley = 0;
		double middledis = 0;
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
    						GeneralDrawing.drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5, pane);
    					}
    					else if(prev.getY()<current.getY()) {
    						System.out.println(k);
    						middledis = (current.getX() - prev.getX())/2;
    						middlex = prev.getX() + middledis;
    						middley = current.getY() + 13;
    						GeneralDrawing.drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5, pane);
    					}
    				}
    			}
    		}
    	}
	}
}