package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Scorepart2 {
	
	private String partname;
	private List<ScoreInstrument2> listOfScoreInstruments;
	private List<MidiInstrument2> listOfMidiInstruments;
	private String id;
	
	public Scorepart2() {
	}

	public Scorepart2(String partname, List<ScoreInstrument2> listOfScoreInstruments,
			List<MidiInstrument2> listOfMidiInstruments, String id) {
		this.partname = partname;
		this.listOfScoreInstruments = listOfScoreInstruments;
		this.listOfMidiInstruments = listOfMidiInstruments;
		this.id = id;
	}
	
	@XmlElement(name = "part-name")
	public String getPartname() {
		return partname;
	}

	public void setPartname(String partname) {
		this.partname = partname;
	}

	@XmlElement(name = "score-instrument")
	public List<ScoreInstrument2> getListOfScoreInstruments() {
		return listOfScoreInstruments;
	}

	public void setListOfScoreInstruments(List<ScoreInstrument2> listOfScoreInstruments) {
		this.listOfScoreInstruments = listOfScoreInstruments;
	}

	@XmlElement(name = "midi-instrument")
	public List<MidiInstrument2> getListOfMidiInstruments() {
		return listOfMidiInstruments;
	}

	public void setListOfMidiInstruments(List<MidiInstrument2> listOfMidiInstruments) {
		this.listOfMidiInstruments = listOfMidiInstruments;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Scorepart2 [partname=" + partname + ", listOfScoreInstruments=" + listOfScoreInstruments
				+ ", listOfMidiInstruments=" + listOfMidiInstruments + ", id=" + id + "]";
	}
	
}
