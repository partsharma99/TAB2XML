package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Pulloff2 {
	
	private int number;
	private String type;
	private String text;
	
	public Pulloff2() {
	}

	public Pulloff2(int number, String type, String text) {
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
		return "Pulloff2 [number=" + number + ", type=" + type + ", text=" + text + "]";
	}
	
}
