package GUI;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.integration.MusicXmlParser;
import org.staccato.StaccatoParserListener;

import converter.Converter;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import utility.MusicXMLCreator;

public class NewSheet extends MainViewController {
	JFrame frame = new JFrame();
	JLabel label=new JLabel();
	private MusicXMLCreator mxlc;

	
		public NewSheet(String xml){
			
		JLabel label=new JLabel(InstrumentType.getInstrumentType(xml));
		JLabel label2=new JLabel(String.valueOf(Lines.getLines(xml)));
			label.setBounds(0,0,100,50);
			label.setFont(new Font(null,Font.PLAIN,25));
			label2.setBounds(0,200,100,50);
			label2.setFont(new Font(null,Font.PLAIN,25));
			frame.add(label);
			frame.add(label2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(420,420);
			frame.setLayout(null);
			frame.setVisible(true);
			
			
		}
		
		
	

}
