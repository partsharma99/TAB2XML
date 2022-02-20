package GUI;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="part")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlConverter {
	
	
	

	    @XmlElementWrapper(name="attributes")
	    @XmlElement(name = "divisions")
	    private int divisions;

	    @XmlElementWrapper(name="clef")
	    @XmlElement(name="sign")
	    private String sign;
	    @XmlElement(name="line")
	    private int line;
	    
	    @XmlElementWrapper(name="measure")
	    @XmlElement(name="note")
	    private List<Note> notes;

//	    @XmlElementWrapper(name="RemainingChildList")
//	    @XmlElement(name="ChildID")
//	    private List<String> remainingChildren;
	
//	public XmlConverter(String xml) {
//		JAXBContext jaxbContext;
//		Note note = new Note();
//		try {
//			 jaxbContext = JAXBContext.newInstance(Note.class);
//			 Unmarshaller jaxbUnmarshallar = jaxbContext.createUnmarshaller();
//			 note = (Note) jaxbUnmarshallar.unmarshal(new StringReader(xml));
//			 System.out.println(note);
//		}
//		catch(JAXBException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Note getNotes(String xml) {
//		JAXBContext jaxbContext;
//		Note note = new Note();
//		try {
//			 jaxbContext = JAXBContext.newInstance(Note.class);
//			 Unmarshaller jaxbUnmarshallar = jaxbContext.createUnmarshaller();
//			 note = (Note) jaxbUnmarshallar.unmarshal(new StringReader(xml));
//			 System.out.println(note);
//		}
//		catch(JAXBException e) {
//			e.printStackTrace();
//		}
//		return note;
//	}
	    
	    public List<Note> getNotes(){
	    	return this.notes;
	    }
	    
	    public String getSign() {
	    	System.out.println(sign);
	    	return sign;
	    	
	    }
}
