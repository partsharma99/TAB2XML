package xml.to.sheet.converter.POJOClasses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class StaffDetails2 {
	
	private int stafflines;
	private List<StaffTuning2> listOfStafftuning;

	public StaffDetails2() {
	}

	public StaffDetails2(int stafflines, List<StaffTuning2> listOfStafftuning) {
		this.stafflines = stafflines;
		this.listOfStafftuning = listOfStafftuning;
	}
	
	@XmlElement(name = "staff-lines")
	public int getStafflines() {
		return stafflines;
	}

	public void setStafflines(int stafflines) {
		this.stafflines = stafflines;
	}
	
	@XmlElement(name = "staff-tuning")
	public List<StaffTuning2> getListOfStafftuning() {
		return listOfStafftuning;
	}

	public void setListOfStafftuning(List<StaffTuning2> listOfStafftuning) {
		this.listOfStafftuning = listOfStafftuning;
	}

	@Override
	public String toString() {
		return "StaffDetails2 [stafflines=" + stafflines + ", listOfStafftuning=" + listOfStafftuning + "]";
	}
	
}
