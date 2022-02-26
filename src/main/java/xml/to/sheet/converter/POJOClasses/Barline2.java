package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Barline2 {
	
	private String barstyle;
	private Repeat2 repeat;
	private String location;
	
	public Barline2() {
	}

	public Barline2(String barstyle, Repeat2 repeat, String location) {
		this.barstyle = barstyle;
		this.repeat = repeat;
		this.location = location;
	}
	
	@XmlElement(name = "bar-style")
	public String getBarstyle() {
		return barstyle;
	}

	public void setBarstyle(String barstyle) {
		this.barstyle = barstyle;
	}
	
	@XmlElement
	public Repeat2 getRepeat() {
		return repeat;
	}

	public void setRepeat(Repeat2 repeat) {
		this.repeat = repeat;
	}

	@XmlAttribute
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Barline2 [barstyle=" + barstyle + ", repeat=" + repeat + ", location=" + location + "]";
	}
	
}
