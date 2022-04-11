package GUI;

import java.io.IOException;

/*
 * 1237pm on mar 13: stopped at making Mary had a little lamb play w the method composedrumset
 * want to experiment with the tempo, duration, velocity to see what effect they have on the sound
 * this will be translated to the drumset and guitar sound
 * 
 * 
 * 1109pm on mar 14: sure that this system now identifies chords and can play the notes at a 
 * suitable interval. Need to figurer out a better way to convert from the duration to ticks
 * and how to play the chords because the chords are not playing in the expected way (can't tell the separate notes)
 * 
 * april 3 working on the chords. revised approach at playing notes - started using prevNoteDurations rather than
 * currNoteDurations to play curr notes. need to calculate spacing of chords based on duration and tickFactor
 */
 //perhaps it might be helpful to examine how many sequences are being created in one run
 //when looping through notes

import java.util.List;

import javax.sound.midi.Instrument;
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
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;
import org.jfugue.integration.MusicXmlParser;
import org.jfugue.integration.MusicXmlParserListener;
import org.jfugue.midi.MidiParser;
import org.jfugue.midi.MidiParserListener;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.rhythm.Rhythm;
import org.staccato.StaccatoParserListener;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import xml.to.sheet.converter.POJOClasses.Measure2;
import xml.to.sheet.converter.POJOClasses.Note2;
import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class Misc extends Thread{
    private MainViewController mvc;
    private ScorePartwise2 sc;
    private Synthesizer midiSynth;
    private Sequencer sequencer;
    private Sequence seq;
    
    @FXML AnchorPane tabPlayerPane;
    @FXML Button playButton;
    @FXML Button pauseButton;
    @FXML Slider slider;
    
    private int tick_noteON;
    private int tick_noteOFF;
    private final String SEQ_DEV_NAME = "default";
    private final String SEQ_PROP_KEY = "javax.sound.midi.Sequence";
	
    
    public Misc(ScorePartwise2 inputSC, Synthesizer inputSynth, MainViewController mvc) {
    	this.sc = inputSC;
    	this.midiSynth = inputSynth;
    	this.sequencer = getSequencer();
    	
    	this.mvc = mvc;
    	tick_noteON =0;
    	tick_noteOFF =1;
    	
    	//setSliderSize();
    }
    
    @FXML
    public void setSliderSize() {
    	slider.setLayoutX(tabPlayerPane.getWidth() - 210);
    	slider.setLayoutY(tabPlayerPane.getHeight() - 200);
	}
    
    @FXML 
	public void play() throws JAXBException {
//    	if(!playThread.isAlive()) {
//    		playThread.start();
//    	}
    	
    	MidiChannel thisChannel=null;
    	if(sc.getInstrumentName().equalsIgnoreCase("drumset")) {
			this.composeDrumset2(midiSynth.getChannels()[9], 9);

		} else if(sc.getInstrumentName().equalsIgnoreCase("Guitar")) {
			this.composeGuitar(midiSynth.getChannels()[0],0);
		}
	}
	
	@SuppressWarnings("unused")
	public void composeDrumset1(MidiChannel thisChannel, int i) {
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

	public void startProgressBar() {
		
	}
	
	public void composeDrumset(MidiChannel channel, int channelIndex) {
		MusicXmlParserListener jfListener = new MusicXmlParserListener();
		MusicXmlParser jfParser;
		StaccatoParserListener staccListener = new StaccatoParserListener();
		try {
			jfParser = new MusicXmlParser();
			jfParser.addParserListener(jfListener);
			jfParser.addParserListener(staccListener);
			jfParser.parse(mvc.converter.getMusicXML());
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String patternString = staccListener.getPattern().toString();
		
		Pattern pattern = new Pattern(staccListener.getPattern().setVoice(9));
		System.out.println(pattern.getPattern());
		pattern.setInstrument("Drumset");
		
		Player player = new Player();
		player.play(pattern);
		
		
		
		Rhythm rhythm = new Rhythm()
		        .addLayer("x                        x       ")
		        .addLayer("  x x x x x x x                  ")
		        .addLayer("    o       o    oooo            ")
		        .addLayer("                     oo          ")
		        .addLayer("                       oo        ")
		        .addLayer("o       o        o       o       ");
		    //new Player().play(rhythm.getPattern().repeat(2));
		
		   
	   
	}
	public void composeDrumset2(MidiChannel channel, int channelIndex) {
		List<Measure2> allMeasures = sc.getListOfParts().get(0).getListOfMeasures();
		int tickFactor = 0;
		
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
	
				/*
				 * usual ID is in the format P1-I[ID]
				 * so the following code will get the integers 
				 * from the last two characters in thisID
				 */

				String numStr = "";
				numStr += thisID.charAt(thisID.length()-2);
				numStr += thisID.charAt(thisID.length()-1);

				
				int note = Integer.parseInt(numStr);
				System.out.println("ID: " + note);
				int volume = 90;
				/*
				System.out.println("The string is: " + numStr);
                System.out.println("THe instrument is " + notes.get(j).getInstrument().toString());
				*/	
				
				if(j == 0) {
					addMidiEvent(track, ShortMessage.NOTE_ON, channelIndex, note, volume, incrementTick(0, tickFactor));
					addMidiEvent(track, ShortMessage.NOTE_OFF, channelIndex, note, 0, incrementTick(0, tickFactor)+1);
				}else {
					int prevDuration = notes.get(j-1).getDuration();
					if(notes.get(j).getChord() == null) {
						addMidiEvent(track, ShortMessage.NOTE_ON, channelIndex, note, volume, incrementTick(prevDuration, tickFactor));
						addMidiEvent(track, ShortMessage.NOTE_OFF, channelIndex, note, 0, incrementTick(prevDuration, tickFactor)+1);
						System.out.println("incrementTick noteON: " + incrementTick(prevDuration, tickFactor));
						System.out.println("incrementTick noteOFF: " + incrementTick(prevDuration, tickFactor)+1);
					}else {
						addMidiEvent(track, ShortMessage.NOTE_ON, channelIndex, note, volume, incrementTick(prevDuration, tickFactor-1)+2);
						addMidiEvent(track, ShortMessage.NOTE_OFF, channelIndex, note, 0, incrementTick(prevDuration, tickFactor-1)+3);
						System.out.println("The note at position " + j + "has a chord");
					}
				}
				
				
				
				
				tickFactor+=1; 
//				System.out.println("duration " + duration);
				numStr="";
			}
			
			
		}
		//Player player = new Player(); player.play(seq);
		//startSequencer(seq);
//		System.out.println("How many tracks: " + seq.getTracks().length);
//		System.out.println("microsec length: " + seq.getMicrosecondLength());
//		System.out.println("Tick length: " + seq.getTickLength());
//		System.out.println("Number of events " + seq.getTracks()[0].size());
		
	}

	public void composeGuitar(MidiChannel channel, int channelIndex) {
		MusicXmlParserListener jfListener = new MusicXmlParserListener();
		MusicXmlParser jfParser;
		StaccatoParserListener staccListener = new StaccatoParserListener();
		try {
			jfParser = new MusicXmlParser();
			jfParser.addParserListener(jfListener);
			jfParser.addParserListener(staccListener);
			jfParser.parse(mvc.converter.getMusicXML());
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Pattern pattern = new Pattern(staccListener.getPattern());
		
		Player player = new Player();
		player.play(pattern);
		
		/*
		 * 
		 
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
						 *
						channel.noteOn(noteNum, 78);
						sleepFor(500);
					}
				}


			}
		}
		channel.allNotesOff();
		*/
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
		int completion = 0;
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
			if(playButton.isPressed() && sequencer.isOpen()) {
				pause();
				System.out.println("paused");
			}
			slider.setValue(completion); completion++;
			//sleepFor(200);
			
		}
		
		sleepFor(waitTime);
		//sequencer.close();
	}
	
	@SuppressWarnings("static-access")
	private void sleepFor(long millis) {
		try { Thread.sleep(millis); // wait time in milliseconds to control duration
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
	
	private int incrementTick(int duration, int tickFactor) {
		
		return (duration+(6*tickFactor));
	}
	
	@FXML
	public void exit() {
		try {
			mvc.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void pause() {
		if(sequencer.isOpen()) {
			sequencer.stop(); System.out.println("paused");
		}
		
	}

	public MainViewController getMvc() {
		return mvc;
	}

	public ScorePartwise2 getSc() {
		return sc;
	}

	public Synthesizer getMidiSynth() {
		return midiSynth;
	}

	public Sequence getSeq() {
		return seq;
	}
}
