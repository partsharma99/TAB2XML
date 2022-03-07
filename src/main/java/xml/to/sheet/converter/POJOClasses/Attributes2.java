package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class Attributes2 {
	
	private int divisions;
	private Key2 key;
	private Time2 time;
	private Clef2 clef;
	private StaffDetails2 staffdetails;
	
	public Attributes2() {
	}

	public Attributes2(int divisions, Key2 key, Time2 time, Clef2 clef, StaffDetails2 staffdetails) {
		super();
		this.divisions = divisions;
		this.key = key;
		this.time = time;
		this.clef = clef;
		this.staffdetails = staffdetails;
	}
	
	@XmlElement
	public int getDivisions() {
		return divisions;
	}

	public void setDivisions(int divisions) {
		this.divisions = divisions;
	}
	
	@XmlElement
	public Key2 getKey() {
		return key;
	}

	public void setKey(Key2 key) {
		this.key = key;
	}

	@XmlElement
	public Time2 getTime() {
		return time;
	}

	public void setTime(Time2 time) {
		this.time = time;
	}
	
	@XmlElement
	public Clef2 getClef() {
		return clef;
	}

	public void setClef(Clef2 clef) {
		this.clef = clef;
	}
	
	@XmlElement(name = "staff-details")
	public StaffDetails2 getStaffdetails() {
		return staffdetails;
	}

	public void setStaffdetails(StaffDetails2 staffdetails) {
		this.staffdetails = staffdetails;
	}

	@Override
	public String toString() {
		return "Attributes2 [divisions=" + divisions + ", key=" + key + ", time=" + time + ", clef=" + clef
				+ ", staffdetails=" + staffdetails + "]";
	}
	
}
