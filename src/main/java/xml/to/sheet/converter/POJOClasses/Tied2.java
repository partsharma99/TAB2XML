package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Tied2 {
	
	private String type;

	public Tied2() {
	}

	public Tied2(String type) {
		this.type = type;
	}
	
	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Tied2 [type=" + type + "]";
	}
	
}
