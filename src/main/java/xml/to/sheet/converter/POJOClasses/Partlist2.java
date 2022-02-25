package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Partlist2 {
	
	private List<Scorepart2> listOfSoreparts;

	public Partlist2() {
	}

	public Partlist2(List<Scorepart2> listOfScoreparts) {
		this.listOfSoreparts = listOfScoreparts;
	}

	@XmlElement(name = "score-part")
	public List<Scorepart2> getScorepart() {
		return listOfSoreparts;
	}

	public void setScorepart(List<Scorepart2> listOfScoreparts) {
		this.listOfSoreparts = listOfScoreparts;
	}

	@Override
	public String toString() {
		return "Partlist2 [listOfSoreparts=" + listOfSoreparts + "]";
	}
	
}
