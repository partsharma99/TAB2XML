package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Slide2 {
	
	private String type;
	private int number;

	public Slide2() {
	}

	public Slide2(String type, int number) {
		this.type = type;
		this.number = number;
	}
	
	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Slide2 [type=" + type + ", number=" + number + "]";
	}
	
}
