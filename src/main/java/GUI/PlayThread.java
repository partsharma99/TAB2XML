package GUI;

import xml.to.sheet.converter.POJOClasses.ScorePartwise2;

public class PlayThread extends Thread {

	private ScorePartwise2 sc;
	private PlayTabController controller;
	
    
	public PlayThread(PlayTabController controller, ScorePartwise2 sc) {
		this.sc = sc;
    	this.controller = controller;
	}
	
	public void run() {
		//playing the music using the jaxb parser on a note-by-note basis
		if(sc.getInstrumentName().equalsIgnoreCase("drumset")) {
			controller.composeDrumset();

		} else if(sc.getInstrumentName().equalsIgnoreCase("Guitar") || sc.getInstrumentName().equalsIgnoreCase("Bass")) {
			controller.composeGuitar();
		}
	}

}