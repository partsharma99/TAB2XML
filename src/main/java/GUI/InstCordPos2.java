package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.Part2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class InstCordPos2 {

	public static ArrayList<measureinfo> getListofPositions(ScorePartwise2 sc, String instName, List<Note2> notelist, double xstart, double ystart, 
																													  double xincrement, double yincrement, 
																													  double baseincrement, int diffbwstaves, double maxX,
																													  Pane pane) {
		//(1)
		//Create and initialize the primary note positioning variables.
		//////////////////////////////////////////////////////////////////////////////
		double startx = 0;
		double starty = 0;
		double xInc = 0;
		double yInc = 0;
		startx = xstart;
		starty = ystart;
		xInc = xincrement;
		yInc = yincrement;
		//////////////////////////////////////////////////////////////////////////////
		
		//(2)
		//Create and initialize helper variables for the note positioning.
		//////////////////////////////////////////////////////////////////////////////
		
		//a)
		//Pointers to note and measures
		NoteAndPos currentn = null;
		NoteAndPos prevn = null;
		measureinfo currentm = null;
		measureinfo prevm = null;
		//////////////////////////////////////////////////////////////
		
		//b)
		//Helper variables for horizontal drawing component.
		int staffcounter = 1;
		int nctotal = 0;
		double totaldurationI = 0;
		double measuremarker = startx;
		boolean first = false;
		double emptymeasuresize = 100;
		int temp = 0;
		double distance = 0;
		//////////////////////////////////////////////////////////////
		
		//c)
		//Helper variables for vertical drawing component.
		double ycord = 0;
		double ydiff = 0;
		int numofspaces = 0;
		double lengthofbar = 0;
		double tempstarty = 0;
		double tempydiff = 0;
		//////////////////////////////////////////////////////////////////////////////
		
		//(3)
		//Arraylist holders: hold information to be used to help position and draw notes and other visual components
		ArrayList<Part2> partlist = new ArrayList<>(sc.getListOfParts());
		ArrayList<measureinfo> listofmeasures = new ArrayList<>();
		ArrayList<NoteAndPos> measurelist = null;
		ArrayList <measureinfo> staffholder = new ArrayList<>();
		ArrayList<barlineinfo> barlineholder= new ArrayList <>();
		ArrayList<barlineinfo> topofstaff = new ArrayList<>();
		ArrayList<Double> maxbeami = new ArrayList<>();
		//////////////////////////////////////////////////////////////////////////////
		
		//(4)
		//Getting all the measures that are inside the sheet music.	
		for(int i=0; i<partlist.size(); i++) {
			if(partlist.get(i)!=null && partlist.get(i).getListOfMeasures()!=null) {
				for(int j=0; j<partlist.get(i).getListOfMeasures().size(); j++) {
					if(partlist.get(i).getListOfMeasures().get(j)!=null) {
						if(partlist.get(i).getListOfMeasures().get(j).getListOfNotes()!=null) {
							measurelist = new ArrayList<>();
							for(int k=0; k<partlist.get(i).getListOfMeasures().get(j).getListOfNotes().size(); k++) {
								measurelist.add(new NoteAndPos(0, i+1, 0, 0, partlist.get(i).getListOfMeasures().get(j).getListOfNotes().get(k), 0, 0, 0));
							}
							listofmeasures.add(new measureinfo(i+1, measurelist, null, null));
						}
						else {
							listofmeasures.add(new measureinfo(i+1, null, null, null));
						}
					}
				}
			}
		}
		
		//(5.1)
		//*Conditional step*
		//Placing guitar or bass notes on there strings
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("bass")) {
			for(int i=0; i<listofmeasures.size(); i++) {
				if(listofmeasures.get(i).getMeasure()!=null) {			
					for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
						ycord = listofmeasures.get(i).getMeasure().get(j).getNote().getNotations().getTechnical().getString();
						listofmeasures.get(i).getMeasure().get(j).setY(yInc * (ycord-1)+(starty+5));
					}
				}
			}
		}
		
		//(5.2)
		//*Condiotnal step*
		//Placing the drum notes according to there id.
		else if(instName.equalsIgnoreCase("Drumset")) {
			String scoreInstName = "";
			for(int i=0; i<listofmeasures.size(); i++) {
				if(listofmeasures.get(i).getMeasure()!=null) {
					for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
						if(listofmeasures.get(i).getMeasure().get(j).getNote().getInstrument()!=null) {
							scoreInstName = listofmeasures.get(i).getMeasure().get(j).getNote().getInstrument().getId();
							if(scoreInstName.equalsIgnoreCase("P1-I50")) {
								ycord = starty-yInc;
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I43")) {
								ycord = starty-(0.5*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I52")) {
								ycord = starty+(0*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I48")) {
								ycord = starty+(0.5*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I46")) {
								ycord = starty+yInc;
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I39")) {
								ycord = starty+(1.5*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I44")) {
								ycord = starty+(2.5*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I42")) {
								ycord = starty+(3*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I36")) {
								ycord = starty+(3.5*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I45")) {
								ycord = starty+(4.5*yInc);
							}
							listofmeasures.get(i).getMeasure().get(j).setY(ycord);
						}
					}
				}
			}
		}
		
		//(6)
		//Setting the types for each of the notes.
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					if(listofmeasures.get(i).getMeasure().get(j).getNote().getType().equalsIgnoreCase("whole")) {
						listofmeasures.get(i).getMeasure().get(j).setType((double)1);
					}
					if(listofmeasures.get(i).getMeasure().get(j).getNote().getType().equalsIgnoreCase("half")) {
						listofmeasures.get(i).getMeasure().get(j).setType((double)1/2);
					}
					else if(listofmeasures.get(i).getMeasure().get(j).getNote().getType().equalsIgnoreCase("quarter")) {
						listofmeasures.get(i).getMeasure().get(j).setType((double)1/4);
					}
					else if(listofmeasures.get(i).getMeasure().get(j).getNote().getType().equalsIgnoreCase("eighth")) {
						listofmeasures.get(i).getMeasure().get(j).setType((double)1/8);
					}
					else if(listofmeasures.get(i).getMeasure().get(j).getNote().getType().equalsIgnoreCase("16th")) {
						listofmeasures.get(i).getMeasure().get(j).setType((double)1/16);
					}
					else if(listofmeasures.get(i).getMeasure().get(j).getNote().getType().equalsIgnoreCase("32nd")){
						listofmeasures.get(i).getMeasure().get(j).setType((double)1/32);
					}
				}
			}
		}
		
		//(7)
		//Setting the x-cordinate for each of the notes.
		for(int i=0; i<listofmeasures.size(); i++) {
			measurelist = listofmeasures.get(i).getMeasure();
			if(measurelist!=null) {
				nctotal = getNumberofnonchorded(measurelist);
				if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
					totaldurationI = getTotalduration(measurelist);
				}
				else if (instName.equalsIgnoreCase("Drumset")){
					totaldurationI = 0;
				}
				for(int j=0; j<measurelist.size(); j++) {
					currentn = measurelist.get(j);
					//do not know if staff is in perfect condition
					//add to staff holder until case 2 or case 3 are met
					if(measuremarker+xInc+(nctotal*xInc)+totaldurationI<=maxX) {
						//first entity
						if(!first) {
							currentn.setX(measuremarker+xInc);
							listofmeasures.get(i).setStartof(new barlineinfo(0, staffcounter, i+1, "full", 0));
							first = true;
						}
						else {
							//start of new measure is on the same staff
							//position first note after previous measure
							if(j==0) {
								currentn.setX(measuremarker+xInc);
								listofmeasures.get(i).setStartof(new barlineinfo(0, staffcounter, i+1, "full", measuremarker));
							}
							//current note is not that start of new measure
							else {
								//current note is a chord
								if(currentn.getNote().getChord()!=null) {
									currentn.setX(listofmeasures.get(i).getMeasure().get(j-1).getX());
								}
								//current note is not a chord
								else {
									currentn.setX(measurelist.get(j-1).getX() + measurelist.get(j-1).getNote().getDuration() + xInc);
								}
							}
						}
						//last note in measure
						//set end of measure bar line
						if(j+1==measurelist.size()) {
							measuremarker = currentn.getX() + currentn.getNote().getDuration() + xInc;
							listofmeasures.get(i).setEndof(new barlineinfo(0, staffcounter, i+1, "full", measuremarker));
							staffholder.add(listofmeasures.get(i));
						}
					}
					//staff is not in perfect condition
					//this means that the measures in the staff do not fill it up 
					else {
						//entire measure does not fit - need to resize it
						if(staffholder.isEmpty()) {
							listofmeasures.get(i).setEndof(new barlineinfo(0, staffcounter, i+1, "full", maxX));
							resizemeasure(listofmeasures.get(i), instName, xInc, true);
							staffholder.add(listofmeasures.get(i));
						}
						else {
							resizestaff(staffholder, instName, startx, xInc, emptymeasuresize, maxX);
						}
						staffholder.clear();
						staffholder = new ArrayList<measureinfo>();
						measuremarker = startx;
						staffcounter++;
						currentn.setX(measuremarker+xInc);
						listofmeasures.get(i).setStartof(new barlineinfo(0, staffcounter, i+1, "full", 0));
					}
				}
			}
			else {
				if(!first) {
					measuremarker = emptymeasuresize;
					listofmeasures.get(i).setStartof(new barlineinfo(0, staffcounter, i+1, "empty", 0));
					listofmeasures.get(i).setEndof(new barlineinfo(0, staffcounter, i+1, "empty", measuremarker));
					staffholder.add(listofmeasures.get(i));
					first = true;
				}
				else {
					if(measuremarker+emptymeasuresize<=maxX) {
						listofmeasures.get(i).setStartof(new barlineinfo(0, staffcounter, i+1, "empty", measuremarker));
						measuremarker+=emptymeasuresize;
						listofmeasures.get(i).setEndof(new barlineinfo(0, staffcounter, i+1, "empty", measuremarker));
						staffholder.add(listofmeasures.get(i));
					}
					else {
						resizestaff(staffholder, instName, startx, xInc, emptymeasuresize, maxX);
						staffholder.clear();
						staffholder = new ArrayList<measureinfo>();
						staffcounter++;
						measuremarker = emptymeasuresize;
						listofmeasures.get(i).setStartof(new barlineinfo(0, staffcounter, i+1, "empty", 0));
						listofmeasures.get(i).setEndof(new barlineinfo(0, staffcounter, i+1, "empty", measuremarker));
						staffholder.add(listofmeasures.get(i));
					}
				}
			}
		}
		//(7.1)
		//*Conditional Step*
		//Setting end barline of last measure if it is not empty
		if(listofmeasures.get(listofmeasures.size()-1)!=null) {
			listofmeasures.get(listofmeasures.size()-1).setEndof(new barlineinfo(0,
							   listofmeasures.get(listofmeasures.size()-1).getStartof().getStaffnum(), listofmeasures.size(), "full", maxX));
		}
		//(7.2)
		//*Conditional Step*
		//Setting end barline of last measure if it is empty
		else {
			listofmeasures.get(listofmeasures.size()-1).setEndof(new barlineinfo(0, 
					           listofmeasures.get(listofmeasures.size()-1).getStartof().getStaffnum(), listofmeasures.size(), "empty", maxX));
		}

		//(8)
		//Realligning any misalligned barlines in spite of resizing each measure in each staff.
		for(int i=0; i<listofmeasures.size(); i++) {
			currentm = listofmeasures.get(i);
			if(i!=0) {
				prevm = listofmeasures.get(i-1);
				if(prevm.getEndof().getXpos()!=maxX && currentm.getStartof().getXpos()!=0) {
					currentm.getStartof().setXpos(prevm.getEndof().getXpos());
				}
			}
		}
		
		//(9.1)
		//Setting the measure number for each of the notes
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					listofmeasures.get(i).getMeasure().get(j).setMeasureNum(i+1);
				}
			}
		}
		
		//(9.2)
		//Setting the staff number for each of the notes. 
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				temp = listofmeasures.get(i).getStartof().getStaffnum();
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					listofmeasures.get(i).getMeasure().get(j).setStaffnum(temp);
				}
			}
		}

		//(10)
		//Shifting all grace notes closer together.
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					currentn = listofmeasures.get(i).getMeasure().get(j);
					if(j!=0) {
						prevn = listofmeasures.get(i).getMeasure().get(j);
						distance = currentn.getX() - prevn.getX();
						//current = no grace AND previous = grace
						// => make current closer to previous
						if(currentn.getNote().getGrace()==null && prevn.getNote().getGrace()!=null) {
							currentn.setX(prevn.getX()+(0.5*distance));
						}

						//current = grace AND previous = no grace
						// => they should maintain their separated distance	(NOTHING HAPPENS)	        			
						else if(currentn.getNote().getGrace()!=null && prevn.getNote().getGrace()==null) {}

						//current = grace AND previous = grace		        			
						// => must check other conditions
						else if(currentn.getNote().getGrace()!=null && prevn.getNote().getGrace()!=null) {

							//current note has chord as well
							//=> current note should be under previous note
							if(currentn.getNote().getChord()!=null) {
								currentn.setX(prevn.getX());
							}

							//current note does not have chord
							//=> current note should move close to previous note
							else {
								currentn.setX(prevn.getX()+(0.5*distance));
							}
						}

						//current = no grace AND previous = no grace
						else if(currentn.getNote().getGrace()==null && prevn.getNote().getGrace()==null) {

							//current note has a chord
							//=> current note should be under previous
							if(currentn.getNote().getChord()!=null) {
								currentn.setX(prevn.getX());	 
							}

							//current note does not have a chord
							//and both do not have a grace so current can be left alone
							else {}
						}
					}
				}
			}
		}
		
		//(11)
		//Setting up an arraylist to hold the necessary barlines.
		for(int i=0; i<listofmeasures.size(); i++) {
			//add start and end barlines for each measure that is the end measure of a staff
			if(listofmeasures.get(i).getEndof().getXpos()==maxX) {
				barlineholder.add(listofmeasures.get(i).getStartof());
				barlineholder.add(listofmeasures.get(i).getEndof());
			}
			else {
				barlineholder.add(listofmeasures.get(i).getStartof());
			}
		}
		
		//(12)
		//Setting the number of spaces, the length of each bar line, and the difference b/w each of the top lines in each staff.
		if(instName.equalsIgnoreCase("Guitar")) {
			numofspaces = 5 + diffbwstaves;
			ydiff = numofspaces*yInc;
			lengthofbar = 5 * yInc;
		}
		else if(instName.equalsIgnoreCase("Drumset")) {
			numofspaces = 4 + diffbwstaves;
			ydiff = numofspaces*yInc;
			lengthofbar = 4 * yInc;
		}
		else if(instName.equalsIgnoreCase("Bass")) {
			numofspaces = 3 + diffbwstaves;
			ydiff = numofspaces*yInc;
			lengthofbar = 3 * yInc;
		}
		
		//(13)
		//Setting the y-cordinate for each note, given that there may be multiple staves.
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					currentn = listofmeasures.get(i).getMeasure().get(j);
					tempydiff = ydiff * (currentn.getStaffnum()-1);
					tempstarty = starty + tempydiff;
					currentn.setY(currentn.getY()+tempydiff);
					currentn.setTopofstaff(tempstarty);
				}
			}
		}
		//(14)
		//Setting the y-cordinate for each bar line, given that there may be multiple staves.
		for(int i=0; i<barlineholder.size(); i++) {
			tempydiff = ydiff * (barlineholder.get(i).getStaffnum()-1);
			tempstarty = starty + tempydiff;
			barlineholder.get(i).setTopofstaff(tempstarty);
		}
		
		//(15)
		//Setting up an arraylist to hold the top y-coordinate for each of the top lines in each staff.
		for(int i=0; i<barlineholder.size(); i++) {
			//reached new staff so add
			if(barlineholder.get(i).getXpos()==0) {
				topofstaff.add(barlineholder.get(i));
			}
		}
		
		//(16.1)
		//*Condiotional step*
		//Setting up an arraylist that holds the middle y-cordinate between staves in the guitar and bass sheet music.
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
			for(int i=0; i<topofstaff.size(); i++) {
				if(i+1<topofstaff.size()) {
					double top1 = topofstaff.get(i).getTopofstaff() + lengthofbar;
					double top2 = topofstaff.get(i+1).getTopofstaff();
					double middis = (top2 - top1)/2;
					maxbeami.add(top1 + middis);
				}
				else {
					double top1 = topofstaff.get(i).getTopofstaff() + lengthofbar;
					double top2 = topofstaff.get(i).getTopofstaff() + ydiff;
					double middis = (top2 - top1)/2;
					maxbeami.add(top1 + middis);
				}
			}
		}
		
		//(16.2)
		//*Condiotional step*
		//Setting up an arraylist that holds the middle y-cordinate between staves in the drumset sheet music.
		else if(instName.equalsIgnoreCase("Drumset")) {
			for(int i=0; i<topofstaff.size(); i++) {
				if(i==0) {
					double top1 = topofstaff.get(i).getTopofstaff();
					double top2 = topofstaff.get(i).getTopofstaff() + lengthofbar - ydiff;
					double middis = (top1 - top2)/2;
					maxbeami.add(top1 - middis);
				}
				else {
					double top1 = topofstaff.get(i).getTopofstaff();
					double top2 = topofstaff.get(i-1).getTopofstaff() + lengthofbar;
					double middis = (top1 - top2)/2;
					maxbeami.add(top1 - middis);
				}
			}
		}		
		
    	ArrayList<ArrayList<NoteAndPos>> beamlist = ComponentClass.getBeamList(listofmeasures, sc, instName, pane);
		NoteAndPos first1 = null;
		NoteAndPos last = null;
		int staffnum = 0;
		
		for(int i=0; i<listofmeasures.size(); i++) {
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
						GeneralDrawing.drawLine(first1.getX(), maxbeami.get(staffnum), last.getX(), maxbeami.get(staffnum), pane);
						j = temp + 2;
					}
					else {
						j++;
					}
				}
			}
		}
		
		//(17)
		//Helps draw the instrument lines.
		drawhelper1(barlineholder, instName, yInc, maxX, pane);
		
		//(18)
		//Helps draw the bar lines.
		drawhelper2(barlineholder, lengthofbar, pane);
		
		//(19)
		//Helps draw the measure number above the bar lines.
		drawHelper3(barlineholder, yInc, maxX, pane);
		
		partlist = null;
		measurelist = null;
		staffholder = null;
		barlineholder= null;
		topofstaff = null;
		maxbeami = null;
		
		return listofmeasures;
	}
	
	//Hepler Method 1: Gets total duration of notes in a single measure.
	private static double getTotalduration(ArrayList<NoteAndPos> currentMeasure) {
		double total = 0;
		for(int i=0; i<currentMeasure.size(); i++) {
			if(currentMeasure.get(i).getNote().getChord()==null){
				total+=currentMeasure.get(i).getNote().getDuration();
			}
		}
		return total;
	}
	
	//Hepler Method 2: Gets total number of non-chorded notes in a single measure.
	private static int getNumberofnonchorded(ArrayList<NoteAndPos> currentMeasure) {
		int count = 0;
		for(int i=0; i<currentMeasure.size(); i++) {
			if(currentMeasure.get(i).getNote().getChord()==null) {
				count++;
			}
		}
		return count;
	}
	
	//Helper Method 3: Helps draw instrument lines.
	private static void drawhelper1(ArrayList<barlineinfo> barlineholder, String instName, double yInc, double maxX, Pane pane) {
		GeneralDrawing.drawInstLinesHelper(barlineholder, instName, yInc, maxX, pane);
	}
	
	//Helper Method 4: Helps draw barlines.
	private static void drawhelper2(ArrayList<barlineinfo> barlineholder, double lengthofbar, Pane pane) {
		GeneralDrawing.drawBarLinesHelper(barlineholder, lengthofbar, pane);
	}
	
	//Helper Method 5: Helps draw measure numbers.
	private static void drawHelper3(ArrayList<barlineinfo> barlineholder, double yInc, double maxX, Pane pane) {
		for(int i=0; i<barlineholder.size(); i++) {
			if(barlineholder.get(i).getXpos()!=maxX) {
				GeneralDrawing.drawNotes(barlineholder.get(i).getXpos()-2, barlineholder.get(i).getTopofstaff()-(0.5*yInc), Integer.toString(barlineholder.get(i).getMeasureNum()), 10, pane);
			}
		}
	}
	
	//Helper Method 6: Helps resize measure.
	private static void resizemeasure(measureinfo m, String instName, double xInc, boolean firstinstaff) {
		int nctotal = getNumberofnonchorded(m.getMeasure());
		double dur = getTotalduration(m.getMeasure());
		NoteAndPos current = null;
		double temp=0;
		
		if(nctotal==1) {
			if(!firstinstaff) {
				m.getMeasure().get(0).setX(m.getStartof().getXpos()+xInc);
			}
			if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
				m.getEndof().setXpos(m.getMeasure().get(m.getMeasure().size()-1).getX() + 
				m.getMeasure().get(m.getMeasure().size()-1).getNote().getDuration() + xInc);
			}
			else {
				m.getEndof().setXpos(m.getMeasure().get(m.getMeasure().size()-1).getX() + xInc);
			}
		}

		for(int i=0; i<m.getMeasure().size(); i++) {
			current = m.getMeasure().get(i);
			//first note in measure
			if(i==0 && !firstinstaff){		
				current.setX(m.getStartof().getXpos()+xInc);
			}
			else if(i!=0 && !firstinstaff){
				//current note is a chord
				if(current.getNote().getChord()!=null) {
					current.setX(m.getMeasure().get(i-1).getX());
				}
				//current note is note a chord
				else {
					if(instName.equals("Guitar") || instName.equalsIgnoreCase("Bass")) {
						current.setX(m.getMeasure().get(i-1).getX()+m.getMeasure().get(i-1).getNote().getDuration()+xInc);
					}
					else {
						current.setX(m.getMeasure().get(i-1).getX()+xInc);
					}
				}
			}
		}
		
		if(instName.equalsIgnoreCase("Drumset")) {
			dur=0;
		}
		
		if(nctotal>1) {
			while(m.getMeasure().get(0).getX()+((nctotal*temp)+dur)<=m.getEndof().getXpos()) {
				temp++;
			}
			temp--;

			for(int i=0; i<m.getMeasure().size(); i++) {
				current = m.getMeasure().get(i);
				if(i==0) {}
				else {
					if(current.getNote().getChord()!=null) {
						current.setX(m.getMeasure().get(i-1).getX());
					}
					else {
						if(instName.equals("Guitar") || instName.equalsIgnoreCase("Bass")) {
							current.setX(m.getMeasure().get(i-1).getX()+m.getMeasure().get(i-1).getNote().getDuration()+temp);
						}
						else {
							current.setX(m.getMeasure().get(i-1).getX()+temp);
						}
					}
				}
			}
		}
	}
	
	//Helper Method 7: Helps resize staff.
	private static void resizestaff(ArrayList<measureinfo> staffholder, String instName, double startx, double xInc, double emptymeasuresize, double maxX) {
		int nonempty = 0;
		double addtoeachm = 0;
		measureinfo prev = null;
		measureinfo current = null;
		double prevendof = 0;
		boolean entered = false;
		boolean firstinstaff = false;
		double diff = maxX - staffholder.get(staffholder.size()-1).getEndof().getXpos();
		if(diff!=0) {
			for(int i=0; i<staffholder.size(); i++) {
				if(staffholder.get(i).getMeasure()!=null) {
					nonempty++;
				}
				if(!entered) {
					entered = true;
				}
			}
			//contains some non-empty mesaures in the staff
			if(nonempty>0) {
				ArrayList<Double> sizelist = new ArrayList<>();
				addtoeachm = diff / nonempty;
				for(int i=0; i<staffholder.size(); i++) {
					current = staffholder.get(i);
					sizelist.add(current.getEndof().getXpos()-current.getStartof().getXpos());
				}

				for(int i=0; i<staffholder.size(); i++) {
					current = staffholder.get(i);
					if(i!=0) {
						prev = staffholder.get(i-1);
						//both full
						if(prev.getMeasure()!=null && current.getMeasure()!=null) {
							current.getStartof().setXpos(prevendof);
							current.getEndof().setXpos(current.getStartof().getXpos()+sizelist.get(i)+addtoeachm);
							resizemeasure(current, instName, xInc, firstinstaff);
							prevendof = current.getEndof().getXpos();
						}
						//full and empty
						else if(prev.getMeasure()!=null && current.getMeasure()==null) {
							current.getStartof().setXpos(prevendof);
							current.getEndof().setXpos(current.getStartof().getXpos()+emptymeasuresize);
							prevendof = current.getEndof().getXpos();
						}
						//empty and full
						else if(prev.getMeasure()==null && current.getMeasure()!=null) {
							current.getStartof().setXpos(prevendof);
							current.getEndof().setXpos(current.getStartof().getXpos()+sizelist.get(i)+addtoeachm);
							resizemeasure(current, instName, xInc, firstinstaff);
							prevendof = current.getEndof().getXpos();
						}
						//both empty
						else if(prev.getMeasure()==null && current.getMeasure()==null) {
							current.getStartof().setXpos(prevendof);
							current.getEndof().setXpos(current.getStartof().getXpos()+emptymeasuresize);
							prevendof = current.getEndof().getXpos();
						}
					}
					else {
						if(current.getMeasure()!=null) {
							current.getEndof().setXpos(current.getEndof().getXpos()+addtoeachm);
							resizemeasure(current, instName, xInc, !firstinstaff);
							prevendof = current.getEndof().getXpos();
						}
						else {
							prevendof = current.getEndof().getXpos();
						}
					}
				}
			}
			//contains only empty measures which we will resize
			else if(nonempty==0 && entered){
				addtoeachm = diff / staffholder.size();
				for(int i=0; i<staffholder.size(); i++) {
					current = staffholder.get(i);
					if(i!=0) {
						prev = staffholder.get(i-1);
						current.getStartof().setXpos(prev.getEndof().getXpos());
						current.getEndof().setXpos(current.getStartof().getXpos()+emptymeasuresize+addtoeachm);
					}
					else {
						current.getEndof().setXpos(current.getEndof().getXpos()+addtoeachm);
					}
				}
			}
		}
		//the difference is negligible
		current.getEndof().setXpos(maxX);
	}

}
