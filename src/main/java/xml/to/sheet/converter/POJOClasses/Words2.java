package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Words2 {
	
	private double relativex;
	private double relativey;
	private String text;
	
	public Words2() {
	}

	public Words2(double relativex, double relativey, String text) {
		this.relativex = relativex;
		this.relativey = relativey;
		this.text = text;
	}

	@XmlAttribute(name = "relative-x")
	public double getRelativex() {
		return relativex;
	}

	public void setRelativex(double relativex) {
		this.relativex = relativex;
	}

	@XmlAttribute(name = "relative-y")
	public double getRelativey() {
		return relativey;
	}

	public void setRelativey(double relativey) {
		this.relativey = relativey;
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
		return "Words2 [relativex=" + relativex + ", relativey=" + relativey + ", text=" + text + "]";
	}
	
}
