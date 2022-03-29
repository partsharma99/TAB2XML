package GUI;

import java.util.ArrayList;
import java.util.List;

import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class InstCordPos2 {

	private static double startx = 66;
	private static double xInc = 24;
	private static double basexInc = 10;
	private static ArrayList<NoteAndPos> onlyfirst = null;
	private static ArrayList<NoteAndPos> firstnotestaffI = null;

	public static ArrayList<NoteAndPos> getListofPositions(ScorePartwise2 sc, String instName, List<Note2> notelist, double maxX) {
		int numOfMeasures = ListOfMeasureAndNote.getlistOfMeasures(sc).size();
		ArrayList <Note2> measureI = new ArrayList<Note2>(); 
		ArrayList <NoteAndPos> nplist = new ArrayList<NoteAndPos>();
		NoteAndPos prev = null;
		NoteAndPos current = null;

		for(int i=0; i<notelist.size(); i++) {
			NoteAndPos np = new NoteAndPos(notelist.get(i), startx, 0);
			nplist.add(i, np);;
		}

		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("bass")) {
			for(int i=0; i<notelist.size(); i++) {
				double ycord = notelist.get(i).getNotations().getTechnical().getString();
				nplist.get(i).setY(13 * (ycord-1)+5);
			}
		}

		else if(instName.equalsIgnoreCase("Drumset")) {
			String scoreInstName = "";
			double ycord = 0;
			for(int i=0; i<notelist.size(); i++) {
				if(notelist.get(i).getInstrument()!=null) {
					scoreInstName = notelist.get(i).getInstrument().getId();
					if(scoreInstName.equalsIgnoreCase("P1-I50")) {
						ycord = -13;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I43")) {
						ycord = -6.5;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I52")) {
						ycord = 0;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I48")) {
						ycord = 6.5;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I46")) {
						ycord = 13;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I39")) {
						ycord = 19.5;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I44")) {
						ycord = 32.5;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I42")) {
						ycord = 39;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I36")) {
						ycord = 45.5;
					}
					else if(scoreInstName.equalsIgnoreCase("P1-I45")) {
						ycord = 58.5;
					}
					nplist.get(i).setY(ycord);
				}
			}
		}
		
		double measuremarker = startx;
		onlyfirst = new ArrayList<>();
		firstnotestaffI = new ArrayList<>();
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Drumset")) {
			int index = 0;
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
								onlyfirst.add(nplist.get(index));
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
							onlyfirst.add(nplist.get(index));
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
					index++;
				}
			}
		}
		else if(instName.equalsIgnoreCase("bass")) {
			int temp = 0;
			double durationtotalI = 0;
			double tempdurationtotalI = 0;
			int index = 0;
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
								onlyfirst.add(nplist.get(index));
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
							onlyfirst.add(nplist.get(index));
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
					index++;
				}
			}
		}

		for(int i=0; i<notelist.size(); i++) {
			current = nplist.get(i);

			//skip grace check for first note
			if(i!=0) {
				prev = nplist.get(i-1);

				//current = no grace AND previous = grace
				// => make current closer to previous
				if(current.getNote().getGrace()==null && prev.getNote().getGrace()!=null) {
					current.setX(prev.getX()+12);
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
						current.setX(prev.getX()+12);
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
		
		double ydiff = 0;
		double staffcounter = 1;
		for(int i=0; i<firstnotestaffI.size(); i++) {
			current = firstnotestaffI.get(i);
			if(i!=0) {
				prev = firstnotestaffI.get(i-1);
				if(prev!=null && current!=null) {
					int first = nplist.indexOf(prev);
					int last = nplist.indexOf(current);
					for(int j=first; j<=last; j++) {
						if(j==first) {
							ydiff = (104 * (staffcounter-1));
						}
						nplist.get(j).setY(nplist.get(j).getY()+ydiff);
					}
					staffcounter++;
				}
			}
		}

		return nplist;
	}
}
