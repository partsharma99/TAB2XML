package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class InstCordPos2 {

	public static ArrayList<ArrayList<NoteAndPos>> getListofPositions(ScorePartwise2 sc, String instName, List<Note2> notelist, double xstart, double ystart, 
																													 double xincrement, double yincrement, 
																													 double baseincrement, int diffbwstaves, double maxX,
																													 Pane pane) {
		ArrayList<NoteAndPos> currentmeasure = null;
		NoteAndPos currentnote = null;
		double startx = 0;
		double starty = 0;
		double xInc = 0;
		double yInc = 0;
		double basexInc = 0;
		ArrayList<NoteAndPos> firstnotestaffI = null;
		int staffcounter = 1;
		ArrayList<Double> topofeachstaff = null;
		
		//////////////////////////////////////////////////////////////////////////////
		
		startx = xstart;
		starty = ystart;
		xInc = xincrement;
		yInc = yincrement;
		basexInc = baseincrement;
		
		//////////////////////////////////////////////////////////////////////////////		
		
		int numOfMeasures = ListOfMeasureAndNote.getlistOfMeasures(sc).size();
		
		ArrayList <Note2> measureI = new ArrayList<Note2>(); 
		ArrayList <ArrayList<NoteAndPos>> nplist = new ArrayList<>();
		
		NoteAndPos prev = null;
		NoteAndPos current = null;
		
		ArrayList<barlineinfo> barlineholder= new ArrayList <>();
		int nctotal = 0;
		
		double emptymeasuresize = 100;
		double measuremarker = startx;
		boolean first = false;
		
		int temp = 0;
		double totaldurationI = 0;
		
		double ydiff = 0;
		double tempydiff = 0;
		int numofspaces = 0;
		double tempstarty = 0;
		topofeachstaff = new ArrayList<Double>();
		double lengthofbar = 0;
		
		//////////////////////////////////////////////////////////////////////////////
		
		//Creating array of measures for sheet music.
		for(int i=0; i<numOfMeasures; i++) {
			if(ListOfMeasureAndNote.getNotesInMeasureI(sc, i)==null) {
				nplist.add(i, null);
			}
			else {
				measureI = ListOfMeasureAndNote.getNotesInMeasureI(sc, i);
				ArrayList<NoteAndPos> measurelist = new ArrayList<>();
				for(int j=0; j<measureI.size(); j++) {
					NoteAndPos np = new NoteAndPos(0, 0, i+1, measureI.get(j), startx, 0);
					measurelist.add(j, np);
				}
				nplist.add(i, measurelist);
			}	
		}
		
		//Putting the notes on the corresponding string for the guitar or bass.
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("bass")) {
			for(int i=0; i<nplist.size(); i++) {
				if(nplist.get(i)!=null) {			
					for(int j=0; j<nplist.get(i).size(); j++) {
						double ycord = nplist.get(i).get(j).getNote().getNotations().getTechnical().getString();
						nplist.get(i).get(j).setY(yInc * (ycord-1)+(starty+5));
					}
				}
			}
		}
		
		//Putting the notes in the corresponding spot for the drum-set.
		else if(instName.equalsIgnoreCase("Drumset")) {
			String scoreInstName = "";
			double ycord = 0;
			for(int i=0; i<nplist.size(); i++) {
				if(nplist.get(i)!=null) {
					for(int j=0; j<nplist.get(i).size(); j++) {
						if(nplist.get(i).get(j).getNote().getInstrument()!=null) {
							scoreInstName = nplist.get(i).get(j).getNote().getInstrument().getId();
							if(scoreInstName.equalsIgnoreCase("P1-I50")) {
								ycord = starty-yInc;
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I43")) {
								ycord = starty-(0.5*yInc);
							}
							else if(scoreInstName.equalsIgnoreCase("P1-I52")) {
								ycord = starty-(0*yInc);
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
							nplist.get(i).get(j).setY(ycord);
						}
					}
				}
			}
		}
		ArrayList<measureinfo> staffholder = new ArrayList<>();	
		//Setting the x-values for the notes for the guitar or drum-set.
		//Creating the ArrayList of bar lines corresponding to each measure.
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Drumset")) {
			for(int i=0; i<nplist.size(); i++) {
				if(nplist.get(i)!=null) {
					//current non-empty measure
					currentmeasure = nplist.get(i);
					for(int j=0; j<nplist.get(i).size(); j++) {
						//current note in non-empty measure
						currentnote = nplist.get(i).get(j);
						//total number of notes in current measure that are not chorded
						nctotal=getNumberofnonchorded(currentmeasure);
						//the measure will fit in the staff
						if((measuremarker)+(nctotal*xInc)<=maxX) {
							//first measure is non-empty
							//first note in current first measure
							if(!first) {
								currentnote.setX(measuremarker+xInc);
								barlineholder.add(new barlineinfo(0, staffcounter, 0, "full", 0));
								first=true;
							}
							//first note in current measure (not first note in first measure)
							else if(j==0) {
								currentnote.setX(measuremarker+xInc);
							}
							//not first note in current measure
							else {
								//note is a chord
								if(currentnote.getNote().getChord()!=null) {
									currentnote.setX(nplist.get(i).get(j-1).getX());
								}
								//note is not a chord
								else if(currentnote.getNote().getChord()==null) {
									currentnote.setX(nplist.get(i).get(j-1).getX()+xInc);
								}
							}
							//last note in measure
							//end of measure so we add bar line corresponding to this completed measure
							//add measure on current staff to staff holder
							if(j+1==currentmeasure.size()) {
								measuremarker=currentnote.getX()+xInc;
								barlineholder.add(new barlineinfo(0, staffcounter, 0, "full", measuremarker));
								staffholder.add(new measureinfo(currentmeasure, barlineholder.get(barlineholder.size()-1)));	
							}
						}
						//the measure will not fit into the staff
						//first note of measure is an xInc away from bar line
						else {
							resizestaff(staffholder, startx, xInc, maxX);
							barlineholder.add(new barlineinfo(0, staffcounter, -1, "not", maxX));
							measuremarker=startx;
							currentnote.setX(measuremarker+xInc);
							staffcounter++;
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "full", 0));
						}
						currentnote.setStaffnum(staffcounter);
					}
				}
				else if(nplist.get(i)==null) {
					//empty measure is first measure
					if(!first) {
						measuremarker=emptymeasuresize;
						barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", 0));
						barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", measuremarker));
						first=true;
					}
					//empty measure is not first measure
					else {
						//empty measure fits into staff
						if(measuremarker+emptymeasuresize<=maxX) {
							measuremarker+=emptymeasuresize;
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", measuremarker));
						}
						//empty measure does not fit into staff
						else {
							barlineholder.add(new barlineinfo(0, staffcounter, -1, "not", maxX));
							measuremarker=emptymeasuresize;
							staffcounter++;
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", 0));
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", measuremarker));	
						}
					}
				}
			}
			startx = startx +xInc;
			barlineholder.add(new barlineinfo(0, staffcounter, -1, "not", maxX));
		}
		
		//Setting the x-values for the notes for the bass.
		//Creating the ArrayList of bar lines corresponding to each measure.
		if(instName.equalsIgnoreCase("Bass")) {
			for(int i=0; i<nplist.size(); i++) {
				if(nplist.get(i)!=null) {
					for(int j=0; j<nplist.get(i).size(); j++) {
						//current non-empty measure
						currentmeasure = nplist.get(i);
						//current note in non-empty measure
						currentnote = nplist.get(i).get(j);
						//total number of notes in current measure that are not chorded
						nctotal=getNumberofnonchorded(currentmeasure);
						//total duration of the notes in the current measure
						totaldurationI=getTotalduration(currentmeasure);
						//the measure will fit in the staff
						if((measuremarker+basexInc)+((nctotal*basexInc)+totaldurationI)<=maxX) {
							//first measure is non-empty
							//first note in current first measure
							if(!first) {
								currentnote.setX(measuremarker+basexInc);
								barlineholder.add(new barlineinfo(0, staffcounter, 0, "full", 0));
								first=true;
							}
							//first note in current measure (not first note in first measure)
							else if(j==0) {
								currentnote.setX(measuremarker+basexInc);
							}
							//not first note in current measure
							else {
								//note is a chord
								if(currentnote.getNote().getChord()!=null) {
									currentnote.setX(nplist.get(i).get(j-1).getX());
								}
								//note is not a chord
								else if(currentnote.getNote().getChord()==null) {
									currentnote.setX(nplist.get(i).get(j-1).getX()+(nplist.get(i).get(j-1).getNote().getDuration()+basexInc));
								}
							}
							//last note in measure
							//end of measure so we add bar line corresponding to this completed measure
							if(j+1==currentmeasure.size()) {
								measuremarker=currentnote.getX()+currentnote.getNote().getDuration()+basexInc;
								barlineholder.add(new barlineinfo(0, staffcounter, 0, "full", measuremarker));
							}
						}
						//the measure will not fit into the staff
						//first note of measure is an xInc away from bar line
						else {
							barlineholder.add(new barlineinfo(0, staffcounter, -1, "not", maxX));
							measuremarker=startx;
							currentnote.setX(measuremarker+basexInc);
							staffcounter++;
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "full", 0));
						}
						currentnote.setStaffnum(staffcounter);
					}
				}
				else if(nplist.get(i)==null) {
					//empty measure is first measure
					if(!first) {
						measuremarker=emptymeasuresize;
						barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", 0));
						barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", measuremarker));
						first=true;
					}
					//empty measure is not first measure
					else {
						//empty measure fits into staff
						if(measuremarker+emptymeasuresize<=maxX) {
							measuremarker+=emptymeasuresize;
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", measuremarker));
						}
						//empty measure does not fit into staff
						else {
							barlineholder.add(new barlineinfo(0, staffcounter, -1, "not", maxX));
							measuremarker=emptymeasuresize;
							staffcounter++;
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", 0));
							barlineholder.add(new barlineinfo(0, staffcounter, 0, "empty", measuremarker));
						}
					}
				}
			}
			startx = startx + basexInc;
			barlineholder.add(new barlineinfo(0, staffcounter, -1, "not", maxX));
		}
		
		//Removing the unnecessary bar lines.
		barlineinfo prevbar = null;
		barlineinfo currentbar = null;
		for(int i=0; i<barlineholder.size(); i++) {
			currentbar = barlineholder.get(i);
			if(i!=0) {
				prevbar = barlineholder.get(i-1);
				//end bar line of staff
				if(currentbar.getMeasureNum()==-1) {
//					//previous bar line does not end at end of staff and the previous bar line is not for an empty measure
//					if(prevbar.getXpos()!=currentbar.getXpos() && !(prevbar.getEmptyorfullornot().equalsIgnoreCase("empty"))) {
//						currentbar.setMeasureNum(prevbar.getMeasureNum());
//						barlineholder.remove(prevbar);
//						
//					}
//					//previous bar line does not end at end of staff and the previous bar line is for an empty measure
//					else if(prevbar.getXpos()!=currentbar.getXpos() && prevbar.getEmptyorfullornot().equalsIgnoreCase("empty")) {
//						barlineholder.remove(currentbar);
//					}
					//previous bar does not complete at the end of staff
					if(prevbar.getXpos()!=currentbar.getXpos()) {
						//previous measure completes after staff
						//set it to end at the end of staff
						//do not change it's measure number
						if(prevbar.getXpos()>maxX) {
							prevbar.setXpos(maxX);							
						}
						//previous measure completes before staff
						//do not set it to end at the end of staff
						//change it's measure number
						else {
							prevbar.setMeasureNum(-1);
						}
						barlineholder.remove(currentbar);
					}
					//previous bar completes at the end of staff
					else if(prevbar.getXpos()==currentbar.getXpos()) {
						barlineholder.remove(currentbar);
					}
				}
			}
		}
		
		
		
