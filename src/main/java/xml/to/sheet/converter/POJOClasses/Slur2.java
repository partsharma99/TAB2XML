package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;

public class Slur2 {
	
	private int number;
	private String placement;
	private String type;

	public Slur2() {
	}

	public Slur2(int number, String placement, String type) {
		this.number = number;
		this.placement = placement;
		this.type = type;
	}
	
	@XmlAttribute
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	@XmlAttribute
	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
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
		return "Slur2 [number=" + number + ", placement=" + placement + ", type=" + type + "]";
	}
	
}
