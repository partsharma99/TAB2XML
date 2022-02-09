package xml.to.sheet.converter;

import java.awt.Graphics;
import java.io.File;
import java.util.List;

import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;
import org.jfugue.integration.MusicXmlParser;
import org.jfugue.integration.MusicXmlParserListener;
import org.jfugue.parser.Parser;
import org.jfugue.parser.ParserListener;
import org.jfugue.tools.GetInstrumentsUsedTool;


public class SheetMusicParserListener extends JPanel implements ParserListener {
	/*
	 * The conversion from Musicxml to sheet music 
	 * using specific methods on the parser listener
	 * 
	 * Jpanel will be used to section the symbols that will be drawn
	 * in spaces called "panels"
	 * 
	 */
	
	private java.io.File musicXmlFile;
	private List<ThingToDraw> thingsToDraw;
	
	private MusicXmlParser parser;
	private MusicXmlParserListener parserListener1;
	
	public SheetMusicParserListener(java.io.File musicXmlFile) {
		//super();
		this.musicXmlFile = musicXmlFile;
		try {
			parser = new MusicXmlParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parserListener1 = new MusicXmlParserListener();
		parser.addParserListener(parserListener1);
	}
	
	public SheetMusicParserListener(String fileLocation) {
		//work in progress	
	}
	
	public void draw(Graphics g) {
		//define what "drawing" is for a single parsed object
	}
	
	@Override
	public void paint(Graphics g) {
		for (ThingToDraw thingToDraw : thingsToDraw) {
			thingToDraw.draw(g);
		}
	}
	
	@Override
	public void onNoteParsed(Note note) {
		thingsToDraw.add(new NoteDrawing(note));
		
	}
	public java.io.File getSheetMusic() { 
		
		try {
			parser.parse(musicXmlFile);
			parser.fireAfterParsingFinished();
			
		}catch(Exception ex) {

		}
		return musicXmlFile;
	}
	
	public String getMusicXmlAsString() {
		return parserListener1.getMusicXMLString();
	}

	@Override
	public void beforeParsingStarts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterParsingFinished() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackChanged(byte track) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLayerChanged(byte layer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInstrumentParsed(byte instrument) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTempoChanged(int tempoBPM) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeySignatureParsed(byte key, byte scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTimeSignatureParsed(byte numerator, byte powerOfTwo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBarLineParsed(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackBeatTimeBookmarked(String timeBookmarkId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackBeatTimeBookmarkRequested(String timeBookmarkId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTrackBeatTimeRequested(double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPitchWheelParsed(byte lsb, byte msb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChannelPressureParsed(byte pressure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPolyphonicPressureParsed(byte key, byte pressure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSystemExclusiveParsed(byte... bytes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerEventParsed(byte controller, byte value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLyricParsed(String lyric) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarkerParsed(String marker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFunctionParsed(String id, Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNotePressed(Note note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNoteReleased(Note note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChordParsed(Chord chord) {
		// TODO Auto-generated method stub
		
	}
	
    
    /*
    public void write(File outputFile, Pattern musicPattern) throws Exception {
        MusicXmlRenderer renderer = new MusicXmlRenderer();
        
        MusicStringParser parser = new MusicStringParser();
        parser.addParserListener(renderer);
        parser.parse(musicPattern);
        
        FileHelper fileHelper = new FileHelper();
        fileHelper.write(outputFile, renderer.getMusicXMLString());
    }*/
  /*  
    public Pattern read(File inputFile) throws Exception{
        MusicStringRenderer renderer = new MusicStringRenderer();

        MusicXmlParser parser = new MusicXmlParser();
            
        parser.addParserListener(renderer);
        parser.parse(inputFile);
        
        return renderer.getPattern();
    }*/
}
