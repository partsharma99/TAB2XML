package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Direction2 {
	
	private DirectionType2 directiontype;
	private String placement;
	
	public Direction2() {
	}

	public Direction2(DirectionType2 directiontype, String placement) {
		this.directiontype = directiontype;
		this.placement = placement;
	}
	
	@XmlElement(name = "direction-type")
	public DirectionType2 getDirectiontype() {
		return directiontype;
	}

	public void setDirectiontype(DirectionType2 directiontype) {
		this.directiontype = directiontype;
	}
	
	@XmlAttribute
	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	@Override
	public String toString() {
		return "Direction2 [directiontype=" + directiontype + ", placement=" + placement + "]";
	}
	
}
