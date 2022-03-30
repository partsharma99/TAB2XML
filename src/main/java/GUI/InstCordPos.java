package GUI;

import java.util.ArrayList;
import java.util.List;

import xml.to.sheet.converter.POJOClasses.*;

public class InstCordPos {
	
	public static ArrayList<NoteAndPos> setAndGetNotesPos(String instName, List<Note2> notelist, double maxX){
		 ArrayList <NoteAndPos> nplist = new ArrayList<NoteAndPos>();
		 ArrayList <NoteAndPos> originalGuitarPos = new ArrayList<NoteAndPos>();
		
		 double startx = 48;
	     double xInc = 18;
	     NoteAndPos current;
	     NoteAndPos prev;
	     NoteAndPos current2;
	     NoteAndPos prev2;
		 
	     //all notes start at a point of (48,0)
		 for(int i=0; i<notelist.size(); i++) {
     		NoteAndPos np = new NoteAndPos(0, 0, notelist.get(i), startx, 0);
     		NoteAndPos ogP = new NoteAndPos(0, 0, notelist.get(i), startx, 0);
     		nplist.add(i, np);
     		originalGuitarPos.add(i, ogP);
		 }
		 
		 //set y for each point for guitar or bass => string of note
		 if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("bass")) {
		     	for(int i=0; i<notelist.size(); i++) {
		     		double ycord = notelist.get(i).getNotations().getTechnical().getString();
		     		nplist.get(i).setY(13 * (ycord-1)+5);
		     		originalGuitarPos.get(i).setY(13 * (ycord-1)+5);
		     	}
		 }
		 
		 //set y for each point for drum-set => display-step
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
     	
     	 //set x for each point for guitar and drum-set => (chord)
		 if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Drumset")) {
			 for(int i=0; i<notelist.size(); i++) {
				 current = nplist.get(i);
				 current2 = originalGuitarPos.get(i);
				 //skip chord check for first note
				 if(i!=0) {
					 prev = nplist.get(i-1);
					 prev2 = originalGuitarPos.get(i-1);
					 //if current note has chord then it has same x as previous
					 if(current.getNote().getChord()!=null) {
						 current.setX(prev.getX());
						 current2.setX(prev2.getX());
						 }
					 //if current note does not have a chord then it has a different x
					 else {
						 current.setX(prev.getX()+xInc);
						 current2.setX(prev2.getX()+xInc);
						 }
					 }
				 }
			 }
		 
		 //set x for each point for bass => duration
		 else if(instName.equalsIgnoreCase("Bass")){
			 double basexInc = 10;
			 for(int i=0; i<notelist.size(); i++) {
				 current = nplist.get(i);
				 //skip chord check for first note
				 if(i!=0) {
					 prev = nplist.get(i-1);
					 current.setX(prev.getX() +(basexInc + prev.getNote().getDuration()));
					 }
				 }
			 }

		 //set x for each point (grace)
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
		 
		 //shifting the guitar notes for when there are graces so then the horizontal distance between notes is the same
		 if(instName.equalsIgnoreCase("Guitar")) {
			 shiftGuitar2Original(nplist, originalGuitarPos);
		 }
		 
		 //will store all the first notes in each staff
		 ArrayList <NoteAndPos> firstnotestaff = new ArrayList<NoteAndPos>();
		 int first = 0;
		 int firstnew = 0;
		 double xdiff = 0;
		 double ydiff = 0;
		 firstnotestaff.add(nplist.get(0));
		 int j = 1;
		 for(int i=0; i<nplist.size(); i++) {
			 current = nplist.get(i);
			 if(current.getX()>=(maxX*j)) {
				 firstnotestaff.add(current);
				 j++;
			 }
		 }
		 
		 //will shift all the notes from the first to the last note in each staff by some multiple of the difference between
		 //the first notes x position and the starting x position
		 //iterating through the first notes in each staff
		 for(j=0; j<firstnotestaff.size(); j++) {
			 if(j!=0) {
				 
				 //first = index of the first note in the current staff (inclusive lower bound)
				 //first new = index of the first note in the next staff (exclusive upper bound)
				 first = nplist.indexOf(firstnotestaff.get(j-1));
				 firstnew = nplist.indexOf(firstnotestaff.get(j));
				 
				 //start from first of current staff to the last of current staff (i<firstnew means we do shift up until we reach the last note
				 //of current staff)
				 //change the start of the first note 
				 for(int i=first; i<firstnew; i++) {
					 current = nplist.get(i);
					 if(i==first) {
						 xdiff = current.getX()-startx;
					 }
					 if(current.getX()-xdiff>=maxX) {
						 firstnotestaff.add(j, current);
						 firstnotestaff.remove(j+1);
						 break; 	 
					 }
					 else {
						 current.setX(current.getX()-xdiff);	 
					 }
				 }
			 }
		 }
		 
		 //will shift all the notes downwards a distance of ydiff from the 1st string in the 1st staff
		 for(j=0; j<firstnotestaff.size(); j++) {
			 if(j!=0) {
				 
				 //first = index of the first note in the current staff (inclusive lower bound)
				 //first new = index of the first note in the next staff (exclusive upper bound)
				 first = nplist.indexOf(firstnotestaff.get(j-1));
				 firstnew = nplist.indexOf(firstnotestaff.get(j));
				 
				 //start from first of current staff to the last of current staff (i<firstnew means we do shift up until we reach the last note
				 //of current staff)
				 for(int i=first; i<firstnew; i++) {
					 current = nplist.get(i);
					 if(i==first) {
						 ydiff = (104 * (j-1));
					 }
					 current.setY(current.getY()+ydiff);
				 }
			 }
		 }
		 
		 
		return nplist;
	}
	
	private static void shiftGuitar2Original(ArrayList<NoteAndPos> nplist, ArrayList<NoteAndPos> originalGuitarPos) {
		double shift = 0;
		int indexofshift = 0;
		boolean shiftoccurs = false;
		NoteAndPos npelement = null;
		NoteAndPos ogpelement = null;
		for(int i=0; i<nplist.size(); i++) {
			npelement = nplist.get(i);
			ogpelement = originalGuitarPos.get(i);
			if(npelement.getX()!=ogpelement.getX()) {
				shift = ogpelement.getX() - npelement.getX();
				indexofshift = i;
				shiftoccurs = true;
				break;
			}
		}
		
		if(shiftoccurs) {
			//shift occurs at a note with a grace
			if(nplist.get(indexofshift).getNote().getGrace()!=null) {
				int n = indexofshift + 1;
				while(n<nplist.size() && (
						//current note has a grace
						//having a chord or not in this case does not matter in this case
						(nplist.get(n).getNote().getGrace()!=null) ||
						//current note does not have a grace
						//current note does not have a chord
						//previous note has a grace
						(nplist.get(n).getNote().getGrace()==null && 
						 nplist.get(n).getNote().getChord()==null && nplist.get(n-1).getNote().getGrace()!=null) ||
						//current note has a chord
						(nplist.get(n).getNote().getChord()!=null))) {
					n++;
				}
				for(int i=n; i<nplist.size(); i++) {
					nplist.get(i).setX(nplist.get(i).getX()-shift);
				}
			}
			else if(nplist.get(indexofshift).getNote().getGrace()==null){
				int n = indexofshift + 1;
				//find the next note that is connected that is not a chord
				//that will be where the shift starts from
				while(n<nplist.size() &&
					  nplist.get(n).getNote().getChord()!=null) {
					n++;
				}
				for(int i=n; i<nplist.size(); i++) {
					nplist.get(i).setX(nplist.get(i).getX()-shift);
				}
			}
		}
	}
}
