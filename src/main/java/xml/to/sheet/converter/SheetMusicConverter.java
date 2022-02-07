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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SheetMusicConverter {
	
	private Score score;
	private MusicXMLCreator mxlc;
	private MainViewController mvc;
	private static final String FILENAME = null;
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
		
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 try {
			 dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File("my-file.xml"));
	          
	          // we want to extract data measure by measure so we iterate through
	          // list of measure nodes
	           
	          NodeList measurelist = doc.getElementsByTagName("measure");
	          for (int i = 0; i < measurelist.getLength(); i++) {
	              
	        	  // measureNode will contain the ith measure in the measureList
	        	  Node measureNode = measurelist.item(i);	
	              
	              if (measureNode.getNodeType() == Node.ELEMENT_NODE) {
	            	  Element element = (Element) measureNode;
	            	  
	            	  // the noteList contains all the notes in the ith measure
	            	  NodeList notelist = element.getElementsByTagName("note");
	            	  
	            	  for(int j=0; j<notelist.getLength();j++) {
	            		  
	            		  // the noteNode will contain the jth note in the measure
	            		  Node noteNode = notelist.item(j);
	            		  
	            		  if (noteNode.getNodeType() == Node.ELEMENT_NODE) {
	    	            	  Element element2 = (Element) noteNode;
	    	            	  
	    	            	  // the pitchlist contains the pitch of each note (1 pitch per note)
	    	            	  NodeList pitchlist = element2.getElementsByTagName("pitch");
	    	            	  
	    	            	  Node pitch = pitchlist.item(0);
	    	            	  
	    	            	  if (pitch.getNodeType() == Node.ELEMENT_NODE) {
		    	            	  Element element3 = (Element) pitch;
		    	            	  
		    	            	  String step = element3.getElementsByTagName("Step").item(0).getTextContent();
		    	            	  String alter = element3.getElementsByTagName("alter").item(0).getTextContent();
		    	            	  String octave = element3.getElementsByTagName("octave").item(0).getTextContent();
	    	            	  }
	            		  }
	            		  
	            	  }
	              }
	          }
		 }
		 catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace(); 
		 }
	}
}
		 
