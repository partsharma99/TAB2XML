package GUI;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;

import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class PlayThread extends Thread {

	private ScorePartwise2 sc;
    private Synthesizer midiSynth;
	private MidiChannel channel;
	private PlayTabController controller;
    
	public PlayThread(PlayTabController controller, ScorePartwise2 sc) {
		this.sc = sc;
    	this.controller = controller;
	}
	
	public void run() {
		
		if(sc.getInstrumentName().equalsIgnoreCase("drumset")) {
			controller.composeDrumset();

		} else if(sc.getInstrumentName().equalsIgnoreCase("Guitar")) {
			controller.composeGuitar();
		}
	}

}
