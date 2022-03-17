package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class MidiInstrument2 {
	
	private int midichannel;
	private int midiprogram;
	private int midiunpitched;
	private double volume;
	private int pan;
	private String id;

	public MidiInstrument2() {
		
	}
	public MidiInstrument2(int midichannel, int midiprogram, int midiunpitched, double volume, int pan, String id) {
		this.midichannel = midichannel;
		this.midiprogram = midiprogram;
		this.midiunpitched = midiunpitched;
		this.volume = volume;
		this.pan = pan;
		this.id = id;
	}
	
	@XmlElement(name = "midi-channel")
	public int getMidichannel() {
		return midichannel;
	}

	public void setMidichannel(int midichannel) {
		this.midichannel = midichannel;
	}

	@XmlElement(name = "midi-program")
	public int getMidiprogram() {
		return midiprogram;
	}

	public void setMidiprogram(int midiprogram) {
		this.midiprogram = midiprogram;
	}
	
	@XmlElement(name = "midi-unpitched")
	public int getMidiunpitched() {
		return midiunpitched;
	}

	public void setMidiunpitched(int midiunpitched) {
		this.midiunpitched = midiunpitched;
	}
	
	@XmlElement
	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	@XmlElement
	public int getPan() {
		return pan;
	}

	public void setPan(int pan) {
		this.pan = pan;
	}
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String toString() {
		return "MidiInstrument2 [midichannel=" + midichannel + ", midiprogram=" + midiprogram + ", midiunpitched="
				+ midiunpitched + ", volume=" + volume + ", pan=" + pan + ", id=" + id + "]";
	}

	
}
