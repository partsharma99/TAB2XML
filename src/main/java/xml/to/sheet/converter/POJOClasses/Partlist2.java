package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Partlist2 {
	
	private List<Scorepart2> listOfScoreparts;

	public Partlist2() {
	}

	public Partlist2(List<Scorepart2> listOfScoreparts) {
		this.listOfScoreparts = listOfScoreparts;
	}

	@XmlElement(name = "score-part")
	public List<Scorepart2> getScorepart() {
		return listOfScoreparts;
	}

	public void setScorepart(List<Scorepart2> listOfScoreparts) {
		this.listOfScoreparts = listOfScoreparts;
	}

	@Override
	public String toString() {
		return "Partlist2 [listOfSoreparts=" + listOfScoreparts + "]";
	}
	
}
