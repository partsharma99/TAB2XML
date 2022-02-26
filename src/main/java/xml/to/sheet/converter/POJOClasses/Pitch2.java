package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

public class Pitch2 {
	
	private String step;
	private int alter;
	private int octave;

	public Pitch2() {
	}

	public Pitch2(String step, int alter, int octave) {
		this.step = step;
		this.alter = alter;
		this.octave = octave;
	}

	@XmlElement
	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
	
	@XmlElement
	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		this.alter = alter;
	}

	@XmlElement
	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}

	@Override
	public String toString() {
		return "Pitch2 [step=" + step + ", alter=" + alter + ", octave=" + octave + "]";
	}

}
