package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;

public class Repeat2 {
	
	private String direction;
	private int times;

	public Repeat2() {
	}
	
	public Repeat2(String direction, int times) {
		this.direction = direction;
		this.times = times;
	}
	
	@XmlAttribute
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	@XmlAttribute
	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return "Repeat2 [direction=" + direction + ", times=" + times + "]";
	}

}
