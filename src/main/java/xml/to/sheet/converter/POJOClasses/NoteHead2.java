package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class NoteHead2 {

    private String parentheses;
    private String text;

    public NoteHead2() {
    }

    public NoteHead2(String parentheses, String text) {
        this.parentheses = parentheses;
        this.text = text;
    }

    @XmlAttribute
    public String getParentheses() {
        return parentheses;
    }

    public void setParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @XmlValue
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



}