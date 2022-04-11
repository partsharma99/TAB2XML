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

public class PlayTabController extends Thread{
    private MainViewController mvc;
    private ScorePartwise2 sc;
    private Player player;
    
    @FXML AnchorPane tabPlayerPane;
    @FXML Button playButton;
    @FXML Button pauseButton;
    @FXML Slider slider;
	  
    public PlayTabController(ScorePartwise2 inputSC, MainViewController mvc) {
    	this.sc=inputSC;
    	this.mvc=mvc;
    	player = new Player();
    }
    
    @FXML
    public void setSliderSize() {
	}
    
    @FXML 
	public void play() throws JAXBException {
    	Thread playThread = new PlayThread(this, sc);
    	playThread.start();
	}
	
    private String getDurationLetter(String type) {
    	String duration="";
    	switch(type) {
    	case ("one-twenty-eighth"): duration="o";break;
    	case ("128th"): duration="o";break;
    	case ("sixty-fourth"): duration="x";break;
    	case ("64th"): duration="x";break;
    	case ("thirty-second"): duration="t";break;
    	case ("32nd"): duration="t";break;
    	case ("sixteenth"): duration="s";break;
    	case ("16th"): duration="s";break;
    	case ("eighth"): duration="i";break;
    	case ("quarter"): duration="q";break;
    	case ("half"): duration="h";break;
    	case ("whole"): duration="w";break;
    	}
    	return duration;
    }
    private int getIDDigit(String thisID) {
    	/*
		 * usual ID is in the format P1-I[ID]
		 * so the following code will get the integers 
		 * from the last two characters in thisID
		 */
    	
    	String numStr = "";
		numStr += thisID.charAt(thisID.length()-2);
		numStr += thisID.charAt(thisID.length()-1);
		int note = Integer.parseInt(numStr);
		
		return note;
    }
    private Pattern getDrumPattern() {
    	Pattern pattern =new Pattern("V9");
    	List<Measure2> allMeasures = sc.getListOfParts().get(0).getListOfMeasures();

    	for(int i=0; i < allMeasures.size(); i++) {
    		List<Note2> notes = allMeasures.get(i).getListOfNotes();
    		int divisions = allMeasures.get(i).getAttributes().getDivisions();

    		for(int j=0; j < notes.size(); j++) {
    			String thisID = notes.get(j).getInstrument().getId();
    			int lastNoteIndex = notes.size()-1;
    			
    			boolean lastNoteIsChord = false;
				if(notes.get(notes.size()-1).getChord() != null) lastNoteIsChord=true;
    			
    			if( ((j!= lastNoteIndex) && (notes.get(j+1).getChord() != null)) || ((j== lastNoteIndex) && (lastNoteIsChord == true)) ){
    				int k=j; String chord1=""; String chord="";
    				
    				while((k!= lastNoteIndex) && (notes.get(k+1).getChord() != null || notes.get(k).getChord() != null)) {
    					chord1+=(getIDDigit(notes.get(k).getInstrument().getId()) + getDurationLetter(notes.get(k).getType()) + " ");
    					k+=1;
    				}
    				if((k== lastNoteIndex) && (lastNoteIsChord == true) && (j!= lastNoteIndex)) {
        				//append the note to the end of the last chord in the pattern 
    					chord1+=(getIDDigit(notes.get(k).getInstrument().getId()) + getDurationLetter(notes.get(k).getType()) + " ");
        				
        			}
    					 chord1=chord1.replace(' ', '+');
    					 for(int l=0; l<chord1.length()-1; l++) chord+=chord1.charAt(l);
    				
    				pattern.add(chord);
    			}
    			 
    			else if(notes.get(j).getChord() != null) continue;
    			else{
    				pattern.add(getIDDigit(thisID) + getDurationLetter(notes.get(j).getType()));		
    			}

    			/*
				System.out.println("The string is: " + numStr);
                System.out.println("THe instrument is " + notes.get(j).getInstrument().toString());
    			 */	
    		}
    	}
    	System.out.println("pattern from drum: " + pattern.getPattern());
    	return pattern;
    }
	public void composeDrumset() {
		player.play(this.getDrumPattern());
	}
		

	public void composeGuitar() {
		Player player = new Player();
		//player.play(pattern);
	}
		
private void sleepFor(long millis) {
		try { Thread.sleep(millis); // wait time in milliseconds to control duration
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
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
		ManagedPlayer mplayer = player.getManagedPlayer();
	 	boolean pause = false;
	 	
	 	if(!pause) {

 			mplayer.pause();
 			pause = true;

 		}else {

 			mplayer.resume();
 			pause = false;

 		}
		
	}

	public MainViewController getMvc() {
		return mvc;
	}

	public ScorePartwise2 getSc() {
		return sc;
	}

}