package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Measure2 {
	
	private Attributes2 attributes;
	private List<Note2> listOfNotes;
	private List<Barline2> listOfBarlines;
	private Direction2 direction;
	private int number;
	
	public Measure2() {
	}

	public Measure2(Attributes2 attributes, List<Note2> listOfNotes, List<Barline2> listOfBarlines,
			Direction2 direction, int number) {
		this.attributes = attributes;
		this.listOfNotes = listOfNotes;
		this.listOfBarlines = listOfBarlines;
		this.direction = direction;
		this.number = number;
	}
	
	@XmlElement
	public Attributes2 getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes2 attributes) {
		this.attributes = attributes;
	}

	@XmlElement(name = "note")
	public List<Note2> getListOfNotes() {
		return listOfNotes;
	}

	public void setListOfNotes(List<Note2> listOfNotes) {
		this.listOfNotes = listOfNotes;
	}
	
	@XmlElement(name = "barline")
	public List<Barline2> getListOfBarlines() {
		return listOfBarlines;
	}

	public void setListOfBarlines(List<Barline2> listOfBarlines) {
		this.listOfBarlines = listOfBarlines;
	}
	
	@XmlElement
	public Direction2 getDirection() {
		return direction;
	}

	public void setDirection(Direction2 direction) {
		this.direction = direction;
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
		return "Measure2 [attributes=" + attributes + ", listOfNotes=" + listOfNotes + ", listOfBarlines="
				+ listOfBarlines + ", direction=" + direction + ", number=" + number + "]";
	}
	
}
