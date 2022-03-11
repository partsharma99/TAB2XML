package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "score-partwise")
public class ScorePartwise2 {
	
	private Identification2 identification;
	private Partlist2 partlist;
	private List<Part2> listOfParts;
	private double version;
	
	public ScorePartwise2() {
	}

	public ScorePartwise2(Identification2 identification, Partlist2 partlist, List<Part2> listOfParts, double version) {
		this.identification = identification;
		this.partlist = partlist;
		this.listOfParts = listOfParts;
		this.version = version;
	}
	
	@XmlElement
	public Identification2 getIdentification() {
		return identification;
	}

	public void setIdentification(Identification2 identification) {
		this.identification = identification;
	}
	
	@XmlElement(name = "part-list")
	public Partlist2 getPartlist() {
		return partlist;
	}

	public void setPartlist(Partlist2 partlist) {
		this.partlist = partlist;
	}
	
	public String getInstrumentName() {
		return this.getPartlist().getScorepart().get(0).getPartname();
	}
	public String getCleff() {
		return this.getListOfParts().get(0).getListOfMeasures().get(0).getAttributes().getClef().getSign();
	}

	@XmlElement(name = "part")
	public List<Part2> getListOfParts() {
		return listOfParts;
	}

	public void setListOfParts(List<Part2> listOfParts) {
		this.listOfParts = listOfParts;
	}

	@XmlAttribute
	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}
	
	//just added
	public Scorepart2[] getScoreParts() {
		int numOfScoreParts = this.getPartlist().getScorepart().size();
		Scorepart2[] scoreParts = new Scorepart2[numOfScoreParts];
		for (int i = 0; i < numOfScoreParts; i++) {
			scoreParts[i] = this.getPartlist().getScorepart().get(i);
		}
		
		return scoreParts;
	}

	@Override
	public String toString() {
		return "ScorePartwise2 [identification=" + identification + ", partlist=" + partlist + ", listOfParts="
				+ listOfParts + ", version=" + version + "]";
	}
	
}
