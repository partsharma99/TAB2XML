package GUI;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import models.measure.Measure;
import xml.to.sheet.converter.POJOClasses.Measure2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.Time2;

public class ComponentClass {
	
	public static ArrayList<NoteAndPos> getGracList(ArrayList<measureinfo> listofmeasures){
		NoteAndPos current = null;
    	NoteAndPos prev = null;
    	ArrayList<NoteAndPos> gracelist = new ArrayList<NoteAndPos>();
    	
    	for(int i=0; i<listofmeasures.size(); i++) {
    		if(listofmeasures.get(i).getMeasure()!=null) {
    			for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
    				current = listofmeasures.get(i).getMeasure().get(j);
    				if(j!=0) {
    					prev = listofmeasures.get(i).getMeasure().get(j-1);
    					if(current.getNote().getGrace()!=null) {
    						gracelist.add(current);
    					}
    					else if(current.getNote().getGrace()==null && prev.getNote().getGrace()!=null) {
    						gracelist.add(current);
    						int l = i + 1;
    						while(l<listofmeasures.get(i).getMeasure().size() && listofmeasures.get(i).getMeasure().get(l).getNote().getChord()!=null) {
    							gracelist.add(current);
    							l++;
    						}
    						gracelist.add(null);
    						i=l-1;
    					}
    				}
    			}
    		}
    		else {
    			if(current.getNote().getGrace()!=null) {
    				gracelist.add(current);
    			}
    		}
    	}
    	return gracelist;
//    		current = nplist.get(i);
//    		if(i!=0) {
//    			prev = nplist.get(i-1);
//    			//current note has a grace (do not need to check previous note)
//    			//add current note
//    			if(current.getNote().getGrace()!=null) {
//    				gracelist.add(current);
//    			}
//    			//current does not have a grace (need to check previous note)
//    			//previous note has grace 
//    			//add current note
//    			//check to see if it is the last in the grace group by checking to see if the next note has a chord
//    			//if next note has a chord keep checking
//    			//if next note does not have a chord then end the search and add null to grace list to show end of grace group
//    			else if(current.getNote().getGrace()==null && prev.getNote().getGrace()!=null) {
//    				gracelist.add(current);
//    				int l = i + 1;
//    				while(l<nplist.size() && nplist.get(l).getNote().getChord()!=null) {
//    					gracelist.add(nplist.get(l));
//    					l++;
//    				}
//    				gracelist.add(null);
//    				i=l-1;
//    			}
//    		}
//    		else {
//    			if(current.getNote().getGrace()!=null) {
//    				gracelist.add(current);
//    			}
//    		}
//    	}
//    	return gracelist;
	}
	
	public static ArrayList<NoteAndPos> getTieList(ArrayList<ArrayList<NoteAndPos>> nplist, String instName){
		NoteAndPos current = null;
		NoteAndPos next = null;
		ArrayList<NoteAndPos> tiedlist = new ArrayList<NoteAndPos>();
    	ArrayList<NoteAndPos> orderedtiedlist = new ArrayList<NoteAndPos>();
    	for(int i=0; i<nplist.size(); i++) {
    		if(nplist.get(i)!=null) {
    			for(int j=0; j<nplist.get(i).size(); j++) {
    				current = nplist.get(i).get(j);
    				if(current.getNote().getNotations()!=null && 
    			       current.getNote().getNotations().getListOfTieds()!=null &&
    			       current.getNote().getRest()==null) {
    					tiedlist.add(current);
    				}
    			}
    		}
    	}
    	
    	
    	
//    	for(int k=0; k<nplist.size(); k++) {
//    		current = nplist.get(k);
//    		if(current.getNote().getNotations()!=null && 
//    		   current.getNote().getNotations().getListOfTieds()!=null &&
//    		   current.getNote().getRest()==null) {
//    			tiedlist.add(current);
//    		}
//    	}
    	
    	//sorting the list of tied notes (adjacent elements must have the same string value for guitar
    	for(int i = 0; i<tiedlist.size(); i++) {
    		current = tiedlist.get(i);
    		
    		//current note has a tied and it is a start
    		if(current.getNote().getNotations().getListOfTieds().size()==1 &&
    		   current.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("start")) {
    			
    			//add current note that has a tied and is a start into the ordered tied list
    			//find all the notes that have a tied that are linked to current and add them to the ordered tied list in the
    			//order that they appear
    			//start finding from the next note in the tied list
    			
    			orderedtiedlist.add(current);
    			
    			int l = i + 1;
    			while(l<tiedlist.size()) {
    				next = tiedlist.get(l);
    				
    				//if the next note has already been added to the ordered tied list then skip the check
    				if(!(orderedtiedlist.contains(next))) {
    					
    					//if the current note and the next note are not on the same string then do nothing (do not add the next note)
    					if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
    						if(current.getNote().getNotations().getTechnical().getString()==
    								next.getNote().getNotations().getTechnical().getString()) {

    							//if next note is on the same string and is a start and stop then add
    							if(next.getNote().getNotations().getListOfTieds().size()==2 &&
    									next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop") &&
    									next.getNote().getNotations().getListOfTieds().get(1).getType().equalsIgnoreCase("start")){
    								orderedtiedlist.add(next);
    							}
    							//if next note is on the same string and is a stop then add
    							//after this is reached that means that all the notes that are tied have been added in the same order
    							//break from the loop to then stop the check as it is completed
    							else if(next.getNote().getNotations().getListOfTieds().size()==1 &&
    									next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop")){
    								orderedtiedlist.add(next);
    								orderedtiedlist.add(null);
    								break;		
    							}
    						}
    					}
    					else if(instName.equalsIgnoreCase("Drumset")) {
    						if(current.getNote().getInstrument().getId().equalsIgnoreCase(next.getNote().getInstrument().getId())) {

    							//if next note is on the same string and is a start and stop then add
    							if(next.getNote().getNotations().getListOfTieds().size()==2 &&
    									next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop") &&
    									next.getNote().getNotations().getListOfTieds().get(1).getType().equalsIgnoreCase("start")){
    								orderedtiedlist.add(next);
    							}
    							//if next note is on the same string and is a stop then add
    							//after this is reached that means that all the notes that are tied have been added in the same order
    							//break from the loop to then stop the check as it is completed
    							else if(next.getNote().getNotations().getListOfTieds().size()==1 &&
    									next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop")){
    								orderedtiedlist.add(next);
    								orderedtiedlist.add(null);
    								break;		
    							}
    						}
    					}
    				}
    				l++;
    			}
    		}
    	}
    	return orderedtiedlist;
	}
	
	public static ArrayList<NoteAndPos> getSlurList(ArrayList<measureinfo> listofmeasures, String instName){
		NoteAndPos next = null;
		NoteAndPos current = null;
		//setting the list of slurs
    	ArrayList<NoteAndPos> onlyslurlist = new ArrayList<NoteAndPos>();
    	ArrayList<NoteAndPos> orderedslurlist = new ArrayList<NoteAndPos>();
    	for(int i=0; i<listofmeasures.size(); i++) {
    		if(listofmeasures.get(i).getMeasure()!=null) {
    			for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
    				current = listofmeasures.get(i).getMeasure().get(j);
    				if(instName.equalsIgnoreCase("Guitar") || instName.equalsIgnoreCase("Bass")) {
    					if(current.getNote().getNotations()!=null &&
    							current.getNote().getNotations().getListOfSlurs()!=null &&
    							current.getNote().getGrace()==null) {
    						onlyslurlist.add(current);
    					}
    				}
    				else if(instName.equalsIgnoreCase("Drumset")) {
    					if(current.getNote().getNotations()!=null && 
    							current.getNote().getNotations().getListOfSlurs()!=null) {
    						onlyslurlist.add(current);
    					}
    				}
    			}
    		}
    	}
    	
    	for(int i=0; i<onlyslurlist.size(); i++) {
    		current = onlyslurlist.get(i);
    		
    		//only checking for starting slurs
    		if(current.getNote().getNotations().getListOfSlurs().size()==1 &&
    		   current.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
    			
    			orderedslurlist.add(current);
    			
    			int l = i + 1;
    			while(l<onlyslurlist.size()) {
    				next = onlyslurlist.get(l);
    				
    				//if the next note has already been added to the ordered slur list then skip the check
    				if(!(orderedslurlist.contains(next))) {
    							        						
    					//if next note is stop and start then add
    					//the search for the last note in the slur continues
    					if(next.getNote().getNotations().getListOfSlurs().size()==2 &&
    					   next.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start") &&
    					   next.getNote().getNotations().getListOfSlurs().get(1).getType().equalsIgnoreCase("stop")){
    						orderedslurlist.add(next);
    					}
    					//if next note is a stop then add
    					//after this is reached that means that all the notes that are tied have been added in the same order
    					//break from the loop to then stop the check as it is completed
    					else if(next.getNote().getNotations().getListOfSlurs().size()==1 &&
    							next.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("stop")){
    						orderedslurlist.add(next);
    						orderedslurlist.add(null);
	        				break;		
    					}
    				}
    				l++;
    			}
    		}
    	}
    	return orderedslurlist;
	}
	
	public static ArrayList<ArrayList<NoteAndPos>> getBeamList(ArrayList<measureinfo> listofmeasures, ScorePartwise2 sc, String instName, Pane pane){
		ArrayList<ArrayList<NoteAndPos>> beamlist = new ArrayList<>();;
		double totalbeats=0;
		double worth1=0;
		NoteAndPos first=null;
		NoteAndPos last=null;
		int temp=0;
		//iterating through each measure
		for(int i=0; i<listofmeasures.size(); i++) {
			
			//non-empty measure is beamed
			if(listofmeasures.get(i).getMeasure()!=null) {
				
				//extracting total beats in measure and the note type that is worth a single beat
				//in the case that this branch is not reached we call beamhelper method with previous 
				if(sc.getListOfParts().get(listofmeasures.get(i).getMeasure().get(0).
				   getPartnum()-1).getListOfMeasures().get(i).getAttributes().getTime()!=null) {
					
					totalbeats = (double) sc.getListOfParts().get(listofmeasures.get(i).getMeasure().get(0).
								 getPartnum()-1).getListOfMeasures().get(i).getAttributes().getTime().getBeats();
					worth1 = 1 / (double) sc.getListOfParts().get(listofmeasures.get(i).getMeasure().get(0).
							 getPartnum()-1).getListOfMeasures().get(i).getAttributes().getTime().getBeattype();
				}
				getbeamlisthelper(totalbeats, worth1, listofmeasures, beamlist, instName, i);
			}
			
			else {
				beamlist.add(null);
			}
		}
		for(int i=0; i<listofmeasures.size(); i++) {
			if(beamlist.get(i)!=null) {
				for(int j=0; j<beamlist.get(i).size();) {
					first = beamlist.get(i).get(j);
 					if(first!=null) {
						last = first;
						temp = j;
						while(temp<beamlist.get(i).size() && beamlist.get(i).get(temp)!=null) {
							temp++;
						}
						temp--;
						last = beamlist.get(i).get(temp);
						GeneralDrawing.drawLine(first.getX(), first.getY()-50, last.getX(), first.getY()-50, pane);
						j = temp + 2;
					}
					else {
						j++;
					}
				}
			}
		}
		return beamlist;
	}

	private static void getbeamlisthelper(double totalbeats, double worth1, ArrayList<measureinfo> listofmeasures, ArrayList<ArrayList<NoteAndPos>> beamlist, String instName, int indexm) {
		int holder=0;
		double count=0;
		boolean passcheck=false;
		NoteAndPos current = null;
		ArrayList<NoteAndPos> tempslurlist = ComponentClass.getSlurList(listofmeasures, instName);
		ArrayList<NoteAndPos> measurei = new ArrayList<>();
		
		for(int i=0; i<listofmeasures.get(indexm).getMeasure().size(); i++) {
			current = listofmeasures.get(indexm).getMeasure().get(i);
			//count is reached means beam group is formed
			//we add null to seperate the beam groups in each measure
			//count is reset in order to form the next group
			if(count==1) {
				measurei.add(null);
				count=0;
			}
			//special case where we are inside a beam group but the count has surpassed the worth1 value
			//may be the case where we have a quarter a half and then a quarter
			else if(count>1 && count%worth1==0) {
				measurei.add(null);
				count=0;
			}
			//non chorded notes, grace notes, and rests can only be beamed
			if(current.getNote().getChord()==null && current.getNote().getGrace()==null && current.getNote().getRest()==null) {
				
				//only stop slurs can be beamed: determine if it is a slur, and in the case that it is determine if it is a stop slur
				if(ComponentClass.getSlurList(listofmeasures, instName).contains(current)) {
					holder = ComponentClass.getSlurList(listofmeasures, instName).indexOf(current);
					
					//it is a stop slur
					if(tempslurlist.get(holder).getNote().getNotations().getListOfSlurs().size()==1 &&
					   tempslurlist.get(holder).getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("stop")) {
						
						passcheck=true;	
					}
					//it is not a stop slur
					else if(tempslurlist.get(holder).getNote().getNotations().getListOfSlurs().size()!=1 || 
							!tempslurlist.get(holder).getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("stop")) {
						
						passcheck=false;
					}
				}
				//not a slur
				else if (!ComponentClass.getSlurList(listofmeasures, instName).contains(current)){
					passcheck=true;
				}
				//note is dotted
				if(current.getNote().getDot()!=null) {
					count = count + ((0.5*current.getType()+current.getType())/worth1);
				}
				//note is not dotted
				else if(current.getNote().getDot()==null) {
					count = count + (current.getType()/worth1);
				}
			}
			if(passcheck) {
				measurei.add(current);
				passcheck=false;
			}
		}
		if(measurei.size()==0 || measurei.get(measurei.size()-1)!=null) {
			measurei.add(null);
		}
		beamlist.add(measurei);
	}	
}
