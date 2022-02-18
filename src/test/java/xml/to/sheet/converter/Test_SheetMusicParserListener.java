package xml.to.sheet.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class Test_SheetMusicParserListener {

	@Test
	public void test1() {
		//File musicxmlFile = new File("src/main/resources/musicXmlFiles/demo1.musicxml");
		SheetMusicParserListener parserListener = 
				new SheetMusicParserListener("src/main/resources/musicXmlFiles/demo1.musicxml");
		
		assertTrue(parserListener.getMusicXmlAsString().equals(""));
	}

}
