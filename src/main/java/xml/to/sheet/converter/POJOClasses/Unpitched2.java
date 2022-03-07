package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Unpitched2 {
	
	private String displaystep;
	private int displayoctave;

	public Unpitched2() {
	}

	public Unpitched2(String displaystep, int displayoctave) {
		this.displaystep = displaystep;
		this.displayoctave = displayoctave;
	}
	
	@XmlElement(name = "display-step")
	public String getDisplaystep() {
		return displaystep;
	}

	public void setDisplaystep(String displaystep) {
		this.displaystep = displaystep;
	}
	
	@XmlElement(name = "display-octave")
	public int getDisplayoctave() {
		return displayoctave;
	}

	public void setDisplayoctave(int displayoctave) {
		this.displayoctave = displayoctave;
	}

	@Override
	public String toString() {
		return "Unpitched2 [displaystep=" + displaystep + ", displayoctave=" + displayoctave + "]";
	}

}
