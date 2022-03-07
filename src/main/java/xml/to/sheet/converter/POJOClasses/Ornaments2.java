package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Ornaments2 {
	
	private Tremelo2 tremelo;
	
	public Ornaments2() {
	}

	public Ornaments2(Tremelo2 tremelo) {
		this.tremelo = tremelo;
	}
	
	@XmlElement
	public Tremelo2 getTremelo() {
		return tremelo;
	}

	public void setTremelo(Tremelo2 tremelo) {
		this.tremelo = tremelo;
	}

	@Override
	public String toString() {
		return "Ornaments2 [tremelo=" + tremelo + "]";
	}
	
}
