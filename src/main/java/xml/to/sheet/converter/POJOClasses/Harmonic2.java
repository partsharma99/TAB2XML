package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

public class Harmonic2 {
	
	private Natural2 natural;
	
	public Harmonic2() {
	}

	public Harmonic2(Natural2 natural) {
		this.natural = natural;
	}
	
	@XmlElement
	public Natural2 getNatural() {
		return natural;
	}

	public void setNatural(Natural2 natural) {
		this.natural = natural;
	}

	@Override
	public String toString() {
		return "Harmonic2 [natural=" + natural + "]";
	}
	
}
