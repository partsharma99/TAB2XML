package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.jfugue.integration.MusicXmlParser;
import org.staccato.StaccatoParserListener;

import converter.Converter;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import utility.MusicXMLCreator;
import xml.to.sheet.converter.DrawPane;

public class NewSheet extends MainViewController {
		JFrame frame = new JFrame();
		JLabel label=new JLabel();
		//private MusicXMLCreator mxlc;
		
		
		public NewSheet(String xml){
			XmlConverter nn = new XmlConverter();
			try {
				JAXBContext jc = JAXBContext.newInstance(XmlConverter.class);

		        Unmarshaller unmarshaller = jc.createUnmarshaller();
		        nn = (XmlConverter) unmarshaller.unmarshal(new StringReader(xml));
		        }
				catch (JAXBException e) 
				{
				  e.printStackTrace();
				}
			
			frame.setContentPane(new DrawPane());
			JLabel label= new JLabel(InstrumentType.getInstrumentType(xml));
			JLabel label2= new JLabel(String.valueOf(Lines.getLines(xml)));
			JLabel label3 = new JLabel(nn.getSign());
			label.setBounds(0,200,100,50);
			label.setFont(new Font(null,Font.PLAIN,25));
			label2.setBounds(0,240,100,50);
			label2.setFont(new Font(null,Font.PLAIN,25));
			label3.setBounds(0,270,100,50);
			label3.setFont(new Font(null,Font.PLAIN,25));
			frame.add(label);
			frame.add(label2);
			frame.add(label3);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(420,420);
			frame.setLayout(null);
			frame.setVisible(true);
//			
			
		}
		
//		
	

}
