package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Notations2 {
	
	private List<Tied2> listOfTieds;
	private List<Slur2> listOfSlurs;
	private Slide2 slide;
	private Technicial2 technical;

	public Notations2() {
	}

	public Notations2(List<Tied2> listOfTieds, List<Slur2> listOfSlurs, Slide2 slide, Technicial2 technical) {
		this.listOfTieds = listOfTieds;
		this.listOfSlurs = listOfSlurs;
		this.slide = slide;
		this.technical = technical;
	}
	
	@XmlElement(name = "tied")
	public List<Tied2> getListOfTieds() {
		return listOfTieds;
	}

	public void setListOfTieds(List<Tied2> listOfTieds) {
		this.listOfTieds = listOfTieds;
	}

	@XmlElement(name = "slur")
	public List<Slur2> getListOfSlurs() {
		return listOfSlurs;
	}

	public void setListOfSlurs(List<Slur2> listOfSlurs) {
		this.listOfSlurs = listOfSlurs;
	}
	
	@XmlElement
	public Slide2 getSlide() {
		return slide;
	}

	public void setSlide(Slide2 slide) {
		this.slide = slide;
	}

	@XmlElement
	public Technicial2 getTechnical() {
		return technical;
	}

	public void setTechnical(Technicial2 technical) {
		this.technical = technical;
	}

	@Override
	public String toString() {
		return "Notations2 [listOfTieds=" + listOfTieds + ", listOfSlurs=" + listOfSlurs + ", slide=" + slide
				+ ", technical=" + technical + "]";
	}
	
}
