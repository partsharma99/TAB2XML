package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class InstCordPos2 {

	public static ArrayList<NoteAndPos> getListofPositions(ScorePartwise2 sc, String instName, List<Note2> notelist, double xstart, double ystart, 
																													 double xincrement, double yincrement, 
																													 double baseincrement, int diffbwstaves, double maxX,
																													 Pane pane) {
		
		double startx = 0;
		double starty = 0;
		double xInc = 0;
		double yInc = 0;
		double basexInc = 0;
		ArrayList<NoteAndPos> firstnotestaffI = null;
		int staffcounter = 0;
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
		ArrayList <NoteAndPos> nplist = new ArrayList<NoteAndPos>();
		
		NoteAndPos prev = null;
		NoteAndPos current = null;
		
		double measuremarker = startx;
		firstnotestaffI = new ArrayList<>();
		
		int temp = 0;
		double durationtotalI = 0;
		double tempdurationtotalI = 0;
		int index = 0;
		
		double ydiff = 0;
		double tempydiff = 0;
		int numofspaces = 0;
		double tempstarty = 0;
		topofeachstaff = new ArrayList<Double>(); 
		
		//////////////////////////////////////////////////////////////////////////////
		
		for(int i=0; i<notelist.size(); i++) {
			NoteAndPos np = new NoteAndPos(0, 0, notelist.get(i), startx, 0);
			nplist.add(i, np);;
		}
		
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("bass")) {
			for(int i=0; i<notelist.size(); i++) {
				double ycord = notelist.get(i).getNotations().getTechnical().getString();
				nplist.get(i).setY(13 * (ycord-1)+(starty+5));
			}
		}

		else if(instName.equalsIgnoreCase("Drumset")) {
			String scoreInstName = "";
			double ycord = 0;
			for(int i=0; i<notelist.size(); i++) {
				if(notelist.get(i).getInstrument()!=null) {
					scoreInstName = notelist.get(i).getInstrument().getId();
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
					nplist.get(i).setY(ycord);
				}
			}
		}
		
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Drumset")) {
			for(int i=0; i<numOfMeasures; i++) {
				if(ListOfMeasureAndNote.getNotesInMeasureI(sc, i)==null) {
					measuremarker+=(3 * xInc);
					continue;
				}
				else {
					measureI = ListOfMeasureAndNote.getNotesInMeasureI(sc, i);
				}
				for(int j=0; j<measureI.size(); j++) {
					if(j==0) {
						if((measuremarker+(measureI.size()*xInc))<=maxX){
							if(index==0) {
								nplist.get(index).setX(startx);
								firstnotestaffI.add(nplist.get(index));
							}
							else {
								if(nplist.get(index).getNote().getChord()!=null) {
									nplist.get(index).setX(nplist.get(index-1).getX());
								}
								else {
									nplist.get(index).setX(nplist.get(index-1).getX()+xInc);
								}
							}
						}
						else {
							nplist.get(index).setX(startx);
							firstnotestaffI.add(nplist.get(index-1));
							firstnotestaffI.add(null);
							firstnotestaffI.add(nplist.get(index));
						}
					}
					else {
						if(nplist.get(index).getNote().getChord()!=null) {
							nplist.get(index).setX(nplist.get(index-1).getX());
						}
						else {
							nplist.get(index).setX(nplist.get(index-1).getX()+xInc);
						}
					}
					if(j+1==measureI.size()) {
						measuremarker = nplist.get(index).getX()+xInc;
					}
					if(i+1==numOfMeasures && j+1==measureI.size()) {
						firstnotestaffI.add(nplist.get(index));
						firstnotestaffI.add(null);
					}
					nplist.get(index).setMeasureNum(i);
					index++;
				}
			}
		}
		
		else if(instName.equalsIgnoreCase("bass")) {
			for(int i=0; i<numOfMeasures; i++) {
				if(ListOfMeasureAndNote.getNotesInMeasureI(sc, i)==null) {
					measuremarker+=(3*(nplist.get(index-2).getNote().getDuration()+basexInc));
					continue;
				}
				else {
					measureI = ListOfMeasureAndNote.getNotesInMeasureI(sc, i);
					while(temp<measureI.size()) {
						durationtotalI+=measureI.get(temp).getDuration();
						temp++;
					}
					tempdurationtotalI = durationtotalI;
					durationtotalI=0;
					temp=0;
				}
				for(int j=0; j<measureI.size(); j++) {
					if(j==0) {
						if((measuremarker+((measureI.size()*basexInc) + tempdurationtotalI))<=maxX){
							if(index==0) {
								nplist.get(index).setX(startx);
								firstnotestaffI.add(nplist.get(index));
							}
							else {
								if(nplist.get(index).getNote().getChord()!=null) {
									nplist.get(index).setX(nplist.get(index-1).getX());
								}
								else {
									nplist.get(index).setX(nplist.get(index-1).getX() + 
											(nplist.get(index-1).getNote().getDuration()+basexInc));
								}
							}
						}
						else {
							nplist.get(index).setX(startx);
							firstnotestaffI.add(nplist.get(index-1));
							firstnotestaffI.add(null);
							firstnotestaffI.add(nplist.get(index));
						}
					}
					else {
						if(nplist.get(index).getNote().getChord()!=null) {
							nplist.get(index).setX(nplist.get(index-1).getX());
						}
						else {
							nplist.get(index).setX(nplist.get(index-1).getX() + 
									(nplist.get(index-1).getNote().getDuration()+basexInc));
						}
					}
					if(j+1==measureI.size()) {
						measuremarker = nplist.get(index).getX() + (nplist.get(index).getNote().getDuration()+basexInc);
					}
					if(i+1==numOfMeasures && j+1==measureI.size()) {
						firstnotestaffI.add(nplist.get(index));
						firstnotestaffI.add(null);
					}
					nplist.get(index).setMeasureNum(i);
					index++;
				}
			}
		}
		
		if(instName.equalsIgnoreCase("Guitar") || (instName.equalsIgnoreCase("Drumset"))) {
			for(int i=0; i<notelist.size(); i++) {
				current = nplist.get(i);

				//skip grace check for first note
				if(i!=0) {
					prev = nplist.get(i-1);

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
		
		else if(instName.equalsIgnoreCase("Bass")) {
			for(int i=0; i<notelist.size(); i++) {
				current = nplist.get(i);

				//skip grace check for first note
				if(i!=0) {
					prev = nplist.get(i-1);

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
		
		if(instName.equalsIgnoreCase("Guitar")) {
			numofspaces = 5 + diffbwstaves;
			ydiff = numofspaces*yInc;
		}
		else if(instName.equalsIgnoreCase("Drumset")) {
			numofspaces = 4 + diffbwstaves;
			ydiff = numofspaces*yInc;
		}
		else if(instName.equalsIgnoreCase("Bass")) {
			numofspaces = 3 + diffbwstaves;
			ydiff = numofspaces*yInc;
		}
		
		ArrayList<ArrayList<NoteAndPos>> stafflist = new ArrayList<>();
		
		for(int i=0; i<firstnotestaffI.size(); i++) {
			current = firstnotestaffI.get(i);
			if(i!=0) {
				prev = firstnotestaffI.get(i-1);
				if(prev!=null && current!=null) {
					int first = nplist.indexOf(prev);
					int last = nplist.indexOf(current);
					for(int j=first; j<=last; j++) {
						if(j==first) {
							tempydiff = ydiff * staffcounter;
							tempstarty = starty + tempydiff;
							topofeachstaff.add(tempstarty);
							for(int t=first; t<=last; t++) {
								nplist.get(t).setTopofstaff(tempstarty);
							}
							ArrayList<NoteAndPos> notesinstaffi = new ArrayList<>();
							for(int t=first; t<=last; t++) {
								notesinstaffi.add(nplist.get(t));
							}
							stafflist.add(staffcounter, notesinstaffi);
						}
						nplist.get(j).setY(nplist.get(j).getY()+tempydiff);
					}
					staffcounter++;
				}
			}
		}
		
		drawhelper(topofeachstaff, instName, yInc, maxX, pane);
		drawhelper2(stafflist, instName, xInc, yInc, maxX, pane);
		return nplist;
	}
	
	private static void drawhelper(ArrayList<Double> topofeachstaff, String instName, double yInc, double maxX, Pane pane) {
		GeneralDrawing.drawInstLinesHelper(topofeachstaff, instName, yInc, maxX, pane);
	}
	
	private static void drawhelper2(ArrayList<ArrayList<NoteAndPos>> stafflist, String instName, double xInc, double yInc, double maxX, Pane pane) {
		GeneralDrawing.drawBarLinesHelper(stafflist, instName, xInc, yInc, maxX, pane);
	}

}
