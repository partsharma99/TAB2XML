package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class DirectionType2 {
	
	private Words2 words;
	
	public DirectionType2() {
	}
	
	public DirectionType2(Words2 words) {
		this.words = words;
	}
	
	@XmlElement
	public Words2 getWords() {
		return words;
	}
	
	public void setWords(Words2 words) {
		this.words = words;
	}

	@Override
	public String toString() {
		return "DirectionType2 [words=" + words + "]";
	}
	
}
