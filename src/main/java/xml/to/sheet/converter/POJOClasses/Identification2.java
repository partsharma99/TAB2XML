package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

public class Identification2 {
	
	private Creator2 creator;

	public Identification2() {
	}
	
	public Identification2(Creator2 creator) {
		this.creator = creator;
	}
	
	@XmlElement
	public Creator2 getCreator() {
		return creator;
	}

	public void setCreator(Creator2 creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Identification2 [creator=" + creator + "]";
	}

}
