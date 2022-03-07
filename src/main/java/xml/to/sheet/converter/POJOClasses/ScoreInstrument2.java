package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class ScoreInstrument2 {
	
	private String instrumentname;
	private String id;
		
	public ScoreInstrument2() {	
	}

	public ScoreInstrument2(String instrumentname, String id) {
		this.instrumentname = instrumentname;
		this.id = id;
	}
	
	@XmlElement(name = "instrument-name")
	public String getInstrumentname() {
		return instrumentname;
	}

	public void setInstrumentname(String instrumentname) {
		this.instrumentname = instrumentname;
	}
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ScoreInstrument2 [instrumentname=" + instrumentname + ", id=" + id + "]";
	}
	
}