//		//Resetting the measure number for each end bar
//		for(int i=0; i<barlineholder.size(); i++) {
//			currentbar = barlineholder.get(i);
//			if(i!=0) {
//				prevbar = barlineholder.get(i-1);
//				if(currentbar.getXpos()==0 && prevbar.getXpos())
//			}
//		}
		
		//Setting the measure number for each bar line.
		temp = 1;
		for(int i=0; i<barlineholder.size(); i++) {
			if(barlineholder.get(i).getMeasureNum()!=-1) {
				barlineholder.get(i).setMeasureNum(temp);
			}
			else {
				temp--;
			}
			temp++;
		}
		
		//Shifting the notes that are graces, closer together for the guitar or drum-set.
		if(instName.equalsIgnoreCase("Guitar") || (instName.equalsIgnoreCase("Drumset"))) {
			for(int i=0; i<nplist.size(); i++) {
				if(nplist.get(i)!=null) {
					for(int j=0; j<nplist.get(i).size(); j++) {
						current=nplist.get(i).get(j);
						if(j!=0) {
							prev=nplist.get(i).get(j-1);
							//current = no grace AND previous = grace
							// => make current closer to previous
							if(current.getNote().getGrace()==null && prev.getNote().getGrace()!=null) {
								current.setX(prev.getX()+(0.5*xInc));
							}

							//current = grace AND previous = no grace
							// => they should maintain their separated distance	(NOTHING HAPPENS)	        			
							else if(current.getNote().getGrace()!=null && prev.getNote().getGrace()==null) {}

							//current = grace AND previous = grace		        			
							// => must check other conditions
							else if(current.getNote().getGrace()!=null && prev.getNote().getGrace()!=null) {

								//current note has chord as well
								//=> current note should be under previous note
								if(current.getNote().getChord()!=null) {
									current.setX(prev.getX());
								}

								//current note does not have chord
								//=> current note should move close to previous note
								else {
									current.setX(prev.getX()+(0.5*xInc));
								}
							}

							//current = no grace AND previous = no grace
							else if(current.getNote().getGrace()==null && prev.getNote().getGrace()==null) {

								//current note has a chord
								//=> current note should be under previous
								if(current.getNote().getChord()!=null) {
									current.setX(prev.getX());	 
								}

								//current note does not have a chord
								//and both do not have a grace so current can be left alone
								else {}
							}
						}
					}
				}
			}
		}
		
		//Shifting the notes that are graces, closer together for the bass.
		else if(instName.equalsIgnoreCase("Bass")) {
			for(int i=0; i<nplist.size(); i++) {
				if(nplist.get(i)!=null) {
					for(int j=0; j<nplist.get(i).size(); j++) {
						current=nplist.get(i).get(j);
						if(j!=0) {
							prev=nplist.get(i).get(j-1);
							//current = no grace AND previous = grace
							// => make current closer to previous
							if(current.getNote().getGrace()==null && prev.getNote().getGrace()!=null) {
								current.setX(prev.getX()+(0.5*(prev.getNote().getDuration()+basexInc)));
							}

							//current = grace AND previous = no grace
							// => they should maintain their separated distance	(NOTHING HAPPENS)	        			
							else if(current.getNote().getGrace()!=null && prev.getNote().getGrace()==null) {}

							//current = grace AND previous = grace		        			
							// => must check other conditions
							else if(current.getNote().getGrace()!=null && prev.getNote().getGrace()!=null) {

								//current note has chord as well
								//=> current note should be under previous note
								if(current.getNote().getChord()!=null) {
									current.setX(prev.getX());
								}

								//current note does not have chord
								//=> current note should move close to previous note
								else {
									current.setX(prev.getX()+(0.5*(prev.getNote().getDuration()+basexInc)));
								}
							}

							//current = no grace AND previous = no grace
							else if(current.getNote().getGrace()==null && prev.getNote().getGrace()==null) {

								//current note has a chord
								//=> current note should be under previous
								if(current.getNote().getChord()!=null) {
									current.setX(prev.getX());	 
								}

								//current note does not have a chord
								//and both do not have a grace so current can be left alone
								else {}
							}
						}
					}
				}
			}
		}
		
		//Setting the difference between each of the first lines of the different staves.
		//Setting the length of the bar line corresponding to the instrument.
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
		
		//Setting the y-value of each note, given that there may be multiple staves.
		for(int i=0; i<nplist.size(); i++) {
			if(nplist.get(i)!=null) {
				for(int j=0; j<nplist.get(i).size(); j++) {
					current = nplist.get(i).get(j);
					tempydiff = ydiff * (current.getStaffnum()-1);
					tempstarty = starty + tempydiff;
					current.setY(current.getY()+tempydiff);
					current.setTopofstaff(tempstarty);
				}
			}
		}
		
		//Setting the y-value for each bar line, given that there may be multiple staves.
		for(int i=0; i<barlineholder.size(); i++) {
			tempydiff = ydiff * (barlineholder.get(i).getStaffnum()-1);
			tempstarty = starty + tempydiff;
			barlineholder.get(i).setTopofstaff(tempstarty);
		}
		
		//Helps draw the instrument lines.
		drawhelper1(barlineholder, instName, yInc, maxX, pane);
		
		//Helps draw the bar lines.
		drawhelper2(barlineholder, instName, yInc, pane);
		
		//Helps draw the measure number above the bar lines.
		drawHelper3(barlineholder, yInc, pane);
		
		return nplist;
	}
	
	private static void resizestaff(ArrayList<measureinfo> staffholder, double startx, double xInc, double maxX) {
		ArrayList<NoteAndPos> currentmeasure = null;
		NoteAndPos currentnote = null;
		barlineinfo currentendof = null;
		double diff = maxX - staffholder.get(staffholder.size()-1).getEndof().getXpos();
		double shift = 0;
		boolean first = false;
		for(int i=0; i<staffholder.size(); i++) {
			currentmeasure = staffholder.get(i).getMeasure();
			currentendof = staffholder.get(i).getEndof();
			if(!currentmeasure.isEmpty()) {
				for(int j=0; j<currentmeasure.size(); j++) {
					currentnote = currentmeasure.get(j);
					//note is the first entity in staff
					//do not move it (do nothing)
					if(!first) {
						first=true;
					}
					//note is not the first entity in staff
					//shift every note in measure from its current position by diff
					else {
						//accounts for all notes that may be chorded to the first note
						//do not move (do nothing)
						if(currentnote.getX()==(startx+xInc)) {}
						//notes that are not chorded to the first note are shifted by diff
						else {
							currentnote.setX(currentnote.getX()+diff);
						}
						//last note in measure (must shift the end bar line)
						if(j+1==currentmeasure.size()) {
							currentendof.setXpos(currentendof.getXpos()+diff);
						}
					}
				}
			}
			//current measure is empty
			else {
				//first measure is empty
				if(!first) {
					first=true;
				}
			}
		}
		
	}

	private static double getTotalduration(ArrayList<NoteAndPos> currentMeasure) {
		double total = 0;
		for(int i=0; i<currentMeasure.size(); i++) {
			total+=currentMeasure.get(i).getNote().getDuration();
		}
		return total;
	}

	private static int getNumberofnonchorded(ArrayList<NoteAndPos> currentMeasure) {
		int count = 0;
		for(int i=0; i<currentMeasure.size(); i++) {
			if(currentMeasure.get(i).getNote().getChord()==null) {
				count++;
			}
		}
		return count;
	}

	private static void drawhelper1(ArrayList<barlineinfo> barlineholder, String instName, double yInc, double maxX, Pane pane) {
		GeneralDrawing.drawInstLinesHelper(barlineholder, instName, yInc, maxX, pane);
	}
	
	private static void drawhelper2(ArrayList<barlineinfo> barlineholder, String instName, double yInc, Pane pane) {
		GeneralDrawing.drawBarLinesHelper(barlineholder, instName, yInc, pane);
	}
	
	private static void drawHelper3(ArrayList<barlineinfo> barlineholder, double yInc, Pane pane) {
		for(int i=0; i<barlineholder.size(); i++) {
			if(barlineholder.get(i).getMeasureNum()!=-1) {
				GeneralDrawing.drawNotes(barlineholder.get(i).getXpos()-2, barlineholder.get(i).getTopofstaff()-(0.5*yInc), Integer.toString(barlineholder.get(i).getMeasureNum()), 10, pane);
			}
		}
	}

}
