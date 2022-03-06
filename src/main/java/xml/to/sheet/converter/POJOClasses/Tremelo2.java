package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Tremelo2 {
	
	private String type;
	private int text;

	public Tremelo2() {
	}
	
	public Tremelo2(String type, int text) {
		this.type = type;
		this.text = text;
	}
	
	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement
	public int getText() {
		return text;
	}

	public void setText(int text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Tremelo [type=" + type + ", text=" + text + "]";
	}
	
}

