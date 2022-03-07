package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Bend2 {
	
	private double bendalter;

	public Bend2() {
	}

	public Bend2(double bendalter) {
		this.bendalter = bendalter;
	}
	
	@XmlElement(name = "bend-alter")
	public double getBendalter() {
		return bendalter;
	}

	public void setBendalter(double bendalter) {
		this.bendalter = bendalter;
	}

	@Override
	public String toString() {
		return "Bend2 [bendalter=" + bendalter + "]";
	}

}
