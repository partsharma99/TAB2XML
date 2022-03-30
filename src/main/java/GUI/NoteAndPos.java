package GUI;

import xml.to.sheet.converter.POJOClasses.Note2;

public class NoteAndPos {
	
	private double topofstaff;
	private int measureNum;
	private Note2 note;
	private double x;
	private double y;
	
	public NoteAndPos() {
	}

	public NoteAndPos(double topofstaff, int measureNum, Note2 note, double x, double y) {
		this.topofstaff = topofstaff;
		this.measureNum = measureNum;
		this.note = note;
		this.x = x;
		this.y = y;
	}

	public double getTopofstaff() {
		return topofstaff;
	}

	public void setTopofstaff(double topofstaff) {
		this.topofstaff = topofstaff;
	}

	public int getMeasureNum() {
		return measureNum;
	}

	public void setMeasureNum(int measureNum) {
		this.measureNum = measureNum;
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
