package GUI;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DrawGuitarOrBass {

	public static void drawGBNotes(ArrayList<measureinfo> listofmeasures, double font, double gracefont, Pane pane) {
		String notenum = "";
		double xcord = 0;
		double ycord = 0;
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					xcord = listofmeasures.get(i).getMeasure().get(j).getX();
					ycord = listofmeasures.get(i).getMeasure().get(j).getY();
					notenum = "" + listofmeasures.get(i).getMeasure().get(j).getNote().getNotations().getTechnical().getFret();
					if(ycord>=pane.getMaxHeight()) {
						pane.resize(pane.getMaxWidth(), pane.getMaxHeight()+100);
					}
					if(listofmeasures.get(i).getMeasure().get(j).getNote().getGrace()!=null) {
						GeneralDrawing.drawNotes(xcord, ycord, notenum, gracefont, pane);
					}
					else {
						GeneralDrawing.drawNotes(xcord, ycord, notenum, font, pane);
					}
				}
			}
		}
	}

	public static void drawGBGraces(ArrayList<ArrayList<NoteAndPos>> gracelist, double font, double gracefont, double yInc, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middledis = 0;
		double middlex = 0;
		double middley = 0;
		for(int i=0; i<gracelist.size(); i++) {
			if(gracelist.get(i)!=null) {
				for(int j=0; j<gracelist.get(i).size(); j++) {
					current = gracelist.get(i).get(j);
					if(j!=0) {
						prev = gracelist.get(i).get(j-1);
						if(prev!=null && current!=null) {
							//upward curve
							if(prev.getNote().getNotations().getTechnical().getString()<3) {
								//grace + grace
								if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
								   current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
									middledis = (current.getX() - prev.getX()) / 2;
									middlex = prev.getX() + middledis;
									middley = current.getY() - yInc;
									GeneralDrawing.drawQuad(prev.getX(), prev.getY()-(gracefont/2), middlex, middley, current.getX(), current.getY()-(gracefont/2), pane);
								}
								//grace + none
								else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
										current.getNote().getGrace()==null && current.getNote().getChord()==null) {
									middledis = (current.getX() - prev.getX()) / 2;
									middlex = prev.getX() + middledis;
									middley = current.getY() - yInc;
									GeneralDrawing.drawQuad(prev.getX(), prev.getY()-(gracefont/2), middlex, middley, current.getX(), current.getY()-(font/2), pane);
								}
								//grace and chord + grace
								else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
										current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
									middledis = (current.getX() - prev.getX()) / 2;
									middlex = prev.getX() + middledis;
									middley = current.getY() - yInc;
									GeneralDrawing.drawQuad(prev.getX(), current.getY()-(gracefont/2), middlex, middley, current.getX(), current.getY()-(gracefont/2), pane);
								}
								//grace and chord + none
								else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
										current.getNote().getGrace()==null && current.getNote().getChord()==null) {
									middledis = (current.getX() - prev.getX()) / 2;
									middlex = prev.getX() + middledis;
									middley = current.getY() - yInc;
									GeneralDrawing.drawQuad(prev.getX(), current.getY()-(gracefont/2), middlex, middley, current.getX(), current.getY()-(font/2), pane);
								}
							}
							//downward curve
							else if(prev.getNote().getNotations().getTechnical().getString()>=3) {
								//grace + grace
								if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
								   current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
									middledis = (current.getX() - prev.getX()) / 2;
									middlex = prev.getX() + middledis;
									middley = current.getY() + yInc;
									GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(gracefont/2), middlex, middley, current.getX(), current.getY()+(gracefont/2), pane);
								}
								//grace + none
								else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
								        current.getNote().getGrace()==null && current.getNote().getChord()==null) {
								   middledis = (current.getX() - prev.getX()) / 2;
								   middlex = prev.getX() + middledis;
								   middley = current.getY() + yInc;
								   GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(gracefont/2), middlex, middley, current.getX(), current.getY()+(font/2), pane);
								}
								//grace and chord + grace
								else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
									    current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
									middledis = (current.getX() - prev.getX()) / 2;
									middlex = prev.getX() + middledis;
									middley = prev.getY() + yInc;
									GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(gracefont/2), middlex, middley, current.getX(), prev.getY()+(gracefont/2), pane);
								}
								//grace and chord + none
								else if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
									    current.getNote().getGrace()==null && current.getNote().getChord()==null) {
									middledis = (current.getX() - prev.getX()) / 2;
									middlex = prev.getX() + middledis;
									middley = prev.getY() + yInc;
									GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(gracefont/2), middlex, middley, current.getX(), prev.getY()+(font/2), pane);
								}
							}
						}
					}
				}
			}			
		}
	}

	public static void drawGBTies(ArrayList<NoteAndPos> orderedtiedlist, double font, double yInc, double maxX, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middledis = 0;
		double middlex = 0;
		double middley = 0;
		for(int i=0; i<orderedtiedlist.size(); i++) {
			current = orderedtiedlist.get(i);
			if(i!=0) {
				prev = orderedtiedlist.get(i-1);
				if(prev!=null && current!=null && prev.getNote().getGrace()==null && current.getNote().getGrace()==null) {
					if(prev.getNote().getNotations().getTechnical().getString()<3) {
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-(font/2), middlex, middley, maxX, prev.getY()-(font/2), pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() - yInc;
							GeneralDrawing.drawQuad(0, current.getY()-(font/2), middlex, middley, current.getX(), current.getY()-(font/2), pane);

						}
						else {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-(font/2), middlex, middley, current.getX(), current.getY()-(font/2), pane);
						}
					}
					else if(prev.getNote().getNotations().getTechnical().getString()>=3) {
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(font/2), middlex, middley, maxX, prev.getY()+(font/2), pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() + yInc;
							GeneralDrawing.drawQuad(0, current.getY()+(font/2), middlex, middley, current.getX(), current.getY()+(font/2), pane);
						}
						else {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(font/2), middlex, middley, current.getX(), current.getY()+(font/2), pane);
						}
					}
				}
			}
		}
	}

	public static void drawGBSlurs(ArrayList<NoteAndPos> orderedslurlist, double font, double yInc, double maxX, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double middledis = 0;
		double middlex = 0;
		double middley = 0;
		String placement = "";
		for(int i=0; i<orderedslurlist.size(); i++) {
			current = orderedslurlist.get(i);
			if(i!=0) {
				prev = orderedslurlist.get(i-1);
				if(prev!=null && current!=null && prev.getNote().getGrace()==null && current.getNote().getGrace()==null) {
					if(prev.getNote().getNotations().getListOfSlurs().size()==1 &&
							prev.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
						placement = prev.getNote().getNotations().getListOfSlurs().get(0).getPlacement();
					}
					if(placement==null || placement.equalsIgnoreCase("above")) {
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-(font/2), middlex, middley, maxX, prev.getY()-(font/2), pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() - yInc;
							GeneralDrawing.drawQuad(0, current.getY()-(font/2), middlex, middley, current.getX(), current.getY()-(font/2), pane);
						}
						else {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-(font/2), middlex, middley, current.getX(), current.getY()-(font/2), pane);
						}
					}
					else if(placement!=null && !(placement.equalsIgnoreCase("above"))) {
						if(prev.getStaffnum()!=current.getStaffnum()) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(font/2), middlex, middley, maxX, prev.getY()+(font/2), pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() + yInc;
							GeneralDrawing.drawQuad(0, current.getY()+(font/2), middlex, middley, current.getX(), current.getY()+(font/2), pane);
						}
						else {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+(font/2), middlex, middley, current.getX(), current.getY()+(font/2), pane);
						}
					}
				}
			}
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	if(i!=0) {
//		prev = gracelist.get(i-1);
//		if(prev!=null && current!=null) {
//			if(prev.getNote().getNotations().getTechnical().getString()<3) {     					
//				//prev = grace , no chord
//				//current = no grace , no chord
//				if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
//						current.getNote().getGrace()==null && current.getNote().getChord()==null) {
//					middledis = (current.getX() - prev.getX())/2;
//					middlex = prev.getX() + middledis;
//					middley = current.getY() - 15;
//					GeneralDrawing.drawQuad(prev.getX(), prev.getY()-5, middlex, middley, current.getX(), current.getY()-10, pane);
//				}
//				//prev = grace , no chord
//				//current = grace , no chord
//				if(prev.getNote().getGrace()!=null && prev.getNote().getChord()==null &&
//						current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
//					middledis = (current.getX() - prev.getX())/2;
//					middlex = prev.getX() + middledis;
//					middley = current.getY() - 15;
//					GeneralDrawing.drawQuad(prev.getX(), current.getY()-5, middlex, middley, current.getX(), current.getY()-5, pane);
//				}
//				//prev = grace and chord
//				//current = grace and no chord
//				if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
//						current.getNote().getGrace()!=null && current.getNote().getChord()==null) {
//					middledis = (current.getX() - prev.getX())/2;
//					middlex = prev.getX() + middledis;
//					middley = current.getY() - 15;
//					GeneralDrawing.drawQuad(prev.getX(), current.getY()-5, middlex, middley, current.getX(), current.getY()-5, pane);
//				}
//				//prev = grace and chord
//				//current = no grace and no chord
//				if(prev.getNote().getGrace()!=null && prev.getNote().getChord()!=null &&
//						current.getNote().getGrace()==null && current.getNote().getChord()==null) {
//					middledis = (current.getX() - prev.getX())/2;
//					middlex = prev.getX() + middledis;
//					middley = current.getY() - 15;
//					GeneralDrawing.drawQuad(prev.getX(), current.getY()-5, middlex, middley, current.getX(), current.getY()-10, pane);
//				}
//			}
//			else if (prev.getNote().getNotations().getTechnical().getString()>=3){
//				middledis = (current.getX() - prev.getX())/2;
//				middlex = prev.getX() + middledis;
//				middley = prev.getY() + 11.5;
//				GeneralDrawing.drawQuad(prev.getX(), prev.getY()+1.5, middlex, middley, current.getX(), current.getY()+1.5, pane);
//			}
//		}
//	}
	
	
	
	
	
	
	
	
	
}
