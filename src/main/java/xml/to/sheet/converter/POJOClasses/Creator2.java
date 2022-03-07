package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Creator2 {
	
	private String type;

	public Creator2() {
	}

	public Creator2(String type) {
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
		return "Creator2 [type=" + type + "]";
	}
	
}
