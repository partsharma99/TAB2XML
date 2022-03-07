package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class HammerOn2 {
	
	private int number;
	private String type;
	private String text;
	
	public HammerOn2() {
	}

	public HammerOn2(int number, String type, String text) {
		this.number = number;
		this.type = type;
		this.text = text;
	}
	
	@XmlAttribute
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "HammerOn2 [number=" + number + ", type=" + type + ", text=" + text + "]";
	}
	
}
