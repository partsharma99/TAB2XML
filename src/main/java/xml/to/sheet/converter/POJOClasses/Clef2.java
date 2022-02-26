package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

public class Clef2 {
	
	private String sign;
	private int line;

	public Clef2() {
	}

	public Clef2(String sign, int line) {
		this.sign = sign;
		this.line = line;
	}
	
	@XmlElement
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@XmlElement
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "Clef2 [sign=" + sign + ", line=" + line + "]";
	}
	
}
