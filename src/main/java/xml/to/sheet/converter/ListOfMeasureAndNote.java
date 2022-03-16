package xml.to.sheet.converter;

import java.util.ArrayList;
import java.util.List;

import xml.to.sheet.converter.POJOClasses.*;

public class ListOfMeasureAndNote {

	public ListOfMeasureAndNote() {
	}
	
	//returns ordered list of all measures in tab
	public static List<Measure2> getlistOfMeasures(ScorePartwise2 scorepart) {
		List<Measure2> result = new ArrayList<Measure2>();
		for(int i=0; i < scorepart.getListOfParts().get(0).getListOfMeasures().size(); i++) {
			result.add(i, scorepart.getListOfParts().get(0).getListOfMeasures().get(i));
		}
		return result;
	}
	
	//returns ordered list of all notes in tab
	public static List<Note2> getlistOfNotes(ScorePartwise2 scorepart) {
		int index = 0;
		List<Note2> result = new ArrayList<Note2>();
		for(int i=0; i < scorepart.getListOfParts().get(0).getListOfMeasures().size(); i++) {
			if(scorepart.getListOfParts().get(0).getListOfMeasures().get(i).getListOfNotes()!=null) {
				for(int j=0; j < scorepart.getListOfParts().get(0).getListOfMeasures().get(i).getListOfNotes().size(); j++) {
					result.add(index, scorepart.getListOfParts().get(0).getListOfMeasures().get(i).getListOfNotes().get(j));
					index++;
				}
			}
		}
		return result;
	}
	
//	//returns an array-list of all notes in tab where every occurrence of null signifies the end of a measure and is a flag to let us know that
//	//notes before the null are in that measure.
//	//do not use returned array-list to get the total number of notes
//	public static ArrayList<Note2> getNotesInMeasure(ScorePartwise2 scorepart) {
//		int index = 0;
//		List<Measure2> measurelist = scorepart.getListOfParts().get(0).getListOfMeasures();
//		ArrayList<Note2> result = new ArrayList<Note2>();
//		for(int i=0; i < measurelist.size();) {
//			int size2 = measurelist.get(i).getListOfNotes().size();
//			for(int j=0; j < size2;) {
//				if((j+1) == size2) {
//					result.add(index, measurelist.get(i).getListOfNotes().get(j));
//					result.add(index+1, null);
//					index++;
//				}
//				else {
//					result.add(index, measurelist.get(i).getListOfNotes().get(j));
//				}
//				j++;
//				index++;
//			}
//			i++;
//		}
//		result.trimToSize();
//		return result;
//	}
	
	
	//returns an array-list of all notes in the "ith" measure
	public static ArrayList<Note2> getNotesInMeasureI(ScorePartwise2 scorepart, int i) {
		Measure2 measureI = scorepart.getListOfParts().get(0).getListOfMeasures().get(i);
		ArrayList<Note2> result = new ArrayList<Note2>(measureI.getListOfNotes());
		return result;
	}
}
