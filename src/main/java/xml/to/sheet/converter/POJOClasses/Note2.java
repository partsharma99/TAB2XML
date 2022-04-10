package xml.to.sheet.converter.POJOClasses;

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
    private Dot2 dot;
    private String stem;
    private NoteHead2 notehead;
    private Notations2 notations;
    private Rest2 rest;

    public Note2() {
    }

    public Note2(Chord2 chord, Grace2 grace, Pitch2 pitch, Unpitched2 unpitched, int duration, Instrument2 instrument, int voice, String type,
            TimeModification2 timemodification, Dot2 dot, String stem, NoteHead2 notehead, Notations2 notations, Rest2 rest) {
        this.chord = chord;
        this.grace = grace;
        this.pitch = pitch;
        this.unpitched = unpitched;
        this.duration = duration;
        this.instrument = instrument;
        this.voice = voice;
        this.type = type;
        this.timemodification = timemodification;
        this.dot = dot;
        this.stem = stem;
        this.notehead = notehead;
        this.notations = notations;
        this.rest = rest;
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

    @XmlElement
    public Dot2 getDot() {
        return dot;
    }

    public void setDot(Dot2 dot) {
        this.dot = dot;
    }

    @XmlElement
    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }
    
    @XmlElement
    public NoteHead2 getNotehead() {
        return notehead;
    }

    public void setNotehead(NoteHead2 notehead) {
        this.notehead = notehead;
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

    @Override
    public String toString() {
        return "Note2 [chord=" + chord + ", grace=" + grace + ", pitch=" + pitch + ", unpitched=" + unpitched
                + ", duration=" + duration + ", instrument=" + instrument + ", voice=" + voice + ", type=" + type
                + ", timemodification=" + timemodification + ", dot=" + dot + ", stem=" + stem + ", notehead="
                + notehead + ", notations=" + notations + ", rest=" + rest + "]";
    }
    
}