package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Beam2 {
	
	private int number;
	private String type;
	
	public Beam2() {
	}

	public Beam2(int number, String type) {
		this.number = number;
		this.type = type;
	}
	
	@XmlAttribute
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Beam2 [number=" + number + ", type=" + type + "]";
	}
	
}
