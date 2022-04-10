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
	
	public static void drawStems(ArrayList<measureinfo> listofmeasures, ArrayList<Double> maxbeami,  ArrayList<ArrayList<NoteAndPos>> beamlist, double lengthofbar, double yInc, Pane pane) {
		NoteAndPos current = null;
//		for(int i=0; i<listofmeasures.size(); i++) {
//			if(listofmeasures.get(i).getMeasure()!=null) {
//				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
//					current = listofmeasures.get(i).getMeasure().get(j);
//					if(current.getNote().getChord()==null && current.getNote().getStem()!=null && beamlist.get(i).contains(current)==false) {
//						GeneralDrawing.drawLine(current.getX(), current.getTopofstaff()+lengthofbar+yInc, current.getX(), maxbeami.get(current.getStaffnum()-1),pane);
//					}
//				}
//			}
//		}
		for(int i=0; i<beamlist.size(); i++) {
			if(beamlist.get(i)!=null) {
				for(int j=0; j<beamlist.get(i).size(); j++) {
					current = beamlist.get(i).get(j);
					if(current!=null) {
						GeneralDrawing.drawLine(current.getX(), current.getTopofstaff()+lengthofbar+yInc, current.getX(), maxbeami.get(current.getStaffnum()-1),pane);
					}
				}
			}
		}
	}
	
	public static void drawBeams(ArrayList<ArrayList<NoteAndPos>> beamlist, ArrayList<Double> maxbeami, double yInc, Pane pane) {
		NoteAndPos first1 = null;
		NoteAndPos last = null;
		int staffnum = 0;
		int temp = 0;
		
		for(int i=0; i<beamlist.size(); i++) {
			if(beamlist.get(i)!=null) {
				for(int j=0; j<beamlist.get(i).size();) {
					first1 = beamlist.get(i).get(j);
					if(first1!=null) {
						last = first1;
						temp = j;
						while(temp<beamlist.get(i).size() && beamlist.get(i).get(temp)!=null) {
							temp++;
						}
						temp--;
						last = beamlist.get(i).get(temp);
						staffnum = beamlist.get(i).get(j).getStaffnum()-1;
						if(temp!=j) {
							DrawLine dl = new DrawLine(first1.getX(), maxbeami.get(staffnum), last.getX(), maxbeami.get(staffnum));
							dl.setWidth(0.25*yInc);
							pane.getChildren().add(dl.getLine());
						}
						j = temp + 2;
					}
					else {
						j++;
					}
				}	
			}
		}
		first1 = null;
		last = null;
		temp = 0;
		NoteAndPos current = null;
		NoteAndPos current2 = null;
		for(int i=0; i<beamlist.size(); i++) {
			if(beamlist.get(i)!=null) {
				for(int j=0; j<beamlist.get(i).size(); j++) {
					current = beamlist.get(i).get(j);
					if(current!=null) {
						current2 = current;
						temp = j + 1;
						while(temp<beamlist.get(i).size() && beamlist.get(i).get(temp)!=null) {
							//if types are the same then beam are drawn
							if(current2.getType()==beamlist.get(i).get(temp).getType()) {
								if(current2.getType()==((double)1/16)) {
									double y = maxbeami.get(current.getStaffnum()-1) - (0.75*yInc);
									DrawLine dl = new DrawLine(current2.getX(), y, beamlist.get(i).get(temp).getX(), y);
									dl.setWidth(0.25*yInc);
									pane.getChildren().add(dl.getLine());
								}
								else if(current2.getType()==((double)1/32)) {
									double y = maxbeami.get(current.getStaffnum()-1) - (1.25*yInc);
									DrawLine dl = new DrawLine(current2.getX(), y, beamlist.get(i).get(temp).getX(), y);
									dl.setWidth(0.25*yInc);
									pane.getChildren().add(dl.getLine());
								}
							}
							else {
							}
							current2 = beamlist.get(i).get(temp);
							temp++;
						}
						j = temp;
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
