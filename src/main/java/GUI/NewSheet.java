package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

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
		int x;
		int y;
		int framex=420;
		int framey=420;
		
		public NewSheet(String xml){
			XmlConverter nn = new XmlConverter();
//			try {
//				JAXBContext jc = JAXBContext.newInstance(XmlConverter.class);
//
//		        Unmarshaller unmarshaller = jc.createUnmarshaller();
//		        nn = (XmlConverter) unmarshaller.unmarshal(new StringReader(xml));
//		        }
//				catch (JAXBException e) 
//				{
//				  e.printStackTrace();
//				}
			
			frame.setContentPane(new DrawPane());
			
			JLabel label= new JLabel(InstrumentType.getInstrumentType(xml));
			JLabel label2= new JLabel(String.valueOf(Lines.getLines(xml)));
//			JLabel label3 = new JLabel(nn.getSign());
			JLabel label4 = new JLabel(String.valueOf(Noteamount.getSize(xml)));
			ArrayList<Integer> yaxis =NoteInfo.GgetY(xml);
			ArrayList<Integer> xaxis =NoteInfo.GgetX(xml);
			ArrayList<Integer> fret =NoteInfo.Ggetfret(xml);

			//loop will tun to the amount of notes found in xml to draw each note
			
			System.out.print(Noteamount.getSize(xml));
			//NoteInfo.GgetY(xml);

			
			
			
			
			label.setBounds(0,200,100,50);
			label.setFont(new Font(null,Font.PLAIN,25));
			label2.setBounds(0,240,100,50);
			label2.setFont(new Font(null,Font.PLAIN,25));
//			label3.setBounds(0,270,100,50);
//			label3.setFont(new Font(null,Font.PLAIN,25));
			label4.setBounds(0,270, 100, 50);
			label4.setFont(new Font(null, Font.PLAIN, 25));
for(int i=0;i<Noteamount.getSize(xml);i++) {
				
				JLabel note = new JLabel(String.valueOf(NoteInfo.Ggetfret(xml).get(i)));
				
				// x= note line
				// y=note timing
				

				y=20+(yaxis.get(i)-1)*30;
				x=20+(xaxis.get(i)-1)*30;
				
				
				note.setBounds(x, y, x, y);
				frame.add(note);
				
				//extending the frame
				//framex=framex+10;
				//framey=framey+10;
				System.out.println(yaxis);
				
			}
			frame.add(label);
//			frame.add(label2);
//			frame.add(label3);
			frame.add(label4);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(framex,framey);
			frame.setLayout(null);
			frame.setVisible(true);
//			
			
		}
		
//		
	

}
