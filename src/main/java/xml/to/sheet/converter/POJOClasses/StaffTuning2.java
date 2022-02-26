package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class StaffTuning2 {
	
	private String tuningstep;
	private int tuningoctave;
	private int line;
	
	public StaffTuning2() {
	}

	public StaffTuning2(String tuningstep, int tuningoctave, int line) {
		this.tuningstep = tuningstep;
		this.tuningoctave = tuningoctave;
		this.line = line;
	}
	
	@XmlElement(name = "tuning-step")
	public String getTuningstep() {
		return tuningstep;
	}

	public void setTuningstep(String tuningstep) {
		this.tuningstep = tuningstep;
	}
	
	@XmlElement(name = "tuning-octave")
	public int getTuningoctave() {
		return tuningoctave;
	}

	public void setTuningoctave(int tuningoctave) {
		this.tuningoctave = tuningoctave;
	}
	
	@XmlAttribute
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "StaffTuning2 [tuningstep=" + tuningstep + ", tuningoctave=" + tuningoctave + ", line=" + line + "]";
	}
	
}
