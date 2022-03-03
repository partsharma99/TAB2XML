package GUI;

import java.util.List;

import javax.xml.bind.JAXBException;

import xml.to.sheet.converter.ListOfMeasureAndNote;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;
import xml.to.sheet.converter.POJOClasses.XmlToJava;

public class PlayTabController {
    private MainViewController mvc;

	public void play() throws JAXBException {
		
		ScorePartwise2 sc;

		sc = XmlToJava.unmarshal(mvc.converter.getMusicXML(), ScorePartwise2.class);
		 List<Note2> notes = ListOfMeasureAndNote.getlistOfNotes(sc);

		for(int i=0;i<notes.size();i++) {
			
			
			
			
		}
		
		
	}

}
