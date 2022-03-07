package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class TimeModification2 {
	
	private int actualnotes;
	private int normalnotes;

	public TimeModification2() {
	}

	public TimeModification2(int actualnotes, int normalnotes) {
		this.actualnotes = actualnotes;
		this.normalnotes = normalnotes;
	}
	
	@XmlElement
	public int getActualnotes() {
		return actualnotes;
	}

	public void setActualnotes(int actualnotes) {
		this.actualnotes = actualnotes;
	}
	
	@XmlElement
	public int getNormalnotes() {
		return normalnotes;
	}

	public void setNormalnotes(int normalnotes) {
		this.normalnotes = normalnotes;
	}

	@Override
	public String toString() {
		return "TimeModification2 [actualnotes=" + actualnotes + ", normalnotes=" + normalnotes + "]";
	}
	
}
