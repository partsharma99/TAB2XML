package GUI;

import xml.to.sheet.converter.POJOClasses.Note2;

public class NoteAndPos {
	
	private double topofstaff;
	private int partnum;
	private int staffnum;
	private int measureNum;
	private Note2 note;
	private double type;
	private double x;
	private double y;
	
	public NoteAndPos() {
	}

	public NoteAndPos(double topofstaff, int partnum, int staffnum, int measureNum, Note2 note, double type, double x, double y) {
		this.topofstaff = topofstaff;
		this.partnum = partnum;
		this.staffnum = staffnum;
		this.measureNum = measureNum;
		this.note = note;
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public double getTopofstaff() {
		return topofstaff;
	}

	public void setTopofstaff(double topofstaff) {
		this.topofstaff = topofstaff;
	}
	
	public int getPartnum() {
		return partnum;
	}

	public void setPartnum(int partnum) {
		this.partnum = partnum;
	}

	public int getStaffnum() {
		return staffnum;
	}

	public void setStaffnum(int staffnum) {
		this.staffnum = staffnum;
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
	

	public double getType() {
		return type;
	}

	public void setType(double type) {
		this.type = type;
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
