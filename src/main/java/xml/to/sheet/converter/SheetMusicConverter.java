package xml.to.sheet.converter;
import converter.*;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import utility.MusicXMLCreator;
import GUI.*;
public class SheetMusicConverter {
	
	private Score score;
	private MusicXMLCreator mxlc;
	private MainViewController mvc;
	
	public SheetMusicConverter(MainViewController mvc, Score score) {
		this.mvc = mvc;
		this.score = score;
	}
	public void update() {
		score = new Score(mvc.mainText.getText());
		mxlc = new MusicXMLCreator(score);
	}
	
	public String getMusicXML() {
		return mxlc.generateMusicXML();
		}
	
	public Score getScore() {
		return this.score;
	}
	@FXML
	public void musicXMLtoSheet() throws IOException {
		
		String xml = this.getMusicXML();
		
		java.io.FileWriter fw = new java.io.FileWriter("my-file.xml");
		fw.write(xml);
		fw.close();
		
		}
}
