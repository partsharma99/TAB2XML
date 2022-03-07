package xml.to.sheet.converter.POJOClasses;

//POJO Class used to support the JAXB library being used to parse
//the xml string.

public class NoteHead2 {
	
	private String parantheses;
	private String text;

	public NoteHead2() {
	}

	public NoteHead2(String parantheses, String text) {
		this.parantheses = parantheses;
		this.text = text;
	}

	public String getParantheses() {
		return parantheses;
	}

	public void setParantheses(String parantheses) {
		this.parantheses = parantheses;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "NoteHead2 [parantheses=" + parantheses + ", text=" + text + "]";
	}
	
}
