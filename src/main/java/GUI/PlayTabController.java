package GUI;

/*
 * 1237pm on mar 13: stopped at making Mary had a little lamb play w the method composedrumset
 * want to experiment with the tempo, duration, velocity to see what effect they have on the sound
 * this will be translated to the drumset and guitar sound
 * 
 * 
 * 1109pm on mar 14: sure that this system now identifies chords and can play the notes at a 
 * suitable interval. Need to figurer out a better way to convert from the duration to ticks
 * and how to play the chords because the chords are not playing in the expected way (can't tell the separate notes)
 */

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.midi.ShortMessage;
import javax.xml.bind.JAXBException;

import org.jfugue.theory.Note;

import javafx.fxml.FXML;
import xml.to.sheet.converter.POJOClasses.Measure2;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class PlayTabController {
    private MainViewController mvc;
    private ScorePartwise2 sc;
    private Synthesizer midiSynth;
    Sequencer sequencer = getSequencer();
    private Sequence seq;
    private int tick_noteON;
    private int tick_noteOFF;
    private final String SEQ_DEV_NAME = "default";
    private final String SEQ_PROP_KEY = "javax.sound.midi.Sequence";
	
    public PlayTabController(ScorePartwise2 inputSC, Synthesizer inputSynth) {
    	this.sc = inputSC;
    	this.midiSynth = inputSynth;
    	tick_noteON =0;
    	tick_noteOFF =1;
    }

    @FXML
	public void play() throws JAXBException {
		MidiChannel thisChannel;
		
		//playing the music using the jaxb parser on a note-by-note basis
		if(sc.getInstrumentName().equalsIgnoreCase("drumset")) {
			thisChannel = midiSynth.getChannels()[9];
			composeDrumset(thisChannel, 9);

		} else if(sc.getInstrumentName().equalsIgnoreCase("Guitar")) {
			thisChannel = midiSynth.getChannels()[0];
			composeGuitar(thisChannel, 0);
		}
	}
	
	@SuppressWarnings("unused")
	private void composeDrumset1(MidiChannel thisChannel, int i) {
		int channel = 0;
		int volume = 64;
		int note = 61;
		
		try {
			seq = new Sequence(Sequence.PPQ, 16); //division type for the music set to pulse per quarter
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		Track track = seq.createTrack();
		
		for(int h=0; h < 5; h++) {
			addMidiEvent(track, ShortMessage.NOTE_ON, channel, note, volume, 0);
			addMidiEvent(track, ShortMessage.NOTE_OFF, channel, note, 0, 1);
			addMidiEvent(track, ShortMessage.NOTE_ON, channel, note - 2, volume, getIncrementedTick_NoteON());
			addMidiEvent(track, ShortMessage.NOTE_OFF, channel, note - 2, 0, getIncrementedTick_NoteOFF());
			addMidiEvent(track, ShortMessage.NOTE_ON, channel, note - 4, volume, getIncrementedTick_NoteON());
			addMidiEvent(track, ShortMessage.NOTE_OFF, channel, note - 4, 0, getIncrementedTick_NoteOFF());
			addMidiEvent(track, ShortMessage.NOTE_ON, channel, note - 2, volume, getIncrementedTick_NoteON());
			addMidiEvent(track, ShortMessage.NOTE_OFF, channel, note - 2, 0, getIncrementedTick_NoteOFF());
			addMidiEvent(track, ShortMessage.NOTE_ON, channel, note, volume, getIncrementedTick_NoteON());
			addMidiEvent(track, ShortMessage.NOTE_OFF, channel, note, 0, getIncrementedTick_NoteOFF());
			addMidiEvent(track, ShortMessage.NOTE_ON, channel, note, volume, getIncrementedTick_NoteON());
			addMidiEvent(track, ShortMessage.NOTE_OFF, channel, note, 0, getIncrementedTick_NoteOFF());
			addMidiEvent(track, ShortMessage.NOTE_ON, channel, note, volume, getIncrementedTick_NoteON());
			addMidiEvent(track, ShortMessage.NOTE_OFF, channel, note, 0, getIncrementedTick_NoteOFF());
		}
		startSequencer(seq);
	}

	/*
	 * Sets the message of the ShortMessage msg with the given parameters 
	 * using the short message constructor:
	 * ShortMessage(int command, int channel, int note, int volume)
	 * 
	 * Adds a new event to the track with this info
	 */
	public void addMidiEvent(Track track, int command, int channel, int data1, int data2, int ticks) {
		//ticks is the timestamp for the midi event
		ShortMessage msg = new ShortMessage();
		
		try {
			msg.setMessage(command, channel, data1, data2);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		
		track.add(new MidiEvent(msg, ticks));
		
	}
	
	@FXML
	public void pause() {
		sequencer.stop();
	}

	public void composeDrumset(MidiChannel channel, int channelIndex) {
		List<Measure2> allMeasures = sc.getListOfParts().get(0).getListOfMeasures();
		int counterOfTickFactor = 0;
		
		for(int i=0; i < allMeasures.size(); i++) {
			List<Note2> notes = allMeasures.get(i).getListOfNotes();
			int divisions = allMeasures.get(i).getAttributes().getDivisions();
	
			try {
				seq = new Sequence(Sequence.PPQ, divisions); //division type for the music set to pulse per quarter
			} catch (InvalidMidiDataException e) {
				e.printStackTrace();
			}
			Track track =  seq.createTrack();
			
			for(int j=0; j < notes.size(); j++) {
				String thisID = notes.get(j).getInstrument().getId();
				int duration = notes.get(j).getDuration();
				
				/*
				 * usual ID is in the format P1-I[ID]
				 * so the following code will get the integers 
				 * from the last two characters in thisID
				 */

				String numStr = "";
				numStr += thisID.charAt(thisID.length()-2);
				numStr += thisID.charAt(thisID.length()-1);

				int note = Integer.parseInt(numStr);
				int volume = 90;
				/*
				System.out.println("The string is: " + numStr);
                System.out.println("THe instrument is " + notes.get(j).getInstrument().toString());
				*/	
				addMidiEvent(track, ShortMessage.NOTE_ON, channelIndex, note, volume, incrementTick(duration, counterOfTickFactor));
				addMidiEvent(track, ShortMessage.NOTE_OFF, channelIndex, note, 0, incrementTick(duration, counterOfTickFactor)+1);
				System.out.println(notes.get(j).getInstrument());
//				if(notes.get(j).getChord() == null) {
//					addMidiEvent(track, ShortMessage.NOTE_ON, channelIndex, note, volume, incrementTick(duration, counterOfTickFactor));
//					addMidiEvent(track, ShortMessage.NOTE_OFF, channelIndex, note, 0, incrementTick(duration, counterOfTickFactor)+1);
//					System.out.println("incrementTick noteON: " + incrementTick(duration, counterOfTickFactor));
//					System.out.println("incrementTick noteOFF: " + incrementTick(duration, counterOfTickFactor));
//				}else {
//					addMidiEvent(track, ShortMessage.NOTE_ON, channelIndex, note, volume, incrementTick(duration, counterOfTickFactor-1)+1);
//					addMidiEvent(track, ShortMessage.NOTE_OFF, channelIndex, note, 0, incrementTick(duration, counterOfTickFactor-1)+2);
//					System.out.println("The note at position " + j + "has a chord");
//				}
				
				
				counterOfTickFactor+=1; 
//				System.out.println("duration " + duration);
				numStr="";
			}
			
			
		}
		startSequencer(seq);
//		System.out.println("How many tracks: " + seq.getTracks().length);
//		System.out.println("microsec length: " + seq.getMicrosecondLength());
//		System.out.println("Tick length: " + seq.getTickLength());
//		System.out.println("Number of events " + seq.getTracks()[0].size());
		
	}

	public void composeGuitar(MidiChannel channel, int channelIndex) {
		List<Measure2> allMeasures = sc.getListOfParts().get(0).getListOfMeasures();
		
		for(int i=0; i < allMeasures.size(); i++) {
			List<Note2> notes = allMeasures.get(i).getListOfNotes();
			int divisions = allMeasures.get(i).getAttributes().getDivisions();
			
			for(int j=0; j < notes.size(); j++) {
				int duration = notes.get(j).getDuration();
				int octave = notes.get(j).getPitch().getOctave();
				String step = notes.get(j).getPitch().getStep();
				String tone = step+(octave);

				try {
					seq = new Sequence(0.0f, divisions); //division type for the music set to pulse per quarter
				} catch (InvalidMidiDataException e) {
					e.printStackTrace();
				}

				for(int noteNum = 0; noteNum < 128; noteNum++) {
					if(tone.equals(Note.getToneString((byte) noteNum))) {
						noteNum+=1;
						/*
                        System.out.println("got the note number " + noteNum + " from step: " + tone);
                        System.out.println("octave: " + octave);
                        System.out.println("tone: " + tone);
						 */
						channel.noteOn(noteNum, 78);
						sleepFor(500);
					}
				}


			}
		}
		channel.allNotesOff();
	}
	
	/*
	 * checks if the given note has a tag of chord on it
	 * and 
	 */

	public int getIncrementedTick_NoteON() {
		tick_noteON+=10;
		return tick_noteON;
	}
	public int getIncrementedTick_NoteOFF() {
		tick_noteOFF+=10;
		return tick_noteOFF;
	}
	private Sequencer getSequencer() {
		if (!SEQ_DEV_NAME.isEmpty()
				|| !"default".equalsIgnoreCase(SEQ_DEV_NAME)) {
			System.setProperty(SEQ_PROP_KEY, SEQ_DEV_NAME);
		}

		try {
			return MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			System.err.println("Error getting sequencer");
			e.printStackTrace();
			return null;
		}
	}	
	
	private void startSequencer(Sequence seq) {
		try {
			sequencer.open();
		} catch (MidiUnavailableException e){
			e.printStackTrace();
		}
		
		sequencer.setTempoInBPM(144.0f);
		System.out.println("tempo of sequencer " + sequencer.getTempoInBPM());
		try {
			sequencer.setSequence(seq);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		
		Long waitTime = Long.valueOf(500);
		sleepFor(waitTime);
		sequencer.start();
		
		while (sequencer.isRunning()) {
			sleepFor(200);
		}
		
		sleepFor(waitTime);
		sequencer.close();
	}
	
	private void sleepFor(long millis) {
		try { Thread.sleep(millis); // wait time in milliseconds to control duration
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
	private int incrementTick(int duration, int counterOfTickFactor) {
		int dur = duration+(6*counterOfTickFactor);
//		System.out.println("dur " + dur);
		return dur;
	}
}
