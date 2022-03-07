package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Part2 {
	
	private List<Measure2> listOfMeasures;
	private String id;
	
	public Part2() {
	}

	public Part2(List<Measure2> listOfMeasures, String id) {
		this.listOfMeasures = listOfMeasures;
		this.id = id;
	}
	
	@XmlElement(name = "measure")
	public List<Measure2> getListOfMeasures() {
		return listOfMeasures;
	}

	public void setListOfMeasures(List<Measure2> listOfMeasures) {
		this.listOfMeasures = listOfMeasures;
	}
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Part2 [listOfMeasures=" + listOfMeasures + ", id=" + id + "]";
	}
	
}
