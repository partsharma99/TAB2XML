package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Note2 {
	
	private Chord2 chord;
	private Grace2 grace;
	private Pitch2 pitch;
	private Unpitched2 unpitched;
	private int duration;
	private Instrument2 instrument;
	private int voice;
	private String type;
	private TimeModification2 timemodification;
	private List<Dot2> listofdot;
	private String stem;
	private String notehead;
	private NoteHead2 notehead2;
	private Notations2 notations;
	private Rest2 rest;
	private Beam2 beam;
	

	public Note2() {
	}

	public Note2(Chord2 chord, Grace2 grace, Pitch2 pitch, Unpitched2 unpitched, int duration, Instrument2 instrument, int voice, String type,
			TimeModification2 timemodification, List<Dot2> listofdot, String stem, String notehead,NoteHead2 notehead2, Notations2 notations,
			Rest2 rest, Beam2 beam) {
		this.chord = chord;
		this.grace = grace;
		this.pitch = pitch;
		this.unpitched = unpitched;
		this.duration = duration;
		this.instrument = instrument;
		this.voice = voice;
		this.type = type;
		this.timemodification = timemodification;
		this.listofdot = listofdot;
		this.stem = stem;
		this.notehead = notehead;
		this.notehead2 = notehead2;
		this.notations = notations;
		this.rest = rest;
		this.beam = beam;
	}
	
	@XmlElement
	public Chord2 getChord() {
		return chord;
	}

	public void setChord(Chord2 chord) {
		this.chord = chord;
	}
	
	@XmlElement
	public Grace2 getGrace() {
		return grace;
	}

	public void setGrace(Grace2 grace) {
		this.grace = grace;
	}

	@XmlElement
	public Pitch2 getPitch() {
		return pitch;
	}

	public void setPitch(Pitch2 pitch) {
		this.pitch = pitch;
	}
	
	@XmlElement
	public Unpitched2 getUnpitched() {
		return unpitched;
	}

	public void setUnpitched(Unpitched2 unpitched) {
		this.unpitched = unpitched;
	}
	
	@XmlElement
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@XmlElement
	public Instrument2 getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument2 instrument) {
		this.instrument = instrument;
	}
	
	@XmlElement
	public int getVoice() {
		return voice;
	}

	public void setVoice(int voice) {
		this.voice = voice;
	}
	
	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name = "time-modification")
	public TimeModification2 getTimemodification() {
		return timemodification;
	}

	public void setTimemodification(TimeModification2 timemodification) {
		this.timemodification = timemodification;
	}

	@XmlElement(name = "dot")
	public List<Dot2> getListofdot() {
		return listofdot;
	}

	public void setListofdot(List<Dot2> listofdot) {
		this.listofdot = listofdot;
	}

	@XmlElement
	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}
	
	@XmlElement
	public String getNotehead() {
		return notehead;
	}

	public void setNotehead(String notehead) {
		this.notehead = notehead;
	}
	
	
	@XmlElement(name = "notehead")
	public NoteHead2 getNotehead2() {
		return notehead2;
	}

	public void setNotehead2(NoteHead2 notehead2) {
		this.notehead2 = notehead2;
	}

	@XmlElement
	public Notations2 getNotations() {
		return notations;
	}

	public void setNotations(Notations2 notations) {
		this.notations = notations;
	}
	
	@XmlElement
	public Rest2 getRest() {
		return rest;
	}

	public void setRest(Rest2 rest) {
		this.rest = rest;
	}
	
	@XmlElement
	public Beam2 getBeam() {
		return beam;
	}

	public void setBeam(Beam2 beam) {
		this.beam = beam;
	}

	@Override
	public String toString() {
		return "Note2 [chord=" + chord + ", grace=" + grace + ", pitch=" + pitch + ", unpitched=" + unpitched
				+ ", duration=" + duration + ", instrument=" + instrument + ", voice=" + voice + ", type=" + type
				+ ", timemodification=" + timemodification + ", listofdot=" + listofdot + ", stem=" + stem
				+ ", notehead=" + notehead + ", notehead2=" + notehead2 + ", notations=" + notations + ", rest=" + rest
				+ ", beam=" + beam + "]";
	}
	
}