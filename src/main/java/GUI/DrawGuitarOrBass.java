package GUI;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DrawGuitarOrBass {

	public static void drawGBNotes(ArrayList<ArrayList<NoteAndPos>> nplist, Pane pane) {
		String notenum = "";
		double xcord = 0;
		double ycord = 0;
		for(int i=0; i<nplist.size(); i++) {
			if(nplist.get(i)!=null) {
				for(int j=0; j<nplist.get(i).size(); j++) {
					xcord = nplist.get(i).get(j).getX();
					ycord = nplist.get(i).get(j).getY();
					notenum = "" + nplist.get(i).get(j).getNote().getNotations().getTechnical().getFret();
					if(ycord>=pane.getMaxHeight()) {
						pane.resize(pane.getMaxWidth(), pane.getMaxHeight()+100);
					}
					if(nplist.get(i).get(j).getNote().getGrace()!=null) {
						GeneralDrawing.drawNotes(xcord, ycord, notenum, 6.5, pane);
					}
					else {
						GeneralDrawing.drawNotes(xcord, ycord, notenum, 13, pane);
					}
				}
			}
		}
	}

	public static void drawGBGraces(ArrayList<NoteAndPos> gracelist, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middledis = 0;
		double middlex = 0;
		double middley = 0;
		for(int i=0; i<gracelist.size(); i++) {
			current = gracelist.get(i);
			if(i!=0) {
				prev = gracelist.get(i-1);
				if(prev!=null && current!=null) {
					if(prev.getNote().getNotations().getTechnical().getString()<3) {     					
						//prev = grace , no chord
						//current = no grace , no chord
						if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
								current.getNote().getGrace()==null && current.getNote().getChord()==null) {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = current.getY() - 15;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-5, middlex, middley, current.getX(), current.getY()-10, pane);
						}
						//prev = grace , no chord
						//current = grace , no chord
						if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
								current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = current.getY() - 15;
							GeneralDrawing.drawQuad(prev.getX(), current.getY()-5, middlex, middley, current.getX(), current.getY()-5, pane);
						}
						//prev = grace and chord
						//current = grace and no chord
						if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
								current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = current.getY() - 15;
							GeneralDrawing.drawQuad(prev.getX(), current.getY()-5, middlex, middley, current.getX(), current.getY()-5, pane);
						}
						//prev = grace and chord
						//current = no grace and no chord
						if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
								current.getNote().getGrace()==null && current.getNote().getChord()==null) {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = current.getY() - 15;
							GeneralDrawing.drawQuad(prev.getX(), current.getY()-5, middlex, middley, current.getX(), current.getY()-10, pane);
						}
					}
					else if (prev.getNote().getNotations().getTechnical().getString()>=3){
						middledis = (current.getX() - prev.getX())/2;
						middlex = prev.getX() + middledis;
						middley = prev.getY() + 11.5;
						GeneralDrawing.drawQuad(prev.getX(), prev.getY()+1.5, middlex, middley, current.getX(), current.getY()+1.5, pane);
					}
				}
			}
		}
	}

	public static void drawGBTies(ArrayList<NoteAndPos> orderedtiedlist, double maxX, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middledis = 0;
		double middlex = 0;
		double middley = 0;
		for(int i=0; i<orderedtiedlist.size(); i++) {
			current = orderedtiedlist.get(i);
			if(i!=0) {
				prev = orderedtiedlist.get(i-1);
				if(prev!=null && current!=null) {
					if(prev.getNote().getNotations().getTechnical().getString()<3) {
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - 21;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-11.5, middlex, middley, maxX, prev.getY()-11.5, pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() - 21;
							GeneralDrawing.drawQuad(0, current.getY()-11.5, middlex, middley, current.getX(), current.getY()-11.5, pane);

						}
						else {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - 21;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-11.5, middlex, middley, current.getX(), current.getY()-11.5, pane);
						}
					}
					else if(prev.getNote().getNotations().getTechnical().getString()>=3) {
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + 13;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, maxX, prev.getY()+3.5, pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() + 13;
							GeneralDrawing.drawQuad(0, current.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5, pane);
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
	}

	public static void drawGBSlurs(ArrayList<NoteAndPos> orderedslurlist, double maxX, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middledis = 0;
		double middlex = 0;
		double middley = 0;
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
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - 21;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-11.5, middlex, middley, maxX, prev.getY()-11.5, pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() - 21;
							GeneralDrawing.drawQuad(0, current.getY()-11.5, middlex, middley, current.getX(), current.getY()-11.5, pane);
						}
						else {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - 21;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-11.5, middlex, middley, current.getX(), current.getY()-11.5, pane);
						}
					}
					else if(placement!=null && !(placement.equalsIgnoreCase("above"))) {
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + 13;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+3.5, middlex, middley, maxX, prev.getY()+3.5, pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() + 13;
							GeneralDrawing.drawQuad(0, current.getY()+3.5, middlex, middley, current.getX(), current.getY()+3.5, pane);
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
	}

	public static void drawInstrumentLines(ArrayList<NoteAndPos> nplist, double starty, double yInc, int diffbwstaves) {

	}
}
