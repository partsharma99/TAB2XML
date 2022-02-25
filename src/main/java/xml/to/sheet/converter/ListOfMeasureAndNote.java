package xml.to.sheet.converter;

import java.util.ArrayList;
import java.util.List;

import xml.to.sheet.converter.POJOClasses.*;

public class ListOfMeasureAndNote {

	public ListOfMeasureAndNote() {
	}
	
	public static List<Measure2> getlistOfMeasures(ScorePartwise2 scorepart) {
		
		List<Measure2> result = new ArrayList<Measure2>();
		for(int i=0; i < scorepart.getListOfParts().get(0).getListOfMeasures().size(); i++) {
			result.add(i, scorepart.getListOfParts().get(0).getListOfMeasures().get(i));
		}
		return result;
		
	}
	
	public static List<Note2> getlistOfNotes(ScorePartwise2 scorepart) {
		int index = 0;
		List<Note2> result = new ArrayList<Note2>();
		for(int i=0; i < scorepart.getListOfParts().get(0).getListOfMeasures().size(); i++) {
			for(int j=0; j < scorepart.getListOfParts().get(0).getListOfMeasures().get(i).getListOfNotes().size(); j++) {
				result.add(index, scorepart.getListOfParts().get(0).getListOfMeasures().get(i).getListOfNotes().get(j));
				index++;
			}
		}
		return result;
		
	}
	
	

}
