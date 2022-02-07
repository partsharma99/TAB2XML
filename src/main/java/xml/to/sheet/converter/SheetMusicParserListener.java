package xml.to.sheet.converter;

import java.io.File;

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
	
	private java.io.File musicXmlFile;
	
	public SheetMusicParserListener(java.io.File musicXmlFile) {
		this.musicXmlFile = musicXmlFile;
	}
	
	/*
	 * The conversion from Musicxml to sheet music will be done
	 * using the MusicXmlParser class in particular
	 */

	public java.io.File getSheetMusic() throws ParserConfigurationException {
		MusicXmlParser parser = new MusicXmlParser();
		MusicXmlParserListener parserParserListener1 = new MusicXmlParserListener();
		parser.addParserListener(parserParserListener1); 
		
		try {
			parser.parse(musicXmlFile);
			parser.fireAfterParsingFinished();
			
		}catch(Exception ex) {

		}
		return musicXmlFile;
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
	public void onNoteParsed(Note note) {
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
