package GUI;

import xml.to.sheet.converter.POJOClasses.Note2;

public class notePosition {
	
	private Note2 note;
	private double x;
	private double y;
	
	public notePosition() {
	}

	public notePosition(Note2 note, double x, double y) {
		this.note = note;
		this.x = x;
		this.y = y;
	}

	public Note2 getNote() {
		return note;
	}

	public void setNote(Note2 note) {
		this.note = note;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
