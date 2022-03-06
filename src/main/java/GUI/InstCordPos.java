package GUI;

import java.util.ArrayList;
import java.util.List;

import xml.to.sheet.converter.POJOClasses.*;

public class InstCordPos {
	
	public static ArrayList<NoteAndPos> setAndGetNotesPos(String instName, List<Note2> notelist){
		 ArrayList <NoteAndPos> nplist = new ArrayList<NoteAndPos>();
		
		 double x = 48;
	     double xInc = 24;
	     NoteAndPos current;
	     NoteAndPos prev;
		 
		 for(int i=0; i<notelist.size(); i++) {
     		NoteAndPos np = new NoteAndPos(notelist.get(i), x, 0);
     		nplist.add(i, np);
     	}
		//set y for each point for guitar or bass => string of note
		 if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("bass")) {
		     	for(int i=0; i<notelist.size(); i++) {
		     		double ycord = notelist.get(i).getNotations().getTechnical().getString();
		     		nplist.get(i).setY(13 * (ycord-1)+5);
		     	}
		 }
		//set y for each point for drum-set => display-step
		 else if(instName.equalsIgnoreCase("Drumset")) {
			 for(int i=0; i<notelist.size(); i++) {
		     		String step = notelist.get(i).getUnpitched().getDisplaystep();
		     		double ycord = 0;
		     		if(step.equalsIgnoreCase("A")) {
		     			ycord = -13;
		     		}
		     		else if(step.equalsIgnoreCase("C")) {
		     			ycord = 18.5;
		     		}
		     		else if(step.equalsIgnoreCase("G")) {
		     			ycord = -6.5;
		     		}
		     		else if(step.equalsIgnoreCase("F")) {
		     			ycord = 44.5;
		     		}
		     		nplist.get(i).setY(ycord);
		     	}
		 }
     	
     	//set x for each point for guitar and drum-set => (chord)
		if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Drumset")) {
			for(int i=0; i<notelist.size(); i++) {
     			current = nplist.get(i);
     			//skip chord check for first note
     			if(i!=0) {
     				prev = nplist.get(i-1);
     				//if current note has chord then it has same x as previous
     				if(current.getNote().getChord()!=null) {
     					current.setX(prev.getX());
     				}
     				//if current note does not have a chord then it has a different x
     				else {
     				current.setX(prev.getX()+xInc);
     				}
     			}
     		}
		}
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
		
		//set x for each point for bass => duration
//		else if(instName.equalsIgnoreCase("Bass")) {
//			
//		}
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
		return nplist;
	}
}
