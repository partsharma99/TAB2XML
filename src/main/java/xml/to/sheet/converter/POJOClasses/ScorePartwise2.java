package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

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

	@Override
	public String toString() {
		return "ScorePartwise2 [identification=" + identification + ", partlist=" + partlist + ", listOfParts="
				+ listOfParts + ", version=" + version + "]";
	}
	
}
