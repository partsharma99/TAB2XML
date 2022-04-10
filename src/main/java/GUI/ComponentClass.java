package GUI;

import java.util.ArrayList;

public class ComponentClass {
	
	public static ArrayList<NoteAndPos> getGracList(ArrayList<NoteAndPos> nplist){
		NoteAndPos current = null;
    	NoteAndPos prev = null;
    	ArrayList<NoteAndPos> gracelist = new ArrayList<NoteAndPos>();
    	for(int k=0; k<nplist.size(); k++) {
    		current = nplist.get(k);
    		if(k!=0) {
    			prev = nplist.get(k-1);
    			//current note has a grace (do not need to check previous note)
    			//add current note
    			if(current.getNote().getGrace()!=null) {
    				gracelist.add(current);
    			}
    			//current does not have a grace (need to check previous note)
    			//previous note has grace 
    			//add current note
    			//check to see if it is the last in the grace group by checking to see if the next note has a chord
    			//if next note has a chord keep checking
    			//if next note does not have a chord then end the search and add null to grace list to show end of grace group
    			else if(current.getNote().getGrace()==null && prev.getNote().getGrace()!=null) {
    				gracelist.add(current);
    				int l = k + 1;
    				while(l<nplist.size() && nplist.get(l).getNote().getChord()!=null) {
    					gracelist.add(nplist.get(l));
    					l++;
    				}
    				gracelist.add(null);
    				k=l-1;
    			}
    		}
    		else {
    			if(current.getNote().getGrace()!=null) {
    				gracelist.add(current);
    			}
    		}
    	}
    	return gracelist;
	}
	
	public static ArrayList<NoteAndPos> getTieList(ArrayList<NoteAndPos> nplist, String instName){
		NoteAndPos current = null;
		NoteAndPos next = null;
		ArrayList<NoteAndPos> tiedlist = new ArrayList<NoteAndPos>();
    	ArrayList<NoteAndPos> orderedtiedlist = new ArrayList<NoteAndPos>();
    	for(int k=0; k<nplist.size(); k++) {
    		current = nplist.get(k);
    		if(current.getNote().getNotations()!=null && 
    		   current.getNote().getNotations().getListOfTieds()!=null &&
    		   current.getNote().getRest()==null) {
    			tiedlist.add(current);
    		}
    	}
    	
    	//sorting the list of tied notes (adjacent elements must have the same string value for guitar
    	for(int k = 0; k<tiedlist.size(); k++) {
    		current = tiedlist.get(k);
    		
    		//current note has a tied and it is a start
    		if(current.getNote().getNotations().getListOfTieds().size()==1 &&
    		   current.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("start")) {
    			
    			//add current note that has a tied and is a start into the ordered tied list
    			//find all the notes that have a tied that are linked to current and add them to the ordered tied list in the
    			//order that they appear
    			//start finding from the next note in the tied list
    			
    			orderedtiedlist.add(current);
    			
    			int l = k + 1;
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
	
	public static ArrayList<NoteAndPos> getSlurList(ArrayList<NoteAndPos> nplist, String instName){
		NoteAndPos next = null;
		NoteAndPos current = null;
		//setting the list of slurs
    	ArrayList<NoteAndPos> onlyslurlist = new ArrayList<NoteAndPos>();
    	ArrayList<NoteAndPos> orderedslurlist = new ArrayList<NoteAndPos>();
    	for(int k=0; k<nplist.size(); k++) {
    		current = nplist.get(k);
    		
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
    	
    	for(int k=0; k<onlyslurlist.size(); k++) {
    		current = onlyslurlist.get(k);
    		
    		//only checking for starting slurs
    		if(current.getNote().getNotations().getListOfSlurs().size()==1 &&
    		   current.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("start")) {
    			
    			orderedslurlist.add(current);
    			
    			int l = k + 1;
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
	
	public static ArrayList<NoteAndPos> getBeamList(ArrayList<NoteAndPos> nplist){
		//8 = 1/2
		//16 = 1/4
		//32 = 1/8
		int countto1 = 0;
		int countto4 = 0;
		NoteAndPos prev= null;
		NoteAndPos current = null;
		ArrayList<NoteAndPos> measureI = new ArrayList<NoteAndPos>();
    	ArrayList<NoteAndPos> beamlist = new ArrayList<NoteAndPos>();
    	for(int k=0; k<nplist.size(); k++) {
    		current = nplist.get(k);
    		if(k!=0) {
    			prev = nplist.get(k-1);
    			if(prev.getNote().getRest()==null && current.getNote().getRest()==null) {
    				
    			}
    			
    		}
    	}
    	return beamlist;
	}
	
}
