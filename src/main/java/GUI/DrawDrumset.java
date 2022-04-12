package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import xml.to.sheet.converter.POJOClasses.Dot2;
import xml.to.sheet.converter.POJOClasses.NoteHead2;

public class DrawDrumset {

	public static void drawDrumNotesAndStems(ArrayList<measureinfo> listofmeasures, ArrayList<Double> maxbeami, double yInc, Pane pane) {
		NoteAndPos current = null;
		double xcord = 0;
		double ycord = 0;
		double half = 0.5*yInc;
		double three4 = 0.75*yInc;
		NoteHead2 notehead = null;
		//(1)
		//Drawing the note heads for each note.
		//iterating through list of measures to draw note heads for each note.
		for(int i=0; i<listofmeasures.size(); i++) {
			
			//non-empty measure.
			if(listofmeasures.get(i).getMeasure()!=null) {
				
				//interating through the list of notes in the current measure.
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					
					//current note in the current measure.
					current = listofmeasures.get(i).getMeasure().get(j);
					xcord = current.getX();
					ycord = current.getY();
					notehead = current.getNote().getNotehead();
					
					//check if notes are close
					//will draw noteheads in helper 
					//if check close is true we shift and contine
					if(j+1<listofmeasures.get(i).getMeasure().size()) {
						if (checkclose(current, listofmeasures.get(i).getMeasure().get(j+1), yInc)==true) {
							shiftclosenotes(current, listofmeasures.get(i).getMeasure().get(j+1), yInc, half, three4, pane);
							continue;
						}
					}
					//has a notehead
					if(notehead!=null) {
						
						//x
						if(notehead.getText().equalsIgnoreCase("x")) {
							GeneralDrawing.drawX(xcord, ycord, half, pane);
							
							//x with line
							if(current.getNote().getInstrument().getId().equalsIgnoreCase("P1-I50")) {
								GeneralDrawing.drawLine(xcord-half, ycord, xcord+half, ycord, pane);
							}
						}
						
						//O
						else if(notehead.getText().equalsIgnoreCase("normal")){
							GeneralDrawing.drawCircle(xcord, ycord, half, pane);
						}
						
						//O with brackets or X with brackets
						if(notehead.getParentheses()!=null) {
							GeneralDrawing.drawQuad(xcord-half, ycord+three4, xcord-three4, ycord, xcord-half, ycord-three4, pane);
							GeneralDrawing.drawQuad(xcord+half, ycord+three4, xcord+three4, ycord, xcord+half, ycord-three4, pane);
						}
					}
					
					else if(notehead==null) {
						if(current.getNote().getRest()!=null) {
							DrawLine dl = new DrawLine(xcord-half, ycord, xcord+half, ycord);
							dl.setWidth(0.25*yInc);
							pane.getChildren().add(dl.getLine());
						}
						else {
							GeneralDrawing.drawCircle(xcord, ycord, half, pane);
						}
					}
				}
			}
		}
		
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					current = listofmeasures.get(i).getMeasure().get(j);
					//no chord + stem
					if(current.getNote().getChord()==null && current.getNote().getStem()!=null && current.getNote().getStem().equalsIgnoreCase("up")) {
						nochordandstem(current.getStaffnum(), half, current, maxbeami, pane);
					}
					//chord + stem
					else if (current.getNote().getChord()!=null && current.getNote().getStem()!=null && current.getNote().getStem().equalsIgnoreCase("up")) {
						chordandstem(current.getStaffnum(), half, j-1, listofmeasures.get(i).getMeasure(), current, maxbeami, pane);
					}
				}
			}
		}
	}

	private static void nochordandstem(int staffnum, double half, NoteAndPos current, ArrayList<Double> maxbeami, Pane pane) {
		double top = maxbeami.get(staffnum-1);
		if(current.getNote().getNotehead()!=null) {
			if(current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
				GeneralDrawing.drawLine(current.getX()+half, current.getY()-half, current.getX()+half, top, pane);
			}
			else if(current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
				GeneralDrawing.drawLine(current.getX()+half, current.getY(), current.getX()+half, top, pane);
			}
		}
		else {
			if(current.getNote().getRest()==null) {
				GeneralDrawing.drawLine(current.getX()+half, current.getY(), current.getX()+half, top, pane);
			}
		}
	}
	
	private static void chordandstem(int staffnum, double half, int j, ArrayList<NoteAndPos> measure, NoteAndPos current, ArrayList<Double> maxbeami, Pane pane) {
		NoteAndPos prev = measure.get(j);
		double top = 0;
		if(prev.getNote().getNotehead()!=null) {
			//prev = x
			if(prev.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
				top = prev.getY()-half;
			}
			//prev = O
			else if(prev.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
				top = prev.getY();
			}
		}
		else {
			//prev = O
			if(prev.getNote().getRest()==null) {
				top = prev.getY();
			}
		}
		
		if(current.getNote().getNotehead()!=null) {
			//current = x
			if(current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
				GeneralDrawing.drawLine(current.getX()+half, current.getY()-half, current.getX()+half, top, pane);
			}
			//current = O
			else if(current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
				GeneralDrawing.drawLine(current.getX()+half, current.getY(), current.getX()+half, top, pane);
			}
		}
		else {
			//current = O
			if(prev.getNote().getRest()==null) {
				GeneralDrawing.drawLine(current.getX()+half, current.getY(), current.getX()+half, top, pane);
			}
		}
	}
	
	private static boolean checkclose(NoteAndPos current, NoteAndPos next, double yInc) {
		//close together
		if(next.getNote().getChord()!=null && (next.getY()-current.getY()==(0.5*yInc))) {
			return true;
		}
		//not close together
		else {
			return false;
		}
	}
	
	private static void shiftclosenotes(NoteAndPos current, NoteAndPos next, double yInc, double half, double three4, Pane pane) {
		//current note
		double xcord = current.getX() + yInc;
		double ycord = current.getY();
		if(current.getNote().getNotehead()!=null) {
			if(current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
				GeneralDrawing.drawX(xcord, ycord, half, pane);

				//x with line
				if(current.getNote().getInstrument().getId().equalsIgnoreCase("P1-I50")) {
					GeneralDrawing.drawLine(xcord-half, ycord, xcord+half, ycord, pane);
				}
			}
			else if(current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
				GeneralDrawing.drawCircle(xcord, ycord, half, pane);
			}
			if(current.getNote().getNotehead().getParentheses()!=null) {
				GeneralDrawing.drawQuad(xcord-half, ycord+three4, xcord-three4, ycord, xcord-half, ycord-three4, pane);
				GeneralDrawing.drawQuad(xcord+half, ycord+three4, xcord+three4, ycord, xcord+half, ycord-three4, pane);
			}
		}
		else {
			if(current.getNote().getRest()!=null) {
				DrawLine d1 = new DrawLine((xcord-half), (ycord), (xcord+half), ycord);
				d1.setWidth(0.25*yInc);
			}
			else {
				GeneralDrawing.drawCircle(xcord, ycord, half, pane);
			}
		}
		xcord = next.getX();
		ycord = next.getY();
		if(current.getNote().getNotehead()!=null) {
			if(current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
				GeneralDrawing.drawX(xcord, ycord, half, pane);
				
				//x with line
				if(current.getNote().getInstrument().getId().equalsIgnoreCase("P1-I50")) {
					GeneralDrawing.drawLine(xcord-half, ycord, xcord+half, ycord, pane);
				}
			}
			else if(current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
				GeneralDrawing.drawCircle(xcord, ycord, half, pane);
			}
			if(current.getNote().getNotehead().getParentheses()!=null) {
				GeneralDrawing.drawQuad(xcord-half, ycord+three4, xcord-three4, ycord, xcord-half, ycord-three4, pane);
				GeneralDrawing.drawQuad(xcord+half, ycord+three4, xcord+three4, ycord, xcord+half, ycord-three4, pane);
			}
		}
		else {
			if(current.getNote().getRest()!=null) {
				DrawLine d1 = new DrawLine((xcord-half), (ycord), (xcord+half), ycord);
				d1.setWidth(0.25*yInc);
			}
			else {
				GeneralDrawing.drawCircle(xcord, ycord, half, pane);
			}
		}
	}

	public static void drawDrumTies(ArrayList<NoteAndPos> orderedtiedlist, double font, double yInc, double maxX, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double half = font/2;
		double middlex = 0;
		double middley = 0;
		double middledis = 0;
		for(int i=0; i<orderedtiedlist.size(); i++) {
			current = orderedtiedlist.get(i);
			if(i!=0) {
				prev = orderedtiedlist.get(i-1);
				if(prev!=null && current!=null && prev.getNote().getGrace()==null && current.getNote().getGrace()==null) {
					if(prev.getStaffnum()==current.getStaffnum()) {
						if(prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I50") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I43") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I52") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I48") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I46") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I39")) {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-half, middlex, middley, current.getX(), current.getY()-half, pane);
						}
						else {
							middledis = (current.getX() - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+half, middlex, middley, current.getX(), current.getY()+half, pane);
						}
					}
					else {
						if(prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I50") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I43") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I52") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I48") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I46") ||
								prev.getNote().getInstrument().getId().equalsIgnoreCase("P1-I39")) {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() - yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()-3.5, middlex, middley, maxX, prev.getY()-3.5, pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = current.getY() - yInc;
							GeneralDrawing.drawQuad(0, current.getY()-3.5, middlex, middley, current.getX(), current.getY()-3.5, pane);
						}
						else {
							middledis = (maxX - prev.getX())/2;
							middlex = prev.getX() + middledis;
							middley = prev.getY() + yInc;
							GeneralDrawing.drawQuad(prev.getX(), prev.getY()+font, middlex, middley, maxX, prev.getY()+font, pane);
							middledis = current.getX()/2;
							middlex = 0 + middledis;
							middley = prev.getY() + yInc;
							GeneralDrawing.drawQuad(0, current.getY()+font, middlex, middley, current.getX(), current.getY()+font, pane);
						}
					}
				}
			}
		}
	}

	public static void drawDrumSlurs(ArrayList<NoteAndPos> orderedslurlist, double font, double yInc, double maxX, Pane pane) {
		NoteAndPos prev = null;
		NoteAndPos current = null;
		double half = font/2;
		double middlex = 0;
		double middley = 0;
		double middledis = 0;
		String placement = "";
		boolean isgrace = false;
		for(int i=0; i<orderedslurlist.size(); i++) {
			current = orderedslurlist.get(i);
			if(i!=0) {
				prev = orderedslurlist.get(i-1);
				if(prev!=null && current!=null && prev.getNote().getGrace()==null && current.getNote().getGrace()==null) {
					if(prev.getNote().getNotations().getListOfSlurs().size()==1 &&
							prev.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
						placement = prev.getNote().getNotations().getListOfSlurs().get(0).getPlacement();
					}
					if(prev.getNote().getNotations().getListOfSlurs().size()==1 &&
							prev.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start") &&
							prev.getNote().getGrace()!=null) {
						isgrace = true;
					}
					
					if(placement==null || placement.equalsIgnoreCase("Below")) {
						if(isgrace) {
							if(prev.getY()==current.getY()) {
								middledis = (current.getX() - prev.getX())/2;
								middlex = prev.getX() + middledis;
								middley = prev.getY() + yInc;
								GeneralDrawing.drawQuad(prev.getX(), prev.getY()+half, middlex, middley, current.getX(), current.getY()+half, pane);
							}
							else if(prev.getY()<current.getY()) {
								middledis = (current.getX() - prev.getX())/2;
								middlex = prev.getX() + middledis;
								middley = current.getY() + yInc;
								GeneralDrawing.drawQuad(prev.getX(), prev.getY()+half, middlex, middley, current.getX(), current.getY()+half, pane);	
							}
						}
						else {
							if(prev.getStaffnum()!=current.getStaffnum()) {
								middledis = (maxX - prev.getX())/2;
								middlex = prev.getX() + middledis;
								middley = prev.getY() + yInc;
								GeneralDrawing.drawQuad(prev.getX(), prev.getY()+half, middlex, middley, maxX, prev.getY()+half, pane);
								middledis = current.getX()/2;
								middlex = 0 + middledis;
								middley = current.getY() + yInc;
								GeneralDrawing.drawQuad(0, current.getY()+half, middlex, middley, current.getX(), prev.getY()+half, pane);
							}
							else {
								if(prev.getY()==current.getY()) {
									middledis = (current.getX() - prev.getX())/2;
									middlex = prev.getX() + middledis;
									middley = prev.getY() + yInc;
									GeneralDrawing.drawQuad(prev.getX(), prev.getY()+font, middlex, middley, current.getX(), current.getY()+font, pane);
								}
								else if(prev.getY()<current.getY()) {
									middledis = (current.getX() - prev.getX())/2;
									middlex = prev.getX() + middledis;
									middley = current.getY() + yInc;
									GeneralDrawing.drawQuad(prev.getX(), prev.getY()+font, middlex, middley, current.getX(), current.getY()+font, pane);
								}
							}
						}
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
		double half = 0.5 * yInc;
		
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
							DrawLine dl = new DrawLine(first1.getX()+half, maxbeami.get(staffnum), last.getX()+half, maxbeami.get(staffnum));
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
									double y = maxbeami.get(current.getStaffnum()-1) + (0.5*yInc);
									DrawLine dl = new DrawLine(current2.getX()+half, y, beamlist.get(i).get(temp).getX()+half, y);
									dl.setWidth(0.25*yInc);
									pane.getChildren().add(dl.getLine());
								}
								else if(current2.getType()==((double)1/32)) {
									double y1 = maxbeami.get(current.getStaffnum()-1) + (0.5*yInc);
									DrawLine dl1 = new DrawLine(current2.getX()+half, y1, beamlist.get(i).get(temp).getX()+half, y1);
									dl1.setWidth(0.25*yInc);
									pane.getChildren().add(dl1.getLine());
									double y2 = maxbeami.get(current.getStaffnum()-1) + (1*yInc);
									DrawLine dl2 = new DrawLine(current2.getX()+half, y2, beamlist.get(i).get(temp).getX()+half, y2);
									dl2.setWidth(0.25*yInc);
									pane.getChildren().add(dl2.getLine());
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

	public static void drawdots(ArrayList<measureinfo> m, double xInc, double yInc, Pane pane) {
		NoteAndPos current = null;
		for(int i=0; i<m.size(); i++) {
			if(m.get(i).getMeasure()!=null) {
				for(int j=0; j<m.get(i).getMeasure().size(); j++) {
					current = m.get(i).getMeasure().get(j);
					if(current.getNote().getDot()!=null && current.getNote().getDot().size()>1) {
						int temp=1;
						while(temp<current.getNote().getDot().size()) {
							GeneralDrawing.drawCircle(current.getX()+(0.5*yInc)+(temp+(0.25*xInc)), current.getY(), 0.15*yInc, pane);
							temp++;
						}
					}
					else if(current.getNote().getDot()!=null && current.getNote().getDot().size()==1){
						GeneralDrawing.drawCircle(current.getX()+(0.5*yInc)+(1+(0.25*xInc)), current.getY(), 0.15*yInc, pane);
					}
				}
			}
		}
	}
}

////(2).
////Drawing the stems for each note.
//for(int i=0; i<listofmeasures.size(); i++) {
//	if(listofmeasures.get(i)!=null) {
//		for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
//			current = listofmeasures.get(i).getMeasure().get(j);
//			if(j!=0) {
//				prev = listofmeasures.get(i).getMeasure().get(j-1);
//				//current note has a chord
//				if(current.getNote().getChord()!=null) {
//					//stem is up
//					if(current.getNote().getStem()!=null && current.getNote().getStem().equalsIgnoreCase("up")) {
//						//current has a notehead
//						if(current.getNote().getNotehead()!=null) {
//							//prev has a notehead
//							//prev = x
//							//current = x
//							if(prev.getNote().getNotehead()!=null && 
//							   prev.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
//							   current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
//								GeneralDrawing.drawLine(current.getX()+5, current.getY()-5, prev.getX()+5, prev.getY()-5, pane);
//							}
//							//prev has a notehead
//							//prev = x
//							//current = (o)
//							else if (prev.getNote().getNotehead()!=null && 
//									 prev.getNote().getNotehead().getText().equalsIgnoreCase("x") &&
//									 current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
//								GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY()-5, pane);
//							}
//							//prev does or does not have a notehead
//							//prev = o
//							//OR
//							//prev = (o)
//							//current = (o)
//							else if ((prev.getNote().getNotehead()==null || 
//									  prev.getNote().getNotehead().getText().equalsIgnoreCase("normal")) &&
//									  current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
//								GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY(), pane);
//							}
//						}
//						else if(current.getNote().getNotehead()==null){
//							//prev has a notehead
//							//prev = x
//							//current = o
//							if(prev.getNote().getNotehead()!=null && prev.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
//								GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY()-5, pane);
//							}
//							//prev does or does not have a notehead
//							//prev = o
//							//OR
//							//prev = (o)
//							//current = o
//							else if ((prev.getNote().getNotehead()==null || 
//									  prev.getNote().getNotehead().getText().equalsIgnoreCase("normal"))) {
//								GeneralDrawing.drawLine(current.getX()+5, current.getY(), prev.getX()+5, prev.getY(), pane);
//							}
//						}
//					}
//				}
//				else if(current.getNote().getChord()==null) {
//					if(current.getNote().getStem()!=null && current.getNote().getStem().equalsIgnoreCase("up")) {
//						if(current.getNote().getNotehead()!=null) {
//							if(current.getNote().getNotehead().getText().equalsIgnoreCase("x")) {
//								GeneralDrawing.drawLine(current.getX()+5, current.getY()-5, current.getX()+5, current.getY()-20, pane);
//							}
//							else if(current.getNote().getNotehead().getText().equalsIgnoreCase("normal")) {
//								GeneralDrawing.drawLine(current.getX()+5, current.getY(), current.getX()+5, current.getY()-20, pane);
//							}
//						}
//						else if(current.getNote().getNotehead()==null){
//							GeneralDrawing.drawLine(current.getX()+5, current.getY(), current.getX()+5, current.getY()-20, pane);
//						}
//					}
//				}
//			}
//		}
//	}
//}