package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

public class Time2 {
	
	private int beats;
	private int beattype;
	
	public Time2() {
	}

	public Time2(int beats, int beattype) {
		this.beats = beats;
		this.beattype = beattype;
	}
	
	@XmlElement
	public int getBeats() {
		return beats;
	}

	public void setBeats(int beats) {
		this.beats = beats;
	}
	
	@XmlElement(name = "beat-type")
	public int getBeattype() {
		return beattype;
	}

	public void setBeattype(int beattype) {
		this.beattype = beattype;
	}

	@Override
	public String toString() {
		return "Time2 [beats=" + beats + ", beattype=" + beattype + "]";
	}
	
}
