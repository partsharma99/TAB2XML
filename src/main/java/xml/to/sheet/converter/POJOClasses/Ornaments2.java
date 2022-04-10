package xml.to.sheet.converter.POJOClasses;

import javax.xml.bind.annotation.XmlElement;

public class Ornaments2 {

    private Tremelo tremelo;

    public Ornaments2() {
    }

    public Ornaments2(Tremelo tremelo) {
        this.tremelo = tremelo;
    }

    @XmlElement
    public Tremelo getTremelo() {
        return tremelo;
    }

    public void setTremelo(Tremelo tremelo) {
        this.tremelo = tremelo;
    }

    @Override
    public String toString() {
        return "Ornaments2 [tremelo=" + tremelo + "]";
    }



}