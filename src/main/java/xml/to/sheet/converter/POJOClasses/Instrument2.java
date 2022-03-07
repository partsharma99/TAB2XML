package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Instrument2 {
	
	private String id;

	public Instrument2() {
	}

	public Instrument2(String id) {
		this.id = id;
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
		return "Instrument2 [id=" + id + "]";
	}
	
}
