package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Technicial2 {
	
	private int string;
	private int fret;
	private HammerOn2 hammeron;
	private Bend2 bend;
	private Harmonic2 harmonic;
	private Pulloff2 pulloff;

	public Technicial2() {
	}

	public Technicial2(int string, int fret, HammerOn2 hammeron, Bend2 bend, Harmonic2 harmonic, Pulloff2 pulloff) {
		this.string = string;
		this.fret = fret;
		this.hammeron = hammeron;
		this.bend = bend;
		this.harmonic = harmonic;
		this.pulloff = pulloff;
	}

	@XmlElement
	public int getString() {
		return string;
	}

	public void setString(int string) {
		this.string = string;
	}

	@XmlElement
	public int getFret() {
		return fret;
	}

	public void setFret(int fret) {
		this.fret = fret;
	}
	
	@XmlElement
	public HammerOn2 getHammeron() {
		return hammeron;
	}

	public void setHammeron(HammerOn2 hammeron) {
		this.hammeron = hammeron;
	}
	
	@XmlElement
	public Bend2 getBend() {
		return bend;
	}

	public void setBend(Bend2 bend) {
		this.bend = bend;
	}
	
	@XmlElement
	public Harmonic2 getHarmonic() {
		return harmonic;
	}

	public void setHarmonic(Harmonic2 harmonic) {
		this.harmonic = harmonic;
	}
	
	@XmlElement(name = "pull-off")
	public Pulloff2 getPulloff() {
		return pulloff;
	}

	public void setPulloff(Pulloff2 pulloff) {
		this.pulloff = pulloff;
	}

	@Override
	public String toString() {
		return "Technicial2 [string=" + string + ", fret=" + fret + ", hammeron=" + hammeron + ", bend=" + bend
				+ ", harmonic=" + harmonic + ", pulloff=" + pulloff + "]";
	}
	
}
