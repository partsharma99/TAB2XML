package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

public class Key2 {
	
	private int fifths;
		
	public Key2() {
	}
	
	public Key2(int fifths) {
		this.fifths = fifths;
	}
	
	@XmlElement
	public int getFifths() {
		return fifths;
	}

	public void setFifths(int fifths) {
		this.fifths = fifths;
	}

	@Override
	public String toString() {
		return "Key2 [fifths=" + fifths + "]";
	}

}
