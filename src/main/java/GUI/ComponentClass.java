package GUI;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import models.measure.Measure;
import xml.to.sheet.converter.POJOClasses.Measure2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.Time2;

public class ComponentClass {

	public static ArrayList<ArrayList<NoteAndPos>> getGracList(ArrayList<measureinfo> listofmeasures){
		NoteAndPos current = null;
		NoteAndPos prev = null;
		ArrayList<ArrayList<NoteAndPos>> gracelist = new ArrayList<ArrayList<NoteAndPos>>();
		ArrayList<NoteAndPos> measurei = new ArrayList<NoteAndPos>();

		//(1)
		//Setting the 2D-list of graces.
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					current = listofmeasures.get(i).getMeasure().get(j);
					//not the first note in the measure
					if(j!=0) {
						prev = listofmeasures.get(i).getMeasure().get(j-1);
						//current note is a grace note 
						if(current.getNote().getGrace()!=null) {
							measurei.add(current);
						}
						//current note is not a grace note
						else {
							//previous note is a grace note
							if(prev.getNote().getGrace()!=null) {
								measurei.add(current);
								int temp = j + 1;
								//check to see if there are chorded notes that appear after current
								while(temp<listofmeasures.get(i).getMeasure().size() && 
										listofmeasures.get(i).getMeasure().get(temp).getNote().getChord()!=null) {
									measurei.add(listofmeasures.get(i).getMeasure().get(temp));
									temp++;
								}
								j = temp - 1;
								measurei.add(null);
							}
						}
					}
					//the first note in the measure
					else {
						//current note is a grace
						if(current.getNote().getGrace()!=null) {
							measurei.add(current);
						}
					}
				}
				//no graces in this measure
				if(measurei.size()==0) {
					gracelist.add(null);
				}
				else {
					if(measurei.get(measurei.size()-1)!=null) {
						measurei.add(null);
					}
					gracelist.add(measurei);
				}
				measurei = new ArrayList<>();
			}
			else {
				gracelist.add(null);
			}
		}
		prev = null;
		current = null;
		measurei = null;

		return gracelist;
	}

	public static ArrayList<NoteAndPos> getTieList(ArrayList<measureinfo> listofmeasures, String instName){
		NoteAndPos current = null;
		NoteAndPos next = null;
		ArrayList<NoteAndPos> tiedlist = new ArrayList<NoteAndPos>();
		ArrayList<NoteAndPos> orderedtiedlist = new ArrayList<NoteAndPos>();

		//(1)
		//Setting the list of the of ties.
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					current = listofmeasures.get(i).getMeasure().get(j);
					if(current.getNote().getNotations()!=null &&
							current.getNote().getNotations().getListOfTieds()!=null &&
							current.getNote().getRest()==null) {
						tiedlist.add(current);
					}
				}
			}
		}

		//(2)
		//Grouping and sorting the list of tied notes (adjacent elements must have the same string value for guitar)
		//Grouping and sorting the list of tied notes (adjacent elements must have the same string id for drumset)
		for(int i=0; i<tiedlist.size(); i++) {
			current = tiedlist.get(i);

			//current note has a tied and it is a start
			if(current.getNote().getNotations().getListOfTieds().size()==1 &&
					current.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("start")) {

				//add current note that has a tied and is a start into the ordered tied list
				//find all the notes that have a tied that are linked to current and add them to the ordered tied list in the
				//order that they appear
				//start finding from the next note in the tied list

				orderedtiedlist.add(current);

				int temp = i + 1;
				while(temp<tiedlist.size()) {
					next = tiedlist.get(temp);

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

								//if next note is in the same space and is a start and stop then add
								if(next.getNote().getNotations().getListOfTieds().size()==2 &&
										next.getNote().getNotations().getListOfTieds().get(0).getType().equalsIgnoreCase("stop") &&
										next.getNote().getNotations().getListOfTieds().get(1).getType().equalsIgnoreCase("start")){
									orderedtiedlist.add(next);
								}
								//if next note is in the same space and is a stop then add
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
					temp++;
				}
			}
		}
		current = null;
		next = null;
		tiedlist = null;

		return orderedtiedlist;
	}

	public static ArrayList<NoteAndPos> getSlurList(ArrayList<measureinfo> listofmeasures, String instName){
		NoteAndPos next = null;
		NoteAndPos current = null;
		ArrayList<NoteAndPos> onlyslurlist = new ArrayList<NoteAndPos>();
		ArrayList<NoteAndPos> orderedslurlist = new ArrayList<NoteAndPos>();

		//(1)
		//Setting the list of slurs.
		for(int i=0; i<listofmeasures.size(); i++) {
			if(listofmeasures.get(i).getMeasure()!=null) {
				for(int j=0; j<listofmeasures.get(i).getMeasure().size(); j++) {
					current = listofmeasures.get(i).getMeasure().get(j);
					if(current.getNote().getNotations()!=null && 
							current.getNote().getNotations().getListOfSlurs()!=null) {
						onlyslurlist.add(current);
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

				int temp = i + 1;
				while(temp<onlyslurlist.size()) {
					next = onlyslurlist.get(temp);

					//if the next note has already been added to the ordered slur list then skip the check
					if(orderedslurlist.contains(next)==false) {

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
								next.getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("stop")) {
							orderedslurlist.add(next);
							orderedslurlist.add(null);
							break;		
						}
					}
					temp++;
				}	
			}
		}

		//(2)
		//Grouping slurs.
		next = null;
		current = null;
		onlyslurlist = null;

		return orderedslurlist;
	}

	public static ArrayList<ArrayList<NoteAndPos>> getBeamList(ArrayList<measureinfo> listofmeasures, ScorePartwise2 sc, String instName, Pane pane){
		ArrayList<ArrayList<NoteAndPos>> beamlist = new ArrayList<>();;
		double totalbeats=0;
		double worth1=0;

		//(1)
		//Extracting time-signature values to be used to form the beam groups via the beamlist helper method.
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
		return beamlist;
	}

	private static void getbeamlisthelper(double totalbeats, double worth1, ArrayList<measureinfo> listofmeasures, ArrayList<ArrayList<NoteAndPos>> beamlist, String instName, int indexm) {
		double count=0;
		boolean passcheck=false;
		NoteAndPos current = null;
		ArrayList<NoteAndPos> tempslurlist = ComponentClass.getSlurList(listofmeasures, instName);
		ArrayList<NoteAndPos> measurei = new ArrayList<>();

		//(1)
		//Setting up the beam groups in the current measure.
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
			else if(count>1 && count%1==0 && count<=totalbeats) {
				measurei.add(null);
				count=0;
			}
			//non-chorded notes, non-grace notes, and non-slur or stop-slur notes can be beamed
			if(current.getNote().getChord()==null && 
					current.getNote().getGrace()==null && 
					isitaStop(listofmeasures, tempslurlist, instName, current)==true) {
				//note is a rest - cannot add to beam group
				if(current.getNote().getRest()!=null) {
					passcheck=false;
				}
				//note is not rest - can add to beam group
				else if(current.getNote().getRest()==null){
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
				if(passcheck) {
					measurei.add(current);
					passcheck = false;
				}
			}
		}
		//no beam groups in this measure
		if(measurei.size()==0) {
			beamlist.add(null);
		}
		//beam groups but does not end with null
		else {
			if(measurei.get(measurei.size()-1)!=null) {
				measurei.add(null);
			}
			beamlist.add(measurei);
		}
		current = null;
		tempslurlist = null;
		measurei = null;
	}

	private static boolean isitaStop(ArrayList<measureinfo> listofmeasures, ArrayList<NoteAndPos> tempslurlist, String instName, NoteAndPos current) {
		//(1)
		//Determine if the current note is a stop-slur, a slur, or a non-slur.
		if(tempslurlist.contains(current)) {
			int holder = ComponentClass.getSlurList(listofmeasures, instName).indexOf(current);
			if(tempslurlist.get(holder).getNote().getNotations().getListOfSlurs().size()==1 &&
					tempslurlist.get(holder).getNote().getNotations().getListOfSlurs().get(0).getType().equalsIgnoreCase("stop")) {
				return true;
			}
			else {
				return false;
			}	
		}
		else {
			return true;
		}
	}

	public static ArrayList<NoteAndPos> getSlidelist(ArrayList<measureinfo> listofmeasures) {
		NoteAndPos current = null;
		NoteAndPos next = null;
		ArrayList<measureinfo> m = listofmeasures;
		ArrayList<NoteAndPos> slidelistonly = new ArrayList<>();
		ArrayList<NoteAndPos> orderedslidelist = new ArrayList<>();
		for(int i=0; i<m.size(); i++) {
			if(m.get(i).getMeasure()!=null) {
				for(int j=0; j<m.get(i).getMeasure().size(); j++) {
					current = m.get(i).getMeasure().get(j);
					if(current.getNote().getNotations()!=null && current.getNote().getNotations().getSlide()!=null) {
						slidelistonly.add(current);
					}
				}
			}
		}
		for(int i=0; i<slidelistonly.size(); i++) {
			current = slidelistonly.get(i);

			//only checking for starting slurs
			if(current.getNote().getNotations().getSlide().size()==1 &&
					current.getNote().getNotations().getSlide().get(0).getType().equalsIgnoreCase("start")) {

				orderedslidelist.add(current);

				int temp = i + 1;
				while(temp<slidelistonly.size()) {
					next = slidelistonly.get(temp);

					//if the next note has already been added to the ordered slur list then skip the check
					if(orderedslidelist.contains(next)==false) {

						//if next note is stop and start then add
						//the search for the last note in the slur continues
						if(next.getNote().getNotations().getSlide().size()==2 &&
								next.getNote().getNotations().getSlide().get(0).getType().equalsIgnoreCase("start") &&
								next.getNote().getNotations().getSlide().get(1).getType().equalsIgnoreCase("stop")){
							orderedslidelist.add(next);
						}
						//if next note is a stop then add
						//after this is reached that means that all the notes that are tied have been added in the same order
						//break from the loop to then stop the check as it is completed
						else if(next.getNote().getNotations().getSlide().size()==1 &&
								next.getNote().getNotations().getSlide().get(0).getType().equalsIgnoreCase("stop")) {
							orderedslidelist.add(next);
							orderedslidelist.add(null);
							break;		
						}
					}
					temp++;
				}	
			}
		}
		
		return orderedslidelist;
	}	
}