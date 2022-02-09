package xml.to.sheet.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class Test_SheetMusicParserListener {

	@Test
	public void test1() {
		File musicxmlFile = new File("demo1music.xml");
		SheetMusicParserListener parserListener = 
				new SheetMusicParserListener(musicxmlFile);
		
		assertTrue(parserListener.getMusicXmlAsString().equals(""));
	}

}
